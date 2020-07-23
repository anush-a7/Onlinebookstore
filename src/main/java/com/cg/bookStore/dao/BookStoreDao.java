package com.cg.bookStore.dao;

import java.util.List;

import com.cg.bookStore.entity.BookInformation;
import com.cg.bookStore.entity.CartInformation;
import com.cg.bookStore.entity.OrderInformation;

public interface BookStoreDao {
	
	public List<CartInformation> viewCartByCustomerId(int customerId);
	
	public BookInformation getBook(int bookId);
	
	public String addBookToCart(CartInformation cart);

	public String removeCartItem(CartInformation cart);

	public CartInformation viewCartByCartId(int cartId);

	public boolean updateCartQuantity(CartInformation cart);

	public List<OrderInformation> viewOrderByCustomerId(int customerId);

	OrderInformation viewOrderById(int orderId); 

}
