package cn.mldn.vshop.action.back;

import cn.mldn.util.action.ActionSplitPageUtil;
import cn.mldn.util.factory.Factory;
import cn.mldn.util.web.ModelAndView;
import cn.mldn.vshop.service.back.IMemberServiceBack;
import cn.mldn.vshop.util.action.AbstractBaseAction;

public class MemberActionBack extends AbstractBaseAction {
	public ModelAndView list() {
		if (super.isRoleAndAction("member", "member:list")) {
			ActionSplitPageUtil aspu = new ActionSplitPageUtil(
					"用户名:mid|姓名:name|联系电话:phone|邮箱:email",
					"member.list.action");
			IMemberServiceBack memberService = Factory
					.getServiceInstance("member.service.back");
			ModelAndView mav = new ModelAndView(
					super.getUrl("member.list.page"));
			try {
				mav.add(memberService.list(aspu.getCurrentPage(),
						aspu.getLineSize(), aspu.getColumn(), aspu.getKeyWord()));
			} catch (Exception e) {
				e.printStackTrace();
			}
			return mav ;
		} else {
			super.setUrlAndMsg("index.page", "unaction.msg");
			ModelAndView mav = new ModelAndView(
					super.getUrl("back.front.page"));
			return mav;
		}
	}
}
