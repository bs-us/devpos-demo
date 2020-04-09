package com.jyxc.devops.deploysys.jenkins.job.model;


import lombok.Data;

/**
 * @Authror huiwang
 * @Description 最后编译信息
 * @Date 2019/12/13 10:15
 */
@Data
public class JobLastBuild {

    //最后编译信息
    private BuildInfo lastBuildInfo;
    //最后成功的编译信息
    private BuildInfo lastSuccessfulBuildInfo;
    //最后事变的编译信息
    private BuildInfo lastFailedBuildInfo;
    //最后完成的编译信息
    private BuildInfo lastCompletedBuildInfo;
    //最后稳定的编译信息
    private BuildInfo lastStableBuildInfo;
    //最后不稳定的编译信息
    private BuildInfo lastUnstableBuildInfo;
    //最后未成功的编译信息
    private BuildInfo lastUnsuccessfulBuildInfo;

}
