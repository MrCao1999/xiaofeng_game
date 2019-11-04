package cn.com.onlicebook.bean;

import java.sql.Timestamp;

public class Orders {
	private int oid;//订单编号
	private int userid;//用户id
	private Timestamp createtime;//下单时间
	private double total_price;//订单合计
	
	public Orders() {
		super();
	}

	public Orders(int oid, int userid, Timestamp createtime, double total_price) {
		super();
		this.oid = oid;
		this.userid = userid;
		this.createtime = createtime;
		this.total_price = total_price;
	}

	public int getOid() {
		return oid;
	}

	public void setOid(int oid) {
		this.oid = oid;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public Timestamp getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Timestamp timestamp) {
		this.createtime = timestamp;
	}

	public double getTotal_price() {
		return total_price;
	}

	public void setTotal_price(double total_price) {
		this.total_price = total_price;
	}
	
	
}
