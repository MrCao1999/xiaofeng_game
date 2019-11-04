package cn.com.onlicebook.dao;

import java.util.List;
import java.util.Map;

import cn.com.onlicebook.bean.Userinfo;

public interface UserDao {
	/**
	 * 根据id查询
	 * @param userid
	 * @return
	 */
	public Userinfo getById(Integer userid);
	/**
	 * 登录
	 * @param userinfo
	 * @return
	 */
	public Userinfo queryByParam(Userinfo userinfo);
	/**
	 * 查询所有
	 * @param userinfo
	 * @return
	 */
	public int save(Userinfo userinfo);
	/**
	 * 新增用户
	 * @param userinfo
	 * @return
	 */
	public int add(Userinfo userinfo);
	/**
	 * 修改用户
	 * @param userinfo
	 * @return
	 */
	public int update(Userinfo userinfo);
	/**
	 * 分页模糊查询
	 * @param pageNo
	 * @param pageSize
	 * @param params
	 * @return
	 */
	public List<Userinfo> queryByPage(int pageNo, int pageSize,Map<String, Object>params);
	/**
	 * 模糊查询统计数量
	 * @param param
	 * @return
	 */
	public int count(Map<String,Object>param);
	/**
	 * 逻辑删除用户
	 * @param userid
	 * @return
	 */
	public int deluser(Integer userid);
}
