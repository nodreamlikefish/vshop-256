package cn.mldn.vshop.vo;

import java.io.Serializable;

@SuppressWarnings("serial")
public class City implements Serializable {
	private Integer cid ;
	private Integer pid ;
	private String title ;
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
}
