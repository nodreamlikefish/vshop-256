package cn.mldn.vshop.dao;

import java.sql.SQLException;
import java.util.List;

import cn.mldn.util.dao.IBaseDAO;
import cn.mldn.vshop.vo.Address;

public interface IAddressDAO extends IBaseDAO<Integer, Address> {
	/**
	 * 进行用户地址信息的列出
	 * @param mid 用户的ID
	 * @return 以集合的形式返回，如果没有地址返回0
	 * @throws SQLException SQl异常
	 */
	public List<Address> findAllByMember(String mid) throws SQLException ;
	
	/**
	 * 要根据用户编号取得用户具有地址信息数量，这样可以在业务层中决定deflag的内容
	 * @param mid 用户的ID
 	 * @return 如果有地址信息则返回数量，否则返回就是0
	 * @throws SQLException SQL异常
	 */
	public Integer getCountByMember(String mid) throws SQLException ;
}
