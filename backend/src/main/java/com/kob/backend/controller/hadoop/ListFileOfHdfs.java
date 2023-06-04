package com.kob.backend.controller.hadoop;

import com.kob.backend.pojo.FileInfo;
import com.kob.backend.service.hadoop.HdfsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
//实现了列出hdfs文件的功能
@RestController
public class ListFileOfHdfs {
   @Autowired
    private HdfsService hdfsService;

    @GetMapping("/hadoop/listfile/")
    public List<FileInfo> listFile() throws IOException {
       return hdfsService.fileDetail();
    }
}
