package cn.mldn.vshop.action.back;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;

import cn.mldn.util.action.ActionSplitPageUtil;
import cn.mldn.util.enctype.PasswordUtil;
import cn.mldn.util.factory.Factory;
import cn.mldn.util.web.ModelAndView;
import cn.mldn.vshop.service.back.IMemberServiceBack;
import cn.mldn.vshop.util.action.AbstractBaseAction;
import net.sf.json.JSONObject;

public class MemberActionBack extends AbstractBaseAction {

	public void details(String mid) {
		if (super.isRoleAndAction("member", "member:edit")) {
			IMemberServiceBack memberService = Factory
					.getServiceInstance("member.service.back");
			try {
				Map<String, Object> map = memberService.getDetails(mid);
				JSONObject obj = new JSONObject();
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

	public void editLocked(String[] mid, int locked) {
		Set<String> ids = new HashSet<String>();
		CollectionUtils.addAll(ids, mid); // 直接将接收到的数组转换为Set集合
		if (super.isRoleAndAction("member", "member:edit")) {
			IMemberServiceBack memberService = Factory
					.getServiceInstance("member.service.back");
			try {
				super.print(memberService.editLocked(ids, locked));
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			super.print(false);
		}
	}

	public void editPassword(String mid, String password) {
		System.out.println(password);
		if (super.isRoleAndAction("member", "member:edit")) {
			IMemberServiceBack memberService = Factory
					.getServiceInstance("member.service.back");
			try {
				super.print(memberService.editPassword(mid,
						PasswordUtil.getPassword(password)));
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			super.print(false);
		}
	}

	public ModelAndView list() {
		if (super.isRoleAndAction("member", "member:list")) {
			ActionSplitPageUtil aspu = new ActionSplitPageUtil(
					"用户名:mid|姓名:name|联系电话:phone|邮箱:email",
					"member.list.action");
			IMemberServiceBack memberService = Factory
					.getServiceInstance("member.service.back");
			ModelAndView mav = new ModelAndView(
					super.getUrl("member.list.page"));
			try {
				mav.add(memberService.list(aspu.getCurrentPage(),
						aspu.getLineSize(), aspu.getColumn(),
						aspu.getKeyWord()));
			} catch (Exception e) {
				e.printStackTrace();
			}
			return mav;
		} else {
			super.setUrlAndMsg("index.page", "unaction.msg");
			ModelAndView mav = new ModelAndView(
					super.getUrl("back.front.page"));
			return mav;
		}
	}
}
