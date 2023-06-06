package com.kob.backend.controller.Command;

import com.kob.backend.service.Command.CommandOfSendToLinux;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
@RestController
public class CommandOfKillFlume {
    @Autowired
    private CommandOfSendToLinux wcd;
    @GetMapping("/command/killflume")
    public Map<String, String> WordCount() {
        String cmd = "bash /home/roger/kill_flume_scrit.sh\n";
        return wcd.WordCount(cmd);
    }
}
