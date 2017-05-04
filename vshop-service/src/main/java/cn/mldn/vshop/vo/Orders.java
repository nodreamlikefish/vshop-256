package cn.mldn.vshop.vo;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class Orders implements Serializable {
	private Integer oid ;
	private String mid ;
	private String address ;
	private Date subdate ;
	private Double price ;
	private String note ;
	public Integer getOid() {
		return oid;
	}
	public void setOid(Integer oid) {
		this.oid = oid;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getSubdate() {
		return subdate;
	}
	public void setSubdate(Date subdate) {
		this.subdate = subdate;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	
}
