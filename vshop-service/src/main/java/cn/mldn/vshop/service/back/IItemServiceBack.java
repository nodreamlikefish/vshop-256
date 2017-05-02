package cn.mldn.vshop.service.back;

import java.util.List;

import cn.mldn.vshop.vo.Item;
import cn.mldn.vshop.vo.Subitem;

public interface IItemServiceBack {

	/**
	 * 实现所有的栏目信息列表，调用IItemDAO.findAll()
	 * @return 所有的栏目数据
	 * @throws Exception SQL异常
	 */
	public List<Item> list() throws Exception ;
	/**
	 * 实现栏目信息名称的变更处理操作，调用IItemDAO.doUpdate()方法
	 * @param vo 包含有修改数据的对象
	 * @return 修改成功返回true
	 * @throws Exception SQL异常
	 */
	public boolean edit(Item vo) throws Exception ;
}
