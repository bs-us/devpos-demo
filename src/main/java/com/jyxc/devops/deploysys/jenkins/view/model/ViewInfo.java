package com.jyxc.devops.deploysys.jenkins.view.model;


import lombok.Data;

/**
 * @Authror huiwang
 * @Description TODO
 * @Date 2019/12/12 17:41
 */
@Data
public class ViewInfo {


    /**
     * @Description 视图名称
     */
    private String name;
    /**
     * @Description 对应地址
     */
    private String url;
    /**
     * @Description 视图描述
     */
    private String Description;


}
