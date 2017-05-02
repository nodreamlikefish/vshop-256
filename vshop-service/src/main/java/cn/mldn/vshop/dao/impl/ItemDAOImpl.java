package cn.mldn.vshop.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import cn.mldn.util.dao.abs.AbstractDAO;
import cn.mldn.vshop.dao.IItemDAO;
import cn.mldn.vshop.vo.Item;

public class ItemDAOImpl extends AbstractDAO implements IItemDAO {

	@Override
	public boolean doCreate(Item vo) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doUpdate(Item vo) throws SQLException {
		String sql = "UPDATE item SET title=? WHERE iid=?" ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		super.pstmt.setString(1, vo.getTitle());
		super.pstmt.setInt(2, vo.getIid());
		return super.pstmt.executeUpdate() > 0 ;
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
	public Item findById(Integer id) throws SQLException {
		String sql = "SELECT iid,title FROM item WHERE iid=?" ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		super.pstmt.setInt(1, id);
		ResultSet rs = super.pstmt.executeQuery() ;
		while (rs.next()) {
			Item vo = new Item() ;
			vo.setIid(rs.getInt(1));
			vo.setTitle(rs.getString(2));
			return vo ;
		}
		return null ;
	}

	@Override
	public List<Item> findAll() throws SQLException {
		List<Item> all = new ArrayList<Item>() ;
		String sql = "SELECT iid,title FROM item" ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		ResultSet rs = super.pstmt.executeQuery() ;
		while (rs.next()) {
			Item vo = new Item() ;
			vo.setIid(rs.getInt(1));
			vo.setTitle(rs.getString(2));
			all.add(vo) ;
		}
		return all;
	}

	@Override
	public List<Item> findAllSplit(Integer currentPage, Integer lineSize)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Item> findAllSplit(Integer currentPage, Integer lineSize,
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
