package funix.huutt.prj.dao;

import java.util.List;

import funix.huutt.prj.entity.Test;

public interface TestDAO {

	List<Test> displayTests();

	void save(Test value);

}
