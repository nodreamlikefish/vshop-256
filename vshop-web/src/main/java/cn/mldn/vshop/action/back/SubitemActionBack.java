package cn.mldn.vshop.action.back;

import cn.mldn.util.factory.Factory;
import cn.mldn.util.web.ModelAndView;
import cn.mldn.vshop.service.back.ISubitemServiceBack;
import cn.mldn.vshop.util.action.AbstractBaseAction;
import cn.mldn.vshop.vo.Subitem;
import net.sf.json.JSONObject;

public class SubitemActionBack extends AbstractBaseAction {
	
	public void listAjax(int iid) {
		if (super.isRoleAndAction("goods", "goods:add")) {
			ISubitemServiceBack itemService = Factory.getServiceInstance("subitem.service.back") ;
			JSONObject obj = new JSONObject() ;
			try {
				obj.put("allSubitems", itemService.listByItem(iid))  ;
				super.print(obj);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			super.print("{\"error\":\"unauth\"}");
		}
	}	
	
	public void edit(Subitem vo) {
		if (super.isRoleAndAction("goods", "goods:item")) {
			try {
				ISubitemServiceBack itemService = Factory.getServiceInstance("subitem.service.back") ;
				super.print(itemService.edit(vo)); 
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			super.print(false);
		}
	}
	
	public ModelAndView list(int iid) {
		if (super.isRoleAndAction("goods", "goods:item")) {
			ModelAndView mav = new ModelAndView(
					super.getUrl("subitem.list.page"));
			try {
				ISubitemServiceBack itemService = Factory.getServiceInstance("subitem.service.back") ;
				mav.add("allSubitems", itemService.listByItem(iid));  
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
