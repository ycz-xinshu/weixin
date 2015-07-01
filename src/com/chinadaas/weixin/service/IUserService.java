package com.chinadaas.weixin.service;

import com.chinadaas.weixin.exception.ServiceException;

/**
 * projectName: weixin<br>
 * desc: TODO<br>
 * date: Dec 26, 2014 5:36:56 PM<br>
 * @author 开发者真实姓名[HuPeng]
 */
public interface IUserService {
	/**
	 * desc: 用户登录，绑定微信<br>
	 * date: Dec 27, 2014 6:36:07 PM<br>
	 * @author 开发者真实姓名[HuPeng]
	 * @param username
	 * @param password
	 * @param msgCode
	 * @param code
	 * @throws ServiceException
	 */
	public void login(String username, String password, String msgCode, String code) throws ServiceException;
	
	/**
	 * desc: 验证用户名 发送验证码到邮箱<br>
	 * date: Dec 27, 2014 6:36:25 PM<br>
	 * @author 开发者真实姓名[HuPeng]
	 * @param username
	 * @return
	 * @throws ServiceException
	 */
	public void sendMail(String username) throws ServiceException ;
	
	/**
	 * desc: 根据用户名获取iduser<br>
	 * date: Dec 27, 2014 7:59:56 PM<br>
	 * @author 开发者真实姓名[HuPeng]
	 * @param username
	 * @return
	 * @throws ServiceException
	 */
	public String getIdUser(String username) throws ServiceException;
	
	/**
	 * desc: 更新验证码<br>
	 * date: Dec 29, 2014 10:24:06 AM<br>
	 * @author 开发者真实姓名[HuPeng]
	 * @param idUser
	 * @param str
	 * @throws ServiceException
	 */
	public void updateMsg(String idUser, String str) throws ServiceException;
}

