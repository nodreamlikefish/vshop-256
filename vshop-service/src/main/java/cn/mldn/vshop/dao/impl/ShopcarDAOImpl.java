package cn.mldn.vshop.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.mldn.util.dao.abs.AbstractDAO;
import cn.mldn.vshop.dao.IShopcarDAO;
import cn.mldn.vshop.vo.Shopcar;

public class ShopcarDAOImpl extends AbstractDAO implements IShopcarDAO {
	@Override
	public boolean doRemoveByMemberAndGoods2(String mid, Set<Long> gid)
			throws SQLException { 
		StringBuffer buf = new StringBuffer() ;
		buf.append("DELETE FROM shopcar WHERE mid=? AND gid IN (") ;
		Iterator<Long> iter = gid.iterator() ;
		while (iter.hasNext()) {
			buf.append(iter.next()).append(",") ;
		}
		buf.delete(buf.length() - 1, buf.length()).append(")") ;
		super.pstmt = super.conn.prepareStatement(buf.toString()) ;
		super.pstmt.setString(1, mid);
		return super.pstmt.executeUpdate() > 0 ;
	}
	
	@Override
	public boolean doRemoveByMemberAndGoods(String mid, Set<Integer> gid)
			throws SQLException {
		StringBuffer buf = new StringBuffer() ;
		buf.append("DELETE FROM shopcar WHERE mid=? AND gid IN (") ;
		Iterator<Integer> iter = gid.iterator() ;
		while (iter.hasNext()) {
			buf.append(iter.next()).append(",") ;
		}
		buf.delete(buf.length() - 1, buf.length()).append(")") ;
		super.pstmt = super.conn.prepareStatement(buf.toString()) ;
		super.pstmt.setString(1, mid);
		return super.pstmt.executeUpdate() > 0 ;
	}
	
	@Override
	public boolean doUpdateAmountBatch(String mid, Map<Integer, Integer> sc)
			throws SQLException {
		String sql = "UPDATE shopcar SET amount=? WHERE mid=? AND gid=?" ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		Iterator<Map.Entry<Integer,Integer>> iter = sc.entrySet().iterator() ;
		while (iter.hasNext()) {
			Map.Entry<Integer,Integer> me = iter.next() ;
			super.pstmt.setInt(1, me.getValue());
			super.pstmt.setString(2, mid);
			super.pstmt.setInt(3, me.getKey());
			super.pstmt.addBatch(); 	// 追加批处理
		}
		int result [] = super.pstmt.executeBatch() ;
		for (int x = 0 ; x < result.length ; x ++) {
			if (result[x] == 0) {
				return false ;
			}
		}
		return true ;
	}
	
	@Override
	public boolean doRemoveByMemberAndGid(String mid, Integer gid)
			throws SQLException {
		String sql = "DELETE FROM shopcar WHERE mid=? AND gid=?" ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		super.pstmt.setString(1, mid);
		super.pstmt.setInt(2, gid);
		return super.pstmt.executeUpdate() > 0 ;
	}
	
	@Override
	public Map<Long, Integer> findAllByMember(String mid,Set<Long> gid) throws SQLException {
		Map<Long,Integer> map = new HashMap<Long,Integer>() ;
		StringBuffer buf = new StringBuffer() ;
		buf.append("SELECT gid,amount FROM shopcar WHERE mid=? AND gid IN (") ;
		Iterator<Long> iter = gid.iterator() ;
		while (iter.hasNext()) {
			buf.append(iter.next()).append(",") ;
		}
		buf.delete(buf.length() - 1, buf.length()).append(")") ;
		super.pstmt = super.conn.prepareStatement(buf.toString()) ;
		super.pstmt.setString(1, mid);
		ResultSet rs = super.pstmt.executeQuery() ;
		while (rs.next()) {
			map.put(rs.getLong(1), rs.getInt(2)) ;
		}
		return map;
	}
	
	@Override
	public Map<Long, Integer> findAllByMember(String mid) throws SQLException {
		Map<Long,Integer> map = new HashMap<Long,Integer>() ;
		String sql = "SELECT gid,amount FROM shopcar WHERE mid=?" ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		super.pstmt.setString(1, mid);
		ResultSet rs = super.pstmt.executeQuery() ;
		while (rs.next()) {
			map.put(rs.getLong(1), rs.getInt(2)) ;
		}
		return map;
	}
	@Override
	public boolean doUpdateIncrementById(String mid, Integer gid,Integer amount)
			throws SQLException {
		String sql = "UPDATE shopcar SET amount=? WHERE mid=? AND gid=?" ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		super.pstmt.setInt(1, amount);
		super.pstmt.setString(2, mid);
		super.pstmt.setInt(3, gid);
		return super.pstmt.executeUpdate() > 0 ;
	}
	@Override
	public Shopcar findByMemberAndGoods(String mid, Integer gid)
			throws SQLException {
		String sql = "SELECT scid,gid,mid,amount FROM shopcar WHERE mid=? AND gid=?" ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		super.pstmt.setString(1, mid);
		super.pstmt.setInt(2, gid);
		ResultSet rs = super.pstmt.executeQuery() ;
		if (rs.next()) {
			Shopcar vo = new Shopcar() ;
			vo.setScid(rs.getInt(1));
			vo.setGid(rs.getInt(2));
			vo.setMid(rs.getString(3));
			vo.setAmount(rs.getInt(4));
			return vo ;
		}
		return null;
	}
	
	
	@Override
	public boolean doCreate(Shopcar vo) throws SQLException {
		String sql = "INSERT INTO shopcar (mid,gid,amount) VALUES (?,?,?)" ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		super.pstmt.setString(1, vo.getMid());
		super.pstmt.setInt(2, vo.getGid());
		super.pstmt.setInt(3, vo.getAmount());
		return super.pstmt.executeUpdate() > 0;
	}

	@Override
	public boolean doUpdate(Shopcar vo) throws SQLException {
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
	public Shopcar findById(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Shopcar> findAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Shopcar> findAllSplit(Integer currentPage, Integer lineSize)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Shopcar> findAllSplit(Integer currentPage, Integer lineSize,
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
