package com.jyxc.devops.deploysys.common;

/**
 * Created with IntelliJ IDEA.
 * Description: 返回结果格式，非特殊情况都按照此类返回
 * User: 蔡伟乐
 * Date: 2019-12-12 14:45:26
 */
public class ResultData {
    //业务状态码
    int status;
    //业务提示信息
    String msg;
    //返回结果
    Object result;
    public ResultData(){}
    public ResultData(int status, String msg, Object result){
        this.status = status;
        this.msg = msg;
        this.result = result;
    }
    public static ResultData success(){
        return new ResultData(SysConstants.status.STATUS_OPRATION_SUCCESS,SysConstants.status.STATUS_OPRATION_SUCCESS_MSG,null);
    }
    public static ResultData success(Object result){
        return new ResultData(SysConstants.status.STATUS_OPRATION_SUCCESS,SysConstants.status.STATUS_OPRATION_SUCCESS_MSG,result);
    }
    public static ResultData success(int status,String msg,Object result){
        return new ResultData(status,msg,result);
    }
    public static ResultData error(int status,String msg){
        return new ResultData(status,msg,null);
    }
    public static ResultData error(){
        return new ResultData(SysConstants.status.STATUS_OPRATION_FAILED,SysConstants.status.STATUS_OPRATION_FAILED_MSG,null);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
