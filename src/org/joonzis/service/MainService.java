package org.joonzis.service;

import java.util.List;

import org.joonzis.model.Criteria;
import org.joonzis.vo.MVO;
import org.joonzis.vo.UVO;


public interface MainService {
	public List<MVO> getListWithPaging(Criteria cri);
	public int getTotalRecordCount();
	public int getInsertMain(MVO mvo);
	public MVO getMain(int m_idx);
	public void updateHit(MVO mvo);  	// 조회수 올리기
	public int removeMain(int m_idx);
	public int updateMain(MVO mvo);
	public List<MVO> selectMyPostList(String u_id);
}
