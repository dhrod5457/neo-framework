package kr.co.neo.nativeness.spring.option;

public class OptionDomain {
	private int option_id;
	private String option_name;
	private String option_value;
	private String option_group;

	public int getOption_id() {
		return option_id;
	}

	public void setOption_id(int option_id) {
		this.option_id = option_id;
	}

	public String getOption_name() {
		return option_name;
	}

	public void setOption_name(String option_name) {
		this.option_name = option_name;
	}

	public String getOption_value() {
		return option_value;
	}

	public void setOption_value(String option_value) {
		this.option_value = option_value;
	}

	public String getOption_group() {
		return option_group;
	}

	public void setOption_group(String option_group) {
		this.option_group = option_group;
	}

}
