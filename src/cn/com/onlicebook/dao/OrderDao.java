package cn.com.onlicebook.dao;

import java.util.List;
import java.util.Map;

import cn.com.onlicebook.bean.Orders;

public interface OrderDao {
	/**
	 * 新增订单
	 * @param orders
	 * @return
	 */
	public int save(Orders orders);
	/**
	 * 获取订单表最大的id
	 * @return
	 */
	public int getMaxOrderId();
	/**
	 * 查询所有订单
	 * @return
	 */
	public List<Orders> queryAll(Integer userid);
	/**
	 * 分页模糊查询
	 * @param pageNo
	 * @param pageSize
	 * @param param
	 * @return
	 */
	public List<Orders> queryByPage(int pageNo, int pageSize,Map<String, Object>params);
	/**
	 * 模糊查询统计数量
	 * @param param
	 * @return
	 */
	public int count(Map<String,Object>param);
}
