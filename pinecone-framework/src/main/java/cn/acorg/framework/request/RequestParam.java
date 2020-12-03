package cn.acorg.framework.request;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * 请求参数实体
 * @author 松果
 * @date 2020/10/29 16:20
 */
@Data
@Slf4j
public class RequestParam<T> implements Serializable {

    private static final long serialVersionUID = 1;
    /**
     * 请求头
     */
    private RequestHeader header;
    /**
     * 请求实体
     */
    private T data;

    public RequestParam(T data) {
        this.header = new RequestHeader();
        this.data = data;
    }

    public RequestParam() {
        this.header = new RequestHeader();
    }

    public static <T>RequestParam<T> build(Class<T> target){
        RequestParam<T> param = new RequestParam<>();
        try {
            T t = target.newInstance();
            param.setData(t);
            param.setHeader(new RequestHeader());
        } catch (Exception e) {
            log.error("RequestParam.build ", e);
        }
        return param;
    }
}
