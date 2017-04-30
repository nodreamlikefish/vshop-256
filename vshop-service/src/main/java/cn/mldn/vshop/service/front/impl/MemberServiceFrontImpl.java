package cn.mldn.vshop.service.front.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import cn.mldn.util.factory.Factory;
import cn.mldn.vshop.dao.IActionDAO;
import cn.mldn.vshop.dao.IMemberDAO;
import cn.mldn.vshop.dao.IMemberLogsDAO;
import cn.mldn.vshop.dao.IRoleDAO;
import cn.mldn.vshop.service.abs.AbstractService;
import cn.mldn.vshop.service.front.IMemberServiceFront;
import cn.mldn.vshop.vo.Member;
import cn.mldn.vshop.vo.MemberLogs;

public class MemberServiceFrontImpl extends AbstractService implements IMemberServiceFront {
	private IMemberDAO memberDAO = Factory.getDAOInstance("member.dao") ;
	@Override
	public boolean editPassword(String mid, String oldpassword,
			String newpassword) throws Exception {
		if(this.memberDAO.findLogin(mid, oldpassword) != null) {	// 原始密码有效
			return this.memberDAO.doUpdatePassword(mid, newpassword) ;
		}  
		return false;
	}
	
	@Override
	public Member getEditBasePre(String mid) throws Exception {
		return memberDAO.findById(mid);
	}
	
	@Override
	public boolean editBase(Member vo) throws Exception {
		return memberDAO.doUpdateBase(vo); 
	}
	
	@Override
	public boolean checkMid(String mid) throws Exception {
		return memberDAO.findById(mid) == null ;
	}
	
	@Override
	public boolean edit(String mid, Member vo,Set<Integer> rids) throws Exception {
		if (super.exists(mid, "member:edit")) {
			if (rids == null || rids.size() == 0) {	// 没有角色不应该创建用户
				return false ;
			}
			if (memberDAO.doUpdate(vo)) { // 保存用户数据
				IRoleDAO roleDAO = Factory.getDAOInstance("role.dao");
				if (roleDAO.doRemoveMemberRole(vo.getMid())) {
					return roleDAO.doCreateMemberRole(vo.getMid(), rids);
				}
			}
		}
		return false ;
	}
	
	@Override
	public Map<String, Object> getEditPre(String mid,String editMid) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>() ;
		if (super.exists(mid, "member:edit")) {	// 具备有指定的权限才可以实现追加
			IRoleDAO roleDAO = Factory.getDAOInstance("role.dao") ;
			map.put("allRoles", roleDAO.findAll()) ;
			map.put("memberRoles", roleDAO.findAllByMember(editMid)) ;
			IMemberDAO memberDAO = Factory.getDAOInstance("member.dao") ;
			map.put("member", memberDAO.findById(editMid)) ; 
		}
		return map;
	}
	
	@Override
	public Map<String, Object> list(String mid, int currentPage, int lineSize,
			String column, String keyWord) throws Exception {
		if (super.exists(mid, "member:list")) {
			IMemberDAO memberDAO = Factory.getDAOInstance("member.dao") ;
			Map<String,Object> map = new HashMap<String,Object>() ;
			if (column == null || keyWord == null || "".equals(column) || "".equals(keyWord)) {
				map.put("allMembers",memberDAO.findAllSplit(currentPage, lineSize)) ;
				map.put("memberCount", memberDAO.getAllCount()) ; 
			} else {
				map.put("allMembers", memberDAO.findAllSplit(currentPage, lineSize, column, keyWord)) ; 
				map.put("memberCount", memberDAO.getAllCount(column, keyWord)) ;
			}
			return map ;
		}
		return null;
	}
	@Override
	public Map<String, Object> getAddPre(String mid) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>() ;
		if (super.exists(mid, "member:add")) {	// 具备有指定的权限才可以实现追加
			IRoleDAO roleDAO = Factory.getDAOInstance("role.dao") ;
			map.put("allRoles", roleDAO.findAll()) ;
		}
		return map;
	}
	@Override
	public boolean addMember(Member vo) throws Exception {
		// 设置已知的角色权限
		Set<Integer> rids = new HashSet<Integer>() ;
		rids.add(5) ;
		rids.add(6) ;
		rids.add(7) ;
		rids.add(8) ;
		IMemberDAO memberDAO = Factory.getDAOInstance("member.dao") ;
		Member result =  memberDAO.findById(vo.getMid()) ;
		if (result == null) {
			vo.setRegdate(new Date());	// 注册日期为当前日期
			vo.setName("新用户 - " + (long)Math.random());
			vo.setLocked(0); 	// 默认用户不应该被锁定
			vo.setLastdate(new Date());	// 最后一次登录日期为注册日期
			if (memberDAO.doCreate(vo)) {	// 保存用户数据
				IRoleDAO roleDAO = Factory.getDAOInstance("role.dao") ;
				return roleDAO.doCreateMemberRole(vo.getMid(), rids) ; 
			}
		}
		return false ;
	}
	@Override
	public Map<String, Object> login(String mid, String password)
			throws Exception {
		IMemberDAO memberDAO = Factory.getDAOInstance("member.dao") ;
		Map<String, Object> map = new HashMap<String, Object>();
		// 这个时候的Member类对象result包含有上次登录日期、用户的锁定状态
		Member result = memberDAO.findLogin(mid, password) ;
		if (result == null) {	// 当前用户信息不存在
			map.put("status", 0) ;	// 数字0表示用户登录失败！
		} else {
			if (result.getLocked().equals(0)) {	// 用户状态为0，表示未锁定
				map.put("status", 1);	// 数字1表示用户登录成功
				map.put("name", result.getName()) ;
				map.put("lastdate", result.getLastdate()) ;
				if (memberDAO.doUpdateLastdate(mid, new Date())) {	// 更新最后一次登录日期成功
					IMemberLogsDAO memberLogsDAO = Factory.getDAOInstance("memberlogs.dao") ;
					MemberLogs mlvo = new MemberLogs() ;	// 实例化MemberLogs对象
					mlvo.setMid(mid);	// 设置用户id
					mlvo.setLogindate(new Date()); 	// 设置登录日期
					memberLogsDAO.doCreate(mlvo) ;
				}
				// 要保存用户的所有角色信息
				IRoleDAO roleDAO = Factory.getDAOInstance("role.dao") ;
				map.put("allRoles", roleDAO.findAllByMember(mid)) ;
				// 要保存用户对应的所有权限信息
				IActionDAO actionDAO = Factory.getDAOInstance("action.dao") ;
				map.put("allActions", actionDAO.findAllByMember(mid)) ; 
			} else {	// 用户已经锁定了
				map.put("status", 2) ;	// 数字2表示该用户已经被锁定
			}
		} 
		return map; 
	}

}
