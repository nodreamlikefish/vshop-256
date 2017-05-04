package cn.mldn.vshop.dao;

import java.sql.SQLException;

import cn.mldn.util.dao.IBaseDAO;
import cn.mldn.vshop.vo.Orders;

public interface IOrdersDAO extends IBaseDAO<Integer, Orders> {
	/**
	 * 取得增长后的id数据信息
	 * @return 增长后的id内容
	 * @throws SQLException SQL
	 */
	public Integer findCreateId() throws SQLException ;
}
