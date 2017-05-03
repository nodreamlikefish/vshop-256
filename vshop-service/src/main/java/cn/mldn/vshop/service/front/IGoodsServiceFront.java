package cn.mldn.vshop.service.front;

import java.util.Map;

import cn.mldn.vshop.vo.Goods;

public interface IGoodsServiceFront {
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
