package com.jyxc.devops.deploysys.gitlab.project.service.impl;

import com.jyxc.devops.deploysys.BeanConnectionUtil;
import com.jyxc.devops.deploysys.gitlab.group.model.GroupInfo;
import com.jyxc.devops.deploysys.gitlab.project.model.ProjectInfo;
import com.jyxc.devops.deploysys.gitlab.project.service.ProjectService;
import org.gitlab.api.GitlabAPI;
import org.gitlab.api.models.GitlabGroup;
import org.gitlab.api.models.GitlabProject;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Authror huiwang
 * @Description TODO
 * @Date 2020/1/3 15:15
 */
@Service
public class ProjectServiceImpl implements ProjectService {

    //获取gitlab连接
    private static final GitlabAPI GITLAB_API = (GitlabAPI) BeanConnectionUtil.getBean("gitlabAPI");


    @Override
    public List<ProjectInfo> getAllProjects() {
        List<GitlabProject> allProjects = GITLAB_API.getAllProjects();
        List<ProjectInfo> projectInfos = new ArrayList<>();
        for (GitlabProject project:allProjects){
            ProjectInfo projectInfo = new ProjectInfo();
            copyProperties(project,projectInfo);
            projectInfos.add(projectInfo);
        }
        return projectInfos;
    }

    @Override
    public ProjectInfo createProject(String projectName) {
        try {
            GitlabProject project = GITLAB_API.createProject(projectName);
            ProjectInfo projectInfo = new ProjectInfo();
            copyProperties(project,projectInfo);
            return projectInfo;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ProjectInfo createProject(ProjectInfo projectInfo) {
        GitlabProject gitlabProject = new GitlabProject();
        try {
            GitlabProject gitlabApiProject = GITLAB_API.createProject(gitlabProject);
            ProjectInfo project = new ProjectInfo();
            copyProperties(gitlabApiProject,project);
            return projectInfo;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ProjectInfo createProjectForGroup(String projectName, GroupInfo group) {
        GitlabGroup gitlabGroup = new GitlabGroup();
        gitlabGroup.setId(group.getId());
        gitlabGroup.setName(group.getName());
        gitlabGroup.setPath(group.getPath());
        gitlabGroup.setDescription(group.getDescription());
        try {
            GitlabProject projectForGroup = GITLAB_API.createProjectForGroup(projectName, gitlabGroup);
            ProjectInfo projectInfo = new ProjectInfo();
            copyProperties(projectForGroup,projectInfo);
            return projectInfo;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void deleteProject(String projectId) {
        try {
            GITLAB_API.deleteProject(projectId);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void copyProperties(GitlabProject project,ProjectInfo projectInfo){
        if(project.getId() != null){
            projectInfo.setId(project.getId());
            projectInfo.setName(project.getName());
            projectInfo.setNameWithNamespace(project.getNameWithNamespace());
            projectInfo.setDescription(project.getDescription());
            projectInfo.setDefaultBranch(project.getDefaultBranch());
            //projectInfo.setOwner(project.getOwner());
            projectInfo.setPublicProject(project.isPublic());
            projectInfo.setPathWithNamespace(project.getPathWithNamespace());
        }

    }
}
