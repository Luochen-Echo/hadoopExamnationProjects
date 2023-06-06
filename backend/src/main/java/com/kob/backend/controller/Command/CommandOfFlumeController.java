package com.kob.backend.controller.Command;

import com.kob.backend.service.Command.CommandOfSendToLinux;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class CommandOfFlumeController {
    @Autowired
    private CommandOfSendToLinux wcd;

    @GetMapping("/command/flume")
    public Map<String, String> Flume() {
        String cmd = "bash /home/roger/flume_script.sh\n";
        return wcd.WordCount(cmd);
    }
}
