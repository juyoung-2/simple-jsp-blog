package org.joonzis.service;

import java.util.List;

import org.joonzis.dao.CDao;
import org.joonzis.dao.CDaoImpl;
import org.joonzis.vo.CVO;

public class CommServiceImpl implements CommService{
private CDao cdao = CDaoImpl.getInstance();  // 객체 생성해줘야 한다
	
	public int insertComm(CVO cvo) {
		return cdao.insertComm(cvo);
	};
	
	@Override
	public List<CVO> getCommList(int m_idx) {
		return cdao.getCommList(m_idx);
	}
	
	@Override
	public int removeComm(int c_idx) {
		return cdao.removeComm(c_idx);
	}
	@Override
		public int updateComm(CVO cvo) {
		return cdao.updateComm(cvo);
		}
	
}
