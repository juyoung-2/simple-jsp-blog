package org.joonzis.dao;

import java.util.List;

import org.joonzis.vo.CVO;

public interface CDao {
	public int insertComm(CVO cvo);
	// 2. 댓글 목록
	public List<CVO> getCommList(int m_idx);
	// 3. 댓글 삭제
	public int removeComm(int c_idx);
	// 댓글 수정
	public int updateComm(CVO cvo);
	
}
