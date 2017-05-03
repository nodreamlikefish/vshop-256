package cn.mldn.vshop.service.front;

import cn.mldn.vshop.vo.Goods;

public interface IGoodsServiceFront {
	/**
	 * 进行商品信息的查看操作
	 * @param gid 商品编号
	 * @return 商品单个数据
	 * @throws Exception SQL
	 */
	public Goods get(int gid) throws Exception ;
}
