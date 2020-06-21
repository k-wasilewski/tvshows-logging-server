package com.example.tvshowsloggingserver;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

@RestController
public class LoggingController {
    @PostMapping("/log")
    @CrossOrigin(origins = "http://localhost:3000")
    public void log(@RequestBody String log) {
        Log logObject = new Log(log);

        String systemuser = System.getProperty("user.name");
        String LOGS_PATH = "/home/"+systemuser+"/tvshows-logs";

        File uploadFolder = new File(LOGS_PATH);
        if (!uploadFolder.exists()) {
            uploadFolder.mkdirs();
        }

        try {
            PrintWriter out = new PrintWriter(LOGS_PATH+File.separator+
                    "tvshows-log-"+new java.util.Date()+".txt");
            out.println(logObject.toString());
            out.close();
        } catch (FileNotFoundException e) {}
    }
}
