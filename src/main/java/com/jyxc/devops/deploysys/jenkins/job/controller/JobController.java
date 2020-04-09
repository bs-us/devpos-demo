package com.jyxc.devops.deploysys.jenkins.job.controller;

import com.jyxc.devops.deploysys.common.ResultData;
import com.jyxc.devops.deploysys.jenkins.job.model.JobInfo;
import com.jyxc.devops.deploysys.jenkins.job.service.JobService;
import com.offbytwo.jenkins.model.Job;
import com.offbytwo.jenkins.model.MavenJobWithDetails;
import com.offbytwo.jenkins.model.QueueReference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @Authror huiwang
 * @Description Job(任务) 相关操作
 * (例如对任务的增、删、改、查等操作)
 * @Date 2019/12/14 10:53
 */
@RestController
@RequestMapping("/job")
public class JobController {

    @Resource
    private JobService jobService;

    /**
     * @Description 创建 Job
     * @return java.lang.String
     */
    @RequestMapping("/job")
    public ResultData createJob(String jobName, String description, String projectCloneUrl){
        ResultData resultData = jobService.createJob(jobName, description, projectCloneUrl);
        return resultData;
    }

    /**
     * @Description 更新 Job
     * @return java.lang.String
     */
    @RequestMapping("/update")
    public void updateJob(String jobName,String description,String pipelineScript){
        jobService.updateJob(jobName, description, pipelineScript);
    }

    /**
     * @Description 获取 Job 基本信息
     * @return com.jyxc.devops.jenkins.job.model.JobInfo
     * TODO
     */
    @RequestMapping("/jobInfo")
    public JobInfo getJobInfo(String jobName){
        JobInfo jobInfo = jobService.getJobInfo(jobName);
        return jobInfo;
    }

    /**
     * @Description 获取 Maven Job 信息
     * @return com.offbytwo.jenkins.model.MavenJobWithDetails
     * TODO
     */
    @RequestMapping("/mavenJob")
    public MavenJobWithDetails getMavenJob(String jobName){
        MavenJobWithDetails mavenJob = jobService.getMavenJob(jobName);
        return mavenJob;
    }

    /**
     * @Description 获取 Job 列表
     * @return java.util.Map<java.lang.String,com.offbytwo.jenkins.model.Job>
     */
    @RequestMapping("/jobList")
    public Map<String, Job> getJobList(){
        Map<String, Job> jobList = jobService.getJobList();
        return jobList;
    }

    /**
     * @Description 通过 View 名称获取对应的 Job 列表
     * @return java.util.Map<java.lang.String,com.offbytwo.jenkins.model.Job>
     */
    @RequestMapping("/jobListByView")
    public Map<String, Job> getJobListByView(String viewName){
        Map<String, Job> jobListByView = jobService.getJobListByView(viewName);
        return jobListByView;
    }

    /**
     * @Description 查看 Job XML 信息
     * @return java.lang.String
     */
    @RequestMapping("/jobXMLConfig")
    public String getJobXMLConfig(String jobName){
        String jobXMLConfig = jobService.getJobXMLConfig(jobName);
        return jobXMLConfig;
    }

    /**
     * @Description 执行 Job build
     * @return java.lang.String
     */
    @RequestMapping("/build")
    public ResultData buildJob(String jobName){
        ResultData resultData = jobService.buildJob(jobName);
        return resultData;
    }

    /**
     * @Description 执行带参数 Job build
     * @return java.lang.String
     */
    @RequestMapping("buildParam")
    public QueueReference buildParamJob(String jobName,Map<String,String> param){
        QueueReference queueReference = jobService.buildParamJob(jobName, param);
        return queueReference;
    }

    /**
     * @Description 停止最后构建的 Job Build
     * @return java.lang.String
     */
    @RequestMapping("/stopLastJobBuild")
    public String stopLastJobBuild(String jobName){
        String stop = jobService.stopLastJobBuild(jobName);
        return stop;
    }

    /**
     * @Description 删除 Job
     * @return java.lang.String
     */
    @RequestMapping("/deleteJob")
    public ResultData deleteJob(String jobName){
        ResultData resultData = jobService.deleteJob(jobName);
        return resultData;
    }

    /**
     * @Description 禁用 Job
     * @return java.lang.String
     */
    @RequestMapping("/disableJob")
    public void disableJob(String jobName){
        jobService.disableJob(jobName);
    }

    /**
     * @Description 启用 Job
     * @return java.lang.String
     */
    @RequestMapping("/enableJob")
    public void enableJob(String jobName){
        jobService.enableJob(jobName);
    }


}
