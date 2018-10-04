package com.springinaction4_1;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class BlankDisc implements CompactDisc{

	private String title;
	private String artist;
	private List<String> tracks;
	
	@Override
	public void play(int num) {
		
		for (int i = num; i < tracks.size(); i++) {
			System.out.println(tracks.get(i));
		}
		
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public List<String> getTracks() {
		return tracks;
	}

	public void setTracks(List<String> tracks) {
		this.tracks = tracks;
	}

}
