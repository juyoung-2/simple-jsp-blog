package org.joonzis.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.joonzis.model.Criteria;
import org.joonzis.mybatis.config.DBService;
import org.joonzis.vo.MVO;

public class MDaoImpl implements MDao{
	// DAO 객체 생성
			private static MDaoImpl instance = null;
			private MDaoImpl() {}
			public static MDaoImpl getInstance() {
				if(instance == null) {
					instance = new MDaoImpl();
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
		public List<MVO> getListWithPaging(Criteria cri) {
			return getSqlSession().selectList("main_select_all_with_paging", cri);
		}
		@Override
		public int getTotalRecordCount() {
			return getSqlSession().selectOne("total_count_of_main");
		}
		@Override
		public int getInsertMain(MVO mvo) {
			int result = getSqlSession().insert("insert_main", mvo);
			if(result > 0) {
				getSqlSession().commit();
			}
			return result;
		}
		@Override
		public MVO getMain(int m_idx) {
			return getSqlSession().selectOne("get_main", m_idx);
		} 
		@Override
		public void updateHit(MVO mvo) {
			int result = getSqlSession().update("update_hit", mvo);
			if(result > 0) {
				getSqlSession().commit();
			}
		}
		@Override
		public int removeMain(int m_idx) {
			int result = getSqlSession().delete("delete_main", m_idx );
			if(result > 0) {
				getSqlSession().commit();
			}
			return result;
		}
		@Override
		public int updateMain(MVO mvo) {
			int result = getSqlSession().delete("update_main", mvo );
			if(result > 0) {
				getSqlSession().commit();
			}
			return result;
		}
		@Override
		public List<MVO> selectMyPostList(String u_id) {
			return getSqlSession().selectList("select_my_post_list", u_id);
		}
		
		
		
}