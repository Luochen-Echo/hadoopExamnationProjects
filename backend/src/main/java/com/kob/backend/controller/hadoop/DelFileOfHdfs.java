package com.kob.backend.controller.hadoop;

import com.kob.backend.service.hadoop.HdfsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;
//实现了删除hdfs文件的功能
@RestController
public class DelFileOfHdfs {
    @Autowired
    private HdfsService hdfsService;
    @DeleteMapping("/hadoop/delfile/")
    public Map<String, String> delFile(@RequestParam String filePath) throws IOException {
        if (hdfsService.deleteFile(filePath)) {
            return Map.of("message", "删除成功");
        } else {
            return Map.of("message", "删除失败");
        }
    }
}
