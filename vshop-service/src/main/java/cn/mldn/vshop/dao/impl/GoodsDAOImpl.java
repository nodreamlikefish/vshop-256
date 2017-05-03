package cn.mldn.vshop.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import cn.mldn.util.dao.abs.AbstractDAO;
import cn.mldn.vshop.dao.IGoodsDAO;
import cn.mldn.vshop.vo.Goods;

public class GoodsDAOImpl extends AbstractDAO implements IGoodsDAO {

	@Override
	public List<Goods> findAllByIds(Set<Long> ids) throws SQLException {
		StringBuffer buf = new StringBuffer() ;
		buf.append(" SELECT gid,iid,sid,mid,price,pubdate,note,delflag,photo,title FROM goods ") ;
		buf.append(" WHERE gid IN (") ;
		Iterator<Long> iter = ids.iterator() ;
		while (iter.hasNext()) {
			buf.append(iter.next()).append(",") ;
		}
		buf.delete(buf.length() - 1, buf.length()).append(") AND delflag=0") ;
		super.pstmt = super.conn.prepareStatement(buf.toString()) ;
		List<Goods> all = new ArrayList<Goods>() ;
		ResultSet rs = super.pstmt.executeQuery() ;
		while (rs.next()) {
			Goods vo = new Goods() ;
			vo.setGid(rs.getLong(1));
			vo.setIid(rs.getInt(2));
			vo.setSid(rs.getInt(3));
			vo.setMid(rs.getString(4));
			vo.setPrice(rs.getDouble(5));
			vo.setPubdate(rs.getTimestamp(6));
			vo.setNote(rs.getString(7));
			vo.setDelflag(rs.getInt(8));
			vo.setPhoto(rs.getString(9));
			vo.setTitle(rs.getString(10));
			all.add(vo) ;
		}
		return all;
	}
	@Override
	public boolean doUpdateDeflag(Set<Integer> ids, int delflag)
			throws SQLException {
		StringBuffer buf = new StringBuffer() ;
		buf.append("UPDATE goods SET delflag=? WHERE gid IN (") ;
		Iterator<Integer> iter = ids.iterator() ;
		while (iter.hasNext()) {
			buf.append(iter.next()).append(",") ;
		}
		buf.delete(buf.length() - 1, buf.length()).append(")") ;
		super.pstmt = super.conn.prepareStatement(buf.toString()) ;
		super.pstmt.setInt(1, delflag);
		super.pstmt.executeUpdate() ;
		return true;
	}
	
	@Override
	public boolean doCreate(Goods vo) throws SQLException {
		String sql = "INSERT INTO goods(iid,sid,mid,price,pubdate,note,delflag,photo,title) VALUES (?,?,?,?,?,?,?,?,?)" ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		super.pstmt.setInt(1, vo.getIid());
		super.pstmt.setInt(2, vo.getSid());
		super.pstmt.setString(3, vo.getMid());
		super.pstmt.setDouble(4, vo.getPrice());
		super.pstmt.setTimestamp(5, new java.sql.Timestamp(vo.getPubdate().getTime()));
		super.pstmt.setString(6, vo.getNote());
		super.pstmt.setInt(7, vo.getDelflag());
		super.pstmt.setString(8, vo.getPhoto());
		super.pstmt.setString(9, vo.getTitle());
		return super.pstmt.executeUpdate() > 0 ;
	}

