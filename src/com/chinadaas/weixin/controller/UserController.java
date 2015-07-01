package com.chinadaas.weixin.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chinadaas.weixin.exception.ServiceException;
import com.chinadaas.weixin.service.IUserService;
import com.chinadaas.weixin.service.impl.UserServiceImpl;
import com.chinadaas.weixin.util.Constants;
import com.chinadaas.weixin.util.CryptUtils;
import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;

/**
 * projectName: weixin<br>
 * desc: 用户控制器<br>
 * date: Dec 26, 2014 4:51:59 PM<br>
 * @author 开发者真实姓名[HuPeng]
 */
public class UserController extends Controller {
	
	private static final Logger LOG = LoggerFactory
			.getLogger(UserController.class);
	
	private IUserService userSerivce = new UserServiceImpl();
	
	// unused
	public void index() {
		
	}
	
	@ActionKey("/login")
	public void login() {
		String ukeyNo = getPara("username");
		String password = CryptUtils.encode(getPara("password"));
		String msgCode = getPara("msgcode");
		String code = getPara("code");
		setAttr("code", code);
		try {
			// 登录方法
			userSerivce.login(ukeyNo, password, msgCode, code);
			// 跳转到绑定成功页面
			setAttr("message", Constants.BIND_SUCCESS);
			renderJsp("/success.jsp");
		} catch (ServiceException e) {
			// 发生异常，打印异常信息到异常页面
			setAttr("message", e.getMessage());
			renderJsp("/fail.jsp");
		}
	}
	
	@ActionKey("/code")
	public void code() {
		try {
			String username = getPara("username");
			userSerivce.sendMail(username);
			renderJson();
		} catch (ServiceException e) {
			renderJson("error", e.getMessage());
			LOG.error("send mail error: " + e.getMessage());
			e.printStackTrace();
		}
	}
}

