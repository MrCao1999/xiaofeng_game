package cn.com.onlicebook.biz.impl;

import java.util.List;
import java.util.Map;






import cn.com.onlicebook.bean.Books;
import cn.com.onlicebook.bean.Pagetion;
import cn.com.onlicebook.biz.BooksBiz;
import cn.com.onlicebook.dao.BookDao;
import cn.com.onlicebook.dao.impl.BookDaoImpl;

public class BooksBizImpl implements BooksBiz {
	BookDao bdao = new BookDaoImpl();

	@Override
	public Pagetion listByPage(Pagetion pagetion, Map<String, Object> params) {
		int count = bdao.count(params);
		pagetion.setTotalCount(count);
		
		List<Books> booksList = bdao.queryByPage(pagetion.getPageNo(), pagetion.getPageSize(), params);
		pagetion.setList(booksList);
		return pagetion;
	}

	@Override
	public Books showBooks(Integer bid) {
		return bdao.getById(bid);
	}

	@Override
	public int updateBooks(Books books) {
		return bdao.update(books);
	}

	@Override
	public int addBooks(Books books) {
		return bdao.save(books);
	}

	@Override
	public int delBooks(Integer bid) {
		return bdao.del(bid);
	}

	

	

	

}
