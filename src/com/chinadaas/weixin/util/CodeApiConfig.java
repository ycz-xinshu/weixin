package com.chinadaas.weixin.util;

import java.util.concurrent.atomic.AtomicBoolean;

import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.sd4324530.fastweixin.api.config.ApiConfig;
import com.github.sd4324530.fastweixin.util.JSONUtil;
import com.github.sd4324530.fastweixin.util.NetWorkCenter;

/**
 * projectName: weixin<br>
 * desc: TODO<br>
 * date: Dec 8, 2014 5:40:06 PM<br>
 * @author 开发者真实姓名[HuPeng]
 */
public class CodeApiConfig{

    private static final Logger LOG = LoggerFactory.getLogger(CodeApiConfig.class);

    private final String appid;

    private final String secret;
    
    private final String code;

    private String accessToken;
    
    private String openId;

    public static final AtomicBoolean refreshing = new AtomicBoolean(false);

    /**
     * 唯一构造方法，实现同时获取access_token
     * @param appid 公众号appid
     * @param secret 公众号secret
     */
    public CodeApiConfig(String appid, String secret, String code) {
        this.appid = appid;
        this.secret = secret;
        this.code = code;
        init();
    }

    public String getAppid() {
        return appid;
    }

    public String getSecret() {
        return secret;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
    
    public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getCode() {
		return code;
	}

	/**
     * 初始化微信配置，即第一次获取access_token
     */
    public void init() {
        LOG.debug("开始第一次初始化code_access_token........");
		String url = "https://api.weixin.qq.com/sns/oauth2/access_token?grant_type=authorization_code&appid="
				+ this.appid + "&secret=" + this.secret + "&code=" + this.code;
		LOG.info("Get Openid Url: " + url);
        NetWorkCenter.post(url, null, new NetWorkCenter.ResponseCallback() {
            @Override
            public void onResponse(int resultCode, String resultJson) {
                if (HttpStatus.SC_OK == resultCode) {
                	GetOpenIdByCode response = JSONUtil.toBean(resultJson, GetOpenIdByCode.class);
                    LOG.debug("获取access_token:{}", response.getAccessToken());
                    CodeApiConfig.this.accessToken = response.getAccessToken();
                    CodeApiConfig.this.openId = response.getOpenid();
                }
            }
        });
    }
}

