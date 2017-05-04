package cn.mldn.vshop.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import cn.mldn.util.dao.abs.AbstractDAO;
import cn.mldn.vshop.dao.IDetailsDAO;
import cn.mldn.vshop.vo.Details;

public class DetailsDAOImpl extends AbstractDAO implements IDetailsDAO {

	@Override
	public boolean doCreate(Details vo) throws SQLException {
		String sql = "INSERT INTO details(oid,gid,amount) VALUES (?,?,?)" ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		super.pstmt.setInt(1, vo.getOid());
		super.pstmt.setLong(2, vo.getGid());
		super.pstmt.setInt(3, vo.getAmount());
		return super.pstmt.executeUpdate() > 0 ;
	}

	@Override
	public boolean doUpdate(Details vo) throws SQLException {
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
	public Details findById(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Details> findAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Details> findAllSplit(Integer currentPage, Integer lineSize)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Details> findAllSplit(Integer currentPage, Integer lineSize,
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
