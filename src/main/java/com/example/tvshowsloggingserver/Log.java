package com.example.tvshowsloggingserver;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Map;

public class Log {
    private String msg;
    private String level;
    private String stacktrace;

    public Log(String log) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        try {
            Map<String, Object> logsObject = objectMapper.readValue(log , Map.class);
            List<Map<String, Object>> logObjectList = (List<Map <String, Object>>) logsObject.get("logs");
            Map<String, Object> logObject = logObjectList.get(0);
            this.msg = (String) logObject.get("msg");
            this.level = (String) logObject.get("level");
            this.stacktrace = (String) logObject.get("stacktrace");
        } catch (JsonProcessingException e) {
            this.msg = "Parsing error msg failed at logging-server";
        }
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getStacktrace() {
        return stacktrace;
    }

    public void setStacktrace(String stacktrace) {
        this.stacktrace = stacktrace;
    }

    public String toString() {
        return "msg: "+this.msg+"\n" +
                "level: "+this.level+"\n" +
                "stacktrace: "+this.stacktrace;
    }
}
