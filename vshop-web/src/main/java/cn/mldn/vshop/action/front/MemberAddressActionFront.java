package cn.mldn.vshop.action.front;

import cn.mldn.util.factory.Factory;
import cn.mldn.util.web.ModelAndView;
import cn.mldn.vshop.service.front.IMemberAddressServiceFront;
import cn.mldn.vshop.util.action.AbstractBaseAction;
import cn.mldn.vshop.vo.Address;

public class MemberAddressActionFront extends AbstractBaseAction{
	private static final String ADDRESS_FLAG = "收件地址" ;
	private IMemberAddressServiceFront memberService = Factory.getServiceInstance("memberaddress.service.front") ;
	/**
	 * 进行默认地址的编辑控制
	 * @param adid 要设置为默认地址的地址编号
	 */
	public void editDeflag(int adid) {
		try {
			super.print(this.memberService.editDeflag(super.getMid(), adid));
		} catch (Exception e) {
			super.print(false); 
			e.printStackTrace();
		} 
	}
	
	/**
	 * 进行数据的列表显示，列表数据的属性名称为allAddresss
	 * @return 跳转路径
	 */
	public ModelAndView list() {
		if (super.isRoleAndAction("address", "address:list")) {
			ModelAndView mav = new ModelAndView(super.getUrl("member.address.list.page")) ;
			try {
				mav.add("allAddresss", this.memberService.listByMember(super.getMid()));
			} catch (Exception e) {
				e.printStackTrace();
			}
			return mav ;
		} else {
			super.setUrlAndMsg("index.page", "unaction.msg");
			ModelAndView mav = new ModelAndView(super.getUrl("forward.front.page")) ;
			return mav ; 
		}
	}
	
	/**
	 * 实现用户地址数据的追加
	 * @param vo 用户的地址信息
	 * @return 返回到信息提示页
	 */
	public String add(Address vo) {
		if (super.isRoleAndAction("address", "address:add")) {
			vo.setMid(super.getMid()); 	// 设置当前的操作用户编号
			// member.address.list.action
			try {
				if (this.memberService.add(vo)) {
					super.setUrlAndMsg("member.address.list.action", "action.add.success",ADDRESS_FLAG);
				} else {
					super.setUrlAndMsg("member.address.list.action", "action.add.failure",ADDRESS_FLAG);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return super.getUrl("forward.front.page") ;
		} else {
			super.setUrlAndMsg("index.page", "unaction.msg");
			return super.getUrl("forward.front.page") ;
		}
	}
	
	/**
	 * 进行用户地址信息增加操作处理，调用IMemberAddressServiceFront接口方法
	 * @return 包含有全部的省份信息
	 */
	public ModelAndView addPre() {
		if (super.isRoleAndAction("address", "address:add")) {
			ModelAndView mav = new ModelAndView(super.getUrl("member.address.add.page")) ;
			try {
				mav.add(this.memberService.getAddPre());
			} catch (Exception e) {
				e.printStackTrace();
			}
			return mav ;
		} else {
			super.setUrlAndMsg("index.page", "unaction.msg");
			ModelAndView mav = new ModelAndView(super.getUrl("forward.front.page")) ;
			return mav ;
		}
	}
}
