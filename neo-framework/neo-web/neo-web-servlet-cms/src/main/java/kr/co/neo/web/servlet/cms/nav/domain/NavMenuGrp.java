package kr.co.neo.web.servlet.cms.nav.domain;

import org.hibernate.validator.constraints.NotEmpty;

public class NavMenuGrp {
	private int ID;
	
	@NotEmpty
	private String name;
	
	private String cmd;

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCmd() {
		return cmd;
	}

	public void setCmd(String cmd) {
		this.cmd = cmd;
	}

}
