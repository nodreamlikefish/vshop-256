package cn.mldn.vshop.action.front;

import cn.mldn.util.factory.Factory;
import cn.mldn.util.web.ModelAndView;
import cn.mldn.vshop.service.front.IMemberServiceFront;
import cn.mldn.vshop.util.action.AbstractBaseAction;
import cn.mldn.vshop.vo.Member;

public class MemberCenterActionFront extends AbstractBaseAction {
	public static final String BASE_INFO = "用户信息" ;
	
	private IMemberServiceFront memberService = Factory.getServiceInstance("member.service.front") ;
	/**
	 * 信息修改操作，修改操作之后需要将修改信息提示给用户，所以一定要找到forward.jsp
	 * @param 所有的提交自动转换为VO类对象
	 * @return 更改后跳转路径信息
	 */
	public String editBase(Member vo) {
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
	}
	/**
	 * 进行用户基本信息的更新前的数据取得
	 * @return 包裹有跳转路径以及信息
	 */
	public ModelAndView eidtBasePre() {
		ModelAndView mav = new ModelAndView(super.getUrl("member.edit.base.page")) ;
		try {
			mav.add("memberBase", this.memberService.getEditBasePre(super.getMid()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav ;
	}
}
