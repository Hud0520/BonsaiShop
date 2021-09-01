package com.t9.bsshop.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order extends BaseEntity{
	public enum Status{
		PENDING("Dang cho xu ly"),ACCEPTED("Don hang duoc chap nhan"),CANCELED("Don hang bi huy"),
		DENIED("Don hang bi tu choi"),DELIVERING("Don hang dang duoc giao"),DELIVERED("Giao hang thanh cong");
		private final String label;
		private Status(String label){
			this.label=label;
		}
		
		@Override
		public String toString() {
			return label;
		}
	}
//	cusId bigint references account(id),
//	orderDate date,
//	status varchar,
//	receiverName varchar,
//	receiverAddress varchar,
//	receiverTel varchar,
//	extra varchar
	@Column private String status;
	@Column private String receiverName;
	@Column private String receiverAddress;
	@Column private String receiverTel;
	@Column private String extra;
	@Column private Date orderDate;
	@ManyToOne
	@JoinColumn(name = "acc_id")
	private Account account;
	
	@OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
	private List<OrderDetails> orderDetails;

	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getReceiverName() {
		return receiverName;
	}
	
	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}
	
	public String getReceiverAddress() {
		return receiverAddress;
	}
	
	public void setReceiverAddress(String receiverAddress) {
		this.receiverAddress = receiverAddress;
	}
	
	public String getReceiverTel() {
		return receiverTel;
	}
	
	public void setReceiverTel(String receiverTel) {
		this.receiverTel = receiverTel;
	}
	
	public String getExtra() {
		return extra;
	}
	
	public void setExtra(String extra) {
		this.extra = extra;
	}
	
	public Date getOrderDate() {
		return orderDate;
	}
	
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	
	public Account getAccount() {
		return account;
	}
	
	public List<OrderDetails> getOrderDetails() {
		return orderDetails;
	}
	
	public void setOrderDetails(List<OrderDetails> orderDetails) {
		this.orderDetails = orderDetails;
	}
	
	public void setAccount(Account account) {
		this.account = account;
	}
	public long getSum(){
		return orderDetails.stream().reduce(0l,(sum,details)->sum+details.getSum(), Long::sum);
	}

}
