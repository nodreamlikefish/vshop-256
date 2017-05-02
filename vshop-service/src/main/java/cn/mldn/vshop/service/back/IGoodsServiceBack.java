package cn.mldn.vshop.service.back;

import java.util.Map;

import cn.mldn.vshop.vo.Goods;

public interface IGoodsServiceBack {
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
