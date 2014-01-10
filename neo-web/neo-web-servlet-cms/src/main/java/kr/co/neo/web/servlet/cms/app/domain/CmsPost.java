package kr.co.neo.web.servlet.cms.app.domain;

import java.util.Date;

import kr.co.neo.web.core.web.tags.PagingDomain;

import org.hibernate.validator.constraints.NotEmpty;

public class CmsPost extends PagingDomain {
	private int ID = -1;
	private int author = -1;

	@NotEmpty
	private String content;

	@NotEmpty
	private String title;

	private String stat = "publish";
	private String type = "page";
	private String pwd = "";

	private Date reg_date;
	private Date modify_date;

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public int getAuthor() {
		return author;
	}

	public void setAuthor(int author) {
		this.author = author;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getStatus() {
		return stat;
	}

	public void setStatus(String status) {
		this.stat = status;
	}

	public String getPassword() {
		return pwd;
	}

	public void setPassword(String password) {
		this.pwd = password;
	}

	public Date getReg_date() {
		return reg_date;
	}

	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}

	public Date getModify_date() {
		if (modify_date == null) {
			return new Date();
		}

		return modify_date;
	}

	public void setModify_date(Date modify_date) {
		this.modify_date = modify_date;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
