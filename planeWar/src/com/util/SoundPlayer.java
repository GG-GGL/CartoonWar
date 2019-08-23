package com.util;
/**
* @ClassName: 播放背景音乐
* @Description: TODO(这里用一句话描述这个类的作用)
* @author guanguoliang
* @date 2019年8月20日 下午6:56:24
*
*/
import java.io.InputStream;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;


public class SoundPlayer extends Thread {
	private String mp3Name;
    public SoundPlayer() {
    	
    }
    public SoundPlayer(String mp3Name) {
    	this.mp3Name = mp3Name;
    }
    public void run() {
    	for(;;) {
    		InputStream resourceAsStream = SoundPlayer.class.getClassLoader().getResourceAsStream(mp3Name);
    	try {
			AdvancedPlayer advancedPlayer = new AdvancedPlayer(resourceAsStream);
			advancedPlayer.play();
		} catch (JavaLayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}}
    	
    }
}
