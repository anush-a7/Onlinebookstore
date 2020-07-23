package com.cg.bookStore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.bookStore.dao.BookStoreDao;
import com.cg.bookStore.dao.OrderDao;
import com.cg.bookStore.entity.BookInformation;
import com.cg.bookStore.entity.CartInformation;
import com.cg.bookStore.entity.OrderInformation;
import com.cg.bookStore.exceptions.BookNotFoundException;
import com.cg.bookStore.exceptions.CartIdException;
import com.cg.bookStore.exceptions.InvalidCustomerIdException;
import com.cg.bookStore.exceptions.InvalidQuantityException;
import com.cg.bookStore.exceptions.OrderIdNotFoundException;
import com.cg.bookStore.exceptions.RecordAlreadyPresentException;
import com.cg.bookStore.exceptions.RecordNotFoundException;
import com.cg.bookStore.util.BookStoreConstants;

@Service("bookstore")
@Transactional
public class BookStoreServiceImpl implements BookStoreService{

	@Autowired
	private BookStoreDao bookStoreDao;
	
	@Autowired
	private OrderDao orderDao;

	@Override
	public List<CartInformation> viewCartByCustomerId(int customerId) {
		List<CartInformation> reviewList=bookStoreDao.viewCartByCustomerId(customerId);
		return reviewList;
	}
	
	
	@Override
	public String addBookToCart(int bookId, int customerId, String status) throws BookNotFoundException{
		BookInformation book = bookStoreDao.getBook(bookId);
		if(book == null) {
			throw new BookNotFoundException(BookStoreConstants.BOOK_ID_EXCEPTION);
		}
		else {
		CartInformation cartInfo = new CartInformation();
		cartInfo.setBook(book);
		cartInfo.setQuantity(1);
		cartInfo.setCustomerId(customerId);
		cartInfo.setStatus(status);
		return bookStoreDao.addBookToCart(cartInfo);
		}
	}
	
	@Override
	public String removeCartItem(int cartId) throws CartIdException{
		CartInformation cart=bookStoreDao.viewCartByCartId(cartId);
		if(cart == null) {
			throw new CartIdException(BookStoreConstants.CART_ID_EXCEPTION);
		}
		return bookStoreDao.removeCartItem(cart);
	}
	
	@Override
	public String clearCartByCustomerId(int customerId) throws CartIdException{
		List<CartInformation> carts=bookStoreDao.viewCartByCustomerId(customerId);
		if(carts.isEmpty()) {
			throw new CartIdException(BookStoreConstants.CART_ID_EXCEPTION2);
		}
		int i=0;
		while(i<carts.size()) {
			bookStoreDao.removeCartItem(carts.get(i));
			i++;
		}
		return "Cart cleared Successfully";
	}

	public String updateCart(int cartId,int quantity) throws InvalidQuantityException {
		CartInformation cart = bookStoreDao.viewCartByCartId(cartId);
		if(quantity < 0) {
			throw new InvalidQuantityException(BookStoreConstants.QUANTITY_EXCEPTION);
		}
		else
		{
			cart.setQuantity(quantity);
			bookStoreDao.updateCartQuantity(cart);
			return "Cart Updated";
		}
				
	}
	
	@Override
	public OrderInformation addOrder(OrderInformation order) throws RecordAlreadyPresentException {
		Optional<OrderInformation> newOrder = orderDao.findById(order.getOrderId());
		if(newOrder.isPresent()) {
			throw new RecordAlreadyPresentException();
		}
		else {
			orderDao.save(order);
			return order;
		}
	}

	@Override
	public OrderInformation viewOrderById(int orderId) throws OrderIdNotFoundException {
		OrderInformation order = bookStoreDao.viewOrderById(orderId);
		System.out.println(order);
		if(order==null) {
			throw new OrderIdNotFoundException(BookStoreConstants.ORDER_ID_EXCEPTION);
		}
		else
			return order;
	
	}



	@Override
	public Iterable<OrderInformation> listAllOrder() {
		Iterable<OrderInformation> list = orderDao.findAll();
		if(list==null) {
			throw new RecordNotFoundException();
		}
		else
			return list;
	}
	
	
	/************************************************************************************
	 * Method              :viewOrderByCustomerId
     * Description         :To view list orders using customerId in the Oracle Data base
	 * @param bookId       :customerId
	 * @returns List       :List of Orders
     * Created By          :Anusha
     * Created Date        :17-07-2020                           
    **************************************************************************************/
	@Override
	public List<OrderInformation> viewOrderByCustomerId(int customerId) throws InvalidCustomerIdException{
		List<OrderInformation> list = bookStoreDao.viewOrderByCustomerId(customerId);
		if(list.isEmpty()) {
			throw new InvalidCustomerIdException(BookStoreConstants.CUSTOMER_ID_EXCEPTION);
		}
		else
			return list;
	}

}
