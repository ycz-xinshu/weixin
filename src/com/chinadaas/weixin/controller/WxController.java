package com.chinadaas.weixin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chinadaas.weixin.exception.ServiceException;
import com.chinadaas.weixin.service.IWxService;
import com.chinadaas.weixin.service.impl.WxServiceImpl;
import com.chinadaas.weixin.util.Constants;
import com.chinadaas.weixin.util.StringUtil;
import com.github.sd4324530.fastweixin.message.BaseMsg;
import com.github.sd4324530.fastweixin.message.TextMsg;
import com.github.sd4324530.fastweixin.message.req.MenuEvent;
import com.github.sd4324530.fastweixin.servlet.WeixinSupport;
import com.jfinal.core.Controller;

/**
 * projectName: weixin<br>
 * desc: 微信控制器<br>
 * date: Dec 26, 2014 5:16:33 PM<br>
 * @author 开发者真实姓名[HuPeng]
 */
public class WxController extends Controller {
	private WeixinServlet wxServlet = new WeixinServlet();
	private IWxService wxService = new WxServiceImpl();
	
//	private static final Logger LOG = LoggerFactory
//			.getLogger(WxController.class);
	
	public void index() {
		String resp = null;
		try {
			if ("GET".equalsIgnoreCase(getRequest().getMethod())) {
				resp = wxServlet.doGet(getRequest(), getResponse());
			} else if ("POST".equalsIgnoreCase(getRequest().getMethod())) {
				resp = wxServlet.doPost(getRequest(), getResponse());
			}
		} catch (IOException e) {
			resp = Constants.SYSTEM_ERROR;
		} finally {
			if (!StringUtil.isNullOrEmpty(resp)) {
				renderText(resp);
			}
		}
	}
	
	class WeixinServlet extends WeixinSupport{

		@Override
		protected String getToken() {
			return Constants.TOKEN;
		}
		
		/**
		 * desc: 微信验证<br>
		 * date: Dec 28, 2014 6:07:55 PM<br>
		 * @author 开发者真实姓名[HuPeng]
		 * @param request
		 * @param response
		 * @throws IOException
		 */
	    final String doGet(HttpServletRequest request,  HttpServletResponse response) throws IOException {
	        if (isLegal(request)) {
//	            //绑定微信服务器成功
	        	String resp = request.getParameter("echostr");
	        	return resp;
//	            PrintWriter pw = response.getWriter();
//	            pw.write(request.getParameter("echostr"));
//	            pw.flush();
//	            pw.close();
	        } else {
	        	return null;
	        }
	    }

	    /**
	     * 重写servlet中的post方法，用于接收微信服务器发来的消息，置为final方法，用户已经无需重写这个方法啦
	     *
	     * @param request  http请求对象
	     * @param response http响应对象
	     * @throws ServletException servlet异常
	     * @throws IOException      IO异常
	     */
	    final String doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
	        if (!isLegal(request)) {
	            return null;
	        }
	        //设置响应编码格式
	        response.setCharacterEncoding("UTF-8");
	        String resp = processRequest(request);
	        return resp;
//	        PrintWriter pw = response.getWriter();
//	        pw.write(resp);
//	        pw.flush();
//	        pw.close();
	    }
	    
		@Override
		protected BaseMsg handleMenuClickEvent(MenuEvent event) {
			String openid = event.getFromUserName();
			try {
				String randomStr = wxService.getMsgCode(openid);
				if (StringUtil.isNullOrEmpty(randomStr)) {
					return new TextMsg(Constants.CALL_BACK_URL);
				} else {
					return new TextMsg(randomStr);
				}
			} catch (ServiceException e) {
				return new TextMsg("请重新点击获取验证码");
			}
		}
	}
}

