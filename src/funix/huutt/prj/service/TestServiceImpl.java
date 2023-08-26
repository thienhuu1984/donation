package funix.huutt.prj.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import funix.huutt.prj.dao.TestDAO;
import funix.huutt.prj.entity.Test;

@Service
public class TestServiceImpl implements TestService {
	

	@Autowired
	private TestDAO testDAO;

	@Override
	@Transactional
	public List<Test> displayTests() {

		
		return testDAO.displayTests();
	}

	@Override
	@Transactional
	public void save(Test value) {

		testDAO.save(value);
		
	}

}
