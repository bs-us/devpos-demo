package com.jyxc.devops.deploysys.gitlab.user.controller;

import com.jyxc.devops.deploysys.gitlab.user.model.CreateUserRequestInfo;
import com.jyxc.devops.deploysys.gitlab.user.model.GitlabUserInfo;
import com.jyxc.devops.deploysys.gitlab.user.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Authror huiwang
 * @Description TODO
 * @Date 2019/12/25 9:30
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Resource
    private UserService userService;

    /**
     * @Description 获取所有用户信息
     * @return com.jyxc.devops.common.ResultData
     */
    @RequestMapping("/allUsers")
    public List<GitlabUserInfo> getAllUsers(){
        List<GitlabUserInfo> allUsers = userService.getAllUsers();
//        if(StringUtils.isEmpty(allUsers)){
//            //返回状态码+msg+result
//            ResultData resultData = new ResultData(SysConstants.status.STATUS_FAIL,
//                    SysConstants.status.STATUS_FAIL_MSG,
//                    allUsers);
//            return resultData;
//        }else{
//            //返回状态码+msg+result
//            ResultData resultData = new ResultData(SysConstants.status.STATUS_SUCCESS,
//                    SysConstants.status.STATUS_SUCCESS_MSG,
//                    allUsers);
//            return resultData;
//        }
        return allUsers;
    }

    /**
     * @Description 获取用户信息 by emailOrUsername
     * @return com.jyxc.devops.common.ResultData
     */
    @RequestMapping("/findUsers")
    public List<GitlabUserInfo> findUsersByEmailOrUsername(String emailOrUsername){
        List<GitlabUserInfo> users = userService.findUsersByEmailOrUsername(emailOrUsername);
//        if(StringUtils.isEmpty(users)){
//            ResultData resultData = new ResultData(SysConstants.status.STATUS_FAIL,
//                    SysConstants.status.STATUS_FAIL_MSG,
//                    users);
//            return resultData;
//        }else{
//            //返回状态码+msg+result
//            ResultData resultData = new ResultData(SysConstants.status.STATUS_SUCCESS,
//                    SysConstants.status.STATUS_SUCCESS_MSG,
//                    users);
//            return resultData;
//        }
        return users;
    }

    /**
     * @Description 获取用户信息 by 用户id
     * @return com.jyxc.devops.common.ResultData
     */
    @RequestMapping("/userById")
    public GitlabUserInfo getUser(Integer userId){
        GitlabUserInfo user = userService.getUser(userId);
//        if(StringUtils.isEmpty(user)){
//            //返回状态码+msg+result
//            ResultData resultData = new ResultData(SysConstants.status.STATUS_FAIL,
//                    SysConstants.status.STATUS_FAIL_MSG,
//                    user);
//            return resultData;
//        }else{
//            //返回状态码+msg+result
//            ResultData resultData = new ResultData(SysConstants.status.STATUS_SUCCESS,
//                    SysConstants.status.STATUS_SUCCESS_MSG,
//                    user);
//            return resultData;
//        }
        return user;
    }

    /**
     * @Description
     * @return com.jyxc.devops.common.ResultData
     */
    @RequestMapping("/userByName")
    public GitlabUserInfo getUserViaSudo(String userName){
        GitlabUserInfo userViaSudo = userService.getUserViaSudo(userName);
//        if(StringUtils.isEmpty(userViaSudo)){
//            //返回状态码+msg+result
//            ResultData resultData = new ResultData(SysConstants.status.STATUS_FAIL,
//                    SysConstants.status.STATUS_FAIL_MSG,
//                    userViaSudo);
//            return resultData;
//        }else{
//            //返回状态码+msg+result
//            ResultData resultData = new ResultData(SysConstants.status.STATUS_SUCCESS,
//                    SysConstants.status.STATUS_SUCCESS_MSG,
//                    userViaSudo);
//            return resultData;
//        }
        return userViaSudo;
    }

    /**
     * @Description 创建用户
     * @return com.jyxc.devops.gitlab.project.model.GitlabUserInfo
     */
    @RequestMapping("/create")
    public GitlabUserInfo createUser(CreateUserRequestInfo request){
        GitlabUserInfo user = userService.createUser(request);
//        if(StringUtils.isEmpty(user)){
//            //返回状态码+msg+result
//            ResultData resultData = new ResultData(SysConstants.status.STATUS_FAIL,
//                    SysConstants.status.STATUS_FAIL_MSG,
//                    user);
//            return resultData;
//        }else{
//            //返回状态码+msg+result
//            ResultData resultData = new ResultData(SysConstants.status.STATUS_SUCCESS,
//                    SysConstants.status.STATUS_SUCCESS_MSG,
//                    user);
//            return resultData;
//        }
        return user;
    }

    /**
     * @Description 禁用此用户
     * @return void
     */
    @RequestMapping("/block")
    public void blockUser(Integer targetUserId){
        userService.blockUser(targetUserId);
    }

    /**
     * @Description 解除禁用的用户
     * @return void
     */
    @RequestMapping("/unblock")
    public void unblockUser(Integer targetUserId){
        userService.unblockUser(targetUserId);
    }

    /**
     * @Description 删除用户
     * @return void
     */
    @RequestMapping("/delete")
    public void deleteUser(Integer targetUserId){
        userService.deleteUser(targetUserId);
    }



}
