package cn.mldn.vshop.service.front;

import java.util.Map;
import java.util.Set;

public interface IOrdersServiceFront {
	/**
	 * 进行订单的创建处理，该处理包括如下内容：
	 * 1、需要根据传入的用户配送地址的id编号取得一个配送地址的详细信息，保存在Orders类；<br>
	 * 2、需要根据用户传入的购买商品的编号查询出所有的商品数据，因为需要进行价格计算；<br>
	 * 3、需要根据用户传入的购买商品的编号查询出购物车中对应数据；<br>
	 * 4、进行总价的计算，并且将这个计算的结果保存在Orders类之中；<br>
	 * 5、订单创建完成之后需要进行订单详情的编写，但是订单详情处理的时候一定需要知道订单编号；<br>
	 * 6、订单创建完成之后一定要删除购物车中对应的商品信息。<br>
	 * @param mid 用户编号
	 * @param gid 要购买的商品编号
	 * @param adid 配送地址编号
	 * @param note 配送信息说明
	 * @return 创建成功返回true，否则返回false
	 * @throws Exception SQL
	 */
	public boolean add(String mid,Set<Long> gid,Integer adid,String note) throws Exception ;
	
	/**
	 * 进行订单确认页的信息提示，该确认页需要具有如下的信息内容：<br>
	 * 1、通过传递的商品编号来找到所有对应的商品信息；<br>
	 * 2、既然要进行信息显示，那么一定要将用户购买的商品数量提示出来；<br>
	 * 3、取得用户所有的配送地址信息，这里面一定包含有默认的配送地址。<br>
	 * @param mid 用户编号 
	 * @param gid 购物车中保存的商品编号
	 * @return 返回的数据内容包括如下：<br>
	 * 1、key = allShopcars、value = 购买的商品编号和数量； <br>
	 * 2、key = allGoodss、value = 商品的信息。<br>
	 * 3、key = allAddress、value = 用户所有的配送地址信息。<br>
	 * @throws Exception SQL
	 */
	public Map<String,Object> getAddPre(String mid,Set<Long> gid) throws Exception ;
}
