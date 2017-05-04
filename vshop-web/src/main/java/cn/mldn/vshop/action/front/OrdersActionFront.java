package cn.mldn.vshop.action.front;

import java.util.HashSet;
import java.util.Set;

import cn.mldn.util.factory.Factory;
import cn.mldn.util.web.ModelAndView;
import cn.mldn.vshop.service.front.IOrdersServiceFront;
import cn.mldn.vshop.util.action.AbstractBaseAction;

public class OrdersActionFront extends AbstractBaseAction {
	public ModelAndView createPre(int gid[]) { // 进行订单的创建处理操作
		if (super.isRoleAndAction("orders", "orders:add")) {
			Set<Long> ids= new HashSet<Long>() ;
			for (int x = 0 ; x < gid.length ; x ++) {
				ids.add((long)gid[x]) ;
			}
			ModelAndView mav = new ModelAndView(super.getUrl("orders.add.page")) ;
			IOrdersServiceFront ordersService = Factory.getServiceInstance("orders.service.front") ;
			try {
				mav.add(ordersService.getAddPre(super.getMid(), ids));
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
}
