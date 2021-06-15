package com.example.filereader.entity;

import org.springframework.web.multipart.MultipartFile;

import javax.activation.FileDataSource;
import javax.persistence.*;
import java.sql.Blob;
import java.sql.Clob;

@Entity
@Table(name = "FILES")
public class Files {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "file_id")
    private Long id;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "file_Type")
    private String fileType;

    @Column(name = "file_size")
    private Long fileSize;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private FileData fileData;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public Files(String fileName, String fileType, Long fileSize, FileData fileData) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.fileSize = fileSize;
        this.fileData = fileData;
    }

    public Files() {
    }
}
