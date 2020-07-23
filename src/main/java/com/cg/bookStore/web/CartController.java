package com.cg.bookStore.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.bookStore.entity.CartInformation;
import com.cg.bookStore.exceptions.BookNotFoundException;
import com.cg.bookStore.exceptions.CartIdException;
import com.cg.bookStore.exceptions.InvalidQuantityException;
import com.cg.bookStore.service.BookStoreService;

@RestController
public class CartController {
	
	@Autowired
	public BookStoreService service;
	
	@CrossOrigin
	@GetMapping("/viewcartbycustomerid/{customerid}")
	public List<CartInformation> viewCartByCustomerId(@PathVariable("customerid") int customerId,
			HttpServletRequest request) {
			return service.viewCartByCustomerId(customerId);
	}
	
	@CrossOrigin
	@PostMapping("/addbooktocart/{bookid}/{customerid}/{status}")
	public String addBookToCart(@PathVariable("bookid") int bookId, @PathVariable("customerid")int customerId, @PathVariable("status")String status) throws BookNotFoundException {
		return service.addBookToCart(bookId, customerId, status);
	}
	
	@CrossOrigin
	@DeleteMapping("/removecartitem/{cartid}")
	public String removeCartItem(@PathVariable("cartid") int cartId, HttpServletRequest request) throws CartIdException {
		return service.removeCartItem(cartId);
	}
	
	@CrossOrigin
	@DeleteMapping("/clearcartbycustomerid/{customerid}")
	public String clearCartByCustomerId(@PathVariable("customerid") int customerId, HttpServletRequest request) throws CartIdException {
		return service.clearCartByCustomerId(customerId);
	}
	
	@PostMapping("/update/{cartId}/{newQuantity}")
	public String updateCart(@PathVariable("cartId") int cartId, @PathVariable("newQuantity") int newQuantity ) throws InvalidQuantityException {
		return service.updateCart(cartId, newQuantity);			
	}
	
}
