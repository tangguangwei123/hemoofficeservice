package com.hemooffice.suopu.dto;
/**
 * @author chen
 * @date 2019/7/23
 * @email 15218979950@163.com
 * @description  用于返回前端json数据的工具类
 */
public class Msg {

    public Msg() {
    }

    //状态码
    public  int  status;
    //消息
    public  String message;
    //详细信息
    public String desc;
    //数据
    public  Object data;

    public void setStatus(int status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public String getDesc() {
        return desc;
    }

    public Object getData() {
        return data;
    }

    @Override
    public String toString() {
        return "{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", desc='" + desc + '\'' +
                ", data=" + data +
                '}';
    }

    /**
     * 响应简短消息
     */
    public static Msg send(int status,String message){
        Msg msg = new Msg();
        msg.setStatus(status);
        msg.setMessage(message);
        return msg;
    }
    /**
     * 发送带有详细
     */
    public static Msg fail(int status,String message,String desc){
        Msg msg = new Msg();
        msg.setStatus(status);
        msg.setMessage(message);
        msg.setDesc(desc);
        return msg;
    }

    /**
     * 发送带有数据执行成功消息
     */
    public static Msg success(Object data){
        Msg msg = new Msg();
        msg.setStatus(200);
        msg.setMessage("ok");
        msg.setData(data);
        return msg;
    }

    /**
     * 发送带有详细信息和数据
     */
    public static Msg send(int status,String message,String desc,Object data){
        Msg msg = new Msg();
        msg.setStatus(status);
        msg.setMessage(message);
        msg.setDesc(desc);
        msg.setData(data);
        return msg;
    }
}

