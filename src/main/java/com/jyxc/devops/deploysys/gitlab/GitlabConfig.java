package com.jyxc.devops.deploysys.gitlab;

import com.offbytwo.jenkins.client.JenkinsHttpClient;
import org.gitlab.api.GitlabAPI;
import org.gitlab.api.models.GitlabSession;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

/**
 * @Authror huiwang
 * @Description TODO
 * @Date 2019/12/23 19:49
 */

@Configuration
public class GitlabConfig {

    @Bean(name = "gitlabAPI")
    public GitlabAPI getGitlabConnection(){
        return getGitlabAPI();
    }

    // 连接 Gitlab 需要设置的信息
    @Value("${gitlab.url}")
    private String gitUrl;

//    @Value("${gitlab.username}")
//    private String gitlabUsername;
//
//    @Value("${gitlab.password}")
//    private String gitlabPassword;

    @Value("${gitlab.privateToken}")
    private String gitlabPrivateToken;


    public  GitlabAPI getGitlabAPI(){
        GitlabAPI gitlabAPI = null;
        //GitlabSession gitlabSession = GitlabAPI.connect(gitUrl, gitlabUsername, gitlabPassword);
        gitlabAPI = GitlabAPI.connect(gitUrl, gitlabPrivateToken);
        System.out.println("获取gitlabAPI:开始运行......"+gitUrl+gitlabPrivateToken+gitlabAPI);
        return gitlabAPI;
    }

}
