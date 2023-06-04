package com.kob.backend.service.hadoop;

import com.kob.backend.pojo.FileInfo;
import jakarta.annotation.PostConstruct;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    public List<FileInfo> fileDetail() throws IOException {
        RemoteIterator<LocatedFileStatus> list = fs.listFiles(new Path("/"), true);
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
}
