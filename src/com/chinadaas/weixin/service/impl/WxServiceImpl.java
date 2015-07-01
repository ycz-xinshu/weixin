package com.chinadaas.weixin.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chinadaas.weixin.exception.ServiceException;
import com.chinadaas.weixin.service.IWxService;
import com.chinadaas.weixin.util.Dao;
import com.chinadaas.weixin.util.StringUtil;

/**
 * projectName: weixin<br>
 * desc: TODO<br>
 * date: Dec 27, 2014 8:39:39 PM<br>
 * 
 * @author 开发者真实姓名[HuPeng]
 */
public class WxServiceImpl implements IWxService {

	private static final Logger LOG = LoggerFactory.getLogger(WxServiceImpl.class);

	@Override
	public String getMsgCode(String openid) throws ServiceException {
		if (Dao.exist("select * from t_user_wx where open_id=?", openid)) {
			String randomStr = StringUtil.generate();
			Integer total = Dao.update(
					"update t_user_msgcode set msgcode=?, create_time=sysdate, src_type='2', try_time='0' where id_user in (select id_user from t_user_wx where open_id=?)",
					randomStr, openid);
			if (total == 1) {
				return randomStr;
			} else {
				throw new ServiceException(ServiceException.UPDATE_MSGCODE_FAILED);
			}
		} else {
			return null;
		}
	}

}
