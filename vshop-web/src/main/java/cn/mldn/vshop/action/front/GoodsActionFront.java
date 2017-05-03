package cn.mldn.vshop.action.front;

import cn.mldn.util.factory.Factory;
import cn.mldn.util.web.ModelAndView;
import cn.mldn.vshop.service.front.IGoodsServiceFront;
import cn.mldn.vshop.util.action.AbstractBaseAction;

public class GoodsActionFront extends AbstractBaseAction {
	public ModelAndView show(int gid) {
		ModelAndView mav = new ModelAndView(super.getUrl("goods.show.page")) ;
		IGoodsServiceFront goodsService = Factory.getServiceInstance("goods.service.front") ;
		try {
			mav.add("goods",goodsService.get(gid));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav ;
	} 
}
