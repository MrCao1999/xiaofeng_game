package cn.com.onlicebook.biz;

import java.util.Map;

import cn.com.onlicebook.bean.Pagetion;
import cn.com.onlicebook.bean.Userinfo;

public interface UserInfoBiz {
	/**
	 *根据id查询用户
	 * @param bid
	 * @return
	 */
	public Userinfo showUser(Integer userid);
	/**
	 * 用户登录
	 * @param userinfo
	 * @return
	 */
	public Userinfo login(Userinfo userinfo);
	
	/**
	 * 用户注册
	 * @param userinfo
	 * @return
	 */
	public int register(Userinfo userinfo);
	/**
	 * 修改用户
	 * @param userinfo
	 * @return
	 */
	public int updateUser(Userinfo userinfo);
	/**
	 * 新增用户
	 * @param userinfo
	 * @return
	 */
	public int addUser(Userinfo userinfo);
	
	/**
	 * 分页显示用户
	 * @param pagetion
	 * @param params
	 * @return
	 */
	public Pagetion listByPage(Pagetion pagetion,Map<String, Object> params);
	/**
	 * 删除用户,改变状态(默认为1，为0时不显示)
	 * @param bid
	 * @return
	 */
	public int deluserinfo(Integer userid);
}
