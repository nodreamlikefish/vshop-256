package cn.mldn.vshop.dao;

import java.sql.SQLException;
import java.util.Set;

import cn.mldn.util.dao.IBaseDAO;
import cn.mldn.vshop.vo.Goods;

public interface IGoodsDAO extends IBaseDAO<Integer, Goods> {
	/**
	 * 进行多个商品信息的标记更新处理
	 * @param ids 要更新的商品ID
	 * @param delflag 商品删除标记
	 * @return 更新成功返回 true
	 * @throws SQLException SQL
	 */
	public boolean doUpdateDeflag(Set<Integer> ids,int delflag) throws SQLException ;
}
