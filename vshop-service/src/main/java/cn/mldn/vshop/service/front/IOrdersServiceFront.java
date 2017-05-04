package cn.mldn.vshop.service.front;

import java.util.Map;
import java.util.Set;

public interface IOrdersServiceFront {
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
