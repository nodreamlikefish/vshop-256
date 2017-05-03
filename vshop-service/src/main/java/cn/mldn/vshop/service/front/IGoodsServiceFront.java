package cn.mldn.vshop.service.front;

import java.util.Map;

import cn.mldn.vshop.vo.Goods;

public interface IGoodsServiceFront {
	/**
	 * 进行信息列表显示，根据关键字查询商品名称
	 * @param currentPage 当前页
	 * @param lineSize 每页行
	 * @param keyWord 关键字
	 * @return 返回的数据有如下两个内容：<br>
	 * 1、key = allGoodss、value = 所有的商品数据；<br>
	 * 2、key = allRecorders、value = 商品的数量；<br>
	 * @throws Exception SQL
	 */
	public Map<String,Object> list(int currentPage,int lineSize,String keyWord) throws Exception ;
	

	/**
	 * 实现指定分类的所有商品信息列表
	 * @param sid 商品分类编号
	 * @return 包括如下内容：<br>
	 * 1、key = allRecorders、value = 商品个数；<br>
	 * 2、key = allGoodss、value = 商品数据。<br>
	 * @throws Exception SQL
	 */
	public Map<String,Object> listBySubitem(int sid,int currentPage,int lineSize) throws Exception ;
	
	/**
	 * 进行商品信息的查看操作
	 * @param gid 商品编号
	 * @return 商品单个数据
	 * @throws Exception SQL
	 */
	public Goods get(int gid) throws Exception ;
}
