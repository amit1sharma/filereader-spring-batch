package com.example.filereader;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class FilereaderApplicationTests {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Value("${upload.folder.path}")
    private String path;

    @Test
    void contextLoads() throws Exception {
        byte[] buffer = "fileName,fileType,fileSize\n".getBytes();
        int number_of_lines = 400000;

        FileChannel rwChannel = new RandomAccessFile(new File(path+"test.txt"), "rw").getChannel();
        ByteBuffer wrBuf = rwChannel.map(FileChannel.MapMode.READ_WRITE, 0, buffer.length * number_of_lines);
        for (int i = 0; i < number_of_lines; i++)
        {
            buffer = (i+",fileType,fileSize\n").getBytes();
            wrBuf.put(buffer);
        }
        rwChannel.close();

        InputStream is = new FileInputStream(new File(path+"test.txt"));
        MockMultipartFile file = new MockMultipartFile( "file", "csv.txt",MediaType.APPLICATION_JSON_VALUE, is);
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        mockMvc.perform(multipart("/upload/file").file(file))
                .andExpect(status().isAccepted());
    }

}
