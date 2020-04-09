package com.jyxc.devops.deploysys.gitlab.user.model;

import lombok.Data;

/**
 * @Authror huiwang
 * @Description 用户信息
 * @Date 2019/12/24 10:34
 */
@Data
public class GitlabUserInfo {

    private Integer id;
    private String username;
    private String email;
    private String name;
    private String skype;
    private String linkedin;
    private String twitter;
    private String provider;
    private String state;
    private Boolean blocked;
}
