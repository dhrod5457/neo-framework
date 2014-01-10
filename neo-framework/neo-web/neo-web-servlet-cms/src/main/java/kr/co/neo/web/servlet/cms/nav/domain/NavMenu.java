package kr.co.neo.web.servlet.cms.nav.domain;

import org.hibernate.validator.constraints.NotEmpty;

public class NavMenu {
	private int ID;
	
	@NotEmpty
	private String name;
	
	private String url;
	private String target="_self";
	private int ord;
	private int grp = -1;
	private int parent;

	private String cmd;

	public NavMenu() {
	}

	public NavMenu(int ID) {
		this.ID = ID;
	}

	public NavMenu(String name) {
		this.name = name;
	}

	public NavMenu(int ID, String name) {
		this.ID = ID;
		this.name = name;
	}

	public NavMenu(int ID, int grp, String name) {
		this.ID = ID;
		this.grp = grp;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public int getGrp() {
		return grp;
	}

	public void setGrp(int grp) {
		this.grp = grp;
	}

	public int getParent() {
		return parent;
	}

	public void setParent(int parent) {
		this.parent = parent;
	}

	public String getCmd() {
		return cmd;
	}

	public void setCmd(String cmd) {
		this.cmd = cmd;
	}

	public int getOrd() {
		return ord;
	}

	public void setOrd(int ord) {
		this.ord = ord;
	}

}
