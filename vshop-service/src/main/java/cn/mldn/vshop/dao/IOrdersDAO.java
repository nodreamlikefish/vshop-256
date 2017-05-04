package cn.mldn.vshop.dao;

import java.sql.SQLException;
import java.util.List;

import cn.mldn.util.dao.IBaseDAO;
import cn.mldn.vshop.vo.Orders;

public interface IOrdersDAO extends IBaseDAO<Integer, Orders> {
	/**
	 * 取得增长后的id数据信息
	 * @return 增长后的id内容
	 * @throws SQLException SQL
	 */
	public Integer findCreateId() throws SQLException ;
	/**
	 * 进行用户全部订单信息查看
	 * @param mid 用户编号
	 * @param currentPage 当前页
	 * @param lineSize  每页长度
	 * @return 订单信息
	 * @throws SQLException SQL
	 */
	public List<Orders> findAllSplitByMember(String mid,Integer currentPage,Integer lineSize) throws SQLException ;
	/**
	 * 列出指定用户的订单数量
	 * @param mid 用户名
	 * @return 订单数量
	 * @throws SQLException SQL
	 */
	public Integer getAllCountByMember(String mid) throws SQLException ;
}
