package com.jyxc.devops.deploysys.jenkins.view.service.impl;


import com.jyxc.devops.deploysys.BeanConnectionUtil;
import com.jyxc.devops.deploysys.common.ResultData;
import com.jyxc.devops.deploysys.common.SysConstants;
import com.jyxc.devops.deploysys.jenkins.view.model.ViewInfo;
import com.jyxc.devops.deploysys.jenkins.view.service.ViewService;
import com.offbytwo.jenkins.JenkinsServer;
import com.offbytwo.jenkins.client.JenkinsHttpClient;
import com.offbytwo.jenkins.model.View;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @Authror huiwang
 * @Description View(视图) 相关操作
 * @Date 2019/12/12 17:22
 */
@Service
public class ViewServiceImpl implements ViewService {

    // Jenkins 对象
    private static final JenkinsServer JENKINS_SERVER = (JenkinsServer) BeanConnectionUtil.getBean("jenkinsServer");
    // http 客户端对象
    private static final JenkinsHttpClient JENKINS_HTTP_CLIENT = (JenkinsHttpClient) BeanConnectionUtil.getBean("jenkinsHttpClient");


    @Override
    public Map<String, ViewInfo> getViews() {
        Map<String, ViewInfo> viewsMap = new HashMap<>();
        try {
            Map<String, View> views = JENKINS_SERVER.getViews();
            Iterator it = views.entrySet().iterator();
            while (it.hasNext()) {
                ViewInfo viewInfo = new ViewInfo();
                Map.Entry entry = (Map.Entry) it.next();
                String key = entry.getKey().toString();
                if (!StringUtils.isEmpty(key) || !StringUtils.isEmpty(views.get(key))) {
                    BeanUtils.copyProperties(views.get(key), viewInfo);
                    viewsMap.put(key, viewInfo);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return viewsMap;
    }

    @Override
    public ResultData createView(String viewName, String viewDescription) {
        ResultData resultData = null;
        try {
            //视图名称验重
            Boolean viewNameExists = isViewNameExists(viewName);
            if (viewNameExists) {
                //返回状态码+msg+result
                resultData = new ResultData(SysConstants.status.STATUS_NAME_EXISTS,
                        SysConstants.status.STATUS_NAME_EXISTS_MSG,null);
                return resultData;
            } else {
                //视图名称规范校验
                //TODO

                // 创建一个 xml 字符串，里面设置一个 view 描述信息
                String xml = "<listView _class=\"hudson.model.ListView\">\n" +
                        "<description>" + viewDescription + "</description>\n" +
                        "</listView>";
                // 创建 view
                JENKINS_SERVER.createView(viewName, xml);
                //返回状态码+msg+result
                resultData = new ResultData(SysConstants.status.STATUS_SUCCESS,
                        SysConstants.status.STATUS_SUCCESS_MSG,null);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  resultData;
    }

    @Override
    public ViewInfo getView(String viewName) {
        ViewInfo viewInfo = new ViewInfo();
        try {
            //校验视图名称
            Boolean viewNameExists = isViewNameExists(viewName);
            if (!viewNameExists) {
//                ResultData resultData = new ResultData(SysConstants.status.STATUS_NAME_NOTHING,
//                        SysConstants.status.STATUS_NAME_NOTHING_MSG,viewInfo);
//                return resultData;
            } else {
                // 获取视图基本信息
                View view = JENKINS_SERVER.getView(viewName);
                viewInfo.setName(view.getName());
                viewInfo.setUrl(view.getUrl());
                viewInfo.setDescription(view.getDescription());
//                ResultData resultData = new ResultData(SysConstants.status.STATUS_SUCCESS,
//                        SysConstants.status.STATUS_SUCCESS_MSG,viewInfo);
                return viewInfo;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getViewXMlConfig(String viewName) {
        //TODO
        String viewConfigXml = null;
        try {
            //校验视图名称
            Boolean viewNameExists = isViewNameExists(viewName);
            if (!viewNameExists) {
//                ResultData resultData = new ResultData(SysConstants.status.STATUS_NAME_NOTHING,
//                        SysConstants.status.STATUS_NAME_NOTHING_MSG,viewConfigXml);
//                return resultData;
            } else {
                viewConfigXml = JENKINS_HTTP_CLIENT.get("/view/" + viewName + "/config.xml");
//                ResultData resultData = new ResultData(SysConstants.status.STATUS_SUCCESS,
//                        SysConstants.status.STATUS_SUCCESS_MSG, viewConfigXml);
                return viewConfigXml;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void updateView(String viewName, String newViewName, String newViewDescription) {
        //TODO
        String xml = "<hudson.model.ListView>\n" +
                "<name>" + newViewName + "</name>\n" +
                "<description>" + newViewDescription + "</description>\n" +
                "<filterExecutors>false</filterExecutors>\n" +
                "<filterQueue>false</filterQueue>\n" +
                "<properties class=\"hudson.model.View$PropertyList\"/>\n" +
                "<jobNames>\n" +
                "<comparator class=\"hudson.util.CaseInsensitiveComparator\"/>\n" +
                "</jobNames>\n" +
                "<jobFilters/>\n" +
                "<columns>\n" +
                "<hudson.views.StatusColumn/>\n" +
                "<hudson.views.WeatherColumn/>\n" +
                "<hudson.views.JobColumn/>\n" +
                "<hudson.views.LastSuccessColumn/>\n" +
                "<hudson.views.LastFailureColumn/>\n" +
                "<hudson.views.LastDurationColumn/>\n" +
                "<hudson.views.BuildButtonColumn/>\n" +
                "<hudson.plugins.favorite.column.FavoriteColumn plugin=\"favorite@2.3.2\"/>\n" +
                "</columns>\n" +
                "<recurse>false</recurse>\n" +
                "</hudson.model.ListView>";
        try {
            JENKINS_SERVER.updateView(viewName, xml);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ResultData deleteView(String viewName) {
        ResultData resultData = null;
        //视图名称校验
        Boolean viewNameExists = isViewNameExists(viewName);
        if(!viewNameExists){
            resultData = new ResultData(SysConstants.status.STATUS_NAME_EXISTS,
                    SysConstants.status.STATUS_NAME_NOTHING_MSG, null);
        }else {
            try {
                JENKINS_HTTP_CLIENT.post("/view/" + viewName + "/doDelete");
            } catch (IOException e) {
                e.printStackTrace();
            }
            Boolean viewExists = isViewNameExists(viewName);
            if(!viewExists){
                resultData = new ResultData(SysConstants.status.STATUS_SUCCESS,
                        SysConstants.status.STATUS_SUCCESS_MSG, null);
            }else {
                resultData = new ResultData(SysConstants.status.STATUS_FAIL,
                        SysConstants.status.STATUS_FAIL_MSG, null);
            }
        }
        return resultData;

    }

    /**
     * @return true/false
     * @Description 判断viewName(视图名称)是否存在
     */
    private Boolean isViewNameExists(String viewName) {
        Boolean isViewNameExists = false;
        try {
            Map<String, View> views = JENKINS_SERVER.getViews();
            Iterator<Map.Entry<String, View>> iteratorViews = views.entrySet().iterator();
            while (iteratorViews.hasNext()) {
                Map.Entry<String, View> viewsEntry = iteratorViews.next();
                if (viewName.equals(viewsEntry.getValue().getName())) {
                    isViewNameExists = true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return isViewNameExists;
    }


}
