package cn.mldn.vshop.dao;

import java.sql.SQLException;
import java.util.List;

import cn.mldn.util.dao.IBaseDAO;
import cn.mldn.vshop.vo.City;

public interface ICityDAO extends IBaseDAO<Integer, City> {
	/**
	 * 根据省份取得所有的城市数据信息
	 * @param pid 省份编号
	 * @return 所有的城市信息，如果没有城市则该数据返回的空集合（size()==0）
	 * @throws SQLException SQL异常
	 */
	public List<City> findAllByProvince(Integer pid) throws SQLException ;
}
