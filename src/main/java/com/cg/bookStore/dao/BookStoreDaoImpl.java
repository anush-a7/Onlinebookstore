package com.cg.bookStore.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.cg.bookStore.entity.BookInformation;
import com.cg.bookStore.entity.CartInformation;
import com.cg.bookStore.entity.OrderInformation;



/****************************************************************************************
 *          @author          Anusha 
 *          Description      It is a DAO implementation class that interacts with
 *                                      Oracle DataBase for 
 *                                     cart management and order management.
 *          @version         1.0
 *          Created Date     17-07-2020
*****************************************************************************************/



@Repository
public class BookStoreDaoImpl implements BookStoreDao{
	
	@PersistenceContext
	private EntityManager entityManager;

	

	@Override
	public List<CartInformation> viewCartByCustomerId(int customerId) {
		String jpql = "from CartInformation where customerId=:custId and status='cart'";
		TypedQuery<CartInformation> query = entityManager.createQuery(jpql, CartInformation.class);
		query.setParameter("custId", customerId);
		return query.getResultList();
	}
	
	@Override
	public BookInformation getBook(int bookId) {
		return entityManager.find(BookInformation.class, bookId);
	}
	
	@Override
	public String addBookToCart(CartInformation cart) {
		entityManager.persist(cart);
		return "Book Added To Cart Successfully";
	}
	
	@Override
	public String removeCartItem(CartInformation cart) {
		entityManager.remove(cart);
		return "Book Removed from Cart Successfully";
	}
	
	@Override 
	public CartInformation viewCartByCartId(int cartId) {
		return entityManager.find(CartInformation.class, cartId);
	}

	@Override
	public boolean updateCartQuantity(CartInformation cart) {
		entityManager.merge(cart);
		return true;
	}
	
	/************************************************************************************
	 * Method              :viewOrderByCustomerId
     * Description         :To view the list of orders using customerId in the Oracle Data base
	 * @param bookId       :customerId
	 * @returns List       :List of Orders 
     * Created By          :Anusha
     * Created Date        :17-07-2020                           
    **************************************************************************************/
	
	@Override
	public List<OrderInformation> viewOrderByCustomerId(int customerId) {
		String jpql = "from OrderInformation oi inner join fetch oi.customer c where c.customerId=:custid";
		TypedQuery<OrderInformation> query = entityManager.createQuery(jpql, OrderInformation.class);
		query.setParameter("custid",customerId);
		return query.getResultList();
	}
	
	
	@Override
	public OrderInformation viewOrderById(int orderId) {
		System.out.println(orderId);
		OrderInformation order = entityManager.find(OrderInformation.class,orderId);
		//entityManager.refresh(order);
		System.out.println(order);
		return order;
	}
}
