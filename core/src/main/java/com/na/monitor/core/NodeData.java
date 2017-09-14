package com.na.monitor.core;

import org.springframework.beans.factory.annotation.Value;

import java.io.Serializable;

public class NodeData implements Serializable{
    @Value("${server.display-name}")
    private String displayName;
    @Value("${spring.application.name}")
    private String applicationName;
    @Value("${server.port}")
    private int serverPort;
    @Value("${server.context-path}")
    private String contextPath;
    @Value("${server.address}")
    private String serverAddress;

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public int getServerPort() {
        return serverPort;
    }

    public void setServerPort(int serverPort) {
        this.serverPort = serverPort;
    }

    public String getContextPath() {
        return contextPath;
    }

    public void setContextPath(String contextPath) {
        if(contextPath.trim().length()>0) {
            this.contextPath = contextPath;
        }
    }

    public String getServerAddress() {
        return serverAddress;
    }

    public void setServerAddress(String serverAddress) {
        this.serverAddress = serverAddress;
    }
}
