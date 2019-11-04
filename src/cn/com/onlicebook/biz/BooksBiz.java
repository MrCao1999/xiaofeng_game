package cn.com.onlicebook.biz;

import java.util.Map;

import cn.com.onlicebook.bean.Books;
import cn.com.onlicebook.bean.Pagetion;

public interface BooksBiz {
	/**
	 * 根据id查询图书详情
	 * @param bid
	 * @return
	 */
	public Books showBooks(Integer bid);
	/**
	 * 修改图书
	 * @param books
	 * @return
	 */
	public int updateBooks(Books books);
	/**
	 * 删除图书,改变状态(默认为1，为0时不显示)
	 * @param bid
	 * @return
	 */
	public int delBooks(Integer bid);
	/**
	 * 显示图书
	 * @param bid
	 * @return
	 */
	public Pagetion listByPage(Pagetion pagetion,Map<String, Object> params);
	/**
	 * 新增图书
	 * @param books
	 * @return
	 */
	public int addBooks(Books books);
}
