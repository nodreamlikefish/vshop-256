package cn.mldn.vshop.action.front;

import java.util.HashSet;
import java.util.Set;

import cn.mldn.util.factory.Factory;
import cn.mldn.util.web.ModelAndView;
import cn.mldn.util.web.ParameterValueUtil;
import cn.mldn.vshop.service.front.IOrdersServiceFront;
import cn.mldn.vshop.util.action.AbstractBaseAction;

public class OrdersActionFront extends AbstractBaseAction {
	private static final String ORDERS_FLAG = "订单" ;
	
	public String create(int adid,String note) {
		if (super.isRoleAndAction("orders", "orders:add")) {
			String gid [] = ParameterValueUtil.getParameterValues("gid") ;
			Set<Long> ids= new HashSet<Long>() ;
			for (int x = 0 ; x < gid.length ; x ++) {
				ids.add(Long.parseLong(gid[x])) ;
			}
			IOrdersServiceFront ordersService = Factory.getServiceInstance("orders.service.front") ;
			try {
				if (ordersService.add(super.getMid(), ids, adid, note)) {
					super.setUrlAndMsg("orders.list.action", "action.add.success", ORDERS_FLAG);
				} else {
					super.setUrlAndMsg("shopcar.list.action", "action.add.failure", ORDERS_FLAG);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			super.setUrlAndMsg("index.page", "unaction.msg");
		}
		return super.getUrl("forward.front.page");
	}
	
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
