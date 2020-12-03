package cn.acorg.framework.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 自定义异常
 * @author 松果
 * @date 2020/10/29 16:30
 * @return
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ServiceBaseException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
    private String msg;
	private String code = "-1";
    
    public ServiceBaseException(String msg) {
		super(msg);
		this.msg = msg;
	}
	
	public ServiceBaseException(String msg, Throwable e) {
		super(msg, e);
		this.msg = msg;
	}
	
	public ServiceBaseException(String msg, String code) {
		super(msg);
		this.msg = msg;
		this.code = code;
	}
	
	public ServiceBaseException(String msg, String code, Throwable e) {
		super(msg, e);
		this.msg = msg;
		this.code = code;
	}

	public ServiceBaseException(ResultCode error) {
		super(error.getDesc());
		this.msg = error.getDesc();
		this.code = error.getCode();
	}

	public ServiceBaseException(ResultCode error, String msg) {
		super(error.getDesc() + ":" + msg);
		this.msg = error.getDesc() + ":" + msg;
		this.code = error.getCode();
	}

}
