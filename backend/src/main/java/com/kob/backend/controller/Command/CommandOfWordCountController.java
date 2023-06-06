package com.kob.backend.controller.Command;

import com.kob.backend.service.Command.CommandOfSendToLinux;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class CommandOfWordCountController {
    @Autowired
    private CommandOfSendToLinux wcd;
    @GetMapping("/command/wordcount")
    public Map<String, String> WordCount() {
        String cmd = "bash /home/roger/wordcount_script.sh\n";
        return wcd.WordCount(cmd);
    }
}
