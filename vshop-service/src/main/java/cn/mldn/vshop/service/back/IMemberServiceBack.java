package cn.mldn.vshop.service.back;

import java.util.Map;
import java.util.Set;

public interface IMemberServiceBack {
	/**
	 * 进行单个用户详细信息的显示处理操作，本操作要执行如下功能：
	 * 1、可以根据用户编号查询出一个用户的完整信息；<br>
	 * 2、可以根据用户编号取得用户的所有配送地址信息。
	 * @param mid 用户编号
	 * @return 返回如下的数据内容：<br>
	 * 1、key = member、value = 单个用户数据信息；<br>
	 * 2、key = allAddress、value = 某一个用户的地址信息；<br>
	 * @throws Exception SQL
	 */
	public Map<String,Object> getDetails(String mid) throws Exception ;
	
	/**
	 * 进行锁定状态变更
	 * @param mid 用户编号
	 * @param locked 状态
	 * @return 成功返回true
	 * @throws Exception SQL
	 */
	public boolean editLocked(Set<String> mid,int locked) throws Exception ;
	
	/**
	 * 进行密码的编辑处理
	 * @param mid 用户名
	 * @param password 新密码
	 * @return 修改成功返回true
	 * @throws Exception SQL
	 */
	public boolean editPassword(String mid,String password) throws Exception ;
	
	/**
	 * 进行全部的用户列表显示
	 * @param currentPage 当前页
	 * @param lineSize 每页行
	 * @param column 模糊列
	 * @param keyWord 关键字
	 * @return 包括如下内容：<br>
	 * 1、key = allMembers、value = 全部用户信息；<br>
	 * 2、key = allRecorders、value = 用户量。
	 * @throws Exception SQL
	 */
	public Map<String, Object> list(int currentPage, int lineSize,
			String column, String keyWord) throws Exception;
}
