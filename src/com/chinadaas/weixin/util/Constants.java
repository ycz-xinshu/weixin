package com.chinadaas.weixin.util;
import com.github.sd4324530.fastweixin.api.config.ApiConfig;

/**
 * projectName: weixin<br>
 * desc: TODO<br>
 * date: Dec 2, 2014 2:12:05 PM<br>
 * 
 * @author 开发者真实姓名[HuPeng]
 */
public class Constants {

	public static final String TOKEN = "gsinfoWeixinToken";

	public static final String APPID = "wx0c5c0ded2746f73a";

	public static final String SECRET = "64056df927544b6cd07edf0d6af3628c";

	public static final ApiConfig CONFIG = new ApiConfig(APPID, SECRET);

	public static final String CRYPT_DES_KEY = "gsinfo123456";

	public static final String BIND_SUCCESS = "绑定成功";

	public static final String USER_EXIST = "微信被其他用户绑定";

	public static final String SYSTEM_ERROR = "系统异常";

	public static final String CALL_BACK_URL = String
			.format("<a href=\"https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx0c5c0ded2746f73a&redirect_uri=%s&response_type=code&scope=snsapi_base&state=1#wechat_redirect\">点击绑定</a>",
					PropertiesUtil.getProperty("system.config.weixin.url.login"));
}
