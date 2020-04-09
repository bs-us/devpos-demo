package com.jyxc.devops.deploysys.jenkins;

import com.offbytwo.jenkins.JenkinsServer;
import com.offbytwo.jenkins.client.JenkinsHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * @Authror huiwang
 * @Description 连接 Jenkins(此类主要用于连接 Jenkins)
 * @Date 2019/12/12 17:07
 */

@Configuration
public class JenkinsConfig  {


    @Bean(name = "jenkinsHttpClient")
    public JenkinsHttpClient getJenkinsHttpClient(){
        return getClient();
    }
    @Bean(name = "jenkinsServer")
    public JenkinsServer getJenkinsServer(){
        return connection();
    }


    // 连接 Jenkins 需要设置的信息
    @Value("${jenkins.url}")
    private  String jenkinsUrl;

    @Value("${jenkins.username}")
    private  String jenkinsUsername;

    @Value("${jenkins.password}")
    private  String jenkinsPassword;


    public  JenkinsHttpClient getClient(){
        System.out.println("获取jenkinsHttpClient:开始运行......"+jenkinsUrl+jenkinsUsername+jenkinsPassword);
        JenkinsHttpClient jenkinsHttpClient = null;
        try {
            jenkinsHttpClient = new JenkinsHttpClient(new URI(jenkinsUrl), jenkinsUsername, jenkinsPassword);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return jenkinsHttpClient;
    }

    public  JenkinsServer connection() {
        System.out.println("获取JenkinsServer:开始运行......"+jenkinsUrl+jenkinsUsername+jenkinsPassword);
        JenkinsServer jenkinsServer = null;
        try {
            jenkinsServer = new JenkinsServer(new URI(jenkinsUrl), jenkinsUsername, jenkinsPassword);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return jenkinsServer;
    }


}
