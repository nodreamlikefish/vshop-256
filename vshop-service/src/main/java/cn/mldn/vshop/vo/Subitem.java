package cn.mldn.vshop.vo;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Subitem implements Serializable {
	private Integer sid ;
	private Integer iid ;
	private String title ;
	public Integer getSid() {
		return sid;
	}
	public void setSid(Integer sid) {
		this.sid = sid;
	}
	public Integer getIid() {
		return iid;
	}
	public void setIid(Integer iid) {
		this.iid = iid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Override
	public String toString() {
		return "Subitem [sid=" + sid + ", iid=" + iid + ", title=" + title
				+ "]";
	}
	
}
