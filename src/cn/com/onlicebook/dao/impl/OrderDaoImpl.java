package cn.com.onlicebook.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.com.onlicebook.bean.Orders;
import cn.com.onlicebook.dao.BaseDao;
import cn.com.onlicebook.dao.OrderDao;

public class OrderDaoImpl extends BaseDao implements OrderDao {

	@Override
	public int save(Orders orders) {
		String sql ="insert into orders(userid,createtime,total_price) values(?,sysdate,?)";
		Object[] objs = {orders.getUserid(),orders.getTotal_price()};
		return super.excetueUpdate(sql, objs);
	}

	@Override
	public int getMaxOrderId() {
		Connection conn =null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Integer oid = 0;
		try {
			//调用父类的连接方法，获取连接
			conn = super.openConn();
			//因为参数可能为空，所以在语句中直接加上一个where肯定为真的条件，便于拼接别的条件
			String sql = "SELECT max(oid) FROM orders";
			//创建命令对象
			pstmt = conn.prepareStatement(sql);
			//执行查询，获取结果集
			rs = pstmt.executeQuery();
			//处理结果集
			if(rs.next()) {
				oid = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			super.closeConn();
		}
		return oid;
	}

	@Override
	public List<Orders> queryAll(Integer userid) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Orders> oList = new ArrayList<>();
		try {
			//调用父类的连接方法，获取连接
			conn = super.openConn();
			//因为参数可能为空，所以在语句中直接加上一个where肯定为真的条件，便于拼接别的条件
			String sql = "SELECT * from orders where userid = ? order by createtime desc";
			
			//创建命令对象
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userid);
			//执行查询，获取结果集
			rs = pstmt.executeQuery();
			//处理结果集
			while(rs.next()) {
				Orders orders = new Orders();
				orders.setOid(rs.getInt("oid"));
				//getDate()得到的时日期，没有时分秒，而getTimestamp()是有时分秒的时间
				orders.setCreatetime(rs.getTimestamp("createtime"));
				orders.setTotal_price(rs.getDouble("total_price"));
				oList.add(orders);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			super.closeConn();
		}
		return oList;
	}

	@Override
	public List<Orders> queryByPage(int pageNo, int pageSize,
			Map<String, Object> params) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Orders> oList = new ArrayList<>();
		try {
			//调用父类的连接方法，获取连接
			conn = super.openConn();
			//因为参数可能为空，所以在语句中直接加上一个where肯定为真的条件，便于拼接别的条件
			String sql = "SELECT * from (select b1.* from (select b.*,rownum rn from orders b where rownum <=?) b1 where rn >?) b where 1 = 1";
			String sou = (String)params.get("sou");
			if(sou != null && !"".equals(sou)) {
				sql +=" where b.oid like '%"+sou+"%'";
				
			}
			sql += " order by b.oid desc";
			
			//创建命令对象
			pstmt = conn.prepareStatement(sql);
			//计算起始行的下标
			int begin = (pageNo -1) * pageSize;
			pstmt.setInt(1, pageNo*pageSize);
			pstmt.setInt(2, begin);
			//执行查询，获取结果集
			rs = pstmt.executeQuery();
			//处理结果集
			while(rs.next()) {
				Orders orders = new Orders();
				orders.setOid(rs.getInt(1));
				orders.setUserid(rs.getInt(2));
				orders.setCreatetime(rs.getTimestamp(3));
				orders.setTotal_price(rs.getDouble(4));
				oList.add(orders);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			super.closeConn();
		}
		return oList;
	}

	@Override
	public int count(Map<String, Object> param) {
		Connection conn =null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int num = 0;
		try {
			//调用父类的连接方法，获取连接
			conn = super.openConn();
			//因为参数可能为空，所以在语句中直接加上一个where肯定为真的条件，便于拼接别的条件
			String sql = "SELECT count(1) FROM orders b where 1=1";
			//从map中取出值，然后一一添加到sql条件中
			String sou = (String)param.get("sou");
			if(sou != null && !"".equals(sou)) {
				sql +=" and b.oid like '%"+sou+"%'";
			}
			
			//创建命令对象
			pstmt = conn.prepareStatement(sql);
			//执行查询，获取结果集
			rs = pstmt.executeQuery();
			//处理结果集
			if(rs.next()) {
				num = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			super.closeConn();
		}
		return num;
	}

}
