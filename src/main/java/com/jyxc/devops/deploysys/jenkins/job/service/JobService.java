package com.jyxc.devops.deploysys.jenkins.job.service;

import com.jyxc.devops.deploysys.common.ResultData;
import com.jyxc.devops.deploysys.jenkins.job.model.JobInfo;
import com.offbytwo.jenkins.model.Job;
import com.offbytwo.jenkins.model.MavenJobWithDetails;
import com.offbytwo.jenkins.model.QueueReference;

import java.util.Map;

/**
 * @Authror huiwang
 * @Description Job(任务) 相关操作
 * @Date 2019/12/13 9:45
 */
public interface JobService {

    /**
     * @Description 获取所有的Job
     * @return com.jyxc.devops.common.ResultData
     */
    public Map<String, Job> getAllJobs();

    /**
     * @Description 创建 Job
     * @return java.lang.String
     */
    public ResultData createJob(String jobName, String description, String projectCloneUrl );

    /**
     * @Description 更新 Job
     * @return java.lang.String
     */
    public void updateJob(String jobName, String description, String pipelineScript);

    /**
     * @Description 获取 Job 基本信息
     * @return com.jyxc.devops.jenkins.job.model.JobInfo
     */
    public JobInfo getJobInfo(String jobName);

    /**
     * @Description 获取 Maven Job 信息
     * @return com.offbytwo.jenkins.model.MavenJobWithDetails
     */
    public MavenJobWithDetails getMavenJob(String jobName);

    /**
     * @Description 获取 Job 列表
     * @return java.util.Map<java.lang.String,com.offbytwo.jenkins.model.Job>
     */
    public Map<String,Job> getJobList();

    /**
     * @Description 通过 View 名称获取对应的 Job 列表
     * @return java.util.Map<java.lang.String,com.offbytwo.jenkins.model.Job>
     */
    public Map<String,Job> getJobListByView(String viewName);

    /**
     * @Description 查看 Job XML 信息
     * @return java.lang.String
     */
    public String getJobXMLConfig(String jobName);

    /**
     * @Description 执行 Job build
     * @return java.lang.String
     */
    public ResultData buildJob(String jobName);

    /**
     * @Description 执行带参数 Job build
     * @return java.lang.String
     */
    public QueueReference buildParamJob(String jobName, Map<String, String> param);

    /**
     * @Description 停止最后构建的 Job Build
     * @return java.lang.String
     */
    public String stopLastJobBuild(String jobName);

    /**
     * @Description 删除 Job
     * @return java.lang.String
     */
    public ResultData deleteJob(String jobName);

    /**
     * @Description 禁用 Job
     * @return java.lang.String
     */
    public void disableJob(String jobName);

    /**
     * @Description 启用 Job
     * @return java.lang.String
     */
    public void enableJob(String jobName);

}
