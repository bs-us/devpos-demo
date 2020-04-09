package com.jyxc.devops.deploysys.jenkins.job.model;

import lombok.Data;

/**
 * @Authror huiwang
 * @Description TODO
 * @Date 2020/3/30 10:24
 */
@Data
public class BuildWithDetailsInfo {

    /**
     * @Description Text格式日志
     */
    private String consoleOutputText;

    /**
     * @Description html格式日志
     */
    private String consoleOutputHtml;

    /**
     * @Description 正在构建的信息
     */
    private ConsoleLogInfo consoleLogInfo;
}
