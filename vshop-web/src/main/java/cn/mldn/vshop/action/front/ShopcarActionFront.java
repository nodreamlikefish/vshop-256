package cn.mldn.vshop.action.front;

import cn.mldn.util.factory.Factory;
import cn.mldn.util.web.ModelAndView;
import cn.mldn.vshop.service.front.IShopcarServiceFront;
import cn.mldn.vshop.util.action.AbstractBaseAction;

public class ShopcarActionFront extends AbstractBaseAction {
	
	public void edit(int gid,int amount) {
		if (super.isRoleAndAction("shopcar", "shopcar:edit")) {
			IShopcarServiceFront shopcarService = Factory.getServiceInstance("shopcar.service.front") ;
			try {
				super.print(shopcarService.editAmount(super.getMid(), gid, amount));
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			super.print(false);
		}
	}
	
	public ModelAndView list() {
		if (super.isRoleAndAction("shopcar", "shopcar:list")) {
			ModelAndView mav = new ModelAndView(super.getUrl("shopcar.list.page")) ;
			IShopcarServiceFront shopcarService = Factory.getServiceInstance("shopcar.service.front") ;
			try {
				mav.add(shopcarService.list(super.getMid()));
			} catch (Exception e) {
				e.printStackTrace();
			}
			return mav ;
		} else {
			super.setUrlAndMsg("index.page", "unaction.msg");
			ModelAndView mav = new ModelAndView(
					super.getUrl("forward.front.page"));
			return mav;
		}
	}
	
	public void add(int gid) {
		if (super.isRoleAndAction("shopcar", "shopcar:add")) {
			IShopcarServiceFront shopcarService = Factory.getServiceInstance("shopcar.service.front") ;
			try {
				super.print(shopcarService.add(super.getMid(), gid));
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			super.print(false);
		}
	}
}
