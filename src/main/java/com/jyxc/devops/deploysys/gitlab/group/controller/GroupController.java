package com.jyxc.devops.deploysys.gitlab.group.controller;

import com.jyxc.devops.deploysys.gitlab.group.model.GroupInfo;
import com.jyxc.devops.deploysys.gitlab.group.service.GroupService;
import com.jyxc.devops.deploysys.gitlab.project.model.ProjectInfo;
import org.gitlab.api.models.GitlabGroupMember;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Authror huiwang
 * @Description TODO
 * @Date 2020/2/26 13:50
 */
@RestController
@RequestMapping("/group")
public class GroupController {

    @Resource
    private GroupService groupService;

    /**
     * @Description 通过组id获取组信息
     * @return com.jyxc.devops.gitlab.group.model.GroupInfo
     */
    @RequestMapping(value = "/getGroupByGroupId")
    public GroupInfo getGroup(Integer groupId){
        GroupInfo group = groupService.getGroup(groupId);
        return group;
    }

    /**
     * @Description 通过路径获取组信息
     * @return com.jyxc.devops.gitlab.group.model.GroupInfo
     */
    @RequestMapping(value = "/getGroupByPath")
    public GroupInfo getGroup(String path){
        GroupInfo group = groupService.getGroup(path);
        return group;
    }

    /**
     * @Description 获取所有的组信息
     * @return java.util.List<com.jyxc.devops.gitlab.group.model.GroupInfo>
     */
    @RequestMapping(value = "/getAllGroups")
    public List<GroupInfo> getGroups(){
        List<GroupInfo> groups = groupService.getGroups();
        return groups;
                
    }

    /**
     * @Description //TODO
     * @return java.util.List<com.jyxc.devops.gitlab.group.model.GroupInfo>
     */
    @RequestMapping(value = "/getGroupsViaSudo")
    public List<GroupInfo> getGroupsViaSudo(String username, org.gitlab.api.Pagination pagination){
        List<GroupInfo> groupsViaSudo = groupService.getGroupsViaSudo(username, pagination);
        return groupsViaSudo;
    }

    /**
     * @Description 获取当前组下的项目信息
     * @return java.util.List<com.jyxc.devops.gitlab.project.model.ProjectInfo>
     */
    @RequestMapping(value = "/getGroupProjectsByGroup")
    public List<ProjectInfo> getGroupProjects(GroupInfo group){
        List<ProjectInfo> groupProjects = groupService.getGroupProjects(group);
        return groupProjects;
    }

    /**
     * @Description 通过组id获取当前组下的项目信息
     * @return java.util.List<com.jyxc.devops.gitlab.project.model.ProjectInfo>
     */
    @RequestMapping(value = "/getGroupProjectsByGroupId")
    public List<ProjectInfo> getGroupProjects(Integer groupId){
        List<ProjectInfo> groupProjects = groupService.getGroupProjects(groupId);
        return groupProjects;
    }

    /**
     * @Description //TODO
     * @return java.util.List<org.gitlab.api.models.GitlabGroupMember>
     */
    @RequestMapping(value = "/getGroupMemberByGroup")
    public List<org.gitlab.api.models.GitlabGroupMember> getGroupMembers(org.gitlab.api.models.GitlabGroup group){
        List<GitlabGroupMember> groupMembers = groupService.getGroupMembers(group);
        return groupMembers;
    }

    /**
     * @Description //TODO
     * @return java.util.List<org.gitlab.api.models.GitlabGroupMember>
     */
    @RequestMapping(value = "/getGroupMemberByGroupId")
    public List<org.gitlab.api.models.GitlabGroupMember> getGroupMembers(Integer groupId){
        List<GitlabGroupMember> groupMembers = groupService.getGroupMembers(groupId);
        return groupMembers;
    }

    /**
     * @Description 创建组
     * @return com.jyxc.devops.gitlab.group.model.GroupInfo
     */
    @RequestMapping(value = "/createGroupByGroupName")
    public GroupInfo createGroup(String groupName){
        GroupInfo group = groupService.createGroup(groupName);
        return group;
    }

    /**
     * @Description 创建组
     * @return com.jyxc.devops.gitlab.group.model.GroupInfo
     */
//    @RequestMapping(value = "/createGroupByGroupNameAndPath")
//    public GroupInfo createGroup(String groupName, String path){
//        GroupInfo group = groupService.createGroup(groupName, path);
//        return group;
//    }

