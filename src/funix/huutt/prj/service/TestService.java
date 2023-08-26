package funix.huutt.prj.service;

import java.util.List;



import funix.huutt.prj.entity.Test;


public interface TestService {

	
	public List<Test> displayTests();

	public void save(Test value);
	
	
}
