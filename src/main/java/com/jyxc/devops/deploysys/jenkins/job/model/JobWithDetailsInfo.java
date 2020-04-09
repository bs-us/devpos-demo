package com.jyxc.devops.deploysys.jenkins.job.model;


import lombok.Data;

import java.util.List;

/**
 * @Authror huiwang
 * @Description TODO
 * @Date 2019/12/14 9:45
 */
@Data
public class JobWithDetailsInfo extends JobInfo{
    private String description;
    private String displayName;
    ///private boolean buildable;
    private List<BuildInfo> builds;
    private BuildInfo firstBuild;
    private BuildInfo lastBuild;
    private BuildInfo lastCompletedBuild;
    private BuildInfo lastFailedBuild;
    private BuildInfo lastStableBuild;
    private BuildInfo lastSuccessfulBuild;
    private BuildInfo lastUnstableBuild;
    private BuildInfo lastUnsuccessfulBuild;
    private boolean inQueue;
    //private com.offbytwo.jenkins.model.QueueItem queueItem;
    private List<JobInfo> downstreamProjects;
    private List<JobInfo> upstreamProjects;
}
