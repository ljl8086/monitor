package com.na.monitor.client;

import com.na.monitor.core.NodeData;
import com.na.monitor.core.util.IpUtils;
import org.I0Itec.zkclient.ZkClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;
public class MonitorClientZookeeper {
    private Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ZkClient zkClient;

    @Value("${server.display-name:未知应用}")
    private String displayName;
    @Value("${spring.application.name:app}")
    private String applicationName;
    @Value("${server.port:8080}")
    private int serverPort;
    @Value("${server.context-path}")
    private String contextPath;
    private String serverAddress;


    @PostConstruct
    public void init(){
        NodeData nodeData = new NodeData();
        nodeData.setApplicationName(applicationName);
        nodeData.setDisplayName(displayName);
        nodeData.setServerPort(serverPort);
        nodeData.setContextPath(contextPath);
        if(this.serverAddress==null) {
            try {
                this.serverAddress = IpUtils.getRealIp();
            } catch (Exception e) {
                log.error(e.getMessage(),e);
            }
        }
        nodeData.setServerAddress(serverAddress);

        StringBuilder path = new StringBuilder("/app");
        if(!zkClient.exists(path.toString())){
            zkClient.createPersistent(path.toString());
        }

        path.append("/").append(nodeData.getApplicationName()).append("_");
        zkClient.createEphemeralSequential(path.toString(),nodeData);
    }

}
