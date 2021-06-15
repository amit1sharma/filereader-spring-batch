package com.example.filereader.service;

import com.example.filereader.entity.Files;
import com.example.filereader.exception.ValidationException;
import com.example.filereader.repository.FilesRepository;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class FilesServiceImpl implements FilesService {

    @Value("${max.record.count}")
    private int maxRecordsCount;

    @Value("${upload.folder.path}")
    private String targetPath;

    @Autowired
    private FilesRepository fileRepository;

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job csvInsertjob;



    @Override
    public void saveFile(MultipartFile file) throws IOException, JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        if(file.isEmpty()){
            throw new ValidationException("602");
        } /*else if(file.getSize()/1000000000 >= 1){
            throw new ValidationException("603");
        }*/
        Path path = Paths.get(targetPath+file.getOriginalFilename());
        file.transferTo(path);
        System.out.println("filesaved in filesystem");

        Map<String, JobParameter> maps = new HashMap<>();
        maps.put("time", new JobParameter(System.currentTimeMillis()));
        JobParameters parameters = new JobParameters(maps);
        JobExecution jobExecution = jobLauncher.run(csvInsertjob, parameters);

        System.out.println("JobExecution: " + jobExecution.getStatus());

        System.out.println("Batch is Running...");
        while (jobExecution.isRunning()) {
            System.out.println("...");
        }
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