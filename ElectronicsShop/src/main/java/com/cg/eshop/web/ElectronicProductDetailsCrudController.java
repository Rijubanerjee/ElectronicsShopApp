/**
 * @author ALANGKRITA CHAKRABORTY
 * @Version : 1.0
 * @Description : This Controller Class manages the RestController for Category Management
 */
package com.cg.eshop.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.cg.eshop.entity.ElectronicProductDetails;
import com.cg.eshop.exception.ProductNotFoundException;
import com.cg.eshop.service.IElectronicProductDetailService;

@RestController
public class ElectronicProductDetailsCrudController {
	@Autowired
	private IElectronicProductDetailService electronicProductDetailService;
	/**
	 * @return product details
	 * @throws ProductNotFoundException when product details list is empty
	 * @description This method returns the product details by product Id
	 * @createdAt 18-May-2021 
	 */

	@GetMapping("viewproductdetailbyproductid/{prod_id}")
	public ElectronicProductDetails getProductDetailsByProductId(@PathVariable("prod_id") Integer productId)
			throws ProductNotFoundException {
		return electronicProductDetailService.getProductDetailsByProductId(productId);
	}
}
