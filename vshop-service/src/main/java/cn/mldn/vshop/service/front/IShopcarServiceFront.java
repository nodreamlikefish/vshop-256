package cn.mldn.vshop.service.front;

public interface IShopcarServiceFront {
	/**
	 * 实现购物车信息的添加操作处理
	 * @param mid 用户编号
	 * @param gid 要添加购物车的商品编号
	 * @return 添加成功返回true
	 * @throws Exception SQL
	 */
	public boolean add(String mid,int gid) throws Exception ;
}
