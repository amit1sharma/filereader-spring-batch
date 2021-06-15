package com.example.filereader.controller;

import com.example.filereader.dto.ResponseStatus;
import com.example.filereader.entity.Files;
import com.example.filereader.exception.ValidationException;
import com.example.filereader.service.FilesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.print.attribute.standard.Media;
import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping(value = "/upload")
public class FileReaderController {

    @Autowired
    private FilesService filesService;

    @PostMapping(value = "/file",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseStatus> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            filesService.saveFile(file);
            ResponseStatus rs = new ResponseStatus("100");
            System.out.println(rs.toString());
            return new ResponseEntity<>(rs, HttpStatus.ACCEPTED);
        } catch(ValidationException e){
            throw e;
        } catch(Throwable e){
            e.printStackTrace();
            throw new ValidationException("700", e.getMessage());
        }
    }

    @GetMapping(value = "/file", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Files>> getFiles(@RequestParam int page, @RequestParam int recordCount){
        List<Files> s = filesService.getFiles(page, recordCount);
        return new ResponseEntity<>(s, HttpStatus.OK);
    }

    @GetMapping(value = "/file/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Files> getFile(@PathVariable(value = "id") Long id){
        Files f = filesService.getFile(id);
        return new ResponseEntity<>(f, HttpStatus.OK);
    }

    /*@PostMapping(value = "/content",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseStatus> uploadFileContent(@RequestParam("file") MultipartFile file) {
        try {
            filesService.saveFile(file);
            ResponseStatus rs = new ResponseStatus("100");
            System.out.println(rs.toString());
            return new ResponseEntity<>(rs, HttpStatus.ACCEPTED);
        } catch(Throwable e){
            throw new ValidationException("700", e.getMessage());
        }
    }*/

//    public
}
