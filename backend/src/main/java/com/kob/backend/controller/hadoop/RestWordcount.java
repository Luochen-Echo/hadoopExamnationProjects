package com.kob.backend.controller.hadoop;

import com.kob.backend.service.hadoop.HdfsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

@RestController
public class RestWordcount {
    @Autowired
    private HdfsService hdfsService;
    @GetMapping("/hadoop/resetwordcount")
    public Map<String,String> ResetWordcount() {
        try {
            if (hdfsService.deleteFile("/output/wordcount")) {
                return Map.of("message", "重置成功");
            } else {
                return Map.of("message", "删除失败");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return Map.of("message", "删除失败，发生异常");
        }
    }
}
