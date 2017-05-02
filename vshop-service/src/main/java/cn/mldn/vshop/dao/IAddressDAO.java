package cn.mldn.vshop.dao;

import java.sql.SQLException;

import cn.mldn.util.dao.IBaseDAO;
import cn.mldn.vshop.vo.Address;

public interface IAddressDAO extends IBaseDAO<Integer, Address> {
	/**
	 * 要根据用户编号取得用户具有地址信息数量，这样可以在业务层中决定deflag的内容
	 * @param mid 用户的ID
 	 * @return 如果有地址信息则返回数量，否则返回就是0
	 * @throws SQLException SQL异常
	 */
	public Integer getCountByMember(String mid) throws SQLException ;
}
