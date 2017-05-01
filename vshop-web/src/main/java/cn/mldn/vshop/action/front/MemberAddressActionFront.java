package cn.mldn.vshop.action.front;

import cn.mldn.util.factory.Factory;
import cn.mldn.util.web.ModelAndView;
import cn.mldn.vshop.service.front.IMemberAddressServiceFront;
import cn.mldn.vshop.util.action.AbstractBaseAction;

public class MemberAddressActionFront extends AbstractBaseAction{
	private IMemberAddressServiceFront memberService = Factory.getServiceInstance("memberaddress.service.front") ;
	/**
	 * 进行用户地址信息增加操作处理，调用IMemberAddressServiceFront接口方法
	 * @return 包含有全部的省份信息
	 */
	public ModelAndView addPre() {
		if (super.isRoleAndAction("address", "address:add")) {
			ModelAndView mav = new ModelAndView(super.getUrl("member.address.add.page")) ;
			try {
				mav.add(this.memberService.getAddPre());
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
