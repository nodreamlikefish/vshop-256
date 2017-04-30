package cn.mldn.vshop.util.action;

import java.io.IOException;

import cn.mldn.util.action.ActionMessageUtil;
import cn.mldn.util.web.ServletObjectUtil;

public abstract class AbstractBaseAction {
	/**
	 * 根据当前登录的session取得该用户的编号信息
	 * @return 用户编号，如果没有登录过返回null
	 */
	public String getMid() {
		return (String) ServletObjectUtil.getSession().getAttribute("mid") ;
	}
	
	/**
	 * 取得路径信息，通过ActionMessageUtil.getUrl()方法获得
	 * 
	 * @param key 资源文件key
	 * @return 取得资源对应的内容
	 */
	public String getUrl(String key) {
		return ActionMessageUtil.getUrl(key);
	}
	/**
	 * 通过Request属性范围设置msg与url两个参数的内容
	 * @param urlKey url参数的key
	 * @param msgKey msg参数的key
	 */
	public void setUrlAndMsg(String urlKey,String msgKey,Object ...param ) {
		ServletObjectUtil.getRequest().setAttribute("url", this.getUrl(urlKey));
		ServletObjectUtil.getRequest().setAttribute("msg", this.getMsg(msgKey,param));
	}

	/**
	 * 取得提示文字信息，通过ActionMessageUtil.getMsg()方法获得
	 * 
	 * @param key 资源文件key
	 * @param param 占位符数据
	 * @return 取得资源对应的内容
	 */
	public String getMsg(String key, Object... param) {
		return ActionMessageUtil.getMsg(key, param);
	}

	/**
	 * 进行信息打印输出操作，主要为ajax异步处理操作提供支持
	 * 
	 * @param value 要打印的对象内容
	 */
	public void print(Object value) {
		try {
			ServletObjectUtil.getResponse().getWriter().print(value);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
