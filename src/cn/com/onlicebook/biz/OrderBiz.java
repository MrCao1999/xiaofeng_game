package cn.com.onlicebook.biz;

import java.util.List;
import java.util.Map;

import cn.com.onlicebook.bean.Items;
import cn.com.onlicebook.bean.Orders;
import cn.com.onlicebook.bean.Pagetion;
import cn.com.onlicebook.bean.Userinfo;

public interface OrderBiz {
	/**
	 * 提交订单
	 * @param idArr 需要购买的书籍id
	 * @param cartMap 购物车
	 * @param user 买家
	 * @return
	 */
	public boolean sumbmitOrder(String [] idArr,Map<Integer,Object> cartMap,Userinfo user);
	/**
	 * 获取用户所有
	 * @param user
	 * @return
	 */
	public List<Orders> showOrder(Userinfo user);
	/**
	 * 根据订单编号获取订单详情
	 * @param oid
	 * @return
	 */
	public List<Items> getOrderDetail(int oid);
	/**
	 * 显示订单
	 * @param pagetion
	 * @param params
	 * @return
	 */
	public Pagetion listByPage(Pagetion pagetion,Map<String, Object> params);
}
