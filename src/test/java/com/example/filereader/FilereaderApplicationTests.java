package com.example.filereader;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class FilereaderApplicationTests {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Test
    void contextLoads() throws Exception {
        StringBuffer sb = new StringBuffer("");
        /*for(int i =0;i < 100000000;i++){
            sb.append("{\"name\":\"amit"+i+"\"}");
        }*/
        MockMultipartFile file = new MockMultipartFile( "file", "hello.txt",
                MediaType.APPLICATION_JSON_VALUE, sb.toString().getBytes()
        );
        System.out.println("filesize in gb : "+file.getSize()/1000000000);
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        mockMvc.perform(multipart("/upload/file").file(file))
                .andExpect(status().isAccepted());

    }

}
