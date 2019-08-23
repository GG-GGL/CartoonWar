package com.util;

import java.io.InputStream;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;

public class singlePlay extends Thread{
	private String mp3Name;
    public singlePlay() {

    }
    
    public singlePlay(String mp3Name) {
    	this.mp3Name = mp3Name;
    }
    public void play(String mp3Name) {
    	singlePlay singleplay = new singlePlay(mp3Name);
    	singleplay.start();
    }
    public void run() {
    	InputStream resourceAsStream = SoundPlayer.class.getClassLoader().getResourceAsStream(mp3Name);
    	try {
			AdvancedPlayer advancedPlayer = new AdvancedPlayer(resourceAsStream);
			advancedPlayer.play();
		} catch (JavaLayerException e) {
			e.printStackTrace();
		}
    	}
    	
    
}
