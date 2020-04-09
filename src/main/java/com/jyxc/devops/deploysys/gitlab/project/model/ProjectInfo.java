package com.jyxc.devops.deploysys.gitlab.project.model;


import com.jyxc.devops.deploysys.gitlab.user.model.GitlabUserInfo;
import lombok.Data;

/**
 * @Authror huiwang
 * @Description  项目信息
 * @Date 2019/12/24 10:29
 */
@Data
public class ProjectInfo {

    /**
     * @Description 项目id
     */
    private Integer id;

    /**
     * @Description 项目名称
     */
    private String name;

    /**
     * @Description namespace
     */
    private String nameWithNamespace;

    /**
     * @Description 项目描述
     */
    private String description;

    /**
     * @Description 默认分支
     */
    private String defaultBranch;

    /**
     * @Description 项目所属用户
     */
    private GitlabUserInfo owner;

    /**
     * @Description 项目 公有 or 私有
     */
    private Boolean publicProject;

    /**
     * @Description 地址
     */
    private String pathWithNamespace;
}
