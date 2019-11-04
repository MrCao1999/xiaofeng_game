package cn.com.onlicebook.biz.impl;

import java.util.List;
import java.util.Map;

import cn.com.onlicebook.bean.Pagetion;
import cn.com.onlicebook.bean.Userinfo;
import cn.com.onlicebook.biz.UserInfoBiz;
import cn.com.onlicebook.dao.UserDao;
import cn.com.onlicebook.dao.impl.UserDaoImpl;

public class UserInfoBizImpl implements UserInfoBiz{
	UserDao userdao = new UserDaoImpl();
	@Override
	public Userinfo login(Userinfo userinfo) {
		return userdao.queryByParam(userinfo);
	}
	
	@Override
	public int register(Userinfo userinfo) {
		return userdao.save(userinfo);
	}

	@Override
	public int updateUser(Userinfo userinfo) {
		return userdao.update(userinfo);
	}

	@Override
	public Pagetion listByPage(Pagetion pagetion, Map<String, Object> params) {
		int count = userdao.count(params);
		pagetion.setTotalCount(count);
		
		List<Userinfo> userList = userdao.queryByPage(pagetion.getPageNo(), pagetion.getPageSize(), params);
		pagetion.setList(userList);
		return pagetion;
	}

	@Override
	public int addUser(Userinfo userinfo) {
		return userdao.add(userinfo);
	}

	@Override
	public Userinfo showUser(Integer userid) {
		return userdao.getById(userid);
	}

	@Override
	public int deluserinfo(Integer userid) {
		return userdao.deluser(userid);
	}
	
}
