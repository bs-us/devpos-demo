package com.jyxc.devops.deploysys.jenkins.server.service;


import com.jyxc.devops.deploysys.jenkins.server.model.PluginInfo;
import com.offbytwo.jenkins.model.Computer;

import java.util.List;
import java.util.Map;

/**
 * @Authror huiwang
 * @Description 获取 Jenkins服务端相关信息
 * @Date 2019/12/13 10:56
 */
public interface JenkinsService {

    /**
     * @Description 获取jenkins主机信息
     * @return java.util.Map<java.lang.String,java.lang.String>
     */
    public Map<String, Computer> getComputerInfo();

    /**
     * @Description 重启 Jenkins
     * @return java.lang.String
     */
    public void restart();

    /**
     * @Description 安全重启 Jenkins
     * @return java.lang.String
     */
    public void safeRestart();

    /**
     * @Description 安全结束 Jenkins
     * @return java.lang.String
     */
    public void safeExit();

    /**
     * @Description 关闭 Jenkins 连接
     * @return void
     */
    public void close();

    /**
     * @Description 根据 Label 查找代理节点信息
     * @return com.offbytwo.jenkins.model.LabelWithDetails
     */
    //public ResultData getLabelNodeInfo(String labelName);

    /**
     * @Description 判断 Jenkins 是否运行
     * @return
     */
    public boolean isRunning();

    /**
     * @Description 获取 Jenkins 插件信息
     * @return
     */
    public List<PluginInfo> getPluginInfo();
}
