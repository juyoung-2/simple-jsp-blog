package org.joonzis.service;

import java.util.List;

import org.joonzis.dao.MDao;
import org.joonzis.dao.MDaoImpl;
import org.joonzis.model.Criteria;
import org.joonzis.vo.MVO;

public class MainServiceImpl implements MainService {
	private MDao mdao = MDaoImpl.getInstance();
	
	@Override
	public List<MVO> getListWithPaging(Criteria cri) {
		return mdao.getListWithPaging(cri);
	}
	@Override
	public int getTotalRecordCount() {
		return mdao.getTotalRecordCount();
	}
	@Override
	public int getInsertMain(MVO mvo) {
		return mdao.getInsertMain(mvo);
	}
	@Override
	public MVO getMain(int m_idx) {
		return mdao.getMain(m_idx);
	}
	@Override
	public void updateHit(MVO mvo) {
		 mdao.updateHit(mvo);
	}
	@Override
	public int removeMain(int m_idx) {
		return mdao.removeMain(m_idx);
	}
	@Override
	public int updateMain(MVO mvo) {
		return mdao.updateMain(mvo);
	}
	@Override
	public List<MVO> selectMyPostList(String u_id) {
		return mdao.selectMyPostList(u_id);
	}
	
	
}
