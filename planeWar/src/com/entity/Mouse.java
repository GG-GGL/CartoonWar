package com.entity;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.RenderingHints.Key;
import java.awt.event.KeyEvent;
import java.util.List;

import com.action.ActionAble;
import com.client.GameClient;
import com.constant.Constant;
import com.util.GetImageUtil;
import com.util.singlePlay;

public class Mouse extends Gameobj implements ActionAble{
	singlePlay play = new singlePlay();
    private int speed;
    private boolean left,right,up,down;
    private GameClient gc;
    public boolean IsGood;
    //添加飞机子弹等级
    public int Bulletlevel = 1;
    //加血值
    public int blood;
    public boolean isLive;
	public Mouse() {
	   
   }
   public Mouse(int x,int y,String imgName,GameClient gc,boolean IsGood) {
	  this.x = x;
	  this.y = y;
	  this.img = GetImageUtil.getImg(imgName);
	  this.speed = 10;
	  this.gc = gc;
	  this.IsGood = IsGood;
	  this.blood = 100;
	  this.isLive = true;
   }
    //移动方法
    public void move() {
       if(left) {
    	 x -= speed;  
       }
       if(right) {
    	 x += speed;  
       }
       if(up) {
    	 y -= speed;  
       }
       if(down) {
    	 y += speed;  
       }
       outOfBound();
    }
    //画飞机
    public void draw(Graphics g) {
    g.drawImage(img, x, y,null);
    move();
    g.drawString("飞机当前血量为"+blood,gc.mouses.get(0).x+50,gc.mouses.get(0).y);
    }
    //处理边界问题
    public void outOfBound() {
    	if(y<=30) {
    		y = 30;
    	}
    	if(x<=-25) {
    		x = -25;
    	}
    	if(y>=Constant.GAMEHEIGHT-img.getHeight(null)) {
    		y = Constant.GAMEHEIGHT-img.getHeight(null);
    	}
    	if(x>=Constant.GAMEWIDTH-img.getWidth(null)) {
    		x = Constant.GAMEWIDTH-img.getWidth(null);
    	}
    }
    //键盘按下
    public void keyPressed(KeyEvent e) {
    	switch(e.getKeyCode()) {
    	case KeyEvent.VK_A:
    		left = true;
    		break;
    	case KeyEvent.VK_D:
    		right = true;
    		break;
    	case KeyEvent.VK_W:
    		up = true;
    		break;
    	case KeyEvent.VK_S:
    		down = true;
    		break;
    	case KeyEvent.VK_M:
    		if(isLive) {
    		fire();
    		}
    		break;
    	default:
    		break;
    	}
    }
    //键盘释放
    public void keyReleased(KeyEvent e) {
    	switch(e.getKeyCode()) {
    	case KeyEvent.VK_A:
    		left = false;
    		break;
    	case KeyEvent.VK_D:
    		right = false;
    		break;
    	case KeyEvent.VK_W:
    		up = false;
    		break;
    	case KeyEvent.VK_S:
    		down = false;
    		break;
    	default:
    		break;
    	}
    }
    //老鼠发射子弹
    public void fire() {
    	play.play("com/sound/SOUND_BOOSTER_COIN.mp3");
    	Bullet bullet = new Bullet(x+this.img.getWidth(null),y+this.img.getHeight(null)/2,"com/img/bullet/BULLET_CHAR_ANNA_0"+Bulletlevel+".png",gc,true);
    	gc.bullets.add(bullet);
    }
    //获取到敌人的矩形
  	public Rectangle getRec() {
  		return new Rectangle(x,y,this.img.getWidth(null),this.img.getHeight(null));
  	}
  	//检测我方角色碰到道具
  	public void containItem(Prop prop) {
  		if(this.getRec().intersects(prop.getRect())) {
  			//移除道具
  		    gc.props.remove(prop);
  		    //更改子弹等级
  		    if(this.Bulletlevel>7) {
  		        this.Bulletlevel = 7;
  		    }else {
  		    	this.Bulletlevel += 1; 
  		    }
  		}
  	}
  	//检测我方角色碰到多个道具
  	public void containItem(List<Prop> props) {
  		if(props == null) {
  			return;
  		}else {
  			for(int i = 0;i<props.size();i++) {
  				containItem(props.get(i));
  			}
  		}
  	}
}
