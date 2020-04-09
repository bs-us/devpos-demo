package com.jyxc.devops.deploysys.jenkins.job.model;

import lombok.Data;

import java.util.List;

/**
 * @Authror huiwang
 * @Description TODO
 * @Date 2019/12/14 14:13
 */
@Data
public class BuildChangeSetItem {

    private List<String> affectedPaths;
    private String commitId;
    private String timestamp;
    private BuildChangeSetAuthor author;
    private String comment;
    private String date;
    private String id;
    private String msg;
    private List<BuildChangeSetPath> paths;
}
