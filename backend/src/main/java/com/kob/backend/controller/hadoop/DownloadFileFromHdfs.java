package com.kob.backend.controller.hadoop;

import com.kob.backend.service.hadoop.HdfsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.Map;

@RestController
public class DownloadFileFromHdfs {
    @Autowired
    private HdfsService hdfsService;
    @PostMapping("/hadoop/downloadfile/")
    public Map<String, String> downloadFile(@RequestParam String HdfsFilePath, String loaclFilePath) throws IOException, URISyntaxException {
        URI uri = new URI(HdfsFilePath);
        String fileName = Paths.get(uri.getPath()).getFileName().toString();
        String FilePath = "C:\\Users\\20624\\Desktop\\" + fileName; // windows下载路径
        if (hdfsService.downloadFile(HdfsFilePath, FilePath)) {
            return Map.of("message", FilePath + "下载成功");
        } else {
            return Map.of("message", "下载失败");
        }
    }
}
