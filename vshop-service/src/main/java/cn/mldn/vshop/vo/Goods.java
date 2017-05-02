package cn.mldn.vshop.vo;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class Goods implements Serializable {
	private Integer gid ;
	private Integer iid ;
	private Integer sid ;
	private String title ;
	private String mid ;
	private Double price ;
	private Date pubdate ;
	private String note ;
	private Integer delflag ;
	private String photo ;
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTitle() {
		return title;
	}
	public Integer getGid() {
		return gid;
	}
	public void setGid(Integer gid) {
		this.gid = gid;
	}
	public Integer getIid() {
		return iid;
	}
	public void setIid(Integer iid) {
		this.iid = iid;
	}
	public Integer getSid() {
		return sid;
	}
	public void setSid(Integer sid) {
		this.sid = sid;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Date getPubdate() {
		return pubdate;
	}
	public void setPubdate(Date pubdate) {
		this.pubdate = pubdate;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public Integer getDelflag() {
		return delflag;
	}
	public void setDelflag(Integer delflag) {
		this.delflag = delflag;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
}
