package org.joonzis.service;

import org.joonzis.dao.UDao;
import org.joonzis.dao.UDaoImpl;
import org.joonzis.vo.UVO;

public class UserServiceImpl implements UserService{
	private UDao udao = UDaoImpl.getInstance();
	 
	@Override
	public int validateId(String mId) {
		return udao.validateId(mId);
	}
	
	@Override
	public int insertUser(UVO uvo) {
		return udao.insertUser(uvo);
	}
	
	@Override
	public UVO doLogin(UVO uvo) {
		return udao.doLogin(uvo);
	}
}
