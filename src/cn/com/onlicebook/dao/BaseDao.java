package cn.com.onlicebook.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BaseDao {
	protected Connection conn;
	protected PreparedStatement ps;
	protected ResultSet rs;
	
	//获得数据源连接
	public Connection openConn() {
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/book");
			conn = ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	/**
	 * 公共的增删改方法
	 * @param sql
	 * @return
	 */
	public int excetueUpdate(String sql,Object...params) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			//获取连接
			conn = this.openConn();
			//通过连接对象，获取预处理命令对象
			pstmt = conn.prepareStatement(sql);
			//循环遍历参数，传入sql语句，替换占位符
			for(int  i =0;i<params.length;i++) {
				pstmt.setObject(i+1, params[i]);
			}
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			this.closeConn();
		}
		return result;
	}
	/**
	 * 关闭连接
	 */
	public void closeConn() {
		
			try {
				if(rs!=null)rs.close();
				if(ps!=null)ps.close();
				if(conn!=null)conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		
	}
}
