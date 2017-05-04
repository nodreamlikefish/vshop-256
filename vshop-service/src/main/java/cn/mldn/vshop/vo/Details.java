package cn.mldn.vshop.vo;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Details implements Serializable {
	private Integer dtid ;
	private Integer oid ;
	private Long gid ;
	private Integer amount ;
	public Integer getDtid() {
		return dtid;
	}
	public void setDtid(Integer dtid) {
		this.dtid = dtid;
	}
	public Integer getOid() {
		return oid;
	}
	public void setOid(Integer oid) {
		this.oid = oid;
	}
	public Long getGid() {
		return gid;
	}
	public void setGid(Long gid) {
		this.gid = gid;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
}
