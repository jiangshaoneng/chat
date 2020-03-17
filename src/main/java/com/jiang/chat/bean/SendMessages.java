package com.jiang.chat.bean;

/**
 * \* Created with IntelliJ IDEA.
 * \* 作者: jiang
 * \* 日期/时间: 2020/3/15 13:59
 * \* 描述: 用户发送的消息格式
 * \
 */
public class SendMessages {

    // 房间名称
    private String roomName;

    // 消息类型 connection:首次连接消息  normal:普通聊天消息
    private String type;

    // 昵称
    private String nickName;

    // 消息内容
    private String msg;

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "SendMessages{" +
                "roomName='" + roomName + '\'' +
                ", type='" + type + '\'' +
                ", nickName='" + nickName + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }
}