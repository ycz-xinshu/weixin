package com.chinadaas.weixin;

import java.util.ArrayList;
import java.util.List;

import com.chinadaas.weixin.util.Constants;
import com.github.sd4324530.fastweixin.api.MenuAPI;
import com.github.sd4324530.fastweixin.api.config.ApiConfig;
import com.github.sd4324530.fastweixin.api.entity.Menu;
import com.github.sd4324530.fastweixin.api.entity.MenuButton;
import com.github.sd4324530.fastweixin.api.enums.MenuType;
import com.github.sd4324530.fastweixin.api.enums.ResultType;

/**
 * projectName: weixin<br>
 * desc: TODO<br>
 * date: Dec 3, 2014 11:30:08 AM<br>
 * 
 * @author 开发者真实姓名[HuPeng]
 */
public class Main {
	public static void main(String[] args) {
		ApiConfig config = Constants.CONFIG;
		MenuAPI menuAPI = new MenuAPI(config);
		menuAPI.deleteMenu();
		Menu request = new Menu();
		// 准备一级主菜单
//		MenuButton main1 = new MenuButton();
//		main1.setType(MenuType.VIEW);
//		main1.setKey("register");
//		main1.setName("注册");
//		main1.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx0c5c0ded2746f73a&redirect_uri=http://115.182.16.70/login.jsp&response_type=code&scope=snsapi_base&state=1#wechat_redirect");
//		
		MenuButton main2 = new MenuButton();
		main2.setType(MenuType.CLICK);
		main2.setKey("code");
		main2.setName("验证码");
//		// 准备子菜单
//		MenuButton sub1 = new MenuButton();
//		sub1.setKey("sub1");
//		sub1.setName("name1");
//		sub1.setType(MenuType.VIEW);
//		sub1.setUrl("http://115.182.16.70/");

//		List<MenuButton> list = new ArrayList<MenuButton>();
//		list.add(sub1);
//		// 将子菜单放入主菜单里
//		main1.setSub_button(list);

		List<MenuButton> mainList = new ArrayList<MenuButton>();
//		mainList.add(main1);
		mainList.add(main2);
		// 将主菜单加入请求对象
		request.setButton(mainList);
		// 创建菜单
		ResultType resultType = menuAPI.createMenu(request);
		System.out.println(resultType.toString());
	}
}
