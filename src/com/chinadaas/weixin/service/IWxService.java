package com.chinadaas.weixin.service;

import com.chinadaas.weixin.exception.ServiceException;
/**
 * projectName: weixin<br>
 * desc: <br>
 * date: Dec 27, 2014 8:39:24 PM<br>
 * @author 开发者真实姓名[HuPeng]
 */
public interface IWxService {

	public String getMsgCode(String openid) throws ServiceException;
	
}

