package com.jyxc.devops.deploysys.gitlab.project.model;

import com.jyxc.devops.deploysys.gitlab.user.model.GitlabUserInfo;

import java.util.Date;

/**
 * @Authror huiwang
 * @Description 分支提交信息
 * @Date 2019/12/24 10:53
 */
public class BranchCommitInfo {

    private String id;
    private String tree;
    private String message;
    private GitlabUserInfo author;
    private GitlabUserInfo committer;
    private Date authoredDate;
    private Date committedDate;
}
