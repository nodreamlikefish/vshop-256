package cn.mldn.vshop.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import cn.mldn.util.dao.abs.AbstractDAO;
import cn.mldn.vshop.dao.IOrdersDAO;
import cn.mldn.vshop.vo.Orders;

public class OrdersDAOImpl extends AbstractDAO implements IOrdersDAO {
	@Override
	public Integer findCreateId() throws SQLException {
		String sql = "SELECT LAST_INSERT_ID()" ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		ResultSet rs = super.pstmt.executeQuery() ;
		if (rs.next()) {
			return rs.getInt(1) ;
		}
		return null;
	}
	@Override
	public boolean doCreate(Orders vo) throws SQLException {
		String sql = "INSERT INTO orders(mid,address,subdate,price,note) VALUES (?,?,?,?,?)" ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		super.pstmt.setString(1, vo.getMid());
		super.pstmt.setString(2, vo.getAddress());
		super.pstmt.setTimestamp(3, new java.sql.Timestamp(vo.getSubdate().getTime()));
		super.pstmt.setDouble(4, vo.getPrice());
		super.pstmt.setString(5, vo.getNote());
		return super.pstmt.executeUpdate() > 0 ;
	}

	@Override
	public boolean doUpdate(Orders vo) throws SQLException {
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
	public Orders findById(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Orders> findAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Orders> findAllSplit(Integer currentPage, Integer lineSize)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Orders> findAllSplit(Integer currentPage, Integer lineSize,
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
