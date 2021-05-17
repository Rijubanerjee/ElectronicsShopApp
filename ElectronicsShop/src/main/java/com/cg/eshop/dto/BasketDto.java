package com.cg.eshop.dto;

import javax.validation.constraints.NotNull;

import com.cg.eshop.utils.BasketConstants;

public class BasketDto {
	
	private Integer basketId;
	@NotNull(message = BasketConstants.CUSTOMER_NOT_FOUND)
	private Integer custId;
	@NotNull(message = BasketConstants.PRODUCT_NOT_FOUND)
	private Integer prodId;
	public BasketDto(Integer custId, Integer prodId) {
		super();
		this.custId = custId;
		this.prodId = prodId;
	}
	public Integer getCustId() {
		return custId;
	}
	public void setCustId(Integer custId) {
		this.custId = custId;
	}
	public Integer getProdId() {
		return prodId;
	}
	public void setProdId(Integer prodId) {
		this.prodId = prodId;
	}
	public Integer getBasketId() {
		return basketId;
	}
	
	

}