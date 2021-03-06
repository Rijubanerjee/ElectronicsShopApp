/**
 * @author SUKANYA BISWAS
 * @Version : 1.0
 * @Description : This Service Class contains the service regarding Basket Management
 */
package com.cg.eshop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.eshop.dao.IBasketDao;
import com.cg.eshop.dao.ICustomerDao;
import com.cg.eshop.dao.IElectronicProductDetailsDao;
import com.cg.eshop.dto.BasketDto;
import com.cg.eshop.entity.Basket;
import com.cg.eshop.entity.Customer;
import com.cg.eshop.entity.ElectronicProductDetails;
import com.cg.eshop.exception.BasketException;
import com.cg.eshop.exception.CustomerNotFoundException;
import com.cg.eshop.exception.ProductNotFoundException;
import com.cg.eshop.utils.BasketConstants;

@Service("BasketService")
@Transactional(readOnly = true)
public class BasketServiceImpl implements IBasketService {

	@Autowired
	IBasketDao basketdao;

	@Autowired
	ICustomerDao custdao;

	@Autowired
	IElectronicProductDetailsDao productdao;

	/**
	 * @param custId CustomerId
	 * @return List<Basket>
	 * @throws BasketException, if Basket is empty for a customer Id
	 * @throws CustomerNotFoundException, if Customer Id not found
	 * @description This method returns a list of basket items for a customer Id
	 * @createdAt 15-May-2021
	 */
	@Override
	public List<Basket> viewItems(Integer custId) throws BasketException, CustomerNotFoundException {
		Optional<Customer> cust = custdao.findById(custId);
		if (!cust.isPresent())
			throw new CustomerNotFoundException(BasketConstants.CUSTOMER_NOT_FOUND);
		List<Basket> bkt = basketdao.getItemsFromBasket(custId);
		if (bkt.isEmpty())
			throw new BasketException(BasketConstants.BASKET_EMPTY);
		return bkt;

	}

	/**
	 * @return List<Basket>
	 * @throws BasketException ,if Basket is empty for a customer Id
	 * @description This method returns all the basket items available
	 * @createdAt 15-May-2021
	 */

	@Override
	public List<Basket> viewAllItems() throws BasketException {
		List<Basket> bkt = basketdao.findAll();
		if (bkt.isEmpty())
			throw new BasketException(BasketConstants.BASKET_EMPTY);
		return bkt;

	}

	/**
	 * @param basketdto BasketDto
	 * @return BasketId Integer
	 * @throws CustomerNotFoundException, if Customer Id not found
	 * @throws ProductNotFoundException , if Product Id not found
	 * @description This method add items to the basket for a specific customer and product
	 * @createdAt 15-May-2021
	 */

	@Override
	@Transactional(readOnly = false)
	public Integer addItem(BasketDto basketdto) throws ProductNotFoundException, CustomerNotFoundException {
		Optional<Customer> customer = custdao.findById(basketdto.getCustId());
		if (!customer.isPresent())
			throw new CustomerNotFoundException(BasketConstants.CUSTOMER_NOT_FOUND);
		Optional<ElectronicProductDetails> prod = productdao.findById(basketdto.getProdId());
		if (!prod.isPresent())
			throw new ProductNotFoundException(BasketConstants.PRODUCT_NOT_FOUND);
		Customer cust = customer.get();
		ElectronicProductDetails product = prod.get();
		Basket basket = new Basket();
		basket.setCustomer(cust);
		basket.setProductDetails(product);
		Basket persistedBasket = basketdao.save(basket);
		return persistedBasket.getBasketId();
	}

	/**
	 * @param custId CustomerId
	 * @return boolean
	 * @throws BasketException          ,if Basket is empty for a customer Id
	 * @throws CustomerNotFoundException, if Customer Id not found
	 * @description This method removes all basket items for a customer Id
	 * @createdAt 16-May-2021
	 */

	@Override
	@Transactional(readOnly = false)
	public boolean removeAllItem(int custId) throws CustomerNotFoundException, BasketException {
		Optional<Customer> customer = custdao.findById(custId);
		if (!customer.isPresent())
			throw new CustomerNotFoundException(BasketConstants.CUSTOMER_NOT_FOUND);

		List<Basket> bkt = viewItems(custId);
		if (bkt == null)
			throw new BasketException(BasketConstants.BASKET_EMPTY);
		for (Basket b : bkt) {
			basketdao.delete(b);

		}

		return true;
	}

	/**
	 * @param basketId BasketId
	 * @return boolean
	 * @throws BasketException ,if Basket id is not found
	 * @description This method removes a particular basket item
	 * @createdAt 16-May-2021
	 */

	@Override
	@Transactional(readOnly = false)
	public boolean removeByCartId(int basketId) throws BasketException {
		Optional<Basket> item = basketdao.findById(basketId);
		if (!item.isPresent())
			throw new BasketException(BasketConstants.BASKET_ITEM_NOT_PRESENT);
		Basket bkt = item.get();
		basketdao.delete(bkt);
		return true;
	}

}