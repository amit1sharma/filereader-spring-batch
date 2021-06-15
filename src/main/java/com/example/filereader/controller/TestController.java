package com.example.filereader.controller;

import com.example.filereader.dto.ResponseStatus;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@RestController
public class TestController {

    @GetMapping("/uploadfile")
    public void sendRequest() throws IOException {

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", getTestFile());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/upload/file";
        ResponseEntity<ResponseStatus> response = restTemplate.postForEntity(url ,requestEntity, ResponseStatus.class);
    }

    public static Resource getTestFile() throws IOException {
        StringBuffer sb = new StringBuffer("");
        for(int i =0;i < 10000000;i++){
            sb.append("{\"name\":\"amit"+i+"\"}");
        }

        Path testFile = Files.createTempFile("test-file", ".txt");
        System.out.println("Creating and Uploading Test File: " + testFile);
        Files.write(testFile, sb.toString().getBytes());
        return new FileSystemResource(testFile.toFile());
    }

}
