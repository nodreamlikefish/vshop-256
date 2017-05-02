package cn.mldn.vshop.service.front;

import java.util.List;
import java.util.Map;

import cn.mldn.vshop.vo.Address;

public interface IMemberAddressServiceFront {
	/**
	 * 修改某一个地址的默认处理状态，该业务执行如下操作：<br>
	 * 1、调用IAddressDAO.doUpdateDeflag(String mid,Integer deflag)，取消掉所有的默认状态<br>
	 * 2、调用IAddressDAO.doUpdateDeflag(String mid,Integer adid,Integer deflag)设置一个默认的地址项<br>
	 * @param mid 要修改的用户信息
	 * @param adid 要设置的新的默认地址的地址编号
	 * @return 设置成功返回true，否则返回false
	 * @throws Exception SQL异常
	 */
	public boolean editDeflag(String mid,int adid) throws Exception ;
	
	/**
	 * 进行用户全部地址信息的列出
	 * @param mid 当前用户id
	 * @return 全部地址数据
	 * @throws Exception SQL异常
	 */
	public List<Address> listByMember(String mid) throws Exception ;  
	
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
