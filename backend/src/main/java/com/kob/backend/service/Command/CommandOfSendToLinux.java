package com.kob.backend.service.Command;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

@Service
public class CommandOfSendToLinux {

    public Map<String, String> WordCount(String cmd) {
        String user = "roger"; // 用户名
        String host = "hadoop102"; // 目标主机ip
        String command = cmd; // 执行的命令
        String password = "000000"; // 你的密码

        Map<String, String> result = new HashMap<>();

        try {
            JSch jsch = new JSch();
            Session session = jsch.getSession(user, host, 22);
            session.setConfig("StrictHostKeyChecking", "no"); // 信任目标服务器
            session.setPassword(password);
            session.connect();
            Channel channel = session.openChannel("exec");
            ((ChannelExec) channel).setCommand(command);

            channel.setInputStream(null);
            BufferedReader input = new BufferedReader(new InputStreamReader(channel.getInputStream()));

            channel.connect();

            String line;
            while ((line = input.readLine()) != null) {
                System.out.println(line);
            }

            int exitStatus = channel.getExitStatus();
            channel.disconnect();
            session.disconnect();

            if (exitStatus == 0) {
                result.put("message", "执行成功");
            } else {
                result.put("message", "执行失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.put("message", "执行失败");
        }
        return result;
    }
}
