package com.springinaction4_1;


import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=TrackCounterConfig.class)
public class TrackCounterConfigTest {
	
	@Autowired
	private CompactDisc cd;

	@Autowired
	private TrackCounter counter;

	@Test
	public void testTrackCounter() {
		cd.play(2);
		cd.play(2);
		cd.play(3);
		
		assertEquals(1, counter.getPlayCount(1));
		System.out.println("222");
		assertEquals(1, counter.getPlayCount(2));
	}

}
