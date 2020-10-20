package com.baidu;

import com.baidu.entity.repository.MinioRepository;
import com.baidu.service.MinioService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JpaApplicationTests {
	@Autowired
	private MinioRepository minioRepository;
	@Autowired
	private MinioService minioService;

	@Test
	public void test1() {

	}
}