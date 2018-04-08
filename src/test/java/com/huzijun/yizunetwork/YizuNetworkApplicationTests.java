package com.huzijun.yizunetwork;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.huzijun.yizunetwork.login.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class YizuNetworkApplicationTests {


	@Autowired
	UserMapper userMapper;

	@Test
	public void contextLoads() {
//		User user = new User();
//		int result = 0;
//		user = userMapper.selectById(0);
////		result = userMapper.deleteById(0);
//		user.setVersion(2);
//		result = userMapper.updateById(user);
//		System.out.println(user + " " + result);
//		Page<User> page = new Page<>(0,3);
//		EntityWrapper<User> wrapper = new EntityWrapper<User>(user);
//		page.setSearchCount(true);
//		List<User> result = userMapper.selectPage(page,wrapper);
//		System.out.println(result.toString());
		userMapper.selectById(0);
	}

}
