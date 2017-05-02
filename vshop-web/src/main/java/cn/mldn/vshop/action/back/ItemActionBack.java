package cn.mldn.vshop.action.back;

import cn.mldn.util.factory.Factory;
import cn.mldn.util.web.ModelAndView;
import cn.mldn.vshop.service.back.IItemServiceBack;
import cn.mldn.vshop.util.action.AbstractBaseAction;

public class ItemActionBack extends AbstractBaseAction {
	public ModelAndView list() {
		if (super.isRoleAndAction("goods", "goods:item")) {
			ModelAndView mav = new ModelAndView(
					super.getUrl("item.list.page"));
			try {
				IItemServiceBack itemService = Factory.getServiceInstance("item.service.back") ;
				mav.add("allItems", itemService.list());  
				return mav;
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			super.setUrlAndMsg("index.page", "unaction.msg");
			ModelAndView mav = new ModelAndView(
					super.getUrl("forward.back.page"));
			return mav;
		}
		return null;
	}
}
