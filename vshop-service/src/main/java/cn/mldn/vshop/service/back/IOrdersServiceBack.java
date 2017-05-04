package cn.mldn.vshop.service.back;

import java.util.Map;

public interface IOrdersServiceBack {
	/**
	 * 进行订单详情的查看，在订单详情里面需要包含有如下的内容：<br>
	 * 1、要求知道订单的详细信息，包括：收件人的相关信息、订单的价格等等；
	 * 2、需要知道购买的商品的信息；
	 * 3、购买的商品数量以及商品花费的价格；
	 * @param oid 订单编号
	 * @return 返回的内容包含有如下数据：<br>
	 * 1、key = orders、value = 订单的信息；<br>
	 * 2、key = allDetails、value = Map集合，保存了该订单商品编号和购买数量；
	 * 3、key = allGoods、value = 所有购买的商品详情；
	 * @throws Exception SQL
	 */
	public Map<String,Object> get(int oid) throws Exception ;
	/**
	 * 进行全部的订单信息列出
	 * @param currentPage 当前页
	 * @param lineSize 每页行
	 * @param column 模糊列
	 * @param keyWord 模糊内容
	 * @return 返回如下内容：<br>
	 * 1、key = allOrderss、value = 所有订单数据；<br>
	 * 2、key = allRecorders、value = 全部订单数量；<br>
	 * @throws Exception SQL
	 */
	public Map<String, Object> list(int currentPage, int lineSize,
			String column, String keyWord) throws Exception;
}
