package cn.com.onlicebook.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.com.onlicebook.bean.Books;
import cn.com.onlicebook.bean.Items;
import cn.com.onlicebook.bean.Orders;
import cn.com.onlicebook.dao.BaseDao;
import cn.com.onlicebook.dao.ItemsDao;

public class ItemsDaoImpl extends BaseDao implements ItemsDao {

	@Override
	public int save(Items item) {
		String sql="insert into items(oid,bid,count,price) values(?,?,?,?)";
		Object [] params = {item.getOid(),item.getBid(),item.getCount(),item.getPrice()};
		return super.excetueUpdate(sql, params);
	}

	@Override
	public List<Items> queryByOid(int oid) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Items> iList = new ArrayList<>();
		try {
			//调用父类的连接方法，获取连接
			conn = super.openConn();
			//因为参数可能为空，所以在语句中直接加上一个where肯定为真的条件，便于拼接别的条件
			String sql = "SELECT i.*,b.* from items i,books b where i.bid = b.bid and i.oid =?";
			//创建命令对象
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, oid);
			//执行查询，获取结果集
			rs = pstmt.executeQuery();
			//处理结果集
			while(rs.next()) {
				Items items = new Items();
				items.setCount(rs.getInt("count"));
				items.setPrice(rs.getDouble("price"));
				items.setOid(oid);
				
				Books book = new Books();
				book.setB_price(rs.getDouble("b_price"));
				book.setBookname(rs.getString("bookname"));
				book.setImage(rs.getString("image"));
				items.setBook(book);
				iList.add(items);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			super.closeConn();
		}
		return iList;
	}

	@Override
	public Items queryAll(Items items) {
		String sql ="select * from items";
		return null;
	}

}
