package org.joonzis.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.joonzis.mybatis.config.DBService;
import org.joonzis.vo.CVO;

public class CDaoImpl implements CDao{
	// DAO 객체 생성
		private static CDaoImpl instance = null;
		private CDaoImpl() {}
		public static CDaoImpl getInstance() {
			if(instance == null) {
				instance = new CDaoImpl();
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
		
		// mapper와 연결하는 id = selectAll
		
		@Override
		public int insertComm(CVO cvo) {
			int result = getSqlSession().insert("insert_comm", cvo);
			if(result > 0) {
				getSqlSession().commit();
			}
			return result;
		}
				
		@Override
		public List<CVO> getCommList(int m_idx) {
			return getSqlSession().selectList("comm_select_by_midx", m_idx);
		}
		
		@Override
		public int removeComm(int c_idx) {
			int result = getSqlSession().insert("delete_comm", c_idx);
			if(result > 0) {
				getSqlSession().commit();
			}
			return result;
		}
		// 댓글 수정
		@Override
		public int updateComm(CVO cvo) {
			int result = getSqlSession().update("update_comm", cvo);
			if(result > 0) {
				getSqlSession().commit();
			}
			return result;
		} 
		
}
