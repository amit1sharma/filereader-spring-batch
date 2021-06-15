package com.example.filereader.service;

import com.example.filereader.entity.FileData;
import com.example.filereader.entity.Files;
import com.example.filereader.exception.ValidationException;
import com.example.filereader.repository.FileRepository;
import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Blob;
import java.util.List;
import java.util.Optional;

@Service
public class FilesServiceImpl implements FilesService {

    @Value("${max.record.count}")
    private int maxRecordsCount;

    @Value("${upload.folder.path}")
    private String targetPath;

    @Autowired
    private FileRepository fileRepository;

    @Override
    public Files saveFile(MultipartFile file) throws IOException {
        if(file.isEmpty()){
            throw new ValidationException("602");
        } else if(file.getSize()/1000000000 >= 1){
            throw new ValidationException("603");
        }
//        byte[] bytes = file.getBytes();
        Path path = Paths.get(targetPath+file.getOriginalFilename());
        file.transferTo(path);


//        java.nio.file.Files.write(path, bytes);
        System.out.println("filesaved");
        System.out.println("filesize in gb : "+file.getSize()/1000000000);
        String fileName = file.getOriginalFilename();
        Blob data = BlobProxy.generateProxy(file.getInputStream(), file.getSize());
        FileData fd = new FileData();
        fd.setFileData(data);
        Files fileDb = new Files(fileName, file.getContentType(), file.getSize(), fd);
        fileRepository.save(fileDb);
        file= null;
        return fileDb;
    }

    @Override
    public Files getFile(Long id){
        Optional<Files> of = fileRepository.findById(id);
        if(!of.isPresent()){
            throw new ValidationException("600");
        }
        return of.get();
    }

    @Override
    public List<Files> getFiles(int page, int recordCount){
        if(recordCount>maxRecordsCount){
            throw new ValidationException("601");
        }
        Pageable p = PageRequest.of(page,recordCount);
        return fileRepository.findAll(p).getContent();
    }
}