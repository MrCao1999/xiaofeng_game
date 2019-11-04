package cn.com.onlicebook.bean;

public class Books {
	private int bid;//图书编号
	private String bookname;//图书名称
	private double b_price;//图书价格
	private String image;//图片路径
	private int stock;//库存
	private int state;//状态(默认为1，为0时不显示数据)
	
	public Books() {
		super();
	}

	public Books(String bookname, double b_price, String image, int stock,int state) {
		super();
		this.bookname = bookname;
		this.b_price = b_price;
		this.image = image;
		this.stock = stock;
		this.state = state;
	}

	public Books(int bid, String bookname, double b_price, String image,
			int stock) {
		super();
		this.bid = bid;
		this.bookname = bookname;
		this.b_price = b_price;
		this.image = image;
		this.stock = stock;
	}

	public int getBid() {
		return bid;
	}

	public void setBid(int bid) {
		this.bid = bid;
	}

	public String getBookname() {
		return bookname;
	}

	public void setBookname(String bookname) {
		this.bookname = bookname;
	}

	public double getB_price() {
		return b_price;
	}

	public void setB_price(double b_price) {
		this.b_price = b_price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}
	
	
}
