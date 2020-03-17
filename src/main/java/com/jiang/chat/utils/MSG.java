package com.jiang.chat.utils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: jiang
 * \* Date: 2019/8/3
 * \* Time: 17:16
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 *      返回给前台的数据对象
 *      statusCode:200-成功 其他为异常
 *      msg:描述信息
 * \
 */
@SuppressWarnings("all")
public class MSG implements Serializable {

    private static final Integer SUCCESS = 200;
    private static final Integer ERROR = 201;

    private static String SUCCESS_MSG = "处理成功";
    private static String ERROR_MSG = "处理失败";

    private Integer statusCode;
    private String msg;

    //返回浏览器的数据
    private Map<String,Object> data = new HashMap<String, Object>();

    /**
     * 操作成功,默认状态码和信息
     * @return
     */
    public static MSG success(){
        MSG result = new MSG();
        result.setStatusCode(SUCCESS);
        result.setMsg(SUCCESS_MSG);
        return result;
    }

    /**
     * 操作失败,默认状态码和信息
     * @return
     */
    public static MSG error(){
        MSG result = new MSG();
        result.setStatusCode(ERROR);
        result.setMsg(ERROR_MSG);
        return result;
    }

    /**
     * 操作成功,默认状态码,自定义信息
     * @param msg
     * @return
     */
    public static MSG success(String msg){
        MSG result = new MSG();
        result.setStatusCode(SUCCESS);
        result.setMsg(msg);
        return result;
    }

    /**
     * 操作失败,默认状态码,自定义信息
     * @param msg
     * @return
     */
    public static MSG error(String msg){
        MSG result = new MSG();
        result.setStatusCode(ERROR);
        result.setMsg(msg);
        return result;
    }

    /**
     * 操作失败,自定义状态码和信息
     * @param statusCode
     * @param msg
     * @return
     */
    public static MSG error(Integer statusCode, String msg){
        MSG result = new MSG();
        result.setStatusCode(statusCode);
        result.setMsg(msg);
        return result;
    }

    /**
     * 将数据添加到data中
     * @param key
     * @param value
     * @return
     */
    public MSG add(String key,Object value){
        this.getData().put(key, value);
        return this;
    }


    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }
}