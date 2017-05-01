package cn.mldn.vshop.action.front;

import cn.mldn.util.factory.Factory;
import cn.mldn.vshop.service.front.ICityServiceFront;
import cn.mldn.vshop.util.action.AbstractBaseAction;
import net.sf.json.JSONObject;

public class CityActionFront extends AbstractBaseAction{
	private ICityServiceFront cityServiceFront = Factory.getServiceInstance("city.service.front") ;
	/**
	 * 主要进行Ajax的异步调用，返回一个省份对应的所有城市信息
	 * @param pid 省份编号
	 */
	public void list(int pid) {
		JSONObject obj = new JSONObject() ;
		try {
			obj.put("allCitys", this.cityServiceFront.listByProvince(pid)) ;
			super.print(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
