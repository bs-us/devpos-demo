package com.jyxc.devops.deploysys.gitlab.user.model;

import lombok.Data;

/**
 * @Authror huiwang
 * @Description TODO
 * @Date 2019/12/24 13:43
 */
@Data
public class SSHKeyInfo {

    private Integer _id;
    private String _title;
    private String _key;
    private GitlabUserInfo _user;
}
