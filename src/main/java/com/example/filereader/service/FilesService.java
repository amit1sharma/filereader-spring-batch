package com.example.filereader.service;

import com.example.filereader.entity.Files;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FilesService {
    Files saveFile(MultipartFile file) throws IOException;

    Files getFile(Long id);

    List<Files> getFiles(int page, int recordCount);
}
