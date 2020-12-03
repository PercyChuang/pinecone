package cn.acorg.framework.exception;

/**
 * 服务异常编码，
 * 执行成功为 0，系统异常为 -1，其他编码：前2位为模块编码，后3位为业务编码
 * @author 松果
 * @date 2020/10/29 16:26
 */
public enum CommonResultEnum implements ResultCode {

	/**************************************** 系统基本 1xxxx ******************************************/
	/** 执行成功 */
	SUCCESS("0", "执行成功"),
	/** 系统繁忙 */
	SYS_ERROR("-1", "系统繁忙"),
	/** 请求参数异常 */
	PARAMS_ERROR("10001", "请求参数异常"),
	/** CRUD操作异常 */
	CRUD_ERROR("10002", "数据库操作异常"),
	/** 请求服务超时 */
	REQUEST_TIMEOUT_ERROR("10003", "请求服务超时"),
	/** 系统配置异常 */
	SYS_CONFIG_ERROR("10004", "系统配置异常"),
	/** 提交过于频繁，请稍后再试 */
	SUBMIT_FREQUENTLY("10005", "提交过于频繁，请稍后再试"),
	/** 系统升级中，敬请期待··· */
	NOT_OPEN("10006", "系统升级中，敬请期待···"),
	/** 签名错误，非法请求 */
	SIGN_ERROR("10007","签名错误，非法请求"),
	/** 执行失败！自定义拼接异常内容 */
	SYS_CUSTOM_ERROR("10008", "执行失败！%s"),
	/** 验证码错误 */
	SMS_CODE_ERROR("10009","验证码错误"),
	/** 验证码已经过期，请重新获取 */
	SMS_CODE_NULL("10010","验证码已经过期，请重新获取");


	private final String code;
	private final String desc;

	CommonResultEnum(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	/**
	 * 获取异常枚举 code
	 */
	@Override
	public String getCode() {
		return code;
	}

	/**
	 * 获取异常枚举 描述
	 */
	@Override
	public String getDesc() {
		return desc;
	}
}
