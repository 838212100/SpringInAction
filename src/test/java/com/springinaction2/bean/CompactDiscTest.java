package com.springinaction2.bean;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = CDPlayerConfig.class)
public class CompactDiscTest{
	
	@Autowired
	private CompactDisc cd;

	@Test
	public void testPlay() {
		assertNotNull(cd);
		cd.play();
	}

}
