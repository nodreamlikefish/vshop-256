package cn.mldn.vshop.action.back;

import cn.mldn.util.factory.Factory;
import cn.mldn.util.web.ModelAndView;
import cn.mldn.vshop.service.back.IGoodsServiceBack;
import cn.mldn.vshop.util.action.AbstractBaseAction;

public class GoodsActionBack extends AbstractBaseAction {
	public ModelAndView addPre() {
		if (super.isRoleAndAction("goods", "goods:add")) {
			ModelAndView mav = new ModelAndView(super.getUrl("goods.add.page")) ;
			IGoodsServiceBack goodsServiceBack = Factory.getServiceInstance("goods.service.back") ;
			try {
				mav.add(goodsServiceBack.getAddPre());
			} catch (Exception e) {
				e.printStackTrace();
			}
			return mav ;
		} else {
			super.setUrlAndMsg("index.page", "unaction.msg");
			ModelAndView mav = new ModelAndView(
					super.getUrl("forward.back.page"));
			return mav;
		}
	}
}
