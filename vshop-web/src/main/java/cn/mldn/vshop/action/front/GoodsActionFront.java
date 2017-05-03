package cn.mldn.vshop.action.front;

import cn.mldn.util.action.ActionSplitPageUtil;
import cn.mldn.util.factory.Factory;
import cn.mldn.util.web.ModelAndView;
import cn.mldn.vshop.service.front.IGoodsServiceFront;
import cn.mldn.vshop.util.action.AbstractBaseAction;

public class GoodsActionFront extends AbstractBaseAction {
	
	public ModelAndView search() {
		ModelAndView mav = new ModelAndView(super.getUrl("goods.list.search.page")) ;
		IGoodsServiceFront goodsService = Factory.getServiceInstance("goods.service.front") ;
		try {
			ActionSplitPageUtil aspu = new ActionSplitPageUtil("", "goods.list.search.action") ;
			mav.add(goodsService.list(aspu.getCurrentPage(), 12, aspu.getKeyWord()));
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return mav ;
	} 
	
	public ModelAndView list(int sid) {
		ModelAndView mav = new ModelAndView(super.getUrl("goods.list.subitem.page")) ;
		IGoodsServiceFront goodsService = Factory.getServiceInstance("goods.service.front") ;
		try {
			ActionSplitPageUtil aspu = new ActionSplitPageUtil("", "goods.list.subitem.action") ;
			mav.add(goodsService.listBySubitem(sid, aspu.getCurrentPage(), aspu.getLineSize()));
			mav.add("paramName","sid");
			mav.add("paramValue",sid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav ;
	} 
	
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