    /**
     * @Description 创建组
     * @return com.jyxc.devops.gitlab.group.model.GroupInfo
     */
//    @RequestMapping(value = "/createGroupViaSudo")
//    public GroupInfo createGroupViaSudo(String name, String path, GitlabUserInfo sudoUser){
//        return groupService.createGroupViaSudo(name,path,sudoUser);
//    }

    /**
     * @Description 创建组
     * @return com.jyxc.devops.gitlab.group.model.GroupInfo
     */
//    @RequestMapping(value = "/createGroup")
//    public GroupInfo createGroup(String name, String path, String ldapCn, org.gitlab.api.models.GitlabAccessLevel ldapAccess){
//        GroupInfo group = groupService.createGroup(name, path, ldapCn, ldapAccess);
//        return group;
//    }

    /**
     * @Description 创建组
     * @return com.jyxc.devops.gitlab.group.model.GroupInfo
     */
//    @RequestMapping(value = "/createGroupByGroupInfo")
//    public GroupInfo createGroup(org.gitlab.api.models.CreateGroupRequest request, GitlabUserInfo sudoUser){
//        GroupInfo group = groupService.createGroup(request, sudoUser);
//        return group;
//    }

    /**
     * @Description 创建组
     * @return com.jyxc.devops.gitlab.group.model.GroupInfo
     */
//    @RequestMapping(value = "/createGroupByInfo")
//    public GroupInfo createGroup(String name, String path, String ldapCn, org.gitlab.api.models.GitlabAccessLevel ldapAccess, GitlabUserInfo sudoUser){
//        GroupInfo group = groupService.createGroup(name, path, ldapCn, ldapAccess, sudoUser);
//        return group;
//    }

    /**
     * @Description 创建组
     * @return com.jyxc.devops.gitlab.group.model.GroupInfo
     */
//    @RequestMapping(value = "/createGroupByParentId")
//    public GroupInfo createGroup(String name, String path, String ldapCn, org.gitlab.api.models.GitlabAccessLevel ldapAccess, GitlabUserInfo sudoUser, Integer parentId){
//        GroupInfo group = groupService.createGroup(name, path, ldapCn, ldapAccess, sudoUser, parentId);
//        return group;
//    }

    /**
     * @Description 创建组
     * @return com.jyxc.devops.gitlab.group.model.GroupInfo
     */
//    @RequestMapping("/createGroupByGroup")
//    public GroupInfo createGroup(GroupInfo group, GitlabUserInfo sudoUser){
//        GroupInfo groupInfo = groupService.createGroup(group, sudoUser);
//        return groupInfo;
//    }

    /**
     * @Description 修改组
     * @return com.jyxc.devops.gitlab.group.model.GroupInfo
     */
//    @RequestMapping(value = "/updateGroup")
//    public GroupInfo updateGroup(GroupInfo group, GitlabUserInfo sudoUser){
//        GroupInfo groupInfo = groupService.updateGroup(group, sudoUser);
//        return groupInfo;
//    }

    /**
     * @Description //TODO
     * @return org.gitlab.api.models.GitlabGroupMember
     */
//    @RequestMapping(value = "/addGroupByGroup")
//    public org.gitlab.api.models.GitlabGroupMember addGroupMember(GroupInfo group, GitlabUserInfo user, org.gitlab.api.models.GitlabAccessLevel accessLevel){
//        GitlabGroupMember gitlabGroupMember = groupService.addGroupMember(group, user, accessLevel);
//        return gitlabGroupMember;
//    }

    /**
     * @Description //TODO
     * @return org.gitlab.api.models.GitlabGroupMember
     */
//    @RequestMapping(value = "/addGroupByGroupId")
//    public org.gitlab.api.models.GitlabGroupMember addGroupMember(Integer groupId, Integer userId, org.gitlab.api.models.GitlabAccessLevel accessLevel){
//        GitlabGroupMember gitlabGroupMember = groupService.addGroupMember(groupId, userId, accessLevel);
//        return gitlabGroupMember;
//    }

    /**
     * @Description //TODO
     * @return void
     */
//    @RequestMapping(value = "/deleteGroupByGroup")
//    public void deleteGroupMember(GroupInfo group, GitlabUserInfo user){
//        groupService.deleteGroupMember(group, user);
//    }

    /**
     * @Description //TODO
     * @return void
     */
    @RequestMapping(value = "/deleteGroupByGroupId")
    public void deleteGroupMember(Integer groupId, Integer userId){
        groupService.deleteGroupMember(groupId, userId);
    }

    /**
     * @Description 删除组
     * @return void
     */
    @RequestMapping(value = "/deleteGroup")
    public void deleteGroup(Integer groupId){
        groupService.deleteGroup(groupId);
    }
}
