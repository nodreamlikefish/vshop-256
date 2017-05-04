package cn.mldn.vshop.service.back;

import java.util.Map;

public interface IMemberServiceBack {
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
