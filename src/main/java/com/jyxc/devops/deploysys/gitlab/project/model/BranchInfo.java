package com.jyxc.devops.deploysys.gitlab.project.model;

import lombok.Data;

/**
 * @Authror huiwang
 * @Description 分支信息
 * @Date 2019/12/24 10:52
 */
@Data
public class BranchInfo {


    private String name;

    private BranchCommitInfo commit;

    private boolean branchProtected;
}
