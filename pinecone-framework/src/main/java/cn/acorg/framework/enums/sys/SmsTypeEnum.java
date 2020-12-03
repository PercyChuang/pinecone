package cn.acorg.framework.enums.sys;

import lombok.Getter;

/**
 * 短信类型
 * @date 2020/12/3 19:48
 * @author 松果
 * @version 1.0
 */
@Getter
public enum SmsTypeEnum {
	/**
	 * 快速登录
	 */
	QUICK_LOGIN("QUICK_LOGIN","快速登录"),
	/**
	 * 注册登录
	 */
	REGISTER_LOGIN("REGISTER_LOGIN","注册登录"),
	/**
	 * 忘记密码
	 */
	FORGET_PASSWORD("FORGET_PASSWORD","忘记密码"),
	/**
	 * 修改密码
	 */
	UPDATE_LOGIN_PASSWORD("FORGET_PASSWORD","修改登录密码"),
	/**
	 * 系统消息
	 */
	SYS_INFO("SYS_INFO","系统消息");

	private String value;
	private String name;

	SmsTypeEnum(String value, String name) {
		this.value = value;
		this.name = name;
	}
	/**
	 * description: 获取名称
	 * @param value
	 * @return: java.lang.String
	 * @Author: Z-BL
	 * @Date: 2020/2/11 17:44
	 */
	public static String getName(String value) {
		for (SmsTypeEnum bs : SmsTypeEnum.values()) {
			if (bs.getValue().equals(value)) {
				return bs.getName();
			}
		}
		return null;
	}

}