	@Override
	public boolean doUpdate(Goods vo) throws SQLException {
		String sql = "UPDATE goods SET iid=?,sid=?,mid=?,price=?,note=?,title=?,photo=? WHERE gid=?" ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		super.pstmt.setInt(1, vo.getIid());
		super.pstmt.setInt(2, vo.getSid());
		super.pstmt.setString(3, vo.getMid());
		super.pstmt.setDouble(4, vo.getPrice());
		super.pstmt.setString(5, vo.getNote());
		super.pstmt.setString(6, vo.getTitle());
		super.pstmt.setString(7, vo.getPhoto());
		super.pstmt.setLong(8, vo.getGid());
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
	public Goods findById(Integer id) throws SQLException {
		String sql = "SELECT gid,iid,sid,mid,price,pubdate,note,delflag,photo,title FROM goods WHERE delflag=0 AND gid=?" ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		super.pstmt.setInt(1, id);
		ResultSet rs = super.pstmt.executeQuery() ;
		if (rs.next()) {
			Goods vo = new Goods() ;
			vo.setGid(rs.getLong(1));
			vo.setIid(rs.getInt(2));
			vo.setSid(rs.getInt(3));
			vo.setMid(rs.getString(4));
			vo.setPrice(rs.getDouble(5));
			vo.setPubdate(rs.getTimestamp(6));
			vo.setNote(rs.getString(7));
			vo.setDelflag(rs.getInt(8)); 
			vo.setPhoto(rs.getString(9));
			vo.setTitle(rs.getString(10));
			return vo ;
		}
		return null;
	}

	@Override
	public List<Goods> findAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<Goods> findAllSplitBySubitem(Integer sid, Integer currentPage,
			Integer lineSize) throws SQLException {
		List<Goods> all = new ArrayList<Goods>() ;
		String sql = "SELECT gid,iid,sid,mid,price,pubdate,note,delflag,photo,title FROM goods WHERE delflag=0 AND sid=? ORDER BY pubdate DESC LIMIT ?,?" ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		super.pstmt.setInt(1, sid);
		super.pstmt.setInt(2, (currentPage - 1) * lineSize);
		super.pstmt.setInt(3, lineSize);
		ResultSet rs = super.pstmt.executeQuery() ;
		while (rs.next()) {
			Goods vo = new Goods() ;
			vo.setGid(rs.getLong(1));
			vo.setIid(rs.getInt(2));
			vo.setSid(rs.getInt(3));
			vo.setMid(rs.getString(4));
			vo.setPrice(rs.getDouble(5));
			vo.setPubdate(rs.getTimestamp(6));
			vo.setNote(rs.getString(7));
			vo.setDelflag(rs.getInt(8));
			vo.setPhoto(rs.getString(9));
			vo.setTitle(rs.getString(10));
			all.add(vo) ;
		}
		return all;
	}

	@Override
	public List<Goods> findAllSplit(Integer currentPage, Integer lineSize)
			throws SQLException {
		List<Goods> all = new ArrayList<Goods>() ;
		String sql = "SELECT gid,iid,sid,mid,price,pubdate,note,delflag,photo,title FROM goods WHERE delflag=0 ORDER BY pubdate DESC LIMIT ?,?" ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		super.pstmt.setInt(1, (currentPage - 1) * lineSize);
		super.pstmt.setInt(2, lineSize);
		ResultSet rs = super.pstmt.executeQuery() ;
		while (rs.next()) {
			Goods vo = new Goods() ;
			vo.setGid(rs.getLong(1));
			vo.setIid(rs.getInt(2));
			vo.setSid(rs.getInt(3));
			vo.setMid(rs.getString(4));
			vo.setPrice(rs.getDouble(5));
			vo.setPubdate(rs.getTimestamp(6));
			vo.setNote(rs.getString(7));
			vo.setDelflag(rs.getInt(8));
			vo.setPhoto(rs.getString(9));
			vo.setTitle(rs.getString(10));
			all.add(vo) ;
		}
		return all;
	}

	@Override
	public List<Goods> findAllSplit(Integer currentPage, Integer lineSize,
			String column, String keyWord) throws SQLException {
		List<Goods> all = new ArrayList<Goods>() ;
		String sql = "SELECT gid,iid,sid,mid,price,pubdate,note,delflag,photo,title "
				+ " FROM goods WHERE delflag=0 AND "+column +" LIKE ?  ORDER BY pubdate DESC LIMIT ?,?" ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		super.pstmt.setString(1, "%"+keyWord+"%");
		super.pstmt.setInt(2, (currentPage - 1) * lineSize);
		super.pstmt.setInt(3, lineSize);
		ResultSet rs = super.pstmt.executeQuery() ;
		while (rs.next()) {
			Goods vo = new Goods() ;
			vo.setGid(rs.getLong(1));
			vo.setIid(rs.getInt(2));
			vo.setSid(rs.getInt(3));
			vo.setMid(rs.getString(4));
			vo.setPrice(rs.getDouble(5));
			vo.setPubdate(rs.getTimestamp(6));
			vo.setNote(rs.getString(7));
			vo.setDelflag(rs.getInt(8));
			vo.setPhoto(rs.getString(9));
			vo.setTitle(rs.getString(10));
			all.add(vo) ;
		}
		return all;
	}
	
	@Override
	public Integer getAllCountBySubitem(Integer sid) throws SQLException {
		String sql = "SELECT COUNT(*) FROM goods WHERE delflag=0 AND sid=?" ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		super.pstmt.setInt(1, sid);
		ResultSet rs = super.pstmt.executeQuery() ;
		if (rs.next()) {
			return rs.getInt(1) ;
		}
		return 0;
	}

	@Override
	public Integer getAllCount() throws SQLException {
		return super.handleCount("goods");
	}

	@Override
	public Integer getAllCount(String column, String keyWord)
			throws SQLException {
		return super.handleCount("goods",column,keyWord);
	}

}
