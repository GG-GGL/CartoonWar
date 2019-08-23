package com.entity;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.geom.Ellipse2D;

import org.omg.PortableServer.THREAD_POLICY_ID;

import com.action.ActionAble;
import com.client.GameClient;
import com.util.GetImageUtil;

public class Boom extends Gameobj implements ActionAble {
	private static Image[] imgs = {
			GetImageUtil.getImg("com/img/Boom/e1.png"),
			GetImageUtil.getImg("com/img/Boom/e2.png"),
			GetImageUtil.getImg("com/img/Boom/e3.png"),
			GetImageUtil.getImg("com/img/Boom/e4.png"),
			GetImageUtil.getImg("com/img/Boom/e5.png"),
			GetImageUtil.getImg("com/img/Boom/e6.png"),
			GetImageUtil.getImg("com/img/Boom/e7.png"),
			GetImageUtil.getImg("com/img/Boom/e8.png"),
			GetImageUtil.getImg("com/img/Boom/e9.png")
	};
    private boolean isLive;
    private GameClient gc;
    
    public boolean isLive() {
		return isLive;
	}
    public void setLive(boolean isLive) {
		this.isLive = isLive;
	}
	public Boom() {
		// TODO Auto-generated constructor stub
	}
    public Boom(int x,int y,GameClient gc) {
    	this.x = x;
    	this.y = y;
    	this.isLive = true;
    	this.gc = gc;
    }
	@Override
	public void move() {
		// TODO Auto-generated method stub
		
	}

	int count = 0;
	public void draw(Graphics g) {
		if(isLive) {
			if(count>8) {	
			this.isLive = false;
			gc.booms.remove(this);
			return;
			}
			g.drawImage(imgs[count++], x, y, null);
			}	
	}

	
}
