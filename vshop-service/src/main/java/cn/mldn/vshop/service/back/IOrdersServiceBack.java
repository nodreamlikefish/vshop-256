package cn.mldn.vshop.service.back;

import java.util.Map;

public interface IOrdersServiceBack {
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
