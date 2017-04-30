package cn.mldn.vshop.service.front;

import java.util.Map;
import java.util.Set;

import cn.mldn.vshop.vo.Member;


public interface IMemberServiceFront {
	/**
	 * 进行用户密码的更新处理，本业务操作需要如下执行步骤：<br>
	 * 1、首先要利用mid与oldpassword进行原始登录的验证，调用IMemberDAO.findLogin()方法；<br>
	 * 2、如果findLogin()方法验证通过，则使用IMemberDAO.doUpdatePassword()方法更新密码信息。
	 * @param mid 要更新的用户编号
	 * @param oldpassword 原始密码
	 * @param newpassword 新的密码
	 * @return 更新成功返回true，否则返回false
	 * @throws Exception SQL异常
	 */
	public boolean editPassword(String mid,String oldpassword,String newpassword) throws Exception ;
	
	/**
	 * 进行数据更新前的信息查找，调用IMemberDAO.findById()方法
	 * @param mid 要更新的用户编号
	 * @return 用户信息以VO形式返回
	 * @throws Exception SQL异常
	 */
	public Member getEditBasePre(String mid) throws Exception ;
	/**
	 * 进行用户基本数据的更新，调用IMemberDAO.doUpdateBase()方法
	 * @param vo 包含有更新数据
	 * @return 更新成功返回true
	 * @throws Exception SQL异常
	 */
	public boolean editBase(Member vo) throws Exception ;
	
	/**
	 * 进行用户数据的添加操作处理，添加时请注意如下问题：<br>
	 * 1、必须保证当前的操作用户有指定的权限后才可以调用；<br>
	 * 2、如果当前用户具备有指定的权限，那么此时就进行新增加的用户名称的存在判断<br>
	 * 3、如果用户不存在则进行添加控制，但是新用户一定是未锁定状态，最后一次登录日期为当前的注册日期。<br>
	 * @param vo 新用户信息
	 * @return 增加成功返回true，否则返回false
	 * @throws Exception SQL异常
	 */
	public boolean addMember(Member vo) throws Exception ; 
	/**
	 * 检测用户名是否已经存在
	 * @param mid 要检测的用户名
	 * @return 如果该用户名存在返回false，表示不允许使用，否则返回true表示可以使用
	 * @throws Exception SQL异常
	 */
	public boolean checkMid(String mid) throws Exception ;
	
	/**
	 * 进行用户数据增加前的操作控制，在用执行具体的操作之前一定要对当前所具备的权限进行认证
	 * @param mid 进行当前用户的权限验证
	 * @param editMid 要修改的用户信息
	 * @return 返回的Map集合包含有如下信息：<br>
	 * key = allRoles、value = IRoleDAO.findAll()；<br>
	 * key = member、value = IMemberDAO.findById()。<br>
	 * @throws Exception SQL异常
	 */
	public Map<String,Object> getEditPre(String mid,String editMid) throws Exception ;
	/**
	 * 进行用户的更新处理操作。执行如下操作：
	 * 1、要进行当前用户的权限判断；<br>
	 * 2、更新用户的信息；<br>
	 * 3、删除已有的用户角色信息；<br>
	 * 4、保存用户的角色信息。
	 * @param mid 操作者用户名
	 * @param vo 要修改的用户信息
	 * @param rids 角色编号
	 * @return 修改成功返回true，否则返回false 
	 * @throws Exception 如果不具备权限，或者修改失败抛出异常。
	 */
	public boolean edit(String mid,Member vo,Set<Integer> rids) throws Exception ;
	
	/**
	 * 进行数据的分页列表显示
	 * @param mid 操作用户编号
	 * @param currentPage 当前所在页
	 * @param lineSize 每页显示行数 
	 * @param column 模糊查询列
	 * @param keyWord 关键字 
	 * @return Map集合返回的信息包括：<br>
	 * 1、key = allMembers、value = IMemberDAO.findAllXxx()；<br>
	 * 2、key = memberCount、value = IMemberDAO.getAllCountXxx()；
	 * @throws Exception  如果不具备权限，或者修改失败抛出异常。
	 */
	public Map<String, Object> list(String mid, int currentPage, int lineSize,
			String column, String keyWord) throws Exception;
	/**
	 * 进行用户数据增加前的操作控制，在用执行具体的操作之前一定要对当前所具备的权限进行认证
	 * @param mid 操作用户编号
	 * @return 返回的Map集合包含有如下信息：<br>
	 * key = allRoles、value = IRoleDAO.findAll()；
	 * @throws Exception SQL异常
	 */
	public Map<String,Object> getAddPre(String mid) throws Exception ;
	/**
	 * 进行用户的登录处理操作，该操作的处理流程如下：<br>
	 * 1、首先进行用户名或密码的登录检测，登录完成之后应该取得用户登录的相关的状态信息，包括：上次登录日期、锁定状态；<br>
	 * 2、如果用户为锁定状态，则应该返回一个登录失败的信息；<br>
	 * 3、如果用户为活跃状态，则要求更新用户的最后一次登录日期；<br>
	 * 4、向用户登录日志之中进行相应的信息记录；<br>
	 * 5、根据用户的名称取得用户对应的所有角色信息；<br>
	 * 6、根据用户名称取得用户对应的所有权限信息；<br>
	 * @param mid 用户名
	 * @param password 加密处理后的密码
	 * @return 对于登录的操作可能会包含有如下的几个信息：<br>
	 * 1、key = status、value = 0（登录失败）、1（登录成功）、2（用户锁定无法登录）；
	 * 2、key = allRoles、value = 角色的集合（Set&lt;角色标记&gt;）;<br>
	 * 3、key = allActions、value = 权限集合（Set&lt;权限标记&gt;）。<br> 
	 * 4、key = lastdate、value = 上次登录日期。<br>
	 * 5、key = name、value = 用户真实姓名。
	 * @throws Exception SQL异常
	 */
	public Map<String,Object> login(String mid,String password) throws Exception ;
}
