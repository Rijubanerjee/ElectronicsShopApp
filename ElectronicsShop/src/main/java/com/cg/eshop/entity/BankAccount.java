package com.cg.eshop.entity;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "cg_bank_account")
public class BankAccount {
	@Id
	@Column(name = "bank_id")
	private Integer bankId;
	@Column(name = "card_number", unique = true)
	private Integer cardNumber;
	@OneToOne()
	@JoinColumn(name="cust_id",referencedColumnName = "cust_id")
	private Customer customer;
	@Column(name = "card_holder")
	private String cardHolderName;
	@Column(name = "expiry_dt")
	private LocalDate expiryDt;
	@Column(name = "cvv_number")
	private Integer cvv;
	@Column(name = "amount")
	private Double amount;
	@JsonIgnore
	@OneToMany
	private Set<BankTransaction> banktranxs;


	public BankAccount(Integer bankId, Integer cardNumber, Customer customer, String cardHolderName, LocalDate expiryDt,
			Integer cvv, Double amount) {
		super();
		this.bankId = bankId;
		this.cardNumber = cardNumber;
		this.customer = customer;
		this.cardHolderName = cardHolderName;
		this.expiryDt = expiryDt;
		this.cvv = cvv;
		this.amount = amount;
	}

	public Integer getBankId() {
		return bankId;
	}

	public void setBankId(Integer bankId) {
		this.bankId = bankId;
	}

	public Integer getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(Integer cardNumber) {
		this.cardNumber = cardNumber;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getCardHolderName() {
		return cardHolderName;
	}

	public void setCardHolderName(String cardHolderName) {
		this.cardHolderName = cardHolderName;
	}

	public LocalDate getExpiryDt() {
		return expiryDt;
	}

	public void setExpiryDt(LocalDate expiryDt) {
		this.expiryDt = expiryDt;
	}

	public Integer getCvv() {
		return cvv;
	}

	public void setCvv(Integer cvv) {
		this.cvv = cvv;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
	
		return bankId + " " + cardNumber + " " + cardHolderName + " " + expiryDt + " " + cvv + " " + amount;
	}

	public BankAccount() {
		super();
	}
	

}
