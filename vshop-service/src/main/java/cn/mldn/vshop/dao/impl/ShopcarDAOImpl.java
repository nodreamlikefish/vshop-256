package cn.mldn.vshop.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import cn.mldn.util.dao.abs.AbstractDAO;
import cn.mldn.vshop.dao.IShopcarDAO;
import cn.mldn.vshop.vo.Shopcar;

public class ShopcarDAOImpl extends AbstractDAO implements IShopcarDAO {
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
