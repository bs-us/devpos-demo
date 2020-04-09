package com.jyxc.devops.deploysys.jenkins.job.model;

import lombok.Data;

/**
 * @Authror huiwang
 * @Description JOB构建实时日志
 * @Date 2019/12/13 10:53
 */
@Data
public class ConsoleLogInfo {

    /**
     * @Description 当前日志大小
     */
    private String consoleLog;

    /**
     * @Description 是否已经构建完成，还有更多日志信息
     */
    private Boolean hasMoreData;

    /**
     * @Description 获取当前截取的日志信息
     */
    private Integer currentBufferSize;
}
