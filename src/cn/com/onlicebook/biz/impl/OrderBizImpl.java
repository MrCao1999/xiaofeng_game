package cn.com.onlicebook.biz.impl;

import java.util.List;
import java.util.Map;

import cn.com.onlicebook.bean.Books;
import cn.com.onlicebook.bean.Items;
import cn.com.onlicebook.bean.Orders;
import cn.com.onlicebook.bean.Pagetion;
import cn.com.onlicebook.bean.ShopCart;
import cn.com.onlicebook.bean.Userinfo;
import cn.com.onlicebook.biz.OrderBiz;
import cn.com.onlicebook.dao.ItemsDao;
import cn.com.onlicebook.dao.OrderDao;
import cn.com.onlicebook.dao.impl.ItemsDaoImpl;
import cn.com.onlicebook.dao.impl.OrderDaoImpl;

public class OrderBizImpl implements OrderBiz {
	OrderDao orderDao = new OrderDaoImpl();
	ItemsDao itemsDao = new ItemsDaoImpl();
	@Override
	public boolean sumbmitOrder(String[] idArr, Map<Integer, Object> cartMap, Userinfo user) {
		//求订单合计
		Double totalPrice = 0.0;
		for(String idStr : idArr) {
			//循环遍历，需要购买的书籍id
			Integer bid = Integer.valueOf(idStr);
			//根据要购买的书籍id，从购物车中取出当前书籍的信息
			ShopCart cart = (ShopCart) cartMap.get(bid);
			totalPrice += cart.getTotalPrice();
		}
		//调用订单表的Dao,生成一条订单
		Orders orders = new Orders();
		orders.setUserid(user.getUserid());
		orders.setTotal_price(totalPrice);
		
		int result = orderDao.save(orders);
		if(result > 0) {
			//获取新增订单的订单id
			int oid = orderDao.getMaxOrderId();
			//循环插入到订单详情表
			for(String idStr : idArr) {
				//循环遍历，需要购买的书籍id
				Integer bid = Integer.valueOf(idStr);
				//根据要购买的书籍id，从购物车中取出当前书籍的信息
				ShopCart cart = (ShopCart) cartMap.get(bid);
				//插入到订单详情表中
				Items item = new Items();
				item.setOid(oid);
				item.setBid(bid);
				item.setCount(cart.getCount());
				item.setPrice(cart.getTotalPrice());
				itemsDao.save(item);
			}
			return true;
		}
		
		return false;
	}
	@Override
	public List<Orders> showOrder(Userinfo user) {
		
		return orderDao.queryAll(user.getUserid());
	}
	@Override
	public List<Items> getOrderDetail(int oid) {
		return itemsDao.queryByOid(oid);
	}
	@Override
	public Pagetion listByPage(Pagetion pagetion, Map<String, Object> params) {
		int count = orderDao.count(params);
		pagetion.setTotalCount(count);
		
		List<Orders> oList = orderDao.queryByPage(pagetion.getPageNo(), pagetion.getPageSize(), params);
		pagetion.setList(oList);
		return pagetion;
	}

}
