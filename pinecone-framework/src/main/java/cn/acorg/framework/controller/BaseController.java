package cn.acorg.framework.controller;

import cn.acorg.framework.exception.ServiceBaseException;
import cn.acorg.framework.request.RequestHeader;
import cn.acorg.framework.request.RequestParam;
import cn.acorg.framework.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;

/**
 * 公共 Controller
 * @author 松果
 * @version 1.0
 * @date 2020/11/10 15:35
 */
@Slf4j
public class BaseController implements InitializingBean {

    @Override
    public void afterPropertiesSet() throws Exception {

    }

    /**
     * 构造返回实体
     * @author 松果
     * @date 2020/11/10 15:36
     * @return cn.acorg.framework.response.Response<T>
     */
    public <T> Response<T> buildResp() {
        Response<T> response = new Response<>();
        response.setMark(true);
        return response;
    }

    /**
     * 构造请求实体
     * @param header 请求头
     * @author 松果
     * @date 2020/11/10 15:37
     * @return cn.acorg.framework.request.RequestParam<T>
     */
    public <T> RequestParam<T> buildReq(RequestHeader header) {
        RequestParam<T> req = new RequestParam<>();
        req.setHeader(header);
        return req;
    }

    /**
     * 构造异常返回实体
     * @param e 自定义异常
     * @param resp 返回实体
     * @author 松果
     * @date 2020/11/10 15:37
     * @return void
     */
    protected void buildErrorResp(ServiceBaseException e, Response<?> resp) {
        resp.failure(e.getCode(), e.getMsg(), e.getMessage());
        // 设置消耗时间
        long timestamp = System.currentTimeMillis() - resp.getTimestamp();
        resp.setTimestamp(timestamp);
    }

    /**
     * 打印异常日志
     * @param e 异常
     * @author 松果
     * @date 2020/11/10 15:45
     * @return void
     */
    public void logError(Exception e) {
        StringBuilder msg = new StringBuilder();
        StackTraceElement[] trace = e.getStackTrace();
        for (StackTraceElement s : trace) {
            msg.append("at ").append(s).append("\r\n");
        }
        log.error(e.toString());
        log.error(msg.toString());
    }

    /**
     * 构造异常返回实体
     * @param e  异常
     * @param resp 返回实体
     * @author 松果
     * @date 2020/11/10 15:41
     * @return void
     */
    protected void buildErrorResp(Exception e, Response<?> resp) {
        // 输出异常
        this.logError(e);
        if(e instanceof IllegalArgumentException){
            resp.failure("-1", e.getMessage(), e.getMessage());
        }else{
            resp.failure("-1", "系统正在处理中，请稍等再试。。。", null);
        }
        // 设置消耗时间
        long timestamp = System.currentTimeMillis() - resp.getTimestamp();
        resp.setTimestamp(timestamp);
    }

    /**
     * 构造成功返回实体
     * @param t 对象
     * @author 松果
     * @date 2020/11/10 15:52
     * @return cn.acorg.framework.response.Response<T>
     */
    protected <T> Response<T> buildSuccessResp(T t) {
        Response<T> resp = new Response<>();
        resp.setData(t);
        // 设置消耗时间
        long timestamp = System.currentTimeMillis() - resp.getTimestamp();
        resp.setTimestamp(timestamp);
        resp.success();
        return resp;
    }

    /**
     * 构造成功返回实体
     * @param t 对象
     * @param resp resp对象
     * @author 松果
     * @date 2020/11/10 15:41
     * @return void
     */
    protected <T> void buildSuccessResp(T t, Response<T> resp) {
        resp.setData(t);
        // 设置消耗时间
        long timestamp = System.currentTimeMillis() - resp.getTimestamp();
        resp.setTimestamp(timestamp);
        resp.success();
    }
}
