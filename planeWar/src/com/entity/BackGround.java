package com.entity;

import java.awt.Graphics;

import com.action.ActionAble;
import com.util.GetImageUtil;

public class BackGround extends Gameobj implements ActionAble{
	private int speed;
    public BackGround() {
		// TODO Auto-generated constructor stub
	} 
    public BackGround(int x,int y,String imgName){
    	this.x = x;
    	this.y = y;
    	this.img = GetImageUtil.getImg(imgName);
    	this.speed = 2;
    	
    }
	@Override
	public void move() {
		x -= speed;
		
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(img, x, y, null);
		move();
	}
    
}
