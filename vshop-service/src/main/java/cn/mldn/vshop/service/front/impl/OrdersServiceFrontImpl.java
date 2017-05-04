package cn.mldn.vshop.service.front.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.mldn.util.factory.Factory;
import cn.mldn.vshop.dao.IAddressDAO;
import cn.mldn.vshop.dao.IDetailsDAO;
import cn.mldn.vshop.dao.IGoodsDAO;
import cn.mldn.vshop.dao.IOrdersDAO;
import cn.mldn.vshop.dao.IShopcarDAO;
import cn.mldn.vshop.service.abs.AbstractService;
import cn.mldn.vshop.service.front.IOrdersServiceFront;
import cn.mldn.vshop.vo.Address;
import cn.mldn.vshop.vo.Details;
import cn.mldn.vshop.vo.Goods;
import cn.mldn.vshop.vo.Orders;

public class OrdersServiceFrontImpl extends AbstractService
		implements
			IOrdersServiceFront {
	
	@Override
	public Map<String, Object> get(String mid, int oid) throws Exception {
		Map<String, Object> map = new HashMap<String,Object>() ;
		// 1、根据用户编号和订单编号获取订单信息
		IOrdersDAO ordersDAO = Factory.getDAOInstance("orders.dao") ;
		Orders orders = ordersDAO.findByMemberAndId(mid, oid) ;
		if (orders != null) {	// 已经获取了该订单的信息
			map.put("orders", orders) ;
			// 2、应该根据订单信息获取全部的详情信息，但是考虑到随后还需要进行EL控制。
			IDetailsDAO detailsDAO = Factory.getDAOInstance("details.dao") ;
			Map<Long,Integer> detailsMap = detailsDAO.findAllByOrders(oid) ;
			map.put("allDetailss", detailsMap) ;
			// 3、取得所有的购买的商品信息
			IGoodsDAO goodsDAO = Factory.getDAOInstance("goods.dao") ;
			map.put("allGoodss", goodsDAO.findAllByIds(detailsMap.keySet())) ;
		}
		return map;
	}
	
	@Override
	public Map<String, Object> list(String mid, int currentPage, int lineSize)
			throws Exception {
		Map<String,Object> map = new HashMap<String,Object>() ;
		IOrdersDAO ordersDAO = Factory.getDAOInstance("orders.dao") ;
		map.put("allOrderss", ordersDAO.findAllSplitByMember(mid, currentPage, lineSize)) ;
		map.put("allRecorders", ordersDAO.getAllCountByMember(mid)) ;
		return map;
	}

	@Override
	public boolean add(String mid, Set<Long> gid, Integer adid,String note)
			throws Exception {
		// 1、如果要想进行订单的创建，那么首先必须知道所购买的商品信息有那些
		IGoodsDAO goodsDAO = Factory.getDAOInstance("goods.dao") ;
		List<Goods> allGoods = goodsDAO.findAllByIds(gid) ;	// 要购买的全部的商品信息
		// 2、如果要想计算出商品的总价，那么还需要获取商品的数量信息，数量信息在购物车中保存
		IShopcarDAO shopcarDAO = Factory.getDAOInstance("shopcar.dao") ;
		Map<Long,Integer> shopcarMap = shopcarDAO.findAllByMember(mid, gid) ;
		// 3、有了商品的信息和购买的数量信息就可以进行总价的计算
		double allPrice = 0.0 ;	// 保存总价信息
		Iterator<Goods> goodsIter = allGoods.iterator() ;
		while (goodsIter.hasNext()) {
			Goods goods = goodsIter.next() ;
			allPrice += goods.getPrice() * shopcarMap.get(goods.getGid()) ;
		}
		// 4、根据用户传入的地址编号取得地址的详细信息
		IAddressDAO addressDAO = Factory.getDAOInstance("address.dao") ;
		Address address = addressDAO.findByIdAndMemeber(mid,adid) ;
		// 5、订单的全部信息已经准备到位，如果要进行订单创建那么就必须使用IOrdersDAO中的doCreate()方法
		Orders ordersVO = new Orders() ;
		ordersVO.setAddress(address.getReceiver() + "，" + address.getPhone() + "，" + address.getAddr());
		ordersVO.setSubdate(new Date());
		ordersVO.setPrice(allPrice);	// 设置总价
		ordersVO.setMid(mid);
		ordersVO.setNote(note);
		// 6、创建订单信息
		IOrdersDAO ordersDAO = Factory.getDAOInstance("orders.dao") ;
		if (ordersDAO.doCreate(ordersVO)) {	// 创建订单
			// 7、取得当前订单的信息项
			Integer oid = ordersDAO.findCreateId()  ;	// 取得当前增长数据
			// 8、需要向订单详情中进行信息的保存；
			IDetailsDAO detailsDAD = Factory.getDAOInstance("details.dao") ;
			Iterator<Goods> detailsIter = allGoods.iterator() ;
			while (detailsIter.hasNext()) {
				Goods g = detailsIter.next() ;
				Details details = new Details() ;
				details.setOid(oid); 	// 设置订单编号
				details.setGid(g.getGid());
				details.setAmount(shopcarMap.get(g.getGid()));
				detailsDAD.doCreate(details) ;	// 保存订单详情
			}
			// 9、订单详情信息保存完成之后随后就需要进行购物车记录的删除
			return shopcarDAO.doRemoveByMemberAndGoods2(mid, gid) ;
		}
		return false;
	}
	
	@Override
	public Map<String, Object> getAddPre(String mid, Set<Long> gid)
			throws Exception {
		Map<String,Object> map = new HashMap<String,Object>() ;
		IShopcarDAO shopcarDAO = Factory.getDAOInstance("shopcar.dao") ;
		// 1、取得现在所有购物车中的商品数量，那么最终需要的时候是通过该数量进行信息显示。
		map.put("allShopcars", shopcarDAO.findAllByMember(mid, gid)) ;
		// 2、还需要知道指定商品编号对应的数据信息
		IGoodsDAO goodsDAO = Factory.getDAOInstance("goods.dao") ;
		map.put("allGoodss",goodsDAO.findAllByIds(gid) ) ;
		// 3、需要知道用户所有的配送地址信息
		IAddressDAO addressDAO = Factory.getDAOInstance("address.dao") ;
		map.put("allAddress", addressDAO.findAllByMember(mid)) ;
		return map;
	}

}
