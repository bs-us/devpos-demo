package com.jyxc.devops.deploysys.gitlab.project.controller;

import com.jyxc.devops.deploysys.gitlab.group.model.GroupInfo;
import com.jyxc.devops.deploysys.gitlab.project.model.ProjectInfo;
import com.jyxc.devops.deploysys.gitlab.project.service.ProjectService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Authror huiwang
 * @Description 项目的相关操作
 * @Date 2020/1/3 15:49
 */
@RestController
@RequestMapping(value = "/project")
public class ProjectController {

    @Resource
    private ProjectService projectService;

    /**
     * @Description 获取所有的项目信息
     * @return com.jyxc.devops.common.ResultData
     */
    @RequestMapping(value = "/all")
    public List<ProjectInfo> getAllProjects(){
        List<ProjectInfo> allProjects = projectService.getAllProjects();
        return allProjects;
    }

    /**
     * @Description 创建项目
     * @return com.jyxc.devops.common.ResultData
     */
    @RequestMapping(value = "/createByprojectName")
    public ProjectInfo createProjectByprojectName(String projectName){
        ProjectInfo project = projectService.createProject(projectName);
        return project;
    }

    /**
     * @Description 创建项目
     * @return com.jyxc.devops.common.ResultData
     */
    @RequestMapping(value = "/createByProjectInfo")
    public ProjectInfo createProjectByProjectInfo(ProjectInfo projectInfo){
        ProjectInfo project = projectService.createProject(projectInfo);
        return project;
    }

    /**
     * @Description 在当前组下创建项目
     * @return com.jyxc.devops.common.ResultData
     */
    @RequestMapping(value = "/createForGroup")
    public ProjectInfo createProjectForGroup(String projectName, GroupInfo group){
        ProjectInfo projectForGroup = projectService.createProjectForGroup(projectName, group);
        return projectForGroup;
    }

    /**
     * @Description 通过项目id 删除项目
     * @return void
     */
    @RequestMapping(value = "/deleteProjectByprojectId")
    public void deleteProject(String projectId){
        projectService.deleteProject(projectId);
    }
}
