package cn.com.onlicebook.dao;

import java.util.List;

import cn.com.onlicebook.bean.Items;

public interface ItemsDao {
	/**
	 * 新增订单详情
	 * @param item
	 * @return
	 */
	public int save(Items item);
	/**
	 * 根据订单编号查询
	 * @param oid
	 * @return
	 */
	public List<Items> queryByOid(int oid);
	/**
	 * 查询所有订单
	 * @param items
	 * @return
	 */
	public Items queryAll(Items items);
}
