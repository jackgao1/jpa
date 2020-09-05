package com.baidu;

import com.baidu.entity.User;
import com.baidu.entity.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JpaApplicationTests {
	@Autowired
	private UserRepository userRepository;

	@Test
	public void test1() {
		List<Map<String, Object>> list = this.userRepository.allTable();
		for (Map<String, Object> stringStringMap : list) {
			System.out.println(stringStringMap.toString());
		}
	}
	@Test
	public void test2() {
		List<String> list=new ArrayList();
		list.add("202009031739151110001");
		list.add("202009031739151320001");
		list.add("202009031739151510001");
		list.add("202009031739151720001");
		list.add("202009031739151930001");
		list.add("202009031739152140001");
		list.add("202009031739152500001");
		list.add("202009031739152720001");
		list.add("202009031739152940001");
		list.add("202009031739153140001");
		list.add("202009031739153360001");
		list.add("202009031739153570001");
		list.add("202009031739154190001");
		list.add("202009031739154610001");
		list.add("202009031739154910001");
		list.add("202009031739155120001");
		list.add("202009031739155520001");
		list.add("202009031739155890001");
		list.add("202009031739156270001");
		list.add("202009031739156640001");
		list.add("202009031739157290001");
		list.add("202009031739157530001");
		list.add("202009031739157740001");
		list.add("202009031739157940001");
		list.add("202009031739158140001");
		list.add("202009031739158360001");
		list.add("202009031739158560001");
		list.add("202009031739158780001");
		list.add("202009031739159150001");
		list.add("202009031739159350001");
		list.add("202009031739159550001");
		list.add("202009031739159770001");
		list.add("202009031739159970001");
		list.add("202009031739160190001");
		List<User> name = userRepository.findByNameMatch("张19", list);
		for (User user : name) {
			System.out.println(user.toString());
		}
	}

}
