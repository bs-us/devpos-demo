package com.jyxc.devops.deploysys.jenkins.job.controller;



import com.jyxc.devops.deploysys.common.ResultData;
import com.jyxc.devops.deploysys.jenkins.job.model.BuildInfo;
import com.jyxc.devops.deploysys.jenkins.job.model.JobWithDetailsInfo;
import com.jyxc.devops.deploysys.jenkins.job.service.JobBuildService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;


/**
 * @Authror huiwang
 * @Description Job Build(任务构建) 相关操作
 * (例如对任务 Build 相关的信息进行获取操作、例如获取构建日志)
 * @Date 2019/12/14 14:23
 */
@RestController
@RequestMapping("/build")
public class JobBuildController {

    @Resource
    private JobBuildService jobBuildService;

    /**
     * @Description 获取 Job 最后的 Build
     * @return com.jyxc.devops.jenkins.job.model.JobLastBuild
     */
    @RequestMapping("/lastBuildInfo")
    public JobWithDetailsInfo getJobLastBuild(String jobName){
        JobWithDetailsInfo jobLastBuild = jobBuildService.getJobLastBuild(jobName);
        return jobLastBuild;
    }

    /**
     * @Description 获取 Job 首次 Build
     * @return com.jyxc.devops.jenkins.job.model.BuildInfo
     */
    @RequestMapping("/firstBuildInfo")
    public BuildInfo getJobFirstBuild(String jobName){
        BuildInfo jobFirstBuild = jobBuildService.getJobFirstBuild(jobName);
        return jobFirstBuild;
    }

    /**
     * @Description 根据 Job Build 编号获取编译信息
     * @return com.jyxc.devops.jenkins.job.model.BuildInfo
     */
    @RequestMapping("/buildInfoByBuildNumber")
    public BuildInfo getBuildInfoByBuildNumber(String jobName , Integer buildNumber){
        BuildInfo buildInfoByBuildNumber = jobBuildService.getBuildInfoByBuildNumber(jobName, buildNumber);
        return buildInfoByBuildNumber;
    }

    /**
     * @Description 获取全部 Job Build列表
     * @return java.util.List<com.jyxc.devops.jenkins.job.model.BuildInfo>
     */
    @RequestMapping("/buildInfosAll")
    public List<BuildInfo> getJobBuildListAll(String jobName){
        List<BuildInfo> jobBuildListAll = jobBuildService.getJobBuildListAll(jobName);
        return jobBuildListAll;
    }

    /**
     * @Description 获取 Job 一定范围的 Build 列表
     * @return java.util.List<com.jyxc.devops.jenkins.job.model.BuildInfo>
     */
    @RequestMapping("/buildInfosRange")
    public List<BuildInfo> getJobBuildListRange(String jobName, Integer startNumber,Integer endNumber){
        List<BuildInfo> jobBuildListRange = jobBuildService.getJobBuildListRange(jobName, startNumber, endNumber);
        return jobBuildListRange;
    }

    /**
     * @Description 获取 Build 基本信息
     * @return com.jyxc.devops.jenkins.job.model.BuildInfo
     */
    @RequestMapping("buildInfo")
    public BuildInfo getJobBuildInfo(String jobName){
        BuildInfo jobBuildInfo = jobBuildService.getJobBuildInfo(jobName);
        return jobBuildInfo;
    }

    /**
     * @Description 获取 Build 详细信息
     * @return com.jyxc.devops.jenkins.job.model.BuildInfo
     */
//    @RequestMapping("/buildDetailInfo")
//    public BuildInfo getJobBuildDetailInfo(String jobName){
//        BuildInfo buildInfo = jobBuildService.getJobBuildDetailInfo(jobName);
//        return buildInfo;
//    }

    /**
     * @Description 获取 Build Log 日志信息
     * @return void
     */
    @RequestMapping("/jobBuildLog")
    public ResultData getJobBuildLog(String jobName){
        ResultData resultData = jobBuildService.getJobBuildLog(jobName);
        return resultData;
    }

    /**
     * @Description 获取正在执行构建任务的日志信息
     * @return void
     */
    @RequestMapping("/buildActiveLog")
    public ResultData getBuildActiveLog(String jobName, int bufferOffset){
        ResultData buildActiveLog = jobBuildService.getBuildActiveLog(jobName, bufferOffset);
        return buildActiveLog;
    }

    /**
     * @Description 获取正在执行构建任务的日志信息
     * @return void
     */
    @RequestMapping("/lastBuildLog")
    public ResultData getLastBuildLog(String jobName){
        ResultData lastBuildLog = jobBuildService.getLastBuildLog(jobName);
        return lastBuildLog;
    }
}
