package cn.mldn.vshop.service.back;

import java.util.List;

import cn.mldn.vshop.vo.Subitem;

public interface ISubitemServiceBack {
	/**
	 * 列出指定一级栏目下的所有二级栏目调用ISubitemDAO.findAllByItem()方法
	 * @param iid 一级栏目编号
	 * @return 全部二级栏目信息
	 * @throws Exception SQL
	 */
	public List<Subitem> listByItem(int iid) throws Exception ;
}
