package com.kob.backend.controller.hadoop;

import com.kob.backend.service.hadoop.HdfsService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

@RestController
public class DownloadFileToBrowser {
    @Autowired
    private HdfsService hdfsService;

    @PostMapping("/hadoop/download/")
    public Map<String, String> downloadFile(@RequestParam String hdfsFilePath, HttpServletResponse response) throws IOException {
        boolean downloadSuccessful = hdfsService.downloadFileToBrowser(hdfsFilePath,  response);

        if (downloadSuccessful) {
            return Map.of("message", "下载成功");
        } else {
            return Map.of("message", "下载失败");
        }
    }
}
