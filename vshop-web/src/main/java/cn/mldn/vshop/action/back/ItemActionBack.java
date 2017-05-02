package cn.mldn.vshop.action.back;

import cn.mldn.util.factory.Factory;
import cn.mldn.util.web.ModelAndView;
import cn.mldn.vshop.service.back.IItemServiceBack;
import cn.mldn.vshop.util.action.AbstractBaseAction;
import cn.mldn.vshop.vo.Item;

public class ItemActionBack extends AbstractBaseAction {
	public void edit(Item vo) {
		if (super.isRoleAndAction("goods", "goods:item")) {
			try {
				IItemServiceBack itemService = Factory.getServiceInstance("item.service.back") ;
				super.print(itemService.edit(vo)); 
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			super.print(false);
		}
	}
	
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
