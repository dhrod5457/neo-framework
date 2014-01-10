package kr.co.neo.nativeness.spring.option;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

public class OptionDao {
	@Autowired
	private SqlSessionTemplate session;

	public int insertOption(OptionDomain optionDomain) {
		return session.insert("option.insertOption", optionDomain);
	}

	public int deleteOption(String option_name) {
		return session.delete("option.deleteOptionByName", option_name);
	}

	public int updateOption(OptionDomain optionDomain) {
		return session.update("option.updateOption", optionDomain);
	}

	public int deleteOption(int option_id) {
		return session.delete("option.deleteOptionById", option_id);
	}

	public OptionDomain getOption(String option_name) {
		return session.selectOne("option.getOptionByName", option_name);
	}

	public OptionDomain getOption(int option_id) {
		return session.selectOne("option.getOptionById", option_id);
	}

	public List<OptionDomain> getOptionList() {
		return session.selectList("option.getOptionList");
	}

	public List<OptionDomain> getOptionListByGroup(String groupName) {
		return session.selectList("option.getOptionListByGroup", groupName);
	}
}
