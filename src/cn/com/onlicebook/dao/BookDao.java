package cn.com.onlicebook.dao;

import java.util.List;
import java.util.Map;


import cn.com.onlicebook.bean.Books;


public interface BookDao {
	/**
	 * 根据id查询图书
	 * @param bid
	 * @return
	 */
	public Books getById(Integer bid);
	/**
	 * 修改图书
	 * @param books
	 * @return
	 */
	public int update(Books books);
	/**
	 * 分页模糊查询
	 * @param pageNo
	 * @param pageSize
	 * @param param
	 * @return
	 */
	public List<Books> queryByPage(int pageNo, int pageSize,Map<String, Object>params);
	/**
	 * 模糊查询统计数量
	 * @param param
	 * @return
	 */
	public int count(Map<String,Object>param);
	/**
	 * 新增图书
	 * @param books
	 * @return
	 */
	public int save(Books books);
	/**
	 * 物理删除图书
	 * @param bid
	 * @return
	 */
	public int del(int bid);
	
}
