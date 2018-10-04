package com.springinaction4_1;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class TrackCounter {
	
	private Map<Integer, Integer> trackCounts = new HashMap<>();
	
	@Pointcut("execution(* com.springinaction4_1.CompactDisc.play(int)) && args(trackNumber)")
	public void trackPlayed(int trackNumber) {}
	
	@Before("trackPlayed(trackNumber)")
	public void countTrack(int trackNumber) {
		int currentCount = getPlayCount(trackNumber);
		trackCounts.put(trackNumber, currentCount + 1);
		System.out.println(trackCounts.toString());
	}

	public int getPlayCount(int trackNumber) {
		return trackCounts.containsKey(trackNumber)?trackCounts.get(trackNumber):0;
	}
	
}
