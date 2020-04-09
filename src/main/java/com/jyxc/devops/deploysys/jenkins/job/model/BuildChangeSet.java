package com.jyxc.devops.deploysys.jenkins.job.model;

import lombok.Data;

import java.util.List;

/**
 * @Authror huiwang
 * @Description TODO
 * @Date 2019/12/14 14:12
 */
@Data
public class BuildChangeSet {

    private List<BuildChangeSetItem> items;
    private String kind;

}
