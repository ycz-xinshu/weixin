package com.chinadaas.weixin.util;

import org.apache.log4j.Logger;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

/**
 * projectName: weixin<br>
 * desc: TODO<br>
 * date: Dec 27, 2014 6:26:39 PM<br>
 * @author 开发者真实姓名[HuPeng]
 */
public class Dao extends Db {
	
	private static final Logger LOG = Logger.getLogger(Dao.class);
	
	public static Boolean exist(String sql, Object... paras) {
		LOG.info("exist: " + sql);
		Record record = Db.findFirst(sql, paras);
		if (record == null) {
			return false;
		}
		return true;
	}
	
	public static Boolean exist(String sql) {
		return exist(sql, new Object[]{});
	}
}

