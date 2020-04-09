package com.jyxc.devops.deploysys.jenkins.job.model;

import lombok.Data;

/**
 * @Authror huiwang
 * @Description 构建信息
 * @Date 2019/12/13 10:20
 */
@Data
public class BuildInfo {

    private Integer number;
    private Integer queueId;
    private String url;

    //private String testReport;
    //private String testResult;
}
