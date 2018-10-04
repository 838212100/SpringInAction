package com.springinaction4_1;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class TrackCounterConfig {

	@Bean
	public CompactDisc setPepers() {
		BlankDisc cd = new BlankDisc();
		cd.setTitle("i am title");
		cd.setArtist("i am artist");
		List<String> tracks = new ArrayList<>();
		tracks.add("Sgt . perrer 1");
		tracks.add("Sgt . perrer 2");
		tracks.add("Sgt . perrer 3");
		tracks.add("Sgt . perrer 4");
		tracks.add("Sgt . perrer 5");
		cd.setTracks(tracks);
		return cd;
	}
	
	@Bean
	public TrackCounter trackCounter() {
		return new TrackCounter();
	}
	
	
}
