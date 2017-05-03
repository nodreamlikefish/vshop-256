package cn.mldn.vshop.service.front;

import java.util.Map;
import java.util.Set;

public interface IShopcarServiceFront {
	/**
	 * 根据用户编号和商品编号删除购物车信息
	 * @param mid 用户编号
	 * @param gids 全部商品编号
	 * @return 成功返回true
	 * @throws Exception SQL
	 */
	public boolean deleteByMember(String mid,Set<Integer> gids) throws Exception ;
	
	/**
	 * 进行购物车信息的批量更新操作
	 * @param mid 用户编号
	 * @param sc 购物车的信息（key = gid、value = amount）
	 * @return 更新成功返回true
	 * @throws Exception SQL
	 */
	public boolean editAmounts(String mid,Map<Integer,Integer> sc) throws Exception ;
	
	/**
	 * 修改指定商品的购买数量
	 * @param mid 用户编号
	 * @param gid 商品编号
	 * @param amount 商品数量
	 * @return 更新成功返回true
	 * @throws Exception SQL
	 */
	public boolean editAmount(String mid,int gid,int amount) throws Exception ;
	
	/**
	 * 根据用户列出其所购买的所有商品信息，调用如下操作：<br>
	 * 1、调用IShopcarDAO.findAllByMember()方法取得所有的购物车的商品编号和数量；<br>
	 * 2、调用IGoodsDAO.findByIds()方法根据商品编号取得所有商品信息
	 * @param mid 要列出的用户编号
	 * @return 包含如下内容：<br>
	 * 1、key = allShopcars、value = 购买的商品编号和数量； <br>
	 * 2、key = allGoodss、value = 商品的信息。<br>
	 * @throws Exception SQL
	 */
	public Map<String,Object> list(String mid) throws Exception ;
	
	/**
	 * 实现购物车信息的添加操作处理
	 * @param mid 用户编号
	 * @param gid 要添加购物车的商品编号
	 * @return 添加成功返回true
	 * @throws Exception SQL
	 */
	public boolean add(String mid,int gid) throws Exception ;
}
