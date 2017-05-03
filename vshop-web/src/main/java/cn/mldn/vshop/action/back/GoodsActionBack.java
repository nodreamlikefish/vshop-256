package cn.mldn.vshop.action.back;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.mldn.util.action.ActionSplitPageUtil;
import cn.mldn.util.action.ActionUploadUtil;
import cn.mldn.util.factory.Factory;
import cn.mldn.util.web.ModelAndView;
import cn.mldn.util.web.ParameterValueUtil;
import cn.mldn.util.web.ServletObjectUtil;
import cn.mldn.vshop.service.back.IGoodsServiceBack;
import cn.mldn.vshop.util.action.AbstractBaseAction;
import cn.mldn.vshop.vo.Goods;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class GoodsActionBack extends AbstractBaseAction {
	private static final String GOODS_FLAG = "商品";

	public void listDetails() {
		if (super.isRoleAndAction("goods", "goods:list")) {
			IGoodsServiceBack goodsServiceBack = Factory
					.getServiceInstance("goods.service.back");
			try {
				List<Goods> all = goodsServiceBack.listDetails();
				JSONObject obj = new JSONObject();
				JSONArray array = new JSONArray();
				Iterator<Goods> iter = all.iterator();
				while (iter.hasNext()) {
					array.add(iter.next());
				}
				obj.put("allGoodss", array);
				File file = new File(ServletObjectUtil.getServletContext().getRealPath("/goods.json")) ;
				PrintWriter out = new PrintWriter(new FileOutputStream(file)) ;
				out.println(obj);
				out.close(); 
				super.print(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			super.print(false);
		}
	}

	public void delete(int gids[]) {
		if (super.isRoleAndAction("goods", "goods:list")) {
			// System.out.println(Arrays.toString(gids));
			Set<Integer> all = new HashSet<Integer>();
			for (int x = 0; x < gids.length; x++) {
				all.add(gids[x]);
			}
			IGoodsServiceBack goodsServiceBack = Factory
					.getServiceInstance("goods.service.back");
			// System.out.println(all + " 0- " + goodsServiceBack);
			try {
				super.print(goodsServiceBack.delete(all));
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			super.print(false);
		}
	}

	public String edit(Goods vo) {
		if (super.isRoleAndAction("goods", "goods:edit")) {
			vo.setMid(super.getMid());
			ActionUploadUtil auu = new ActionUploadUtil("upload/goods");

			try {
				if (auu.isUpload()) {
					vo.setPhoto(auu.createSingleFileName());
				} else {
					vo.setPhoto(ParameterValueUtil.getParameter("oldphoto"));
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			IGoodsServiceBack goodsServiceBack = Factory
					.getServiceInstance("goods.service.back");
			try {
				if (goodsServiceBack.edit(vo)) {
					super.setUrlAndMsg("goods.list.action",
							"action.edit.success", GOODS_FLAG);
					if (auu.isUpload()) {
						auu.saveSingleFile(); // 文件保存
					}
				} else {
					super.setUrlAndMsg("goods.list.action",
							"action.edit.failure", GOODS_FLAG);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			super.setUrlAndMsg("index.page", "unaction.msg");
		}
		return super.getUrl("forward.back.page");
	}

	public ModelAndView editPre(int gid) {
		if (super.isRoleAndAction("goods", "goods:edit")) {
			ModelAndView mav = new ModelAndView(
					super.getUrl("goods.edit.page"));
			IGoodsServiceBack goodsServiceBack = Factory
					.getServiceInstance("goods.service.back");
			try {
				mav.add(goodsServiceBack.getEditPre(gid));
			} catch (Exception e) {
				e.printStackTrace();
			}
			return mav;
		} else {
			super.setUrlAndMsg("index.page", "unaction.msg");
			ModelAndView mav = new ModelAndView(
					super.getUrl("forward.back.page"));
			return mav;
		}
	}

	public void show(int gid) {
		if (super.isRoleAndAction("goods", "goods:list")) {
			IGoodsServiceBack goodsServiceBack = Factory
					.getServiceInstance("goods.service.back");
			JSONObject obj = new JSONObject();
			try {
				Map<String, Object> map = goodsServiceBack.show(gid);
				Iterator<Map.Entry<String, Object>> iter = map.entrySet()
						.iterator();
				while (iter.hasNext()) {
					Map.Entry<String, Object> me = iter.next();
					obj.put(me.getKey(), me.getValue());
				}
				super.print(obj);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			super.print("{\"error\":\"unauth\"}");
		}
	}

	public ModelAndView list() {
		if (super.isRoleAndAction("goods", "goods:list")) {
			ActionSplitPageUtil aspu = new ActionSplitPageUtil(
					"商品名称:title|商品描述:note", "goods.list.action");
			ModelAndView mav = new ModelAndView(
					super.getUrl("goods.list.page"));
			IGoodsServiceBack goodsServiceBack = Factory
					.getServiceInstance("goods.service.back");
			try {
				mav.add(goodsServiceBack.list(aspu.getCurrentPage(),
						aspu.getLineSize(), aspu.getColumn(),
						aspu.getKeyWord()));
			} catch (Exception e) {
				e.printStackTrace();
			}
			return mav;
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
			ActionUploadUtil auu = new ActionUploadUtil("upload/goods");
			try {
				vo.setPhoto(auu.createSingleFileName());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			IGoodsServiceBack goodsServiceBack = Factory
					.getServiceInstance("goods.service.back");
			try {
				if (goodsServiceBack.add(vo)) {
					super.setUrlAndMsg("goods.add.action", "action.add.success",
							GOODS_FLAG);
					auu.saveSingleFile(); // 文件保存
				} else {
					super.setUrlAndMsg("goods.add.action", "action.add.failure",
							GOODS_FLAG);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			super.setUrlAndMsg("index.page", "unaction.msg");
		}
		return super.getUrl("forward.back.page");
	}

	public ModelAndView addPre() {
		if (super.isRoleAndAction("goods", "goods:add")) {
			ModelAndView mav = new ModelAndView(super.getUrl("goods.add.page"));
			IGoodsServiceBack goodsServiceBack = Factory
					.getServiceInstance("goods.service.back");
			try {
				mav.add(goodsServiceBack.getAddPre());
			} catch (Exception e) {
				e.printStackTrace();
			}
			return mav;
		} else {
			super.setUrlAndMsg("index.page", "unaction.msg");
			ModelAndView mav = new ModelAndView(
					super.getUrl("forward.back.page"));
			return mav;
		}
	}
}
