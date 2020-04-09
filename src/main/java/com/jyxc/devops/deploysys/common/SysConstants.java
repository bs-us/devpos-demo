package com.jyxc.devops.deploysys.common;

/**
 * Created with IntelliJ IDEA.
 * Description: 系统常量类
 * User: 蔡伟乐
 * Date: 2019-12-12 14:45:48
 */
public class SysConstants {
    public static final String USER_INFO_NAME="userinfo";
    public static final String TOKEN_NAME="token";

    //正常状态
    public static final Integer STATE_NORMAL = 0;
    //删除状态
    public static final Integer STATE_DELETE = -1;
    //禁用状态
    public static final Integer STATE_DISABLED = 1;

    public class status{
        //登录失败
        public static final int STATUS_LOGIN_FAILED = 10001;
        public static final String STATUS_LOGIN_FAILED_MSG = "用户名或密码错误";
        //操作成功
        public static final int STATUS_OPRATION_SUCCESS = 200;
        public static final String STATUS_OPRATION_SUCCESS_MSG = "操作成功";
        //操作失败
        public static final int STATUS_OPRATION_FAILED = -1;
        public static final String STATUS_OPRATION_FAILED_MSG = "操作失败";
        //需要登录
        public static final int STATUS_NEED_LOGIN = 403;
        public static final String STATUS_NEED_LOGIN_MSG = "禁止访问";
        //程序错误
        public static final int STATUS_ERROR_SYSTEM = 500;
        public static final String STATUS_ERROR_SYSTEM_MSG = "程序内部错误";

        //Jenkins相关
        public static final int STATUS_SUCCESS = 10100;
        public static final String STATUS_SUCCESS_MSG = "成功";

        public static final int STATUS_FAIL = 10101;
        public static final String STATUS_FAIL_MSG = "失败";

        public static final int STATUS_NAME_EXISTS = 10102;
        public static final String STATUS_NAME_EXISTS_MSG = "名称已存在";

        public static final int STATUS_NAME_NOTHING = 10103;
        public static final String STATUS_NAME_NOTHING_MSG = "名称不存在";

        public static final int STATUS_RUNNING = 10104;
        public static final String STATUS_RUNNING_MSG = "正在运行";

        public static final int STATUS_CLOSE = 10105;
        public static final String STATUS_CLOSE_MSG = "已关闭";

        public static final int STATUS_BUILDING = 10106;
        public static final String STATUS_BUILDING_MSG = "构建进行中";

        public static final int STATUS_BUILD_SUCCESS = 10107;
        public static final String STATUS_BUILD_SUCCESS_MSG = "构建结束";



    }

}