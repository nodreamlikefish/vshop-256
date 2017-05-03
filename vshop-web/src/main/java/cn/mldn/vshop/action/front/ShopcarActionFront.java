package cn.mldn.vshop.action.front;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import cn.mldn.util.factory.Factory;
import cn.mldn.util.web.ModelAndView;
import cn.mldn.util.web.ParameterValueUtil;
import cn.mldn.vshop.service.front.IShopcarServiceFront;
import cn.mldn.vshop.util.action.AbstractBaseAction;

public class ShopcarActionFront extends AbstractBaseAction {

	public void delete() {
		if (super.isRoleAndAction("shopcar", "shopcar:delete")) {
			String val = ParameterValueUtil.getParameter("sc"); // 要修改的数据
			Set<Integer> sc = new HashSet<Integer>() ;
			String result [] = val.split(",") ;
			for (int x = 0 ; x < result.length ; x ++) {
				sc.add(Integer.parseInt(result[x])) ;
			}
			IShopcarServiceFront shopcarService = Factory
					.getServiceInstance("shopcar.service.front");
			try {
				super.print(shopcarService.deleteByMember(super.getMid(), sc));
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			super.print(false);
		}
	}
	
	public void editAmount() { // 该接收的数据必须通过自己手工处理了
		if (super.isRoleAndAction("shopcar", "shopcar:edit")) {
			// 数据的格式：商品编号:数量,商品编号:数量,...
			String val = ParameterValueUtil.getParameter("sc"); // 要修改的数据
			Map<Integer, Integer> map = new HashMap<Integer, Integer>();
			String result[] = val.split(",");
			for (int x = 0; x < result.length; x++) {
				String temp[] = result[x].split(":");
				map.put(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]));
			}
			IShopcarServiceFront shopcarService = Factory
					.getServiceInstance("shopcar.service.front");
			try {
				super.print(shopcarService.editAmounts(super.getMid(), map));
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			super.print(false);
		}
	}

	public void edit(int gid, int amount) {
		if (super.isRoleAndAction("shopcar", "shopcar:edit")) {
			IShopcarServiceFront shopcarService = Factory
					.getServiceInstance("shopcar.service.front");
			try {
				super.print(
						shopcarService.editAmount(super.getMid(), gid, amount));
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			super.print(false);
		}
	}

	public ModelAndView list() {
		if (super.isRoleAndAction("shopcar", "shopcar:list")) {
			ModelAndView mav = new ModelAndView(
					super.getUrl("shopcar.list.page"));
			IShopcarServiceFront shopcarService = Factory
					.getServiceInstance("shopcar.service.front");
			try {
				mav.add(shopcarService.list(super.getMid()));
			} catch (Exception e) {
				e.printStackTrace();
			}
			return mav;
		} else {
			super.setUrlAndMsg("index.page", "unaction.msg");
			ModelAndView mav = new ModelAndView(
					super.getUrl("forward.front.page"));
			return mav;
		}
	}

	public void add(int gid) {
		if (super.isRoleAndAction("shopcar", "shopcar:add")) {
			IShopcarServiceFront shopcarService = Factory
					.getServiceInstance("shopcar.service.front");
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
