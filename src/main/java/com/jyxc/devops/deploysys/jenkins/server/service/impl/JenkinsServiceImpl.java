package com.jyxc.devops.deploysys.jenkins.server.service.impl;

import com.jyxc.devops.deploysys.BeanConnectionUtil;
import com.jyxc.devops.deploysys.jenkins.server.model.PluginDependencyInfo;
import com.jyxc.devops.deploysys.jenkins.server.model.PluginInfo;
import com.jyxc.devops.deploysys.jenkins.server.service.JenkinsService;
import com.offbytwo.jenkins.JenkinsServer;
import com.offbytwo.jenkins.client.JenkinsHttpClient;
import com.offbytwo.jenkins.model.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Authror huiwang
 * @Description 获取 Jenkins服务端相关信息
 * @Date 2019/12/14 14:38
 */
@Service
public class JenkinsServiceImpl implements JenkinsService {

    // Jenkins 对象
    private static final JenkinsServer JENKINS_SERVER = (JenkinsServer) BeanConnectionUtil.getBean("jenkinsServer");
    // http 客户端对象
    private static final JenkinsHttpClient JENKINS_HTTP_CLIENT = (JenkinsHttpClient) BeanConnectionUtil.getBean("jenkinsHttpClient");


    @Override
    public Map<String, Computer> getComputerInfo() {
        Map<String, Computer> computers = new HashMap<>();
        try {
            computers = JENKINS_SERVER.getComputers();
            for (Computer computer : computers.values()) {
                // 获取当前节点-节点名称
                System.out.println(computer.details().getDisplayName());
                // 获取当前节点-执行者数量
                System.out.println(computer.details().getNumExecutors());
                // 获取当前节点-执行者详细信息
                List<Executor> executorList = computer.details().getExecutors();
                // 查看当前节点-是否脱机
                System.out.println(computer.details().getOffline());
                // 获得节点的全部统计信息
                LoadStatistics loadStatistics = computer.details().getLoadStatistics();
                // 获取节点的-监控数据
                Map<String, Map> monitorData = computer.details().getMonitorData();
                //......
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void restart() {
        try {
            JENKINS_SERVER.restart(true);
//            boolean isRunning = jenkinsServer.isRunning();
//            if(isRunning) {
//                ResultData resultData = new ResultData(SysConstants.status.STATUS_SUCCESS,
//                        SysConstants.status.STATUS_SUCCESS_MSG, null);
//                return resultData;
//            }else {
//                ResultData resultData = new ResultData(SysConstants.status.STATUS_FAIL,
//                        SysConstants.status.STATUS_FAIL_MSG, null);
//                return resultData;
//            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void safeRestart() {
        try {
            JENKINS_SERVER.safeRestart(true);
//            boolean isRunning = jenkinsServer.isRunning();
//            if(isRunning) {
//                ResultData resultData = new ResultData(SysConstants.status.STATUS_SUCCESS,
//                        SysConstants.status.STATUS_SUCCESS_MSG, null);
//                return resultData;
//            }else {
//                ResultData resultData = new ResultData(SysConstants.status.STATUS_FAIL,
//                        SysConstants.status.STATUS_FAIL_MSG, null);
//                return resultData;
//            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void safeExit() {
        try {
            JENKINS_SERVER.safeExit(true);
//            boolean isRunning = jenkinsServer.isRunning();
//            if(isRunning) {
//                ResultData resultData = new ResultData(SysConstants.status.STATUS_FAIL,
//                        SysConstants.status.STATUS_FAIL_MSG, null);
//                return resultData;
//            }else {
//                ResultData resultData = new ResultData(SysConstants.status.STATUS_CLOSE,
//                        SysConstants.status.STATUS_CLOSE_MSG, null);
//                return resultData;
//            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() {
        JENKINS_SERVER.close();
//        boolean isRunning = jenkinsServer.isRunning();
//        if(isRunning) {
//            ResultData resultData = new ResultData(SysConstants.status.STATUS_FAIL,
//                    SysConstants.status.STATUS_FAIL_MSG, null);
//            return resultData;
//        }else {
//            ResultData resultData = new ResultData(SysConstants.status.STATUS_CLOSE,
//                    SysConstants.status.STATUS_CLOSE_MSG, null);
//            return resultData;
//        }
    }

    @Override
    public boolean isRunning() {
        boolean isRunning = JENKINS_SERVER.isRunning();
//        if(isRunning) {
//            ResultData resultData = new ResultData(SysConstants.status.STATUS_RUNNING,
//                    SysConstants.status.STATUS_RUNNING_MSG, null);
//            return resultData;
//        }else {
//            ResultData resultData = new ResultData(SysConstants.status.STATUS_CLOSE,
//                    SysConstants.status.STATUS_CLOSE_MSG, null);
//            return resultData;
//        }
        return isRunning;
    }

    @Override
    public List<PluginInfo> getPluginInfo() {
        try {
            PluginManager pluginManager =JENKINS_SERVER.getPluginManager();
            // 获取插件列表
            List<Plugin> plugins = pluginManager.getPlugins();
            List<PluginInfo> pluginInfos = new ArrayList<PluginInfo>();
            List<PluginDependencyInfo> pluginDependencyInfos = new ArrayList<>();
            for (Plugin plugin:plugins){
                PluginInfo pluginInfo = new PluginInfo();
                // 插件 wiki URL 地址
                pluginInfo.setWikiURL(plugin.getUrl());
                // 版本号
                pluginInfo.setVersion(plugin.getVersion());
                // 简称
                pluginInfo.setShortName(plugin.getShortName());
                // 完整名称
                pluginInfo.setLongName(plugin.getLongName());
                // 是否支持动态加载
                pluginInfo.setIsDynamicLoad(plugin.getSupportsDynamicLoad());
                // 插件依赖的组件
                BeanUtils.copyProperties(plugin.getDependencies(),pluginDependencyInfos);
                pluginInfo.setDependencies(pluginDependencyInfos);
                pluginInfos.add(pluginInfo);
            }
            return pluginInfos;
//            ResultData resultData = new ResultData(SysConstants.status.STATUS_SUCCESS,
//                    SysConstants.status.STATUS_SUCCESS_MSG, pluginInfos);
//            return resultData;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
