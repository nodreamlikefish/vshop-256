package cn.mldn.vshop.service.front;

import java.util.Map;

import cn.mldn.vshop.vo.Address;

public interface IMemberAddressServiceFront {
	/**
	 * 在用户进行地址信息添加前的数据查询处理：<br>
	 * 1、需要查询出所有的省份信息
	 * @return 信息包括如下内容：<br>
	 * 1、allProvinces = IProvinceDAO.findAll()，返回全部的省份数据
	 * @throws Exception SQL异常
	 */
	public Map<String,Object> getAddPre() throws Exception ;
	/**
	 * 进行地址信息的追加处理操作，该处理操作流程如下：<br>
	 * 1、要判断增加地址的用户是否是第一次设置地址，如果是第一次设置地址，则将其设置为默认地址（deflag = 1）；
	 * 2、如果不是第一次增加的地址，则将deflag设置为0
	 * @param vo 包含了要追加的地址信息
	 * @return 增加成功返回true，反之返回false
	 * @throws Exception SQL异常
	 */
	public boolean add(Address vo) throws Exception ;
}
