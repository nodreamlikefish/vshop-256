package cn.mldn.vshop.service.front;

import java.util.List;

import cn.mldn.vshop.vo.City;

public interface ICityServiceFront {
	/**
	 * 根据省份编号列出所有的城市信息
	 * @param pid 省份编号
	 * @return 该省份下的所有城市数据 
	 * @throws Exception SQL异常
	 */
	public List<City> listByProvince(int pid) throws Exception ;
}
