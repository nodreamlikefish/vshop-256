package cn.mldn.vshop.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import cn.mldn.util.dao.abs.AbstractDAO;
import cn.mldn.vshop.dao.IOrdersDAO;
import cn.mldn.vshop.vo.Orders;

public class OrdersDAOImpl extends AbstractDAO implements IOrdersDAO {
	@Override
	public Orders findByMemberAndId(String mid, Integer oid)
			throws SQLException {
		String sql = "SELECT oid,mid,address,subdate,price,note FROM orders WHERE mid=? AND oid=?" ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		super.pstmt.setString(1, mid);
		super.pstmt.setInt(2, oid);
		ResultSet rs = super.pstmt.executeQuery() ;
		if (rs.next()) {
			Orders vo = new Orders() ;
			vo.setOid(rs.getInt(1));
			vo.setMid(rs.getString(2));
			vo.setAddress(rs.getString(3));
			vo.setSubdate(rs.getTimestamp(4));
			vo.setPrice(rs.getDouble(5)); 
			vo.setNote(rs.getString(6));
			return vo ;
		}
		return null;
	}
	
	@Override
	public Integer getAllCountByMember(String mid) throws SQLException {
		String sql = "SELECT COUNT(*) FROM orders WHERE mid=?" ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		super.pstmt.setString(1, mid);
		ResultSet rs = super.pstmt.executeQuery() ;
		if (rs.next()) {
			return rs.getInt(1) ;
		}
		return 0 ;
	}
	@Override
	public List<Orders> findAllSplitByMember(String mid,Integer currentPage,Integer lineSize) throws SQLException {
		List<Orders> all = new ArrayList<Orders>() ;
		String sql = "SELECT oid,mid,address,subdate,price FROM orders WHERE mid=? LIMIT ?,?" ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		super.pstmt.setString(1, mid);
		super.pstmt.setInt(2, (currentPage - 1) * lineSize);
		super.pstmt.setInt(3, lineSize); ;
		ResultSet rs = super.pstmt.executeQuery() ;
		while (rs.next()) {
			Orders vo = new Orders() ;
			vo.setOid(rs.getInt(1));
			vo.setMid(rs.getString(2));
			vo.setAddress(rs.getString(3));
			vo.setSubdate(rs.getTimestamp(4));
			vo.setPrice(rs.getDouble(5)); 
			all.add(vo) ;
		}
		return all ;
	}
	
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
