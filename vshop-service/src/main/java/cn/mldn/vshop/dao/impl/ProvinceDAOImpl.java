package cn.mldn.vshop.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import cn.mldn.util.dao.abs.AbstractDAO;
import cn.mldn.vshop.dao.IProvinceDAO;
import cn.mldn.vshop.vo.Province;

public class ProvinceDAOImpl extends AbstractDAO implements IProvinceDAO {

	@Override
	public boolean doCreate(Province vo) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doUpdate(Province vo) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doRemove(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doRemoveBatch(Set<Integer> ids) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Province findById(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Province> findAll() throws SQLException {
		List<Province> all = new ArrayList<Province>() ;
		String sql = "SELECT pid,title FROM province" ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		ResultSet rs = super.pstmt.executeQuery() ;
		while (rs.next()) {
			Province pro = new Province() ;
			pro.setPid(rs.getInt(1));
			pro.setTitle(rs.getString(2));
			all.add(pro) ;
		}
		return all;
	}

	@Override
	public List<Province> findAllSplit(Integer currentPage, Integer lineSize)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Province> findAllSplit(Integer currentPage, Integer lineSize,
			String column, String keyWord) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getAllCount() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getAllCount(String column, String keyWord)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
