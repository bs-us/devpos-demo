package com.jyxc.devops.deploysys.gitlab.project.controller;

import com.jyxc.devops.deploysys.gitlab.project.model.BranchInfo;
import com.jyxc.devops.deploysys.gitlab.project.model.ProjectInfo;
import com.jyxc.devops.deploysys.gitlab.project.service.BranchService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Authror huiwang
 * @Description branch (分支) 的相关操作
 * @Date 2020/1/6 14:08
 */
@RestController
@RequestMapping(value = "/branch")
public class BranchController {


    @Resource
    private BranchService branchService;



    /**
     * @Description 获取所有分支 by 项目
     * @return com.jyxc.devops.common.ResultData
     */
    @RequestMapping(value = "/getAllBranchesByProject")
    public List<BranchInfo> getAllBranchesByProject(ProjectInfo project){
        List<BranchInfo> allBranchesByProject = branchService.getAllBranchesByProject(project);
        //返回状态码+msg+result
//        ResultData resultData = new ResultData(SysConstants.status.STATUS_SUCCESS,
//                SysConstants.status.STATUS_SUCCESS_MSG,
//                allBranchesByProject);
        return allBranchesByProject;
    }

    /**
     * @Description 获取所有分支 by 项目id
     * @return com.jyxc.devops.common.ResultData
     */
    @RequestMapping(value = "/getAllBranchesByProjectId")
    public List<BranchInfo> getAllBranchesByProjectId(Integer projectId){
        List<BranchInfo> allBranchesByProjectId = branchService.getAllBranchesByProjectId(projectId);
        //返回状态码+msg+result
//        ResultData resultData = new ResultData(SysConstants.status.STATUS_SUCCESS,
//                SysConstants.status.STATUS_SUCCESS_MSG,
//                allBranchesByProjectId);
        return allBranchesByProjectId;
    }

    /**
     * @Description 创建分支 by 项目
     * @return com.jyxc.devops.common.ResultData
     */
    public void createBranchByProject(ProjectInfo project, String branchName, String ref){
        branchService.createBranchByProject(project,branchName,ref);
        //返回状态码+msg+result
//        ResultData resultData = new ResultData(SysConstants.status.STATUS_SUCCESS,
//                SysConstants.status.STATUS_SUCCESS_MSG,
//                null);
//        return resultData;
    }

    /**
     * @Description 创建分支 by 项目id
     * @return com.jyxc.devops.common.ResultData
     */
    public void createBranchByProjectId(Integer projectId, String branchName, String ref){
        branchService.createBranchByProjectId(projectId,branchName,ref);
        //返回状态码+msg+result
//        ResultData resultData = new ResultData(SysConstants.status.STATUS_SUCCESS,
//                SysConstants.status.STATUS_SUCCESS_MSG,
//                null);
//        return resultData;
    }

    /**
     * @Description 删除分支 by 项目id、分支名称
     * @return com.jyxc.devops.common.ResultData
     */
    public void deleteBranch(Integer projectId, String branchName){
        branchService.deleteBranch(projectId, branchName);
        //返回状态码+msg+result
//        ResultData resultData = new ResultData(SysConstants.status.STATUS_SUCCESS,
//                SysConstants.status.STATUS_SUCCESS_MSG,
//                null);
//        return resultData;
    }

    /**
     * @Description 获取分支信息 by 项目
     * @return BranchInfo
     */
    public void getBranchByProject(ProjectInfo project, String branchName){
        BranchInfo branchByProject = branchService.getBranchByProject(project, branchName);
        //返回状态码+msg+result
//        ResultData resultData = new ResultData(SysConstants.status.STATUS_SUCCESS,
//                SysConstants.status.STATUS_SUCCESS_MSG,
//                null);
//        return resultData;
    }

    /**
     * @Description 获取分支信息 by 项目id
     * @return BranchInfo
     */
    public BranchInfo getBranchByProjectId(Integer projectId, String branchName){
        BranchInfo branchByProjectId = branchService.getBranchByProjectId(projectId, branchName);
        //返回状态码+msg+result
//        ResultData resultData = new ResultData(SysConstants.status.STATUS_SUCCESS,
//                SysConstants.status.STATUS_SUCCESS_MSG,
//                branchByProjectId);
        return branchByProjectId;
    }
}
