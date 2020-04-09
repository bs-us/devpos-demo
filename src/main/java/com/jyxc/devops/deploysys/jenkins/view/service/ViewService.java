package com.jyxc.devops.deploysys.jenkins.view.service;


import com.jyxc.devops.deploysys.common.ResultData;
import com.jyxc.devops.deploysys.jenkins.view.model.ViewInfo;

import java.util.Map;

/**
 * @Authror huiwang
 * @Description View(视图) 相关操作
 * @Date 2019/12/12 17:14
 */
public interface ViewService {


    /**
     * @Description 获取所有的视图
     * @return
     */
    public Map<String, ViewInfo> getViews();

    /**
     * @Description 创建视图
     * @return
     */
    public ResultData createView(String viewName, String viewDescription);

    /**
     * @Description 获取视图基本信息
     * @return
     */
    public ViewInfo getView(String viewName);

    /**
     * @Description 获取视图配置 XML 信息
     * @return
     */
    public String getViewXMlConfig(String viewName);

    /**
     * @Description 更新视图信息
     * @return
     */
    public void updateView(String viewName, String newViewName, String newViewDescription);

    /**
     * @Description 删除视图
     * @return
     */
    public ResultData deleteView(String viewName);

}
