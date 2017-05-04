package cn.mldn.vshop.dao;

import java.sql.SQLException;
import java.util.Map;

import cn.mldn.util.dao.IBaseDAO;
import cn.mldn.vshop.vo.Details;

public interface IDetailsDAO extends IBaseDAO<Integer, Details> {
	/**
	 * 根据订单编号查询出所有该订单购买的全部商品信息
	 * @param oid 订单编号
	 * @return 订单详情的记录，主要就是商品编号和数量
	 * @throws SQLException SQL
	 */
	public Map<Long,Integer> findAllByOrders(Integer oid) throws SQLException ;
}
