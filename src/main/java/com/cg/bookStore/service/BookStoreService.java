package com.cg.bookStore.service;

import java.util.List;

import com.cg.bookStore.entity.CartInformation;
import com.cg.bookStore.entity.OrderInformation;
import com.cg.bookStore.exceptions.BookNotFoundException;
import com.cg.bookStore.exceptions.CartIdException;
import com.cg.bookStore.exceptions.InvalidCustomerIdException;
import com.cg.bookStore.exceptions.InvalidQuantityException;
import com.cg.bookStore.exceptions.OrderIdNotFoundException;
import com.cg.bookStore.exceptions.RecordAlreadyPresentException;

public interface BookStoreService {

	public List<CartInformation> viewCartByCustomerId(int customerId);

	public String addBookToCart(int bookId, int customerId, String status) throws BookNotFoundException;

	public String removeCartItem(int cartId) throws CartIdException;

	public String clearCartByCustomerId(int customerId) throws CartIdException;

	public String updateCart(int cartId,int quantity) throws InvalidQuantityException ;
	
	public OrderInformation addOrder(OrderInformation order) throws RecordAlreadyPresentException;
	
	public OrderInformation viewOrderById(int id) throws OrderIdNotFoundException;
	
	public Iterable<OrderInformation> listAllOrder();
	
	public List<OrderInformation> viewOrderByCustomerId(int customerId) throws InvalidCustomerIdException;

}
