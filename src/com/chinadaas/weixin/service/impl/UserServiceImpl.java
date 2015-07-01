package com.chinadaas.weixin.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chinadaas.weixin.exception.ServiceException;
import com.chinadaas.weixin.mail.Mail;
import com.chinadaas.weixin.service.IUserService;
import com.chinadaas.weixin.util.CodeApiConfig;
import com.chinadaas.weixin.util.Constants;
import com.chinadaas.weixin.util.Dao;
import com.chinadaas.weixin.util.StringUtil;
import com.jfinal.plugin.activerecord.Record;

/**
 * projectName: weixin<br>
 * desc: User Service<br>
 * date: Dec 26, 2014 5:37:27 PM<br>
 * 
 * @author 开发者真实姓名[HuPeng]
 */
public class UserServiceImpl implements IUserService {

	private static final Logger LOG = LoggerFactory
			.getLogger(UserServiceImpl.class);
	
	
	@Override
	public void login(String username, String password, String msgCode,
			String code) throws ServiceException {
		String idUser = getIdUser(username);
		// 验证用户名密码验证码是否正确
		validate(username, idUser, password, msgCode);
		// 用户id绑定微信
		bindWeixin(idUser, code);
	}

	@Override
	public void sendMail(String username) throws ServiceException{
		String idUser = getIdUser(username);
		if (StringUtil.isNullOrEmpty(idUser)) {
			throw new ServiceException(ServiceException.USER_IS_NOT_EXIST);
		}
		//查找用户的email
		String email = Dao.findFirst("select * from t_user where id=?", idUser).get("email");
		if (!StringUtil.isNullOrEmpty(email)) {
			//生成验证码
			String randomStr = StringUtil.generate();
			Mail mail = new Mail(email, randomStr);
			//先更新表内验证码数据 再发送邮件
			updateMsg(idUser, randomStr);
			mail.send();
			LOG.info("用户" + idUser + " 发送验证码成功");
		} else {
			throw new ServiceException(ServiceException.EMAIL_NOT_BIND);
		}
	}
	
	/**
	 * desc: 更新验证码数据<br>
	 * date: Dec 27, 2014 8:22:58 PM<br>
	 * @author 开发者真实姓名[HuPeng]
	 * @param idUser
	 * @param str
	 * @throws ServiceException
	 */
	public void updateMsg(String idUser, String str) throws ServiceException {
		boolean exist = Dao.exist("select * from t_user_msgcode where id_user=?", idUser);
		//检查该用户是否存在
		if (exist) {
			//存在则更新验证码
			Dao.update("update t_user_msgcode set msgcode=?, create_time=sysdate, src_type='2', try_time=0, mobile='' where id_user=?", str, idUser);
		} else {
			//新增记录
			Dao.update("insert into t_user_msgcode values (seq_user_msgcode.nextval, ?, ?, sysdate, '2', 0, '') ", idUser, str);
		}	
	}

	/**
	 * desc: 验证用户名密码和验证码的正确性<br>
	 * date: Dec 27, 2014 6:34:07 PM<br>
	 * @author 开发者真实姓名[HuPeng][裔传洲]
	 * @param username
	 * @param password
	 * @param msgCode
	 */
	private void validate(String username, String idUser, String password, String msgCode)
			throws ServiceException {
		// 1.验证用户名是否存在
		boolean isexist = Dao.exist("select * from t_ukey where ukey_no=?", username);
		if(!isexist) {
			throw new ServiceException(ServiceException.USER_IS_NOT_EXIST);
		}
		
		// 2.验证密码
		boolean isauth = Dao.exist("select * from t_ukey where ukey_no=? and pin=?", username, password);
		if(!isauth) {
			throw new ServiceException(ServiceException.PSW_ERROR);
		}
		
		// 3.验证码是否过期
		boolean outofdate = !Dao.exist("select * from t_user_msgcode where id_user=? and (sysdate-create_time)*24*60<5 ", idUser);
		if (outofdate) {
			throw new ServiceException(ServiceException.OUT_OF_DATE_ERROR);
		}
		
		// 4.验证码是否正确
		boolean iscorrect = Dao.exist("select * from t_user_msgcode where msgcode=? and id_user=? and (sysdate-create_time)*24*60<5 ", msgCode, idUser);
		if (!iscorrect) {
			throw new ServiceException(ServiceException.MSG_ERROR);
		}
	}

	/**
	 * desc: 绑定微信<br>
	 * date: 2015年2月11日 上午10:49:55<br>
	 * @author 开发者真实姓名[裔传洲]
	 * @param idUser
	 * @param code
	 * @throws ServiceException
	 */
	private void bindWeixin(String idUser, String code) throws ServiceException {
		//检查用户是否已经绑定过微信
//		Boolean userIsExist = Dao.exist("select * from t_user_wx where id_user=?", idUser);
//		if (userIsExist) {
//			return Constants.USER_EXIST;
//		}
		//根据微信传过来的code获取openid
		String openid = getOpenId(code);
		if (StringUtil.isNullOrEmpty(openid)) {
			LOG.error(idUser + "获取openid失败");
			throw new ServiceException(ServiceException.OPENID_INVALID);
		}
		Record record = new Record().set("id", "seq_user_wx.nextval")
				.set("id_user", idUser)
				.set("open_id", openid);
		if (Dao.save("t_user_wx", record)) {
			updateMsg(idUser, StringUtil.generate());
		} else {
			throw new ServiceException(ServiceException.BIND_WX_FAILED);
		}
	}
	
	/**
	 * desc: 获取公众号的openid<br>
	 * date: 2015年2月11日 上午10:50:28<br>
	 * @author 开发者真实姓名[裔传洲]
	 * @param code
	 * @return
	 */
	private String getOpenId(String code) {
		CodeApiConfig config = new CodeApiConfig(Constants.APPID,
				Constants.SECRET, code);
		return config.getOpenId();
	}

	@Override
	public String getIdUser(String username) {
		Record record = Dao.findFirst(" select id_user_use from t_ukey where ukey_no=? ", username);
		if (record != null) {
			return record.getStr("id_user_use");
		}
		return null;
	}
}
