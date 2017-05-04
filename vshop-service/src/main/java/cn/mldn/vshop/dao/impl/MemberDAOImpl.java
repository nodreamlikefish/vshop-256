package cn.mldn.vshop.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import cn.mldn.util.dao.abs.AbstractDAO;
import cn.mldn.vshop.dao.IMemberDAO;
import cn.mldn.vshop.vo.Member;

public class MemberDAOImpl extends AbstractDAO implements IMemberDAO {
	@Override
	public boolean doUpdateLocked(String mid, Integer locked)
			throws SQLException {
		String sql = "UPDATE member SET locked=? WHERE mid=?" ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		super.pstmt.setInt(1, locked);
		super.pstmt.setString(2, mid);
		return super.pstmt.executeUpdate() > 0 ;
	}
	
	@Override
	public boolean doUpdatePassword(String mid, String password)
			throws SQLException {
		String sql = "UPDATE member SET password=? WHERE mid=?" ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		super.pstmt.setString(1, password);
		super.pstmt.setString(2, mid);
		return super.pstmt.executeUpdate() > 0 ;
	}
	
	@Override
	public boolean doUpdateBase(Member vo) throws SQLException {
		String sql = "UPDATE member SET name=?,email=?,phone=? WHERE mid=?" ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		super.pstmt.setString(1, vo.getName());
		super.pstmt.setString(2, vo.getEmail()); 
		super.pstmt.setString(3, vo.getPhone());
		super.pstmt.setString(4, vo.getMid()); 
		return super.pstmt.executeUpdate() > 0 ;
	}
	@Override
	public boolean doUpdateLastdate(String mid, Date lastdate)
			throws SQLException {
		String sql = "UPDATE member SET lastdate=? WHERE mid=?" ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		super.pstmt.setDate(1, new java.sql.Date(lastdate.getTime()));
		super.pstmt.setString(2, mid);
		return super.pstmt.executeUpdate() > 0 ;
	}
	
	@Override
	public Member findLogin(String mid, String password) throws SQLException {
		String sql = "SELECT name,lastdate,locked FROM member WHERE mid=? AND password=?" ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		super.pstmt.setString(1, mid);
		super.pstmt.setString(2, password);
		ResultSet rs = super.pstmt.executeQuery() ;
		if (rs.next()) {
			Member vo = new Member() ;
			vo.setName(rs.getString(1));
			vo.setLastdate(rs.getDate(2));
			vo.setLocked(rs.getInt(3));
			vo.setMid(mid); 	// 将mid设置到VO对象里面
			return vo ;
		}
		return null;
	}
	@Override
	public boolean doCreate(Member vo) throws SQLException {
		String sql = "INSERT INTO member(mid,name,password,lastdate,regdate,locked) VALUES (?,?,?,?,?,?)" ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		super.pstmt.setString(1, vo.getMid());
		super.pstmt.setString(2, vo.getName());
		super.pstmt.setString(3, vo.getPassword());
		super.pstmt.setDate(4, new java.sql.Date(vo.getLastdate().getTime()));
		super.pstmt.setDate(5, new java.sql.Date(vo.getRegdate().getTime()));
		super.pstmt.setInt(6, vo.getLocked()); 
		return super.pstmt.executeUpdate() > 0 ;
	}

	@Override
	public boolean doUpdate(Member vo) throws SQLException {
		String sql = "UPDATE member SET name=?,locked=? WHERE mid=?" ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		super.pstmt.setString(1, vo.getName());
		super.pstmt.setInt(2, vo.getLocked());
		super.pstmt.setString(3, vo.getMid());
		return super.pstmt.executeUpdate() > 0 ;
	}

	@Override
	public boolean doRemoveBatch(Set<String> ids) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doRemove(String id) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Member findById(String id) throws SQLException {
		String sql = "SELECT name,lastdate,locked,email,phone,regdate FROM member WHERE mid=?" ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		super.pstmt.setString(1, id);
		ResultSet rs = super.pstmt.executeQuery() ;
		if (rs.next()) {
			Member vo = new Member() ;
			vo.setName(rs.getString(1));
			vo.setLastdate(rs.getTimestamp(2));
			vo.setLocked(rs.getInt(3));
			vo.setEmail(rs.getString(4));
			vo.setPhone(rs.getString(5));
			vo.setRegdate(rs.getTimestamp(6));
			vo.setMid(id); 	// 将mid设置到VO对象里面
			return vo ;
		}
		return null;
	}

	@Override
	public List<Member> findAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Member> findAllSplit(Integer currentPage, Integer lineSize)
			throws SQLException {
		List<Member> all = new ArrayList<Member>() ;
		String sql = "SELECT mid,name,lastdate,locked,email,phone,regdate FROM member LIMIT ?,?" ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		super.pstmt.setInt(1, (currentPage - 1) * lineSize);
		super.pstmt.setInt(2, lineSize);
		ResultSet rs = super.pstmt.executeQuery() ;
		while (rs.next()) {
			Member vo = new Member() ;
			vo.setMid(rs.getString(1));
			vo.setName(rs.getString(2));
			vo.setLastdate(rs.getDate(3));
			vo.setLocked(rs.getInt(4));
			vo.setEmail(rs.getString(5));
			vo.setPhone(rs.getString(6));
			vo.setRegdate(rs.getTimestamp(7));
			all.add(vo) ;
		}
		return all;
	}

	@Override
	public List<Member> findAllSplit(Integer currentPage, Integer lineSize,
			String column, String keyWord) throws SQLException {
		List<Member> all = new ArrayList<Member>() ;
		String sql = "SELECT mid,name,lastdate,locked,email,phone,regdate "
				+ "  FROM member "
				+ " WHERE " + column + " LIKE ? LIMIT ?,?" ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		super.pstmt.setString(1, "%"+keyWord+"%");
		super.pstmt.setInt(2, (currentPage - 1) * lineSize);
		super.pstmt.setInt(3, lineSize);
		ResultSet rs = super.pstmt.executeQuery() ;
		while (rs.next()) {
			Member vo = new Member() ;
			vo.setMid(rs.getString(1));
			vo.setName(rs.getString(2));
			vo.setLastdate(rs.getDate(3));
			vo.setLocked(rs.getInt(4));
			vo.setEmail(rs.getString(5));
			vo.setPhone(rs.getString(6));
			vo.setRegdate(rs.getTimestamp(7));
			all.add(vo) ;
		}
		return all;
	}

	@Override
	public Integer getAllCount() throws SQLException {
		String sql = "SELECT COUNT(*) FROM member" ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		ResultSet rs = super.pstmt.executeQuery() ;
		if (rs.next()) {
			return rs.getInt(1) ;
		}
		return 0;
	}

	@Override
	public Integer getAllCount(String column, String keyWord)
			throws SQLException {
		String sql = "SELECT COUNT(*) FROM member WHERE " + column + " LIKE ?" ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		super.pstmt.setString(1, "%"+keyWord+"%");
		ResultSet rs = super.pstmt.executeQuery() ;
		if (rs.next()) {
			return rs.getInt(1) ;
		}
		return 0;
	}


}
