package cn.mldn.vshop.vo;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Province implements Serializable {
	private Integer pid;
	private String title;
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
