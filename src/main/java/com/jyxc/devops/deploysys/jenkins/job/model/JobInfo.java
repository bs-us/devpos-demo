package com.jyxc.devops.deploysys.jenkins.job.model;

import lombok.Data;

/**
 * @Authror huiwang
 * @Description job信息
 * @Date 2019/12/13 9:51
 */
@Data
public class JobInfo {

    //Job 名称
    private String name;
    //Job URL
    private String url;
    //Job 下一个 build 编号
    private Integer nextBuildNumber;
    //Job 显示的名称
    private String displayName;
    //Job 描述信息
    private String description;

}
