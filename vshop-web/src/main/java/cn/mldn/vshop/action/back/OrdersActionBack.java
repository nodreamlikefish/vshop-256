package cn.mldn.vshop.action.back;

import cn.mldn.util.action.ActionSplitPageUtil;
import cn.mldn.util.factory.Factory;
import cn.mldn.util.web.ModelAndView;
import cn.mldn.vshop.service.back.IOrdersServiceBack;
import cn.mldn.vshop.util.action.AbstractBaseAction;

public class OrdersActionBack extends AbstractBaseAction {
	
	public ModelAndView show(int oid) {
		if (super.isRoleAndAction("orders", "orders:show")) {
			ModelAndView mav = new ModelAndView(super.getUrl("back.orders.details.page")) ;
			IOrdersServiceBack ordersService = Factory.getServiceInstance("orders.service.back") ;
			try {
				mav.add(ordersService.get(oid));
			} catch (Exception e) {
				e.printStackTrace();
			}
			return mav ;
		}else {
			super.setUrlAndMsg("index.page", "unaction.msg");
			ModelAndView mav = new ModelAndView(
					super.getUrl("forward.back.page"));
			return mav;
		}
	}
	
	public ModelAndView list() {
		if (super.isRoleAndAction("orders", "orders:list")) {
			ModelAndView mav = new ModelAndView(super.getUrl("back.orders.list.page")) ;
			ActionSplitPageUtil aspu = new ActionSplitPageUtil("用户名:id|收件人:receiver|收件地址:addr", "back.orders.list.action") ;
			IOrdersServiceBack ordersService = Factory.getServiceInstance("orders.service.back") ;
			try {
				mav.add(ordersService.list(aspu.getCurrentPage(),
						aspu.getLineSize(), aspu.getColumn(), aspu.getKeyWord()));
			} catch (Exception e) {
				e.printStackTrace();
			}
			return mav ;
		}else {
			super.setUrlAndMsg("index.page", "unaction.msg");
			ModelAndView mav = new ModelAndView(
					super.getUrl("forward.back.page"));
			return mav;
		}
	}
}
