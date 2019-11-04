package cn.com.onlicebook.bean;
/**
 * 购物车实体
 * 是用于保存购物车的数据，并没有真实的表与之对应
 * @author Mrs.C
 *
 */
public class ShopCart {
	private Books books; //书本信息
	private Integer count;//书本的数量
	private Double totalPrice;  //书本的小计金额
	
	public ShopCart() {
		super();
	}
	
	
	public ShopCart(Books books, Integer count, Double totalPrice) {
		super();
		this.books = books;
		this.count = count;
		this.totalPrice = totalPrice;
	}


	public Books getBooks() {
		return books;
	}
	public void setBooks(Books books) {
		this.books = books;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	
	
}
