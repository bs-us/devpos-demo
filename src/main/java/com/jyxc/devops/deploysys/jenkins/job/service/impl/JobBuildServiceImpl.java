package com.jyxc.devops.deploysys.jenkins.job.service.impl;

import com.jyxc.devops.deploysys.BeanConnectionUtil;
import com.jyxc.devops.deploysys.common.ResultData;
import com.jyxc.devops.deploysys.common.SysConstants;
import com.jyxc.devops.deploysys.jenkins.job.model.BuildInfo;
import com.jyxc.devops.deploysys.jenkins.job.model.BuildWithDetailsInfo;
import com.jyxc.devops.deploysys.jenkins.job.model.ConsoleLogInfo;
import com.jyxc.devops.deploysys.jenkins.job.model.JobWithDetailsInfo;
import com.jyxc.devops.deploysys.jenkins.job.service.JobBuildService;
import com.offbytwo.jenkins.JenkinsServer;
import com.offbytwo.jenkins.client.JenkinsHttpClient;
import com.offbytwo.jenkins.helper.Range;
import com.offbytwo.jenkins.model.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * @Authror huiwang
 * @Description Job Build(任务构建) 相关操作
 * @Date 2019/12/14 12:26
 */
@Service
public class JobBuildServiceImpl implements JobBuildService {

    // Jenkins 对象
    private static final JenkinsServer JENKINS_SERVER = (JenkinsServer) BeanConnectionUtil.getBean("jenkinsServer");
    // http 客户端对象
    private static final JenkinsHttpClient JENKINS_HTTP_CLIENT = (JenkinsHttpClient) BeanConnectionUtil.getBean("jenkinsHttpClient");



