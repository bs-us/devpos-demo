package com.jyxc.devops.deploysys.gitlab.user.service.impl;

import com.jyxc.devops.deploysys.BeanConnectionUtil;
import com.jyxc.devops.deploysys.gitlab.user.model.CreateUserRequestInfo;
import com.jyxc.devops.deploysys.gitlab.user.model.GitlabUserInfo;
import com.jyxc.devops.deploysys.gitlab.user.model.SSHKeyInfo;
import com.jyxc.devops.deploysys.gitlab.user.service.UserService;
import org.gitlab.api.GitlabAPI;
import org.gitlab.api.models.GitlabUser;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Authror huiwang
 * @Description TODO
 * @Date 2019/12/24 14:52
 */
@Service
public class UserServiceImpl implements UserService {

    //获取gitlab连接
    private static final GitlabAPI GITLAB_API = (GitlabAPI) BeanConnectionUtil.getBean("gitlabAPI");


    @Override
    public List<GitlabUserInfo> getAllUsers() {
        List<GitlabUserInfo> userInfos = new ArrayList<>();
        List<GitlabUser> users = GITLAB_API.getUsers();
        if(users != null && !users.isEmpty()){
            for (GitlabUser user:users){
                if(user != null){
                    GitlabUserInfo userInfo = new GitlabUserInfo();
                    copyProperties(user,userInfo);
                    userInfos.add(userInfo);
                }
            }
        }

        return userInfos;
    }

    @Override
    public List<GitlabUserInfo> findUsersByEmailOrUsername(String emailOrUsername) {
        List<GitlabUserInfo> userInfos = new ArrayList<>();
        try {
            List<GitlabUser> users = GITLAB_API.findUsers(emailOrUsername);
            if(users != null && !users.isEmpty()) {
                for (GitlabUser user : users) {
                    if (null != user) {
                        GitlabUserInfo userInfo = new GitlabUserInfo();
                        copyProperties(user, userInfo);
                        userInfos.add(userInfo);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userInfos;
    }

    @Override
    public GitlabUserInfo getUser(Integer userId) {
        GitlabUserInfo userInfo = new GitlabUserInfo();
        try {
            GitlabUser user = GITLAB_API.getUser(userId);
            copyProperties(user,userInfo);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userInfo;
    }

    @Override
    public GitlabUserInfo getUserViaSudo(String userName) {
        GitlabUserInfo userInfo = new GitlabUserInfo();
        try {
            GitlabUser userViaSudo = GITLAB_API.getUserViaSudo(userName);
            copyProperties(userViaSudo,userInfo);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userInfo;
    }

    @Override
    public GitlabUserInfo createUser(String email, String password, String username, String fullName, String skypeId, String linkedIn, String twitter, String website_url, Integer projects_limit, String extern_uid, String extern_provider_name, String bio, Boolean isAdmin, Boolean can_create_group, Boolean skip_confirmation, Boolean external) {
        GitlabUserInfo userInfo = new GitlabUserInfo();
        try {
            GitlabUser user = GITLAB_API.createUser(email, password, username, fullName, skypeId, linkedIn, twitter, website_url, projects_limit, extern_uid, extern_provider_name, bio, isAdmin, can_create_group, skip_confirmation, external);
            copyProperties(user,userInfo);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return userInfo;
    }

    @Override
    public GitlabUserInfo createUser(CreateUserRequestInfo request) {
        GitlabUserInfo userInfo = new GitlabUserInfo();
//        CreateUserRequest createUserRequest = new CreateUserRequest();
//        try {
//            GitlabUser user = gitlabAPI.createUser(createUserRequest);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        return userInfo;
    }

    @Override
    public GitlabUserInfo updateUser(Integer targetUserId, String email, String password, String username, String fullName, String skypeId, String linkedIn, String twitter, String website_url, Integer projects_limit, String extern_uid, String extern_provider_name, String bio, Boolean isAdmin, Boolean can_create_group, Boolean external) {
        return null;
    }

    @Override
    public void blockUser(Integer targetUserId) {
        try {
            GITLAB_API.blockUser(targetUserId);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void unblockUser(Integer targetUserId) {
        try {
            GITLAB_API.unblockUser(targetUserId);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteUser(Integer targetUserId) {
        try {
            GITLAB_API.deleteUser(targetUserId);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public SSHKeyInfo createSSHKey(Integer targetUserId, String title, String key) {
        return null;
    }

    @Override
    public SSHKeyInfo createSSHKey(String title, String key) {
        return null;
    }

    @Override
    public void deleteSSHKey(Integer targetUserId, Integer targetKeyId) {

    }

    @Override
    public List<SSHKeyInfo> getSSHKeys(Integer targetUserId) {
        return null;
    }

    @Override
    public SSHKeyInfo getSSHKey(Integer keyId) {
        return null;
    }

    private void copyProperties(GitlabUser user,GitlabUserInfo userInfo){
        userInfo.setId(user.getId());
        userInfo.setUsername(user.getUsername());
        userInfo.setEmail(user.getEmail());
        userInfo.setName(user.getName());
        userInfo.setTwitter(user.getTwitter());
        userInfo.setState(user.getState());
        userInfo.setSkype(user.getSkype());
    }
}
