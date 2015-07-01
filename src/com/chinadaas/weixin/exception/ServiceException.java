package com.chinadaas.weixin.exception;

/**
 * projectName: weixin<br>
 * desc: TODO<br>
 * date: 2015年1月6日 下午4:51:12<br>
 * @author 开发者真实姓名[裔传洲]
 */
public class ServiceException extends Exception {
	
	public static final String USER_IS_NOT_EXIST = "用户不存在";
	public static final String PSW_ERROR = "密码错误";
	public static final String OUT_OF_DATE_ERROR = "验证码已过期";
	public static final String MSG_ERROR = "验证码错误";
	public static final String EMAIL_NOT_BIND = "邮箱未绑定";
	public static final String OPENID_INVALID = "页面失效或不被服务器信任";
	public static final String BIND_WX_FAILED = "绑定微信失败";
	public static final String UPDATE_MSGCODE_FAILED = "更新微信验证码失败";
	
	public ServiceException(String message) {
		super(message);
	}
	
	public ServiceException(String message, Throwable t) {
		super(message, t);
	}
}

