package cn.mldn.vshop.service.back;

import java.util.Map;

public interface IGoodsServiceBack {
	/**
	 * 进行商品添加页面的信息展示，调用如下操作：<br>
	 * 1、调用IItemDAO.findAll()方法取得全部的商品分类数据
	 * @return 返回的Map集合组成如下：<br>
	 * 1、key = allItems、value = 全部的商品分类数据；
	 * @throws Exception SQL
	 */
	public Map<String,Object> getAddPre() throws Exception ;
}
