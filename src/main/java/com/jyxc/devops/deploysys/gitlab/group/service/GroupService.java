package com.jyxc.devops.deploysys.gitlab.group.service;

import com.jyxc.devops.deploysys.gitlab.group.model.GroupInfo;
import com.jyxc.devops.deploysys.gitlab.project.model.ProjectInfo;
import com.jyxc.devops.deploysys.gitlab.user.model.GitlabUserInfo;

import java.util.List;

/**
 * @Authror huiwang
 * @Description group 的相关操作
 * @Date 2019/12/24 10:10
 */
public interface GroupService {

    /**
     * @Description 通过组id获取组信息
     * @return com.jyxc.devops.gitlab.group.model.GroupInfo
     */
    public GroupInfo getGroup(Integer groupId);

    /**
     * @Description 通过路径获取组信息
     * @return com.jyxc.devops.gitlab.group.model.GroupInfo
     */
    public GroupInfo getGroup(String path);

    /**
     * @Description 获取所有的组信息
     * @return java.util.List<com.jyxc.devops.gitlab.group.model.GroupInfo>
     */
    public List<GroupInfo> getGroups();

    /**
     * @Description //TODO
     * @return java.util.List<com.jyxc.devops.gitlab.group.model.GroupInfo>
     */
    public List<GroupInfo> getGroupsViaSudo(String username, org.gitlab.api.Pagination pagination);

    /**
     * @Description 获取当前组下的项目信息
     * @return java.util.List<com.jyxc.devops.gitlab.project.model.ProjectInfo>
     */
    public List<ProjectInfo> getGroupProjects(GroupInfo group);

    /**
     * @Description 通过组id获取当前组下的项目信息
     * @return java.util.List<com.jyxc.devops.gitlab.project.model.ProjectInfo>
     */
    public List<ProjectInfo> getGroupProjects(Integer groupId);

    /**
     * @Description //TODO
     * @return java.util.List<org.gitlab.api.models.GitlabGroupMember>
     */
    public List<org.gitlab.api.models.GitlabGroupMember> getGroupMembers(org.gitlab.api.models.GitlabGroup group);

    /**
     * @Description //TODO
     * @return java.util.List<org.gitlab.api.models.GitlabGroupMember>
     */
    public List<org.gitlab.api.models.GitlabGroupMember> getGroupMembers(Integer groupId);

    /**
     * @Description 创建组
     * @return com.jyxc.devops.gitlab.group.model.GroupInfo
     */
    public GroupInfo createGroup(String groupName);

    /**
     * @Description 创建组
     * @return com.jyxc.devops.gitlab.group.model.GroupInfo
     */
    public GroupInfo createGroup(String groupName, String path);

    /**
     * @Description 创建组
     * @return com.jyxc.devops.gitlab.group.model.GroupInfo
     */
    public GroupInfo createGroupViaSudo(String name, String path, GitlabUserInfo sudoUser);

    /**
     * @Description 创建组
     * @return com.jyxc.devops.gitlab.group.model.GroupInfo
     */
    public GroupInfo createGroup(String name, String path, String ldapCn, org.gitlab.api.models.GitlabAccessLevel ldapAccess);

    /**
     * @Description 创建组
     * @return com.jyxc.devops.gitlab.group.model.GroupInfo
     */
    public GroupInfo createGroup(org.gitlab.api.models.CreateGroupRequest request, GitlabUserInfo sudoUser);

    /**
     * @Description 创建组
     * @return com.jyxc.devops.gitlab.group.model.GroupInfo
     */
    public GroupInfo createGroup(String name, String path, String ldapCn, org.gitlab.api.models.GitlabAccessLevel ldapAccess, GitlabUserInfo sudoUser);

    /**
     * @Description 创建组
     * @return com.jyxc.devops.gitlab.group.model.GroupInfo
     */
    public GroupInfo createGroup(String name, String path, String ldapCn, org.gitlab.api.models.GitlabAccessLevel ldapAccess, GitlabUserInfo sudoUser, Integer parentId);

    /**
     * @Description 创建组
     * @return com.jyxc.devops.gitlab.group.model.GroupInfo
     */
    public GroupInfo createGroup(GroupInfo group, GitlabUserInfo sudoUser);

    /**
     * @Description 修改组
     * @return com.jyxc.devops.gitlab.group.model.GroupInfo
     */
    public GroupInfo updateGroup(GroupInfo group, GitlabUserInfo sudoUser);

    /**
     * @Description //TODO
     * @return org.gitlab.api.models.GitlabGroupMember
     */
    public org.gitlab.api.models.GitlabGroupMember addGroupMember(GroupInfo group, GitlabUserInfo user, org.gitlab.api.models.GitlabAccessLevel accessLevel);

    /**
     * @Description //TODO
     * @return org.gitlab.api.models.GitlabGroupMember
     */
    public org.gitlab.api.models.GitlabGroupMember addGroupMember(Integer groupId, Integer userId, org.gitlab.api.models.GitlabAccessLevel accessLevel);

    /**
     * @Description //TODO
     * @return void
     */
    public void deleteGroupMember(GroupInfo group, GitlabUserInfo user);

    /**
     * @Description //TODO
     * @return void
     */
    public void deleteGroupMember(Integer groupId, Integer userId);

    /**
     * @Description 删除组
     * @return void
     */
    public void deleteGroup(Integer groupId);
}
