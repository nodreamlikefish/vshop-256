package cn.mldn.vshop.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import cn.mldn.util.dao.IBaseDAO;
import cn.mldn.vshop.vo.Goods;

public interface IGoodsDAO extends IBaseDAO<Integer, Goods> {
	/**
	 * 根据指定的编号取得所有的商品信息
	 * @param ids 所有要查询的商品
	 * @return 商品信息
	 * @throws SQLException SQL异常
	 */
	public List<Goods> findAllByIds(Set<Long> ids) throws SQLException ;
	
	/**
	 * 进行多个商品信息的标记更新处理
	 * @param ids 要更新的商品ID
	 * @param delflag 商品删除标记
	 * @return 更新成功返回 true
	 * @throws SQLException SQL
	 */
	public boolean doUpdateDeflag(Set<Integer> ids,int delflag) throws SQLException ;
	/**
	 * 进行指定商品分类的全部商品信息查询
	 * @param sid 商品分类编号
	 * @param currentPage 当前页
	 * @param lineSize 每页行数
	 * @return 全部商品数据（部分）
	 * @throws SQLException SQL异常
	 */
	public List<Goods> findAllSplitBySubitem(Integer sid,Integer currentPage,Integer lineSize) throws SQLException ;
	/**
	 * 根据商品子分类获取全部的商品数量
	 * @param sid 商品分类编号
	 * @return 数量，没有返回0
	 * @throws SQLException SQL异常
	 */
	public Integer getAllCountBySubitem(Integer sid) throws SQLException ;
	
}
