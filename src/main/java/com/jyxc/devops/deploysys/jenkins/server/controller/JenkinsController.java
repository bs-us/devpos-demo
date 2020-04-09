package com.jyxc.devops.deploysys.jenkins.server.controller;

import com.jyxc.devops.deploysys.jenkins.server.model.PluginInfo;
import com.jyxc.devops.deploysys.jenkins.server.service.JenkinsService;
import com.offbytwo.jenkins.model.Computer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @Authror huiwang
 * @Description 获取 Jenkins 相关信息
 * (例如获取插件信息、获取Label信息、关闭Jenkins等)
 * @Date 2019/12/14 15:20
 */
@RestController
@RequestMapping("/server")
public class JenkinsController {

    @Resource
    private JenkinsService jenkinsService;

    /**
     * @Description 获取jenkins主机信息
     * @return java.util.Map<java.lang.String,java.lang.String>
     */
    @RequestMapping("/computerInfo")
    public Map<String, Computer> getComputerInfo(){
        Map<String, Computer> computerInfo = jenkinsService.getComputerInfo();
        return computerInfo;
    }

    /**
     * @Description 重启 Jenkins
     * @return java.lang.String
     */
    @RequestMapping("/restart")
    public void restart(){
        jenkinsService.restart();
    }

    /**
     * @Description 安全重启 Jenkins
     * @return java.lang.String
     */
    @RequestMapping("/safeRestart")
    public void safeRestart(){
        jenkinsService.safeRestart();
    }

    /**
     * @Description 安全结束 Jenkins
     * @return java.lang.String
     */
    @RequestMapping("/safeExit")
    public void safeExit(){
        jenkinsService.safeExit();
    }

    /**
     * @Description 关闭 Jenkins 连接
     * @return void
     */
    @RequestMapping("/close")
    public void close(){
        jenkinsService.close();
    }

    /**
     * @Description 根据 Label 查找代理节点信息
     * @return com.offbytwo.jenkins.model.LabelWithDetails
     * TODO
     */
//    @RequestMapping("/labelNodeInfo")
//    public ResultData getLabelNodeInfo(String labelName){
//        ResultData resultData = jenkinsService.getLabelNodeInfo(labelName);
//        return resultData;
//    }

    /**
     * @Description 判断 Jenkins 是否运行
     * @return
     */
    @RequestMapping("/isRuning")
    public boolean isRunning(){
        boolean running = jenkinsService.isRunning();
        return running;
    }

    /**
     * @Description 获取 Jenkins 插件信息
     * @return
     */
    @RequestMapping("/pluginInfo")
    public List<PluginInfo> getPluginInfo(){
        List<PluginInfo> pluginInfo = jenkinsService.getPluginInfo();
        return pluginInfo;
    }
}
