package cn.mldn.vshop.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import cn.mldn.util.dao.abs.AbstractDAO;
import cn.mldn.vshop.dao.IGoodsDAO;
import cn.mldn.vshop.vo.Goods;

public class GoodsDAOImpl extends AbstractDAO implements IGoodsDAO {

	@Override
	public boolean doCreate(Goods vo) throws SQLException {
		String sql = "INSERT INTO goods(iid,sid,mid,price,pubdate,note,delflag,photo,title) VALUES (?,?,?,?,?,?,?,?,?)" ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		super.pstmt.setInt(1, vo.getIid());
		super.pstmt.setInt(2, vo.getSid());
		super.pstmt.setString(3, vo.getMid());
		super.pstmt.setDouble(4, vo.getPrice());
		super.pstmt.setDate(5, new java.sql.Date(vo.getPubdate().getTime()));
		super.pstmt.setString(6, vo.getNote());
		super.pstmt.setInt(7, vo.getDelflag());
		super.pstmt.setString(8, vo.getPhoto());
		super.pstmt.setString(9, vo.getTitle());
		return super.pstmt.executeUpdate() > 0 ;
	}

	@Override
	public boolean doUpdate(Goods vo) throws SQLException {
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
	public Goods findById(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Goods> findAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Goods> findAllSplit(Integer currentPage, Integer lineSize)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Goods> findAllSplit(Integer currentPage, Integer lineSize,
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
