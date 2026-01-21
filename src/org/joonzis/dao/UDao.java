package org.joonzis.dao;

import org.joonzis.vo.UVO;

public interface UDao {
	public int validateId(String u_id);
	public int insertUser(UVO uvo);
	public UVO doLogin(UVO uvo);
}
