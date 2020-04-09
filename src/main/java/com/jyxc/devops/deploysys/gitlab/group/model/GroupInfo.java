package com.jyxc.devops.deploysys.gitlab.group.model;

import lombok.Data;

/**
 * @Authror huiwang
 * @Description TODO
 * @Date 2019/12/24 13:58
 */
@Data
public class GroupInfo {

    private Integer id;
    private String name;
    private String path;
    private String description;
}
