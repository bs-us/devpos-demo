package com.jyxc.devops.deploysys.jenkins.job.service;

import com.jyxc.devops.deploysys.common.ResultData;
import com.jyxc.devops.deploysys.jenkins.job.model.BuildInfo;
import com.jyxc.devops.deploysys.jenkins.job.model.ConsoleLogInfo;
import com.jyxc.devops.deploysys.jenkins.job.model.JobWithDetailsInfo;
import com.offbytwo.jenkins.model.BuildWithDetails;

import java.util.List;


/**
 * @Authror huiwang
 * @Description Job Build(任务构建) 相关操作
 * @Date 2019/12/13 10:12
 */
public interface JobBuildService {


    /**
     * @Description 获取 Job 最后的 Build
     * @return com.jyxc.devops.jenkins.job.model.JobLastBuild
     */
    public JobWithDetailsInfo getJobLastBuild(String jobName);

    /**
     * @Description 获取 Job 首次 Build
     * @return com.jyxc.devops.jenkins.job.model.BuildInfo
     */
    public BuildInfo getJobFirstBuild(String jobName);

    /**
     * @Description 根据 Job Build 编号获取编译信息
     * @return com.jyxc.devops.jenkins.job.model.BuildInfo
     */
    public BuildInfo getBuildInfoByBuildNumber(String jobName, Integer buildNumber);

    /**
     * @Description 获取全部 Job Build列表
     * @return java.util.List<com.jyxc.devops.jenkins.job.model.BuildInfo>
     */
    public List<BuildInfo> getJobBuildListAll(String jobName);

    /**
     * @Description 获取 Job 一定范围的 Build 列表
     * @return java.util.List<com.jyxc.devops.jenkins.job.model.BuildInfo>
     */
    public List<BuildInfo> getJobBuildListRange(String jobName, Integer startNumber, Integer endNumber);

    /**
     * @Description 获取 Build 基本信息
     * @return com.jyxc.devops.jenkins.job.model.BuildInfo
     */
    public BuildInfo getJobBuildInfo(String jobName);
    
    /**
     * @Description 获取 Build 详细信息
     * @return com.jyxc.devops.jenkins.job.model.BuildInfo
     */
    //public BuildWithDetails getJobBuildDetailInfo(String jobName);

    /**
     * @Description 获取 Build Log 日志信息
     * @return void
     */
    public ResultData getJobBuildLog(String jobName);

    /**
     * @Description 获取正在执行构建任务的日志信息
     * @return void
     */
    public ResultData getBuildActiveLog(String jobName, int bufferOffset);

    /**
     * @Description 获取正在执行构建任务的日志信息
     * @return void
     */
    public ResultData getLastBuildLog(String jobName);


}