    @Override
    public JobWithDetailsInfo getJobLastBuild(String jobName) {
        JobWithDetailsInfo jobInfo = new JobWithDetailsInfo();
        List<BuildInfo> buildInfos = new ArrayList<>();

        try {
            // 获取 Job 信息
            JobWithDetails job = JENKINS_SERVER.getJob(jobName);
            jobInfo.setDescription(job.getDescription());
            jobInfo.setDisplayName(job.getDisplayName());
            ListIterator<Build> buildListIterator = job.getBuilds().listIterator();
            while (buildListIterator.hasNext()) {
                BuildInfo buildInfo = new BuildInfo();
                Build build = buildListIterator.next();
                BeanUtils.copyProperties(build, buildInfo);
                buildInfos.add(buildInfo);
            }
            jobInfo.setBuilds(buildInfos);
            return jobInfo;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public BuildInfo getJobFirstBuild(String jobName) {
        BuildInfo buildInfo = new BuildInfo();
        try {
            // 获取 Job 信息
            JobWithDetails job = JENKINS_SERVER.getJob(jobName);
            // 获得首次编译信息
            Build firstBuild = job.getFirstBuild();
            BeanUtils.copyProperties(firstBuild, buildInfo);
            return buildInfo;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public BuildInfo getBuildInfoByBuildNumber(String jobName, Integer buildNumber) {
        BuildInfo buildInfo = new BuildInfo();
        try {
            // 获取 Job 信息
            JobWithDetails job = JENKINS_SERVER.getJob(jobName);
            // 根据
            Build numberBuild = job.getBuildByNumber(buildNumber);
            BeanUtils.copyProperties(numberBuild, buildInfo);
            return buildInfo;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<BuildInfo> getJobBuildListAll(String jobName) {
        List<BuildInfo> buildInfos = new ArrayList<>();
        try {
            // 获取 Job 信息
            JobWithDetails job = JENKINS_SERVER.getJob(jobName);
            // 获取全部 Build 信息
            List<Build> builds = job.getAllBuilds();
            for (Build build:builds){
                BuildInfo buildInfo = new BuildInfo();
                BeanUtils.copyProperties(build,buildInfo);
                buildInfos.add(buildInfo);
            }
            return buildInfos;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<BuildInfo> getJobBuildListRange(String jobName, Integer startNumber, Integer endNumber) {
        List<BuildInfo> buildInfos = new ArrayList<BuildInfo>();
        try {
            // 获取 Job 信息
            JobWithDetails job = JENKINS_SERVER.getJob(jobName);
            // 设定范围
            Range range = Range.build().from(startNumber).to(endNumber);
            BuildInfo buildInfo = new BuildInfo();
            // 获取一定范围的 Build 信息
            List<Build> builds = job.getAllBuilds(range);
            for (Build build:builds){
                BeanUtils.copyProperties(build,buildInfo);
                buildInfos.add(buildInfo);
            }
            return buildInfos;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public BuildInfo getJobBuildInfo(String jobName) {
        BuildInfo buildInfo = new BuildInfo();
        try {
            // 获取 Job 信息
            JobWithDetails job = JENKINS_SERVER.getJob(jobName);
            // 这里时最后一次编译
            Build build = job.getLastBuild();
            // 获取构建的 URL 地址
            buildInfo.setUrl(build.getUrl());
            // 获取构建编号
            buildInfo.setNumber(build.getNumber());
            // 获取测试报告
            //build.getTestReport();
            // 获取测试结果
            //build.getTestResult();
            return buildInfo;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

//    @Override
//    public BuildWithDetails getJobBuildDetailInfo(String jobName) {
//        try {
//            // 获取 Job 信息
//            JobWithDetails job = jenkinsServer.getJob(jobName);
//            // 最后一次编译
//            BuildWithDetails build = job.getLastBuild().details();
//            // 获取构建的显示名称
//            System.out.println(build.getDisplayName());
//            // 获取构建的参数信息
//            System.out.println(build.getParameters());
//            // 获取构建编号
//            System.out.println(build.getNumber());
//            // 获取构建结果，如果构建未完成则会显示为null
//            System.out.println(build.getResult());
//            // 获取执行构建的活动信息
//            System.out.println(build.getActions());
//            // 获取构建持续多少时间(ms)
//            System.out.println(build.getDuration());
//            // 获取构建开始时间戳
//            System.out.println(build.getTimestamp());
//            // 获取构建头信息，里面包含构建的用户，上游信息，时间戳等
//            List<BuildCause> buildCauses = build.getCauses();
//            for (BuildCause bc:buildCauses){
//                System.out.println(bc.getUserId());
//                System.out.println(bc.getShortDescription());
//                System.out.println(bc.getUpstreamBuild());
//                System.out.println(bc.getUpstreamProject());
//                System.out.println(bc.getUpstreamUrl());
//                System.out.println(bc.getUserName());
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    @Override
    public ResultData getJobBuildLog(String jobName) {
        ResultData resultData = null;
        BuildWithDetailsInfo buildWithDetailsInfo = null;
        try {
            // 获取 Job 信息
            JobWithDetails job = JENKINS_SERVER.getJob(jobName);
            // 这里用最后一次编译来示例
            BuildWithDetails build = job.getLastBuild().details();
            if(build != null){
            // 获取构建的日志，如果正在执行构建，则会只获取已经执行的过程日志
                if(build.getConsoleOutputText() != null && build.getConsoleOutputText().isEmpty()) {
                    // Text格式日志
                    buildWithDetailsInfo.setConsoleOutputText(build.getConsoleOutputText());
                    // Html格式日志
                    buildWithDetailsInfo.setConsoleOutputHtml(build.getConsoleOutputHtml());
                    // 获取部分日志,一般用于正在执行构建的任务
                    ConsoleLog consoleLog = build.getConsoleOutputText(0);
                    // 获取当前截取的日志信息
                    ConsoleLogInfo consoleLogInfo = new ConsoleLogInfo();
                    consoleLogInfo.setConsoleLog(consoleLog.getConsoleLog());
                    // 是否已经构建完成，还有更多日志信息
                    consoleLogInfo.setHasMoreData(consoleLog.getHasMoreData());
                    // 获取当前日志大小
                    consoleLogInfo.setCurrentBufferSize(consoleLog.getCurrentBufferSize());
                    buildWithDetailsInfo.setConsoleLogInfo(consoleLogInfo);
                }
                resultData = new ResultData(SysConstants.status.STATUS_SUCCESS,
                        SysConstants.status.STATUS_SUCCESS_MSG, buildWithDetailsInfo);
            }else {
                resultData = new ResultData(SysConstants.status.STATUS_FAIL,
                        SysConstants.status.STATUS_FAIL_MSG, buildWithDetailsInfo);
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    return resultData;
    }

    @Override
    public ResultData getBuildActiveLog(String jobName, int bufferOffset) {
        ResultData resultData = null;
        ConsoleLogInfo consoleLogInfo = new ConsoleLogInfo();
        try {
            // 这里用最后一次编译来示例
            BuildWithDetails build = JENKINS_SERVER.getJob(jobName).getLastBuild().details();
            // 当前日志

            ConsoleLog currentLog = build.getConsoleOutputText(bufferOffset);
            consoleLogInfo.setConsoleLog(currentLog.getConsoleLog());
            consoleLogInfo.setCurrentBufferSize(currentLog.getCurrentBufferSize());
            consoleLogInfo.setHasMoreData(currentLog.getHasMoreData());
            // 输出当前获取日志信息
            if(currentLog.getHasMoreData()){
                resultData = new ResultData(SysConstants.status.STATUS_BUILDING,
                        SysConstants.status.STATUS_BUILDING_MSG, consoleLogInfo);
            }else {
                resultData = new ResultData(SysConstants.status.STATUS_BUILD_SUCCESS,
                        SysConstants.status.STATUS_BUILD_SUCCESS_MSG, consoleLogInfo);
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    return resultData;
    }

    @Override
    public ResultData getLastBuildLog(String jobName) {
        JobWithDetails job = null;
        int number = 0;
        try {
            job = JENKINS_SERVER.getJob(jobName);
            ListIterator<Build> buildListIterator = job.getBuilds().listIterator();
            while (buildListIterator.hasNext()) {
                BuildInfo buildInfo = new BuildInfo();
                Build build = buildListIterator.next();
                number = build.getNumber();
            }
            ResultData buildActiveLog = getBuildActiveLog(jobName, number);
            return buildActiveLog;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}

