package cn.com.onlicebook.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.com.onlicebook.bean.Books;
import cn.com.onlicebook.dao.BaseDao;
import cn.com.onlicebook.dao.BookDao;

public class BookDaoImpl extends BaseDao implements BookDao {

	@Override
	public List<Books> queryByPage(int pageNo, int pageSize,
			Map<String, Object> params) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Books> booksList = new ArrayList<>();
		try {
			//调用父类的连接方法，获取连接
			conn = super.openConn();
			//因为参数可能为空，所以在语句中直接加上一个where肯定为真的条件，便于拼接别的条件
			String sql = "SELECT * from (select b1.* from (select b.*,rownum rn from books b where 1= 1";
			String sou = (String)params.get("sou");
			if(sou != null && !"".equals(sou)) {
				sql +=" and b.bookname like '%"+sou+"%'";
			}
			sql += "and rownum <=? and state = 1) b1 where rn >?) b2 order by b2.bid";
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
				Books books = new Books();
				books.setBid(rs.getInt(1));
				books.setBookname(rs.getString(2));
				books.setB_price(rs.getDouble(3));
				books.setImage(rs.getString(4));
				books.setStock(rs.getInt(5));
				books.setState(rs.getInt(6));
				booksList.add(books);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			super.closeConn();
		}
		return booksList;
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
			String sql = "SELECT count(1) FROM books b where 1=1 and state = 1";
			//从map中取出值，然后一一添加到sql条件中
			String sou = (String)param.get("sou");
			if(sou != null && !"".equals(sou)) {
				sql +=" and b.bookname like '%"+sou+"%'";
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

	@Override
	public Books getById(Integer bid) {
		Connection conn =null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Books books = null;
		try {
			//调用父类的连接方法，获取连接
			conn = super.openConn();
			String sql ="select * from books where bid = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bid);
			rs = pstmt.executeQuery();
			//传参
			if(rs.next()) {
				books = new Books();
				books.setBid(rs.getInt(1));
				books.setBookname(rs.getString(2));
				books.setB_price(rs.getDouble(3));
				books.setImage(rs.getString(4));
				books.setStock(rs.getInt(5));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			super.closeConn();
		}
		return books;
	}

	@Override
	public int update(Books books) {
		String sql = "update books set bookname = ?,b_price = ?,image = ?,stock = ? where bid = ?";
		Object [] params = {books.getBookname(),books.getB_price(),books.getImage(),books.getStock(),books.getBid()};
		//调用父类的通用的增删改方法
		return super.excetueUpdate(sql, params);
	}

	@Override
	public int save(Books books) {
		String sql = "insert into books(bookname,b_price,image,stock,state) values(?,?,?,?,?)";
		Object [] params = {books.getBookname(),books.getB_price(),books.getImage(),books.getStock(),books.getState()};
		 return super.excetueUpdate(sql, params);
	}

	@Override
	public int del(int bid) {
		String sql = "update books set state = 0 where bid = ?";
		Object [] params = {bid};
		return super.excetueUpdate(sql, params);
	}

	


	
}
