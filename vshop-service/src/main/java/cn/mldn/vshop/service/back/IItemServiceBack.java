package cn.mldn.vshop.service.back;

import java.util.List;

import cn.mldn.vshop.vo.Item;

public interface IItemServiceBack {
	/**
	 * 实现所有的栏目信息列表，调用IItemDAO.findAll()
	 * @return 所有的栏目数据
	 * @throws Exception SQL异常
	 */
	public List<Item> list() throws Exception ;
}
