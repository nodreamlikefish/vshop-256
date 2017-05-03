package cn.mldn.vshop.dao;

import java.sql.SQLException;
import java.util.Map;

import cn.mldn.util.dao.IBaseDAO;
import cn.mldn.vshop.vo.Shopcar;

public interface IShopcarDAO extends IBaseDAO<Integer, Shopcar> {
	/**
	 * 根据当前用户编号取得所有的购物车信息
	 * @param mid 用户编号
	 * @return key = 商品编号、value = 商品数量。
	 * @throws SQLException
	 */
	public Map<Long,Integer> findAllByMember(String mid) throws SQLException ;
	
	/**
	 * 根据用户的编号和商品编号来查处已有的购物车数据
	 * @param mid 用户编号
	 * @param gid 商品编号
	 * @return 如果发现返回对象，否则返回null
	 * @throws SQLException SQL
	 */
	public Shopcar findByMemberAndGoods(String mid,Integer gid) throws SQLException ;
	/**
	 * 根据指定的购物车的记录编号进行商品购买数量的变更
	 * @param mid 用户编号
	 * @param gid 商品编号
	 * @param amount 商品现在的数量
	 * @return 更新成功返回true，否则返回false
	 * @throws SQLException SQL
	 */
	public boolean doUpdateIncrementById(String mid,Integer gid,Integer amount) throws SQLException ;
}
