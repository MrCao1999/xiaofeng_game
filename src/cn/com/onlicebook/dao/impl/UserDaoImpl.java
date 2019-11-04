package cn.com.onlicebook.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.com.onlicebook.bean.Userinfo;
import cn.com.onlicebook.dao.BaseDao;
import cn.com.onlicebook.dao.UserDao;

public class UserDaoImpl extends BaseDao implements UserDao {

	@Override
	public Userinfo queryByParam(Userinfo userinfo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			//调用父类的连接方法，获取连接
			conn = super.openConn();
			String sql ="select * from userinfo where username = ? and password = ?";
			pstmt = conn.prepareStatement(sql);
			//传参
			pstmt.setString(1, userinfo.getUsername());
			pstmt.setString(2, userinfo.getPassword());
			
			rs = pstmt.executeQuery();
			//处理结果集
			if(rs.next()) {
				userinfo.setUserid(rs.getInt("userid"));
				userinfo.setUsername(rs.getString("username"));
				userinfo.setPassword(rs.getString("password"));
				userinfo.setRole(rs.getInt("role"));
			}else {
				userinfo = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			super.closeConn();
		}
		return userinfo;
	}

	@Override
	public int save(Userinfo userinfo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			//调用父类的连接方法，获取连接
			conn = super.openConn();
			String sql ="insert into userinfo values(null,?,?,?,1,1)";
			pstmt = conn.prepareStatement(sql);
			//传参
			pstmt.setString(1, userinfo.getUsername());
			pstmt.setString(2, userinfo.getPassword());
			pstmt.setString(3, userinfo.getEmail());
			result = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			super.closeConn();
		}
		return result;
	}

	@Override
	public int update(Userinfo userinfo) {
		String sql = "update userinfo set username = ?,password = ?,email = ?,role = ? where userid = ?";
		Object [] params = {userinfo.getUsername(),userinfo.getPassword(),userinfo.getEmail(),userinfo.getRole(),userinfo.getUserid()};
		//调用父类的通用的增删改方法
		return super.excetueUpdate(sql, params);
	}

	@Override
	public List<Userinfo> queryByPage(int pageNo, int pageSize, Map<String, Object> params) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Userinfo> userList = new ArrayList<>();
		try {
			//调用父类的连接方法，获取连接
			conn = super.openConn();
			//因为参数可能为空，所以在语句中直接加上一个where肯定为真的条件，便于拼接别的条件
//			String sql = "SELECT * from (select u1.* from(select us.*,rownum rn from userinfo us where rownum <=? and state =1) u1 where rn >?)u";
//			String sou = (String)params.get("sou");
//			if(sou != null && !"".equals(sou)) {
//				sql +=" where u.username like '%"+sou+"%'";
//			}
			
			String sql = "SELECT * from (select u1.* from (select us.*,rownum rn from userinfo us where 1= 1";
			String sou = (String)params.get("sou");
			if(sou != null && !"".equals(sou)) {
				sql +=" and us.username like '%"+sou+"%'";
			}
			sql += "and rownum <=? and state = 1) u1 where rn >?) u2 order by u2.userid";
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
				Userinfo userinfo = new Userinfo();
				userinfo.setUserid(rs.getInt(1));
				userinfo.setUsername(rs.getString(2));
				userinfo.setPassword(rs.getString(3));
				userinfo.setEmail(rs.getString(4));
				userinfo.setRole(rs.getInt(5));
				userinfo.setState(rs.getInt(6));
				userList.add(userinfo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			super.closeConn();
		}
		return userList;
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
			String sql = "SELECT count(1) FROM userinfo u where 1=1 and state =1";
			//从map中取出值，然后一一添加到sql条件中
			String sou = (String)param.get("sou");
			if(sou != null && !"".equals(sou)) {
				sql +=" and u.username like '%"+sou+"%'";
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
	public int add(Userinfo userinfo) {
		String sql = "insert into  userinfo(username,password,email,role,state) values(?,?,?,?,?)";
		Object [] params = {userinfo.getUsername(),userinfo.getPassword(),userinfo.getEmail(),userinfo.getRole(),userinfo.getState()};
		//调用父类的通用的增删改方法
		return super.excetueUpdate(sql, params);
	}

	@Override
	public Userinfo getById(Integer userid) {
		Connection conn =null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Userinfo userinfo = null;
		try {
			//调用父类的连接方法，获取连接
			conn = super.openConn();
			String sql = "select * from userinfo where userid = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userid);
			rs = pstmt.executeQuery();
			//传参
			if(rs.next()) {
				userinfo = new Userinfo();
				userinfo.setUserid(rs.getInt(1));
				userinfo.setUsername(rs.getString(2));
				userinfo.setPassword(rs.getString(3));
				userinfo.setEmail(rs.getString(4));
				userinfo.setRole(rs.getInt(5));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			super.closeConn();
		}
		return userinfo;
		
		
	}

	@Override
	public int deluser(Integer userid) {
		String sql = "update userinfo set state = 0 where userid = ?";
		
		Object [] params = {userid};
		return super.excetueUpdate(sql, params);
	}
	
}
