package cn.mldn.vshop.dao;

import java.sql.SQLException;
import java.util.Date;

import cn.mldn.util.dao.IBaseDAO;
import cn.mldn.vshop.vo.Member;

public interface IMemberDAO extends IBaseDAO<String, Member> {
	/**
	 * 进行用户的登录密码更新
	 * @param mid 要更新的用户编号
	 * @param password 新的密码
	 * @return 更新成功返回true，否则返回false
	 * @throws SQLException SQL异常
	 */
	public boolean doUpdatePassword(String mid,String password) throws SQLException ;
	
	/**
	 * 更新用户的基本信息，主要包括：姓名、电话、邮箱
	 * @param vo 包含有用户的基本信息，除了要更新的字段内容外还要包括mid的信息
	 * @return 更新成功返回true，否则返回false
	 * @throws SQLException SQL问题
	 */
	public boolean doUpdateBase(Member vo) throws SQLException ;
	/**
	 * 进行用户最后一次登录日期的更新处理
	 * @param mid 用户id
	 * @param lastdate 用户最后一次登录日期
	 * @return 更新成功返回true，否则返回false
	 * @throws SQLException 通过PreparedStatement执行操作返回的是SQL异常，看见SQL异常就查数据层代码
	 */
	public boolean doUpdateLastdate(String mid,Date lastdate) throws SQLException ;
	/**
	 * 进行登录验证处理，只验证用户名和密码的额信息
	 * @param mid 用户编号
	 * @param password 必须是加密后的数据
	 * @return 如果登录成功则将用户的相关信息保存在Member对象之中，并且返回Member的实例，如果用户名密码不存在则返回null
	 * @throws SQLException 通过PreparedStatement执行操作返回的是SQL异常，看见SQL异常就查数据层代码
	 */
	public Member findLogin(String mid,String password) throws SQLException ; 
}
