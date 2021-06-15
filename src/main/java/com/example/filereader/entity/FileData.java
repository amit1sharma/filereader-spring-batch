package com.example.filereader.entity;

import javax.persistence.*;
import java.sql.Blob;

@Table(name = "FILEDATA")
@Entity
public class FileData {

    @Id
    @GeneratedValue
    private Long fileId;

    @Column(name = "file_data")
    @Lob
    private Blob fileData;

    /*@OneToOne(mappedBy = "fileData", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    private Files files;*/

    public Blob getFileData() {
        return fileData;
    }

    public void setFileData(Blob fileData) {
        this.fileData = fileData;
    }
}
