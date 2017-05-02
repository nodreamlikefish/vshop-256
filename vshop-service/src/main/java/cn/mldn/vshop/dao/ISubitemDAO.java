package cn.mldn.vshop.dao;

import java.sql.SQLException;
import java.util.List;

import cn.mldn.util.dao.IBaseDAO;
import cn.mldn.vshop.vo.Subitem;

public interface ISubitemDAO extends IBaseDAO<Integer, Subitem> {
	/**
	 * 进行全部子栏目的信息列出
	 * @param iid 栏目编号
	 * @return 全部子栏目数据
	 * @throws SQLException SQL
	 */
	public List<Subitem> findAllByItem(Integer iid) throws SQLException ;
}
