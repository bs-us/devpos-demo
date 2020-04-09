package com.jyxc.devops.deploysys.jenkins.view.controller;

import com.jyxc.devops.deploysys.common.ResultData;
import com.jyxc.devops.deploysys.jenkins.view.model.ViewInfo;
import com.jyxc.devops.deploysys.jenkins.view.service.ViewService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @Authror huiwang
 * @Description View(视图) 相关操作
 * (例如对视图的增、删、改、查等操作)
 * @Date 2019/12/12 19:02
 */
@RestController
@RequestMapping(value = "/view")
public class ViewController {

    @Resource
    private ViewService viewService;


    /**
     * @Description 获取所有的视图
     * @return
     */
    @RequestMapping("/allViews")
    public Map<String, ViewInfo> getViews(){
        Map<String, ViewInfo> views = viewService.getViews();
        return views;
    }

    /**
     * @Description 创建视图
     * @return
     */
    @RequestMapping(value = "/create")
    public ResultData createView(String viewName, String viewDescription){
        ResultData resultData = viewService.createView(viewName, viewDescription);
        return resultData;
    }

    /**
     * @Description 获取视图基本信息
     * @retur
     */
    @RequestMapping("/getInfo")
    public ViewInfo getViewInfo(String viewName){
        ViewInfo viewInfo = viewService.getView(viewName);
        return viewInfo;
    }

    /**
     * @Description 获取视图配置 XML 信息
     * @return
     */
    @RequestMapping("/getXMLConfig")
    public String getViewXMlConfig(String viewName){
        String viewXMlConfig = viewService.getViewXMlConfig(viewName);
        return viewXMlConfig;
    }

    /**
     * @Description 更新视图信息
     * @return
     * TODO
     */
    @RequestMapping("/update")
    public void updateView(String viewName,String newViewName,String newViewDescription){
        viewService.updateView(viewName, newViewName, newViewDescription);
    }

    /**
     * @Description 删除视图
     * @return
     */
    @RequestMapping("/delete")
    public ResultData deleteView(String viewName){
        ResultData resultData = viewService.deleteView(viewName);
        return resultData;
    }


}
