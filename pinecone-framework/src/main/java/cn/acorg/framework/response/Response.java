package cn.acorg.framework.response;

import cn.acorg.framework.exception.CommonResultEnum;
import cn.hutool.core.util.StrUtil;
import lombok.Data;

import java.io.Serializable;

/**
 * 返回实体
 * @author 松果
 * @date 2020/10/29
 */
@Data
public class Response<T> implements Serializable {

    private static final long serialVersionUID = 1;
    /** 返回的数据 **/
    private T data;
    /** 处理结果 **/
    private boolean success;
    /** 返回状态码 **/
    private String code;
    /** 返回的处理信息 **/
    private String message;
    /** 接入标志 **/
    private boolean mark;
    /** 结果时间 **/
    private long timestamp;
    /** 原始异常信息 **/
    private String orgMessage;

    public Response(){
        this.timestamp = System.currentTimeMillis();
    }

    public Response(T data){
        this.data = data;
    }

    public Response(String code, boolean success, String message){
        this.code = code;
        this.success = success;
        this.message = message;
        this.mark = true;
    }

    public void success() {
        this.code = CommonResultEnum.SUCCESS.getCode();
        this.success = true;
        this.mark = true;
    }

    public void failure() {
        this.success = false;
        this.mark = true;
    }

    public void addException(Exception e, String message) {
        if(StrUtil.isBlank(this.message)){
            this.message = "";
        }
        if(!StrUtil.isNotBlank(message)){
            this.message += message;
        }
        if(e != null){
            this.message += e.getMessage();
        }
        failure();
    }

    public void addException(Exception e) {
        if(StrUtil.isBlank(this.message)){
            this.message = "";
        }
        if(e != null){
            this.message += e.getMessage();
        }
        failure();
    }

    public void addMessage(String message) {
        this.message = StrUtil.isBlank(this.message) ? message : this.message + message;
    }

    public void failure(String code){
        this.success = false;
        this.code = code;
        this.mark = true;
    }

    public void failure(String errorCode, String errorMsg) {
       this.failure(errorCode,errorMsg,null);
    }

    public void failure(String errorCode, String errorMsg,String orgMessage) {
        this.success = false;
        this.code = errorCode;
        this.message = errorMsg + (StrUtil.isBlank(this.message) ? "" : this.message);
        this.orgMessage = orgMessage;
        this.mark = true;
    }

}
