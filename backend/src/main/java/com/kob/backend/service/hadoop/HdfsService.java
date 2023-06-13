package com.kob.backend.service.hadoop;

import com.kob.backend.pojo.FileInfo;
import jakarta.annotation.PostConstruct;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Service
public class HdfsService {

    private FileSystem fs;

    @Value("${hadoop.uri}")
    private String hadoopUri;

    @Value("${hadoop.user}")
    private String hadoopUser;

    @PostConstruct
    public void init() throws URISyntaxException, IOException, InterruptedException {
        URI uri = new URI(hadoopUri);
        Configuration configuration = new Configuration();
        fs = FileSystem.get(uri, configuration, hadoopUser);
    }


    public void checkAuthentication() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new SecurityException("用户未认证");
        }
    }

    public List<FileInfo> fileDetail(String HdfsPath) throws IOException {
        RemoteIterator<LocatedFileStatus> list = fs.listFiles(new Path(HdfsPath), true);
        List<FileInfo> fileList = new ArrayList<>();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        while (list.hasNext()) {
            LocatedFileStatus fileStatus = list.next();
            FileInfo fileInfo = new FileInfo();
            fileInfo.setFileName(fileStatus.getPath().getName());
            fileInfo.setFilePath(fileStatus.getPath().toString());
            fileInfo.setFileOwner(fileStatus.getOwner());
            long fileSizeInBytes = fileStatus.getLen();
            double fileSizeInMB = fileSizeInBytes / (1024.0 * 1024.0);
            fileInfo.setFileSize(String.format("%.2f MB", fileSizeInMB));
            long timestamp = fileStatus.getModificationTime();
            Date date = new Date(timestamp);
            fileInfo.setLastModifiedTime(formatter.format(date));
            fileInfo.setNumReplicas(String.valueOf(fileStatus.getReplication()));
            long blockSizeInBytes = fileStatus.getBlockSize();
            double blockSizeInMB = blockSizeInBytes / (1024.0 * 1024.0);
            fileInfo.setBlockSize(String.format("%.2f MB", blockSizeInMB));
            fileList.add(fileInfo);
        }
        return fileList;
    }

    public boolean deleteFile(String filePath) throws IOException {
        Path file = new Path(filePath);
        // true for recursive deletion, in case it's a directory
        return fs.delete(file, true);
    }

    public boolean deleteFolder(String folderPath) throws IOException {
        // Check if the path exists
        boolean result = false;
        if (fs.exists(new Path(folderPath))) {
            // Delete the folder and all contents
            fs.delete(new Path(folderPath), true);
            result = true;
        }
        return result;
    }

    public boolean putFile(InputStream in, String hdfsFilePath) throws IOException {
        FSDataOutputStream out = fs.create(new Path(hdfsFilePath));
        IOUtils.copyBytes(in, out, 4096, true);
        return true;
    }


    public boolean downloadFile(String hdfsFilePath, String localFilePath) throws IOException {
        Path hdfsFile = new Path(hdfsFilePath);
        Path localFile = new Path(localFilePath);
        fs.copyToLocalFile(hdfsFile, localFile);
        return true;
    }

    public boolean downloadFileToBrowser(String hdfsFilePath, HttpServletResponse response) throws IOException {
        checkAuthentication();  // 在开始写入文件流之前，检查用户是否已经通过了身份验证

        Path hdfsFile = new Path(hdfsFilePath);

        response.setHeader("Content-Disposition", "attachment; filename=\"" + hdfsFile.getName() + "\"");
        response.setContentType("application/octet-stream");

        try (BufferedInputStream inputStream = new BufferedInputStream(fs.open(hdfsFile));
             BufferedOutputStream outputStream = new BufferedOutputStream(response.getOutputStream())) {

            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            outputStream.flush();
        }
        return true;
    }


}
