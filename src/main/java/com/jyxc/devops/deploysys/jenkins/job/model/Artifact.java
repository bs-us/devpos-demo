package com.jyxc.devops.deploysys.jenkins.job.model;

import lombok.Data;

/**
 * @Authror huiwang
 * @Description TODO
 * @Date 2019/12/14 14:11
 */
@Data
public class Artifact {

    private String displayPath;
    private String fileName;
    private String relativePath;
}
