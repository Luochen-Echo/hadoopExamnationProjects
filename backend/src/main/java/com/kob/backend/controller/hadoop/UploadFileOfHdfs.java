package com.kob.backend.controller.hadoop;

import com.kob.backend.pojo.FileInfo;
import com.kob.backend.service.hadoop.HdfsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

@RestController
public class UploadFileOfHdfs {
    @Autowired
    private HdfsService hdfsService;
    @PostMapping("/hadoop/uploadfile/")
    public Map<String, String> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename(); // 获取文件名
        String hdfsPath = "/" + fileName; // 构造HDFS路径
        if (hdfsService.putFile(file.getInputStream(), hdfsPath)) {
            return Map.of("message", hdfsPath + "上传成功");
        } else {
            return Map.of("message", "上传失败");
        }
    }


}
