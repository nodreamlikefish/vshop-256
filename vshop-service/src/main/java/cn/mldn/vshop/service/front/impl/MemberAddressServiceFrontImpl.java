package cn.mldn.vshop.service.front.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.mldn.util.factory.Factory;
import cn.mldn.vshop.dao.IAddressDAO;
import cn.mldn.vshop.dao.ICityDAO;
import cn.mldn.vshop.dao.IProvinceDAO;
import cn.mldn.vshop.service.abs.AbstractService;
import cn.mldn.vshop.service.front.IMemberAddressServiceFront;
import cn.mldn.vshop.vo.Address;

public class MemberAddressServiceFrontImpl extends AbstractService
		implements
			IMemberAddressServiceFront {
	@Override
	public boolean delete(String mid, int adid) throws Exception {
		IAddressDAO addressDAO = Factory.getDAOInstance("address.dao") ;
		return addressDAO.doRemoveByIdAndMember(mid, adid); 
	}
	@Override
	public Map<String, Object> getEditPre(String mid,int adid) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		IAddressDAO addressDAO = Factory.getDAOInstance("address.dao") ;
		Address vo = addressDAO.findByIdAndMemeber(mid, adid) ;
		if (vo != null) {
			IProvinceDAO provinceDAO = Factory.getDAOInstance("province.dao");
			map.put("allProvinces", provinceDAO.findAll()) ;
			ICityDAO cityDAO = Factory.getDAOInstance("city.dao") ;
			map.put("allCitys", cityDAO.findAllByProvince(vo.getPid())) ;
		}
		map.put("address",vo) ;
		return map ;
	}
	@Override
	public boolean edit(Address vo) throws Exception {
		IAddressDAO addressDAO = Factory.getDAOInstance("address.dao") ;
		return addressDAO.doUpdateByMember(vo); 
	}
	
	@Override
	public boolean editDeflag(String mid, int adid) throws Exception {
		IAddressDAO addressDAO = Factory.getDAOInstance("address.dao") ;
		if (addressDAO.doUpdateDeflag(mid, 0)) {	// 取消所有用户的默认状态
			return addressDAO.doUpdateDeflag(mid, adid, 1) ;	// 设置一个地址编号的默认状态
		}
		return false;
	}
	@Override
	public List<Address> listByMember(String mid) throws Exception {
		IAddressDAO addressDAO = Factory.getDAOInstance("address.dao") ;
		return addressDAO.findAllByMember(mid);
	}
	
	@Override
	public Map<String, Object> getAddPre() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		IProvinceDAO provinceDAO = Factory.getDAOInstance("province.dao");
		map.put("allProvinces", provinceDAO.findAll()) ;
		return map ;
	}
	@Override
	public boolean add(Address vo) throws Exception {
		IAddressDAO addressDAO = Factory.getDAOInstance("address.dao") ;
		// 1、需要判断当前的用户具有的地址数量，这个mid一般都是通过session取得的
		if (addressDAO.getCountByMember(vo.getMid()) > 0) {	// 已经有地址了
			vo.setDeflag(0); 
		} else {
			vo.setDeflag(1);	// 第一个地址为默认地址
		}
		return addressDAO.doCreate(vo); 
	}
}
