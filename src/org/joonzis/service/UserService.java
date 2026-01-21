package org.joonzis.service;

import org.joonzis.vo.UVO;

public interface UserService {
	public int validateId(String u_id);
	public int insertUser(UVO uvo);
	public UVO doLogin(UVO uvo);
}
