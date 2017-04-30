package cn.mldn.vshop.service.front;

import java.util.Map;

public interface IMemberAddressServiceFront {
	/**
	 * 在用户进行地址信息添加前的数据查询处理：<br>
	 * 1、需要查询出所有的省份信息
	 * @return 信息包括如下内容：<br>
	 * 1、allProvinces = IProvinceDAO.findAll()，返回全部的省份数据
	 * @throws Exception SQL异常
	 */
	public Map<String,Object> getAddPre() throws Exception ;
}
