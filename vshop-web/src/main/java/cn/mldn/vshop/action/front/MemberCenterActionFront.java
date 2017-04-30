package cn.mldn.vshop.action.front;

import cn.mldn.util.action.ActionMessageUtil;
import cn.mldn.util.enctype.PasswordUtil;
import cn.mldn.util.factory.Factory;
import cn.mldn.util.web.ModelAndView;
import cn.mldn.vshop.service.front.IMemberServiceFront;
import cn.mldn.vshop.util.action.AbstractBaseAction;
import cn.mldn.vshop.vo.Member;

public class MemberCenterActionFront extends AbstractBaseAction {
	public static final String BASE_INFO = "用户信息" ;
	
	private IMemberServiceFront memberService = Factory.getServiceInstance("member.service.front") ;
	/**
	 * 进行用户登录密码的修改处理，要根据session取得当前用户的编号数据
	 * @param oldpassword 原始密码
	 * @param newpassword 新的密码
	 * @return 修改成功后提示路径（forward.jsp），而后由提示路径进行跳转
	 */
	public String editPasssword(String oldpassword,String newpassword) {
		if (super.isRoleAndAction("info", "info:password")) {
			oldpassword = PasswordUtil.getPassword(oldpassword) ;	// 必须对密码进行加密处理
			newpassword = PasswordUtil.getPassword(newpassword) ;	// 必须对密码进行加密处理
			try {
				if (this.memberService.editPassword(super.getMid(), oldpassword, newpassword)) {
					super.setUrlAndMsg("logout.action","member.password.edit.success");
				} else {
					super.setUrlAndMsg("logout.action","member.password.edit.failure");
				}
			} catch (Exception e) {
				super.setUrlAndMsg("logout.action","member.password.edit.failure");
				e.printStackTrace();
			}
			return super.getUrl("forward.front.page") ;
		} else {
			super.setUrlAndMsg("index.page", "unaction.msg");
			return super.getUrl("forward.front.page") ;
		}
	}
	
	/**
	 * 信息修改操作，修改操作之后需要将修改信息提示给用户，所以一定要找到forward.jsp
	 * @param vo 所有的提交自动转换为VO类对象
	 * @return 更改后跳转路径信息
	 */
	public String editBase(Member vo) {
		if (super.isRoleAndAction("info", "info:edit")) {
			vo.setMid(super.getMid()); 	// 如果要想修改数据则要将用户编号传递过去
			try {
				if (this.memberService.editBase(vo)) {
					super.setUrlAndMsg("member.edit.base.action", "action.edit.success",BASE_INFO);
				} else {
					super.setUrlAndMsg("member.edit.base.action", "action.edit.failure",BASE_INFO);
				}
			} catch (Exception e) {
				super.setUrlAndMsg("member.edit.base.action", "action.edit.failure",BASE_INFO);
			}
			return super.getUrl("forward.front.page") ;
		} else {
			super.setUrlAndMsg("index.page", "unaction.msg");
			return super.getUrl("forward.front.page") ;
		}
	}
	/**
	 * 进行用户基本信息的更新前的数据取得
	 * @return 包裹有跳转路径以及信息
	 */
	public ModelAndView eidtBasePre() {
		if (super.isRoleAndAction("info", "info:show")) {
			ModelAndView mav = new ModelAndView(super.getUrl("member.edit.base.page")) ;
			try {
				mav.add("memberBase", this.memberService.getEditBasePre(super.getMid()));
			} catch (Exception e) {
				e.printStackTrace();
			}
			return mav ;
		} else {
			super.setUrlAndMsg("index.page", "unaction.msg");
			ModelAndView mav = new ModelAndView(super.getUrl("forward.front.page")) ;
			return mav ;
		}
	}
}
