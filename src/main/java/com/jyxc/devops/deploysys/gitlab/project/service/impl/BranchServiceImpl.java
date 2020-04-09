package com.jyxc.devops.deploysys.gitlab.project.service.impl;

import com.jyxc.devops.deploysys.BeanConnectionUtil;
import com.jyxc.devops.deploysys.gitlab.project.model.BranchInfo;
import com.jyxc.devops.deploysys.gitlab.project.model.ProjectInfo;
import com.jyxc.devops.deploysys.gitlab.project.service.BranchService;
import org.gitlab.api.GitlabAPI;
import org.gitlab.api.models.GitlabBranch;
import org.gitlab.api.models.GitlabProject;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Authror huiwang
 * @Description branch (分支) 的相关操作
 * @Date 2020/1/3 18:47
 */
@Service
public class BranchServiceImpl implements BranchService {


    //获取gitlab连接
    private static final GitlabAPI GITLAB_API = (GitlabAPI) BeanConnectionUtil.getBean("gitlabAPI");


    @Override
    public List<BranchInfo> getAllBranchesByProject(ProjectInfo project) {
        GitlabProject gitlabProject = new GitlabProject();
        copyProjectInfo(gitlabProject,project);
        List<GitlabBranch> branches = GITLAB_API.getBranches(gitlabProject);
        List<BranchInfo> branchInfos = new ArrayList<>();
        if(branches != null && !branches.isEmpty()) {
            for (GitlabBranch branche : branches) {
                BranchInfo branchInfo = new BranchInfo();
                copyBranchInfo(branche, branchInfo);
                branchInfos.add(branchInfo);

            }
        }
        return branchInfos;

    }

    @Override
    public List<BranchInfo> getAllBranchesByProjectId(Integer projectId) {
        List<GitlabBranch> branches = GITLAB_API.getBranches(projectId);
        List<BranchInfo> branchInfos = new ArrayList<BranchInfo>();
        if(branches != null && !branches.isEmpty()) {
            for (GitlabBranch branche : branches) {
                BranchInfo branchInfo = new BranchInfo();
                copyBranchInfo(branche, branchInfo);
                branchInfos.add(branchInfo);

            }
        }
        return branchInfos;
    }

    @Override
    public void createBranchByProject(ProjectInfo project, String branchName, String ref) {
        GitlabProject gitlabProject = new GitlabProject();
        copyProjectInfo(gitlabProject,project);
        try {
            GITLAB_API.createBranch(gitlabProject,branchName,ref);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void createBranchByProjectId(Integer projectId, String branchName, String ref) {
        try {
            GITLAB_API.createBranch(projectId,branchName,ref);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteBranch(Integer projectId, String branchName) {
        try {
            GITLAB_API.deleteBranch(projectId,branchName);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public BranchInfo getBranchByProject(ProjectInfo project, String branchName) {
        GitlabProject gitlabProject = new GitlabProject();
        copyProjectInfo(gitlabProject,project);
        BranchInfo branchInfo = new BranchInfo();
        try {
            GitlabBranch branch = GITLAB_API.getBranch(gitlabProject, branchName);
            copyBranchInfo(branch,branchInfo);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return branchInfo;
    }

    @Override
    public BranchInfo getBranchByProjectId(Integer projectId, String branchName) {
        BranchInfo branchInfo = new BranchInfo();
        try {
            GitlabBranch branch = GITLAB_API.getBranch(projectId, branchName);
            copyBranchInfo(branch,branchInfo);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return branchInfo;
    }

    private void copyProjectInfo(GitlabProject projectInfo, ProjectInfo project){
        if(StringUtils.isEmpty(project)){
            projectInfo.setId(project.getId());
            projectInfo.setName(project.getName());
            projectInfo.setNameWithNamespace(project.getNameWithNamespace());
            projectInfo.setDescription(project.getDescription());
            projectInfo.setDefaultBranch(project.getDefaultBranch());
            //projectInfo.setOwner(project.getOwner());
            projectInfo.setPublic(project.getPublicProject());
        }

    }

    private void copyBranchInfo(GitlabBranch gitlabBranch, BranchInfo branchInfo){
        if(null != gitlabBranch.getName()){
            branchInfo.setName(gitlabBranch.getName());

            //branchInfo.setCommit(gitlabBranch.getCommit());

        }

    }
}
