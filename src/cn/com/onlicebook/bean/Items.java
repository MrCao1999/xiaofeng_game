
package cn.com.onlicebook.bean;

public class Items {
	private int iid;//详情编号
	private int oid;//订单编号
	private int bid;//图书编号
	private int count;//数量
	private double price;//小计
	
	private Books book; //将书本对象当作一个属性
	public Items() {
		super();
	}

	public Items(int iid, int oid, int bid, int count, double price) {
		super();
		this.iid = iid;
		this.oid = oid;
		this.bid = bid;
		this.count = count;
		this.price = price;
	}

	public int getIid() {
		return iid;
	}

	public void setIid(int iid) {
		this.iid = iid;
	}

	public int getOid() {
		return oid;
	}

	public void setOid(int oid) {
		this.oid = oid;
	}

	public int getBid() {
		return bid;
	}

	public void setBid(int bid) {
		this.bid = bid;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Books getBook() {
		return book;
	}

	public void setBook(Books book) {
		this.book = book;
	}
	
	
}
