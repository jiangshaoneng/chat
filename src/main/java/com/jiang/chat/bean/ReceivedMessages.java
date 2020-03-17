package com.jiang.chat.bean;

import java.util.Date;

/**
 * \* Created with IntelliJ IDEA.
 * \* 作者: jiang
 * \* 日期/时间: 2020/3/15 14:06
 * \* 描述: 接收的消息
 * \
 */
public class ReceivedMessages {

    // 房间名称
    private String roomName;

    // 昵称
    private String nickName;

    // 消息类型
    private String type;

    // 消息内容
    private String msg;

    // 发送时间
    private Date time;

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "ReceivedMessages{" +
                "roomName='" + roomName + '\'' +
                ", nickName='" + nickName + '\'' +
                ", type='" + type + '\'' +
                ", msg='" + msg + '\'' +
                ", time=" + time +
                '}';
    }
}