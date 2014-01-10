package kr.co.neo.nativeness.spring.option;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

@SuppressWarnings("unchecked")
public class OptionService {
	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private OptionDao optionDao;

	public int insertOption(String option_name, Object o) throws Exception {
		String content = objectMapper.writeValueAsString(o);

		OptionDomain optionDomain = new OptionDomain();
		optionDomain.setOption_name(option_name);
		optionDomain.setOption_value(content);
		
		if (isOptionExists(option_name)) {
			return optionDao.updateOption(optionDomain);
		}

		return optionDao.insertOption(optionDomain);
	}
	
	public int insertOption(String option_name, Object o,String option_group) throws Exception {
		String content = objectMapper.writeValueAsString(o);
		
		OptionDomain optionDomain = new OptionDomain();
		optionDomain.setOption_name(option_name);
		optionDomain.setOption_value(content);
		optionDomain.setOption_group(option_group);
		
		if (isOptionExists(option_name)) {
			return optionDao.updateOption(optionDomain);
		}
		
		return optionDao.insertOption(optionDomain); 
	}

	public int updateOption(String option_name, Object o) throws Exception {
		String content = objectMapper.writeValueAsString(o);

		OptionDomain optionDomain = new OptionDomain();
		optionDomain.setOption_name(option_name);
		optionDomain.setOption_value(content);

		if (!isOptionExists(option_name)) {
			return optionDao.insertOption(optionDomain);
		}

		return optionDao.updateOption(optionDomain);
	}

	public int deleteOption(String option_name) {
		return optionDao.deleteOption(option_name);
	}

	public int deleteOption(int option_id) {
		return optionDao.deleteOption(option_id);
	}

	public <T> T getOption(String option_name, Class<?> clazz) throws Exception {
		OptionDomain optionDomain = optionDao.getOption(option_name);

		if(optionDomain == null) return null;
		
		String content = optionDomain.getOption_value();

		return (T) objectMapper.readValue(content, clazz);
	}

	public <T> T getOption(int option_id, Class<?> clazz) throws Exception {
		OptionDomain optionDomain = optionDao.getOption(option_id);

		if(optionDomain == null) return null;
		
		String content = optionDomain.getOption_value();

		return (T) objectMapper.readValue(content, clazz);
	}

	public HashMap<String, Object> getOption(String option_name) throws Exception {
		OptionDomain optionDomain = optionDao.getOption(option_name);

		if(optionDomain == null) return null;
		
		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("option_name", option_name);
		result.putAll(objectMapper.readValue(optionDomain.getOption_value(), HashMap.class));

		return result;
	}

	public boolean isOptionExists(String option_name) {
		return optionDao.getOption(option_name) != null;
	}
	
	public <T> List<T> getOptionListByGroup(String groupName,Class<?> clazz) throws Exception {
		List<T> result = new ArrayList<T>();
		
		List<OptionDomain> optionGroupList = optionDao.getOptionListByGroup(groupName);
		
		for(OptionDomain o : optionGroupList){
			T r = getOption(o.getOption_name(),clazz);
			
			result.add(r);
		}
		
		return result;
	}

	public OptionDao getOptionDao() {
		return optionDao;
	}
}
