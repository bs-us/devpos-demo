package com.jyxc.devops.deploysys.gitlab.user.service;

import com.jyxc.devops.deploysys.gitlab.user.model.CreateUserRequestInfo;
import com.jyxc.devops.deploysys.gitlab.user.model.GitlabUserInfo;
import com.jyxc.devops.deploysys.gitlab.user.model.SSHKeyInfo;

import java.util.List;

/**
 * @Authror huiwang
 * @Description 用户的相关操作
 * @Date 2019/12/24 9:58
 */
public interface  UserService {

    /**
     * @Description 获取所有用户信息
     * @return com.jyxc.devops.common.ResultData
     */
    public List<GitlabUserInfo> getAllUsers();

    /**
     * @Description 获取用户信息 by emailOrUsername
     * @return com.jyxc.devops.common.ResultData
     */
    public List<GitlabUserInfo> findUsersByEmailOrUsername(String emailOrUsername);

    /**
     * @Description 获取用户信息 by 用户id
     * @return com.jyxc.devops.common.ResultData
     */
    public GitlabUserInfo getUser(Integer userId);

    /**
     * @Description
     * @return com.jyxc.devops.common.ResultData
     */
    public GitlabUserInfo getUserViaSudo(String userName);

    /**
     * @Description 创建用户
     * @return com.jyxc.devops.gitlab.project.model.GitlabUserInfo
     */
    public GitlabUserInfo createUser(String email,
                                     String password,
                                     String username,
                                     String fullName,
                                     String skypeId,
                                     String linkedIn,
                                     String twitter,
                                     String website_url,
                                     Integer projects_limit,
                                     String extern_uid,
                                     String extern_provider_name,
                                     String bio,
                                     Boolean isAdmin,
                                     Boolean can_create_group,
                                     Boolean skip_confirmation,
                                     Boolean external);

    /**
     * @Description 创建用户
     * @return com.jyxc.devops.gitlab.project.model.GitlabUserInfo
     */
    public GitlabUserInfo createUser(CreateUserRequestInfo request);

    /**
     * @Description 修改用户
     * @return com.jyxc.devops.gitlab.project.model.GitlabUserInfo
     */
    public GitlabUserInfo updateUser(Integer targetUserId,
                                     String email,
                                     String password,
                                     String username,
                                     String fullName,
                                     String skypeId,
                                     String linkedIn,
                                     String twitter,
                                     String website_url,
                                     Integer projects_limit,
                                     String extern_uid,
                                     String extern_provider_name,
                                     String bio,
                                     Boolean isAdmin,
                                     Boolean can_create_group,
                                     Boolean external);

    /**
     * @Description 禁用此用户
     * @return void
     */
    public void blockUser(Integer targetUserId);

    /**
     * @Description 解除禁用的用户
     * @return void
     */
    public void unblockUser(Integer targetUserId);

    /**
     * @Description 删除用户
     * @return void
     */
    public void deleteUser(Integer targetUserId);

    public SSHKeyInfo createSSHKey(Integer targetUserId, String title, String key);

    public SSHKeyInfo createSSHKey(String title, String key);

    public void deleteSSHKey(Integer targetUserId, Integer targetKeyId);

    public List<SSHKeyInfo> getSSHKeys(Integer targetUserId);

    public SSHKeyInfo getSSHKey(Integer keyId);


}
