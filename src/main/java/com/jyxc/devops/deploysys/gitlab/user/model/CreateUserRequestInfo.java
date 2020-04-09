package com.jyxc.devops.deploysys.gitlab.user.model;

import lombok.Data;

/**
 * @Authror huiwang
 * @Description 创建用户时的请求参数
 * @Date 2019/12/24 11:29
 */
@Data
public class CreateUserRequestInfo {

    private String email;
    private String password;
    private Boolean resetPassword;
    private String username;
    private String name;
    private String skype;
    private String linkedin;
    private String twitter;
    private String websiteUrl;
    private String organization;
    private Integer projectsLimit;
    private String externUid;
    private String provider;
    private String bio;
    private String location;
    private Boolean admin;
    private Boolean canCreateGroup;
    private Boolean skipConfirmation;
    private Boolean external;
    private String avatar;
}
