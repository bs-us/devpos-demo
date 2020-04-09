package com.jyxc.devops.deploysys.jenkins.job.service.impl;

import com.jyxc.devops.deploysys.BeanConnectionUtil;
import com.jyxc.devops.deploysys.common.ResultData;
import com.jyxc.devops.deploysys.common.SysConstants;
import com.jyxc.devops.deploysys.jenkins.job.model.JobInfo;
import com.jyxc.devops.deploysys.jenkins.job.service.JobService;
import com.offbytwo.jenkins.JenkinsServer;
import com.offbytwo.jenkins.client.JenkinsHttpClient;
import com.offbytwo.jenkins.model.*;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @Authror huiwang
 * @Description Job(任务) 相关操作
 * @Date 2019/12/13 11:06
 */
@Service
public class JobServiceImpl implements JobService {

    // Jenkins 对象
    private static final JenkinsServer JENKINS_SERVER = (JenkinsServer) BeanConnectionUtil.getBean("jenkinsServer");
    // http 客户端对象
    private static final JenkinsHttpClient JENKINS_HTTP_CLIENT = (JenkinsHttpClient) BeanConnectionUtil.getBean("jenkinsHttpClient");


    @Override
    public Map<String, Job> getAllJobs(){
        try {
            Map<String, Job> jobs = JENKINS_SERVER.getJobs();
            return jobs;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ResultData createJob(String jobName, String description,String projectCloneUrl) {
        ResultData resultData = null;
        String xml = "<flow-definition plugin=\"workflow-job@2.36\">\n" +
                "  <actions/>\n" +
                "  <description>"+description+"</description>\n" +
                "  <keepDependencies>false</keepDependencies>\n" +
                "  <properties/>\n" +
                "  <definition class=\"org.jenkinsci.plugins.workflow.cps.CpsFlowDefinition\" plugin=\"workflow-cps@2.80\">\n" +
                "    <script>node{\n" +
                "    stage(&apos;git clone&apos;){\n" +
                "        checkout([$class: &apos;GitSCM&apos;, branches: [[name: &apos;*/master&apos;]], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[credentialsId: &apos;MyGitlabAPI&apos;, url: &apos;"+projectCloneUrl+"&apos;]]])\n" +
                "        def pom = readMavenPom file: &apos;pom.xml&apos;\n" +
                "        harbor_host = &quot;192.30.103.122:8084&quot;\n" +
                "        img_name = &quot;${pom.artifactId}&quot;\n" +
                "        file_name = &quot;${pom.artifactId}&quot;\n" +
                "        jar_name = &quot;${pom.artifactId}&quot;\n" +
                "        version = &quot;${pom.version}&quot;\n" +
                "        echo &quot;group: ${pom.groupId}, artifactId: ${pom.artifactId}, version: ${pom.version}&quot;\n" +
                "    }\n" +
                "    stage(&apos;mvn test&apos;){\n" +
                "        withMaven(maven: &apos;maven&apos;) {\n" +
                "                sh &quot;mvn test&quot;\n" +
                "        }\n" +
                "    }\n" +
                "    stage(&apos;mvn build&apos;){\n" +
                "        withMaven(maven: &apos;maven&apos;) {\n" +
                "            sh &quot;mvn clean install -Dmaven.test.skip=true&quot;\n" +
                "            sh &quot;docker --version&quot;\n" +
                "            sh &quot;echo -e &apos;FROM openjdk:8-jre-slim \\nADD target/${jar_name}-${version}.jar /${jar_name}.jar \\nRUN  java -jar /${jar_name}.jar 2&gt;&amp;1&amp;&apos;&gt;Dockerfile&quot;\n" +
                "            sh &quot;docker build -t ${img_name}:${build_tag}-${version} .&quot;\n" +
                "        }\n" +
                "    }\n" +
                "    stage(&apos;push harbor&apos;){\n" +
                "        sh &quot;docker login ${harbor_host} -u admin -p Harbor12345&quot;\n" +
                "        sh &quot;docker tag ${img_name}:${build_tag}-${version} ${harbor_host}/${file_name}/${img_name}:${build_tag}-${version}&quot;\n" +
                "        sh &quot;docker push  ${harbor_host}/${file_name}/${img_name}:${build_tag}-${version}&quot;\n" +
                "    }\n" +
                "}\n" +
                "</script>\n" +
                "    <sandbox>true</sandbox>\n" +
                "  </definition>\n" +
                "  <disabled>false</disabled>\n" +
                "</flow-definition>";
        Boolean jobNameExists = isJobNameExists(jobName);
        if(jobNameExists){
            resultData = new ResultData(SysConstants.status.STATUS_NAME_EXISTS,
                    SysConstants.status.STATUS_NAME_EXISTS_MSG, null);
        }else {
            // 创建 Job
            try {
                JENKINS_SERVER.createJob(jobName,xml);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Boolean jobExists = isJobNameExists(jobName);
            if(jobExists){
                resultData = new ResultData(SysConstants.status.STATUS_SUCCESS,
                        SysConstants.status.STATUS_SUCCESS_MSG, null);
            }else {
                resultData = new ResultData(SysConstants.status.STATUS_FAIL,
                        SysConstants.status.STATUS_FAIL_MSG, null);
            }
        }
        return resultData;
    }

    @Override
    public void updateJob(String jobName,String description,String pipelineScript) {
        String result = null;
        // xml配置文件，且将脚本加入到配置中
        try {
            String xml = "<flow-definition plugin=\"workflow-job@2.32\">\n" +
                    "<actions/>\n" +
                    "<description>" + description + "</description>\n" +
                    "<keepDependencies>false</keepDependencies>\n" +
                    "<properties>\n" +
                    "<hudson.model.ParametersDefinitionProperty>\n" +
                    "<parameterDefinitions>\n" +
                    "<hudson.model.StringParameterDefinition>\n" +
                    "<name>" + jobName + "</name>\n" +
                    "<description>" + description + "</description>\n" +
                    "<defaultValue>" + jobName + "</defaultValue>\n" +
                    "<trim>false</trim>\n" +
                    "</hudson.model.StringParameterDefinition>\n" +
                    "</parameterDefinitions>\n" +
                    "</hudson.model.ParametersDefinitionProperty>\n" +
                    "</properties>\n" +
                    "<definition class=\"org.jenkinsci.plugins.workflow.cps.CpsFlowDefinition\" plugin=\"workflow-cps@2.66\">\n" +
                    "<script>" + pipelineScript + "</script>\n" +
                    "<sandbox>true</sandbox>\n" +
                    "</definition>\n" +
                    "<disabled>false</disabled>\n" +
                    "</flow-definition>";
//            Boolean jobNameExists = isJobNameExists(jobName);
//            if(!jobNameExists){
//                ResultData resultData = new ResultData(SysConstants.status.STATUS_NAME_NOTHING,
//                        SysConstants.status.STATUS_NAME_NOTHING_MSG, null);
//                return resultData;
//            }else {
                // 修改 Job
                JENKINS_SERVER.updateJob(jobName,xml);
//            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public JobInfo getJobInfo(String jobName) {
        JobInfo jobInfo = new JobInfo();
        try {
            // 获取 Job 信息
            JobWithDetails job = JENKINS_SERVER.getJob(jobName);
            if(StringUtils.isEmpty(job)){
                // 获取 Job 名称
                jobInfo.setName(job.getName());
                // 获取 Job URL
                jobInfo.setUrl(job.getUrl());
                // 获取 Job 下一个 build 编号
                jobInfo.setNextBuildNumber(job.getNextBuildNumber());
                // 获取 Job 显示的名称
                jobInfo.setDisplayName(job.getDisplayName());
                // 输出 Job 描述信息
                jobInfo.setDescription(job.getDescription());
                // 获取 Job 下游任务列表
                //System.out.println(job.getDownstreamProjects());
                // 获取 Job 上游任务列表
                //System.out.println(job.getUpstreamProjects());
            }
//            ResultData resultData = new ResultData(SysConstants.status.STATUS_SUCCESS,
//                    SysConstants.status.STATUS_SUCCESS_MSG, jobInfo);
//            return resultData;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jobInfo;
    }

    @Override
    public MavenJobWithDetails getMavenJob(String jobName) {
        MavenJobWithDetails job = null;
        try {
            // 获取 Job 信息
            job = JENKINS_SERVER.getMavenJob(jobName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return job;
    }

    @Override
    public Map<String,Job> getJobList() {
        Map<String,Job> jobs = new HashMap<String, Job>();
        try {
            // 获取 Job 列表
            jobs = JENKINS_SERVER.getJobs();
//            if(StringUtils.isEmpty(jobs)){
//                JobInfo jobInfo = new JobInfo();
//                for (Job job:jobs.values()){
//                    jobInfo.setName(job.getName());
//                }
//            }
//            ResultData resultData = new ResultData(SysConstants.status.STATUS_SUCCESS,
//                    SysConstants.status.STATUS_SUCCESS_MSG, jobs);
//            return resultData;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return jobs;
    }

    @Override
    public Map<String,Job> getJobListByView(String viewName) {
        Map<String, Job> jobs = new HashMap<>();
        try {
            // 获取 Job 列表
            jobs = JENKINS_SERVER.getJobs(viewName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jobs;
    }

    @Override
    public String getJobXMLConfig(String jobName) {
        String jobXMLConfig = null;
        try {
            jobXMLConfig = JENKINS_SERVER.getJobXml(jobName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jobXMLConfig;
    }

    @Override
    public ResultData buildJob(String jobName) {
        ResultData resultData = null;
        QueueReference build = null;
        try {
            build = JENKINS_SERVER.getJob(jobName).build();
            int buildNum = Integer.parseInt(build.getQueueItemUrlPart().substring(39, 42));
            if(build.getQueueItemUrlPart() != null){
                resultData = new ResultData(SysConstants.status.STATUS_SUCCESS,
                        SysConstants.status.STATUS_SUCCESS_MSG, buildNum);
            }else {
                resultData = new ResultData(SysConstants.status.STATUS_FAIL,
                        SysConstants.status.STATUS_FAIL_MSG, buildNum);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultData;
    }

    @Override
    public QueueReference buildParamJob(String jobName,Map<String,String> param){
        QueueReference build = null;
        try {
            /**
             * 例如，现有一个job，拥有一个字符参数"key"
             * 现在对这个值进行设置，然后执行一个输出这个值的脚本
             */
            // 设置参数值
            //Map<String,String> param = new HashMap<>();
            //param.put("key","hello world!");
            // 执行 build 任务
            build = JENKINS_SERVER.getJob(jobName).build(param);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return build;
    }

    @Override
    public String stopLastJobBuild(String jobName) {
        String result = null;
        try {
            // 获取最后的 build 信息
            Build build = JENKINS_SERVER.getJob(jobName).getLastBuild();
            // 停止最后的 build
            result = build.Stop();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ResultData deleteJob(String jobName) {
        ResultData resultData = null;
        Boolean jobNameExists = isJobNameExists(jobName);
        if(!jobNameExists){
            resultData = new ResultData(SysConstants.status.STATUS_NAME_EXISTS,
                    SysConstants.status.STATUS_NAME_NOTHING_MSG, null);
        }else {
            try {
                JENKINS_SERVER.deleteJob(jobName);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Boolean jobExists = isJobNameExists(jobName);
            if(!jobExists){
                resultData = new ResultData(SysConstants.status.STATUS_SUCCESS,
                        SysConstants.status.STATUS_SUCCESS_MSG, null);
            }else {
                resultData = new ResultData(SysConstants.status.STATUS_FAIL,
                        SysConstants.status.STATUS_FAIL_MSG, null);
            }
        }
        return resultData;
    }

    @Override
    public void disableJob(String jobName) {
        try {
            JENKINS_SERVER.disableJob(jobName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void enableJob(String jobName) {
        try {
            JENKINS_SERVER.enableJob(jobName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return true/false
     * @Description 判断viewName(视图名称)是否存在
     */
    private Boolean isJobNameExists(String jobName) {
        Boolean isJobNameExists = false;
        try {
            Map<String, Job> jobs = JENKINS_SERVER.getJobs();
            Iterator<Map.Entry<String, Job>> iteratorJobs = jobs.entrySet().iterator();
            while (iteratorJobs.hasNext()) {
                Map.Entry<String, Job> jobsEntry = iteratorJobs.next();
                if (jobName.equals(jobsEntry.getValue().getName())) {
                    isJobNameExists = true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return isJobNameExists;
    }
}
