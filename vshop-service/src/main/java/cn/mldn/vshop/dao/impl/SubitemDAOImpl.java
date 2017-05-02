package cn.mldn.vshop.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import cn.mldn.util.dao.abs.AbstractDAO;
import cn.mldn.vshop.dao.ISubitemDAO;
import cn.mldn.vshop.vo.Subitem;

public class SubitemDAOImpl extends AbstractDAO implements ISubitemDAO {
	@Override
	public List<Subitem> findAllByItem(Integer iid) throws SQLException {
		List<Subitem> all = new ArrayList<Subitem>() ;
		String sql = "SELECT sid,iid,title FROM subitem WHERE iid=?" ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		super.pstmt.setInt(1, iid);
		ResultSet rs = super.pstmt.executeQuery() ;
		while (rs.next()) {
			Subitem vo = new Subitem() ;
			vo.setSid(rs.getInt(1));
			vo.setIid(rs.getInt(2));
			vo.setTitle(rs.getString(3));
			all.add(vo) ;
		}
		return all;
	}
	@Override
	public boolean doCreate(Subitem vo) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doUpdate(Subitem vo) throws SQLException {
		String sql = "UPDATE subitem SET title=? WHERE sid=?" ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		super.pstmt.setString(1, vo.getTitle());
		super.pstmt.setInt(2, vo.getSid());
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
	public Subitem findById(Integer id) throws SQLException {
		String sql = "SELECT sid,iid,title FROM subitem WHERE sid=?" ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		super.pstmt.setInt(1, id);
		ResultSet rs = super.pstmt.executeQuery() ;
		if (rs.next()) {
			Subitem vo = new Subitem() ;
			vo.setSid(rs.getInt(1));
			vo.setIid(rs.getInt(2));
			vo.setTitle(rs.getString(3));
			return vo ;
		}
		return null;
	}

	@Override
	public List<Subitem> findAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Subitem> findAllSplit(Integer currentPage, Integer lineSize)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Subitem> findAllSplit(Integer currentPage, Integer lineSize,
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
