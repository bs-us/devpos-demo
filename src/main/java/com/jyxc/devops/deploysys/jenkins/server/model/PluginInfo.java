package com.jyxc.devops.deploysys.jenkins.server.model;

import lombok.Data;

import java.util.List;

/**
 * @Authror huiwang
 * @Description TODO
 * @Date 2019/12/20 16:43
 */
@Data
public class PluginInfo {

    /**
     * @Description 插件 wiki URL 地址
     */
    private String wikiURL;
    /**
     * @Description 版本号
     */
    private String version;
    /**
     * @Description 简称
     */
    private String shortName;
    /**
     * @Description  完整名称
     */
    private String longName;
    /**
     * @Description 是否支持动态加载
     */
    private String isDynamicLoad;

    /**
     * @Description 插件依赖的组件
     */
    private List<PluginDependencyInfo> dependencies;

}
