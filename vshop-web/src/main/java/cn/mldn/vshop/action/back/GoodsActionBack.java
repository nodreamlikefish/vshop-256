package cn.mldn.vshop.action.back;

import java.util.Iterator;
import java.util.Map;

import cn.mldn.util.action.ActionSplitPageUtil;
import cn.mldn.util.action.ActionUploadUtil;
import cn.mldn.util.factory.Factory;
import cn.mldn.util.web.ModelAndView;
import cn.mldn.vshop.service.back.IGoodsServiceBack;
import cn.mldn.vshop.util.action.AbstractBaseAction;
import cn.mldn.vshop.vo.Goods;
import net.sf.json.JSONObject;

public class GoodsActionBack extends AbstractBaseAction {
	private static final String GOODS_FLAG = "商品" ;
	
	public void show(int gid) {
		if (super.isRoleAndAction("goods", "goods:list")) {
			IGoodsServiceBack goodsServiceBack = Factory.getServiceInstance("goods.service.back") ;
			JSONObject obj = new JSONObject() ;
			try {
				Map<String,Object> map =  goodsServiceBack.show(gid) ;
				Iterator<Map.Entry<String,Object>> iter = map.entrySet().iterator() ;
				while (iter.hasNext()) {
					Map.Entry<String, Object> me = iter.next() ;
					obj.put(me.getKey(), me.getValue()) ;
				}
				super.print(obj);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			super.print("{\"error\":\"unauth\"}") ;
		}
	}
	
	public ModelAndView list() {
		if (super.isRoleAndAction("goods", "goods:list")) {
			ActionSplitPageUtil aspu = new ActionSplitPageUtil("商品名称:title|商品描述:note","goods.list.action") ;
			ModelAndView mav = new ModelAndView(super.getUrl("goods.list.page")) ;
			IGoodsServiceBack goodsServiceBack = Factory.getServiceInstance("goods.service.back") ;
			try {
				mav.add(goodsServiceBack.list(aspu.getCurrentPage(), aspu.getLineSize(), aspu.getColumn(), aspu.getKeyWord()));
			} catch (Exception e) {
				e.printStackTrace();
			}
			return mav ;
		} else {
			super.setUrlAndMsg("index.page", "unaction.msg");
			ModelAndView mav = new ModelAndView(
					super.getUrl("forward.back.page"));
			return mav;
		}
	}
	
	public String add(Goods vo) {
		if (super.isRoleAndAction("goods", "goods:add")) {
			vo.setMid(super.getMid());
			ActionUploadUtil auu = new ActionUploadUtil("upload/goods") ;
			try {
				vo.setPhoto(auu.createSingleFileName());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			IGoodsServiceBack goodsServiceBack = Factory.getServiceInstance("goods.service.back") ;
			try {
				if(goodsServiceBack.add(vo)) {
					super.setUrlAndMsg("goods.add.action", "action.add.success", GOODS_FLAG);
					auu.saveSingleFile(); 	// 文件保存
				} else {
					super.setUrlAndMsg("goods.add.action", "action.add.failure", GOODS_FLAG);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else {
			super.setUrlAndMsg("index.page", "unaction.msg");
		}
		return super.getUrl("forward.back.page");
	}
	
	public ModelAndView addPre() {
		if (super.isRoleAndAction("goods", "goods:add")) {
			ModelAndView mav = new ModelAndView(super.getUrl("goods.add.page")) ;
			IGoodsServiceBack goodsServiceBack = Factory.getServiceInstance("goods.service.back") ;
			try {
				mav.add(goodsServiceBack.getAddPre());
			} catch (Exception e) {
				e.printStackTrace();
			}
			return mav ;
		} else {
			super.setUrlAndMsg("index.page", "unaction.msg");
			ModelAndView mav = new ModelAndView(
					super.getUrl("forward.back.page"));
			return mav;
		}
	}
}
