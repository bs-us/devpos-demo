package com.jyxc.devops.deploysys.gitlab.group.service.impl;

import com.jyxc.devops.deploysys.BeanConnectionUtil;
import com.jyxc.devops.deploysys.gitlab.group.model.GroupInfo;
import com.jyxc.devops.deploysys.gitlab.group.service.GroupService;
import com.jyxc.devops.deploysys.gitlab.project.model.ProjectInfo;
import com.jyxc.devops.deploysys.gitlab.user.model.GitlabUserInfo;
import org.gitlab.api.GitlabAPI;
import org.gitlab.api.Pagination;
import org.gitlab.api.models.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Authror huiwang
 * @Description TODO
 * @Date 2020/1/6 15:25
 */
@Service
public class GroupServiceImpl implements GroupService {

    //获取gitlab连接
    private static final GitlabAPI GITLAB_API = (GitlabAPI) BeanConnectionUtil.getBean("gitlabAPI");


    @Override
    public GroupInfo getGroup(Integer groupId) {
        GroupInfo groupInfo = new GroupInfo();
        try {
            GitlabGroup group = GITLAB_API.getGroup(groupId);
            copyProperties(group,groupInfo);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return groupInfo;
    }

    @Override
    public GroupInfo getGroup(String path) {
        GroupInfo groupInfo = new GroupInfo();
        try {
            GitlabGroup group = GITLAB_API.getGroup(path);
            copyProperties(group,groupInfo);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return groupInfo;
    }

    @Override
    public List<GroupInfo> getGroups() {
        List<GroupInfo> groupInfos = new ArrayList<>();
        try {
            List<GitlabGroup> groups = GITLAB_API.getGroups();
            if(groups != null && !groups.isEmpty()) {
                for (GitlabGroup group : groups) {
                    GroupInfo groupInfo = new GroupInfo();
                    copyProperties(group, groupInfo);
                    groupInfos.add(groupInfo);

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return groupInfos;
    }

    @Override
    public List<GroupInfo> getGroupsViaSudo(String username, Pagination pagination) {
        List<GroupInfo> groupInfos = new ArrayList<>();
        try {
            List<GitlabGroup> groupsViaSudo = GITLAB_API.getGroupsViaSudo(username, pagination);
            if(groupsViaSudo != null && !groupsViaSudo.isEmpty()) {
                for (GitlabGroup group : groupsViaSudo) {
                    GroupInfo groupInfo = new GroupInfo();
                    copyProperties(group, groupInfo);
                    groupInfos.add(groupInfo);

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return groupInfos;
    }

    @Override
    public List<ProjectInfo> getGroupProjects(GroupInfo group) {
        GitlabGroup gitlabGroup = new GitlabGroup();
        gitlabGroup.setId(group.getId());
        gitlabGroup.setName(group.getName());
        gitlabGroup.setDescription(group.getDescription());
        gitlabGroup.setPath(group.getPath());
        List<GitlabProject> groupProjects = GITLAB_API.getGroupProjects(gitlabGroup);
        List<ProjectInfo> projectInfos = new ArrayList<>();
        if(groupProjects != null && !groupProjects.isEmpty()) {
            for (GitlabProject project : groupProjects) {
                ProjectInfo projectInfo = new ProjectInfo();
                copyProperties(project, projectInfo);
                projectInfos.add(projectInfo);

            }
        }
        return projectInfos;
    }

    @Override
    public List<ProjectInfo> getGroupProjects(Integer groupId) {
        List<GitlabProject> groupProjects = GITLAB_API.getGroupProjects(groupId);
        List<ProjectInfo> projectInfos = new ArrayList<>();
        if(groupProjects != null && !groupProjects.isEmpty()) {
            for (GitlabProject project : groupProjects) {
                ProjectInfo projectInfo = new ProjectInfo();
                copyProperties(project, projectInfo);
                projectInfos.add(projectInfo);

            }
        }
        return projectInfos;
    }

    @Override
    public List<GitlabGroupMember> getGroupMembers(GitlabGroup group) {
        List<GitlabGroupMember> groupMembers = GITLAB_API.getGroupMembers(group);
        return groupMembers;
    }

    @Override
    public List<GitlabGroupMember> getGroupMembers(Integer groupId) {

        return null;
    }

    @Override
    public GroupInfo createGroup(String groupName) {
        GroupInfo groupInfo = new GroupInfo();
        try {
            GitlabGroup group = GITLAB_API.createGroup(groupName);
            copyProperties(group,groupInfo);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return groupInfo;
    }

    @Override
    public GroupInfo createGroup(String groupName, String path) {
        return null;
    }

    @Override
    public GroupInfo createGroupViaSudo(String name, String path, GitlabUserInfo sudoUser) {
        return null;
    }

    @Override
    public GroupInfo createGroup(String name, String path, String ldapCn, GitlabAccessLevel ldapAccess) {
        return null;
    }

    @Override
    public GroupInfo createGroup(CreateGroupRequest request, GitlabUserInfo sudoUser) {
        return null;
    }

    @Override
    public GroupInfo createGroup(String name, String path, String ldapCn, GitlabAccessLevel ldapAccess, GitlabUserInfo sudoUser) {
        return null;
    }

    @Override
    public GroupInfo createGroup(String name, String path, String ldapCn, GitlabAccessLevel ldapAccess, GitlabUserInfo sudoUser, Integer parentId) {
        return null;
    }

    @Override
    public GroupInfo createGroup(GroupInfo group, GitlabUserInfo sudoUser) {
        return null;
    }

    @Override
    public GroupInfo updateGroup(GroupInfo group, GitlabUserInfo sudoUser) {
        return null;
    }

    @Override
    public GitlabGroupMember addGroupMember(GroupInfo group, GitlabUserInfo user, GitlabAccessLevel accessLevel) {
        return null;
    }

    @Override
    public GitlabGroupMember addGroupMember(Integer groupId, Integer userId, GitlabAccessLevel accessLevel) {
        return null;
    }

    @Override
    public void deleteGroupMember(GroupInfo group, GitlabUserInfo user) {

    }

    @Override
    public void deleteGroupMember(Integer groupId, Integer userId) {
        try {
            GITLAB_API.deleteGroupMember(groupId,userId);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void deleteGroup(Integer groupId) {
        try {
            GITLAB_API.deleteGroup(groupId);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void copyProperties(GitlabGroup group,GroupInfo groupInfo){
        if(null != group.getId()){
          groupInfo.setId(group.getId());
          groupInfo.setName(group.getName());
          groupInfo.setDescription(group.getDescription());
          groupInfo.setPath(group.getPath());
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
        }

    }
}
