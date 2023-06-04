package com.kob.backend.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileInfo {
    private String fileName;
    private String filePath;
    private String fileOwner;
    private String fileSize;
    private String lastModifiedTime;
    private String numReplicas;
    private String blockSize;
}
