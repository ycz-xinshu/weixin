package com.chinadaas.weixin.config;
import com.chinadaas.weixin.controller.UserController;
import com.chinadaas.weixin.controller.WxController;
import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.CaseInsensitiveContainerFactory;
import com.jfinal.plugin.activerecord.dialect.OracleDialect;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.jfinal.render.ViewType;

/**
 * projectName: weixin<br>
 * desc: TODO<br>
 * date: Dec 26, 2014 3:52:37 PM<br>
 * @author 开发者真实姓名[HuPeng]
 */
public class WeixinConfig extends JFinalConfig{
	/**
	 * 配置常量
	 */
	public void configConstant(Constants me) {
		loadPropertyFile("../config/system.properties");				// 加载少量必要配置，随后可用getProperty(...)获取值
		me.setDevMode(getPropertyToBoolean("devMode", false));
		me.setViewType(ViewType.JSP); 							// 设置视图类型为Jsp，否则默认为FreeMarker
	}
	
	/**
	 * 配置路由
	 */
	public void configRoute(Routes me) {
		me.add("/", WxController.class);	// 第三个参数为该Controller的视图存放路径
		me.add("/user", UserController.class);			// 第三个参数省略时默认与第一个参数值相同，在此即为 "/code"
	}
	
	/**
	 * 配置插件
	 */
	public void configPlugin(Plugins me) {
		loadPropertyFile("../config/jdbc.properties");				// 加载少量必要配置，随后可用getProperty(...)获取值
		// 配置C3p0数据库连接池插件
		C3p0Plugin c3p0Plugin = new C3p0Plugin(getProperty("url"), getProperty("username"), getProperty("password").trim());
		c3p0Plugin.setDriverClass(getProperty("driverClassName"));
		c3p0Plugin.setInitialPoolSize(Integer.valueOf(getProperty("initialSize")));
		me.add(c3p0Plugin);
		
		// 配置ActiveRecord插件
		ActiveRecordPlugin arp = new ActiveRecordPlugin(c3p0Plugin);
		me.add(arp);
		
		arp.setDialect(new OracleDialect());
		arp.setContainerFactory(new CaseInsensitiveContainerFactory());
//		arp.addMapping("t_user", User.class);	// 映射user 表到 User模型
//		arp.addMapping("t_ukey", Ukey.class);
//		arp.addMapping("t_user_msgcode", Code.class);
//		arp.addMapping("t_user_wx", Weixin.class);
	}
	
	/**
	 * 配置全局拦截器
	 */
	public void configInterceptor(Interceptors me) {
		
	}
	
	/**
	 * 配置处理器
	 */
	public void configHandler(Handlers me) {
		
	}
}

