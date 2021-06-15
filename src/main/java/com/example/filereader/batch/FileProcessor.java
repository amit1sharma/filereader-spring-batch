package com.example.filereader.batch;

import com.example.filereader.dto.FileDto;
import com.example.filereader.entity.Files;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class FileProcessor implements ItemProcessor<FileDto, Files> {

    @Override
    public Files process(FileDto item) throws Exception {
//        System.out.println("reading item : "+ item.toString());
        Files f = new Files(item.getFileName(),item.getFileType(), item.getFileSize());
        return f;
    }
}