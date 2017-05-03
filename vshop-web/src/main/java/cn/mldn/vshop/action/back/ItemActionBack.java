package cn.mldn.vshop.action.back;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import cn.mldn.util.factory.Factory;
import cn.mldn.util.web.ModelAndView;
import cn.mldn.util.web.ServletObjectUtil;
import cn.mldn.vshop.service.back.IItemServiceBack;
import cn.mldn.vshop.util.action.AbstractBaseAction;
import cn.mldn.vshop.vo.Item;
import cn.mldn.vshop.vo.Subitem;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ItemActionBack extends AbstractBaseAction {
	
	public void listDetails() {
		if (super.isRoleAndAction("goods", "goods:item")) {
			IItemServiceBack itemService = Factory.getServiceInstance("item.service.back") ;
			try {
				Map<Item,List<Subitem>> map = itemService.listDetails() ;
				JSONObject all = new JSONObject() ;	// 总的JSON数据
				JSONArray itemArray = new JSONArray() ;
				Iterator<Map.Entry<Item,List<Subitem>>> iter = map.entrySet().iterator() ;
				while (iter.hasNext()) {
					Map.Entry<Item,List<Subitem>> me = iter.next() ;
					JSONObject itemObj = new JSONObject() ;
					itemObj.put("item", me.getKey()) ;	// 设置一级栏目
					JSONArray subitem = new JSONArray() ;
					Iterator<Subitem> subIter = me.getValue().iterator() ;
					while (subIter.hasNext()) {
						subitem.add(subIter.next()) ;
					}
					itemObj.put("subitems", subitem) ;
					itemArray.add(itemObj) ;
				}
				all.put("items", itemArray) ;
				File file = new File(ServletObjectUtil.getServletContext().getRealPath("/item.json")) ;
				PrintWriter out = new PrintWriter(new FileWriter(file)) ;
				out.print(all);
				out.close();
				super.print(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			super.print(false) ;
		}
	}
	
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
