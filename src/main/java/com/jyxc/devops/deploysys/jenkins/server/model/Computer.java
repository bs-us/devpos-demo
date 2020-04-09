package com.jyxc.devops.deploysys.jenkins.server.model;

import lombok.Data;

import java.util.List;

/**
 * @Authror huiwang
 * @Description Jenkins主机信息
 * @Date 2019/12/14 14:43
 */
@Data
public class Computer {
    private String displayName;
    private List<Computer> computer;


}
