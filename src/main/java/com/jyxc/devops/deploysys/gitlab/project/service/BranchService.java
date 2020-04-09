package com.jyxc.devops.deploysys.gitlab.project.service;

import com.jyxc.devops.deploysys.gitlab.project.model.BranchInfo;
import com.jyxc.devops.deploysys.gitlab.project.model.ProjectInfo;

import java.util.List;


/**
 * @Authror huiwang
 * @Description branch (分支) 的相关操作
 * @Date 2019/12/24 10:18
 */
public interface BranchService {


    /**
     * @Description 获取所有分支 by 项目
     * @return com.jyxc.devops.common.ResultData
     */
    public List<BranchInfo> getAllBranchesByProject(ProjectInfo project);

    /**
     * @Description 获取所有分支 by 项目id
     * @return com.jyxc.devops.common.ResultData
     */
    public List<BranchInfo> getAllBranchesByProjectId(Integer projectId);

    /**
     * @Description 创建分支 by 项目
     * @return com.jyxc.devops.common.ResultData
     */
    public void createBranchByProject(ProjectInfo project, String branchName, String ref);

    /**
     * @Description 创建分支 by 项目id
     * @return com.jyxc.devops.common.ResultData
     */
    public void createBranchByProjectId(Integer projectId, String branchName, String ref);

    /**
     * @Description 删除分支 by 项目id、分支名称
     * @return com.jyxc.devops.common.ResultData
     */
    public void deleteBranch(Integer projectId, String branchName);

    /**
     * @Description 获取分支信息 by 项目
     * @return BranchInfo
     */
    public BranchInfo getBranchByProject(ProjectInfo project, String branchName);

    /**
     * @Description 获取分支信息 by 项目id
     * @return BranchInfo
     */
    public BranchInfo getBranchByProjectId(Integer projectId, String branchName);






}
