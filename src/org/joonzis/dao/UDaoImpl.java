package org.joonzis.dao;

import org.apache.ibatis.session.SqlSession;
import org.joonzis.mybatis.config.DBService;
import org.joonzis.vo.UVO;


public class UDaoImpl implements UDao{
	private static UDaoImpl instance = null;
	private UDaoImpl() {}
	public static UDaoImpl getInstance() {
		if(instance == null) {
			instance = new UDaoImpl();
		}
		return instance;
	}
	
	// 필드
	private static SqlSession sqlsession = null;
	private synchronized static SqlSession getSqlSession() {
		if(sqlsession == null) {
			sqlsession = DBService.getFactory().openSession(false);
		}
		return sqlsession;
	}
	
	@Override
	public int validateId(String u_id) {
		return getSqlSession().selectOne("validate_id", u_id);
	}
	
	@Override
	public int insertUser(UVO uvo) {
		int result = getSqlSession().insert("insert_user", uvo);
		if(result > 0) {
			getSqlSession().commit();
		}
		return result;
	}
	
	@Override
	public UVO doLogin(UVO uvo) {
		return getSqlSession().selectOne("do_login", uvo);
	}

}
