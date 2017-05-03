package cn.mldn.vshop.vo;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Shopcar implements Serializable {
	private Integer scid ; 
	private String mid ;
	private Integer gid ;
	private Integer amount ;
	public Integer getScid() {
		return scid;
	}
	public void setScid(Integer scid) {
		this.scid = scid;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public Integer getGid() {
		return gid;
	}
	public void setGid(Integer gid) {
		this.gid = gid;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
}
