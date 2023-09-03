package com.methodChaining;

public class Hobbies 
{
	private String singing;
	private String playing;
	private String trekking;
	public String getSinging() {
		return singing;
	}
	
	public Hobbies isSinging(String singing) {
		this.singing = singing;
		return this;
	}
	public String getPlaying() {
		return playing;
	}
	public Hobbies isPlaying(String playing) {
		this.playing = playing;
		return this;
	}
	public String getTrekking() {
		return trekking;
	}
	public Hobbies isTrekking(String trekking) {
		this.trekking = trekking;
		return this;
	}
	
	public Hobbies andThen() {
		return this;
	}
	
}
