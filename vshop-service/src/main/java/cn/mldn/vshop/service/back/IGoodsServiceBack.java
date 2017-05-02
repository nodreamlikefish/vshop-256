package cn.mldn.vshop.service.back;

import java.util.Map;

import cn.mldn.vshop.vo.Goods;

public interface IGoodsServiceBack {
	/**
	 * 实现单个商品的信息显示
	 * @param gid 商品编号
	 * @return 商品数据，包括如下内容：<br>
	 * 1、key = item、value = 商品分类数据；<br>
	 * 2、key = subitem、value = 商品子分类数据；<br>
	 * 3、key = goods、value = 商品数据。
	 * @throws Exception SQL
	 */
	public Map<String,Object> show(int gid) throws Exception ;
	
	/**
	 * 进行信息列表显示，如果现在没有模糊查询的关键字或者列，则执行全部的数据查询
	 * @param currentPage 当前页
	 * @param lineSize 每页行
	 * @param column 关键列
	 * @param keyWord 关键字
	 * @return 返回的数据有如下两个内容：<br>
	 * 1、key = allGoodss、value = 所有的商品数据；<br>
	 * 2、key = allRecorders、value = 商品的数量；<br>
	 * @throws Exception SQL
	 */
	public Map<String,Object> list(int currentPage,int lineSize,String column,String keyWord) throws Exception ;
	
	/**
	 * 进行商品信息的添加，调用IGoodsDAO.doCreate()方法，需要手工设置商品发布日期以及删除标记（0）
	 * @param vo 要添加的数据
	 * @return 添加成功返回true，否则返回false
	 * @throws Exception SQL
	 */
	public boolean add(Goods vo) throws Exception ;
	
	/**
	 * 进行商品添加页面的信息展示，调用如下操作：<br>
	 * 1、调用IItemDAO.findAll()方法取得全部的商品分类数据
	 * @return 返回的Map集合组成如下：<br>
	 * 1、key = allItems、value = 全部的商品分类数据；
	 * @throws Exception SQL
	 */
	public Map<String,Object> getAddPre() throws Exception ;
}
