package org.joonzis.dao;

import java.util.List;

import org.joonzis.model.Criteria;
import org.joonzis.vo.MVO;

public interface MDao {
	public List<MVO> getListWithPaging(Criteria cri);
	public int getTotalRecordCount();   // 전체 게시글 수 가져오기
	public int getInsertMain(MVO mvo);
	public MVO getMain(int m_idx);
	public void updateHit(MVO mvo);
	public int removeMain(int m_idx);
	public int updateMain(MVO mvo);
	public List<MVO> selectMyPostList(String u_id);
}
