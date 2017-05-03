package cn.mldn.vshop.action.front;

import cn.mldn.util.factory.Factory;
import cn.mldn.vshop.service.front.IShopcarServiceFront;
import cn.mldn.vshop.util.action.AbstractBaseAction;

public class ShopcarActionFront extends AbstractBaseAction {
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
