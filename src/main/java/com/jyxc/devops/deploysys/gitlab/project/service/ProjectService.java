package com.jyxc.devops.deploysys.gitlab.project.service;

import com.jyxc.devops.deploysys.gitlab.group.model.GroupInfo;
import com.jyxc.devops.deploysys.gitlab.project.model.ProjectInfo;

import java.util.List;

/**
 * @Authror huiwang
 * @Description project (项目) 相关操作
 * @Date 2019/12/24 9:36
 */
public interface ProjectService {



    /**
     * @Description 获取所有的项目信息
     * @return com.jyxc.devops.common.ResultData
     */
    public List<ProjectInfo> getAllProjects();

    /**
     * @Description 创建项目
     * @return com.jyxc.devops.common.ResultData
     */
    public ProjectInfo createProject(String projectName);

    /**
     * @Description
     * @return com.jyxc.devops.deploysys.gitlab.project.model.ProjectInfo
     */
    public ProjectInfo createProject(ProjectInfo projectInfo);

    /**
     * @Description 在当前组下创建项目
     * @return com.jyxc.devops.common.ResultData
     */
    public ProjectInfo createProjectForGroup(String projectName, GroupInfo group);

    /**
     * @Description 通过项目id 删除项目
     * @return void
     */
    public void deleteProject(String projectId);

}
