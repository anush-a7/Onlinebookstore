package com.cg.bookStore.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.bookStore.entity.OrderInformation;
import com.cg.bookStore.exceptions.InvalidCustomerIdException;
import com.cg.bookStore.exceptions.NullArgumentException;
import com.cg.bookStore.exceptions.OrderIdNotFoundException;
import com.cg.bookStore.service.BookStoreService;

/**************************************************************************************
 *          @author           Anusha
 *          Description       It is a Rest Controller class that provides the suitable
 *                                       Order Information methods for the given 
 *                                       matching URL and returns response in different
 *                                       types of data objects.
 *          @version          1.0
 *          Created Date      17-07-2020
***************************************************************************************/

@RestController
@RequestMapping
public class OrderController {
	
	@Autowired
	BookStoreService service;
	
	@PostMapping("/addOrder")
	public ResponseEntity<OrderInformation> addOrder(@RequestBody OrderInformation order) {
		
		if(order==null) {
			throw new NullArgumentException();
		}
		else {
			service.addOrder(order);
			return new ResponseEntity<OrderInformation>(order, HttpStatus.OK);
		}
	}	
	
	@GetMapping("/searchOrder/{orderId}")
	public OrderInformation viewOrderById(@PathVariable("orderId") Integer id) throws OrderIdNotFoundException{
		OrderInformation order= service.viewOrderById(id);
		return order;
		
	}
	
	@GetMapping("/viewAllOrder")
	public Iterable<OrderInformation> viewAllOrder(){
		Iterable<OrderInformation> orderList= service.listAllOrder();
		return orderList;
	}
	
	/******************************************************************************************************
	 * Method                 :viewOrdersByCustomerId
     * Description            :To provide the service of getting Order information for the "viewOrderByCustomerId/{customerId} URL.
	 * @param customerId      :customerId(int)
	 * @param request         :HttpServletRequest instance
	 * @throws CustomerReviewException
	 * @returns reviews       :List of Customer Review instance
     * Created By             :Anusha
     * Created Date           :17-07-2020                          
    *****************************************************************************************************/
	@GetMapping("/viewOrderByCustomerId/{customerId}")
	public List<OrderInformation> viewOrderByCustomerId(@PathVariable("customerId") int customerId) throws InvalidCustomerIdException {
		return service.viewOrderByCustomerId(customerId);
	}

	
	
}
