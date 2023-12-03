package com.doodle.backend;


import com.doodle.backend.services.OptionServiceImp;
import com.doodle.backend.services.SandageServiceImp;
import com.doodle.backend.services.UserServiceImp;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class BackendApplicationTests {

	@Autowired
	OptionServiceImp optionServiceImp;
	@Autowired
	UserServiceImp userServiceImp;
	@Autowired
	SandageServiceImp sandageServiceImp;


	@Test
	void contextLoads() {


	}





}
