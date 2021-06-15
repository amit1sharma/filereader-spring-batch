package com.example.filereader.batch;

import com.example.filereader.entity.Files;
import com.example.filereader.repository.FilesRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FileWriter implements ItemWriter<Files> {

    @Autowired
    private FilesRepository filesRepository;
    long records = 0;

    @Override
    public void write(List<? extends Files> items) throws Exception {
        records+=items.size();
        System.out.println("saving in db : "+ records);
        filesRepository.saveAll(items);
    }
}
