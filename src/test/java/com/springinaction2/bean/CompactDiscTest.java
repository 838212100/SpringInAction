package com.springinaction2.bean;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = CDPlayerConfig.class)
public class CompactDiscTest{
	
	public final SystemOutRule log = new SystemOutRule();
	
	@Autowired
	private CompactDisc cd;
	
	@Autowired
	private MdeiaPlayer player;

	@Test
	public void testPlay() {
		assertNotNull(cd);
		//cd.play();
	}
	
	@Test
	public void play() {
		player.play();
		assertEquals("Playing Sgt. Pepper's Lonely Hearts Club Band" +
				"  by The Beatles\n", 
				log.getLog());
	}

}
