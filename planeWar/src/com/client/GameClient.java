package com.client;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.util.List;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import com.constant.Constant;
import com.entity.BackGround;
import com.entity.Boom;
import com.entity.Boos;
import com.entity.Bullet;
import com.entity.EnemyMouse;
import com.entity.Mouse;
import com.entity.Prop;
import com.util.GetImageUtil;
import com.util.SoundPlayer;

public class GameClient extends Frame{
	//创建一个mouse出来     
	Mouse mouse = new Mouse(100,200,"com/img/myPlane_01.png",this,true);
	//创建一个子弹出来
//	Bullet bullet = new Bullet(500, 200, "com/img/bullet/bullet.png");
	//创建一个子弹集合
	public List<Bullet> bullets = new ArrayList();
	//创建一个我方的集合
	public List<Mouse> mouses = new ArrayList<>();
	//创建一个敌军一号
	EnemyMouse enemy1 = new EnemyMouse(850,300,1,this,false);
	//创建敌军二号
	EnemyMouse enemy2 = new EnemyMouse(850,450,1,this,false);
	//创建敌军三号
	EnemyMouse enemy3 = new EnemyMouse(650,200,1,this,false);
	//创建敌军四号
	EnemyMouse enemy4 = new EnemyMouse(650,650,1,this,false);
	//创建一个敌军集合
	public List<Mouse> enemys = new ArrayList<>();
	//创建一个爆炸的集合
	public List<Boom> booms = new ArrayList<>();
	//创建背景图片
	BackGround background = new BackGround(0,0,"com/img/bg01.png");
	//创建一个工具集合
	public List<Prop> props = new ArrayList<>();
	//创建一个boos集合
	public List<Mouse> booss = new ArrayList<>(); 
	//解决图片闪烁的方法
	public void update(Graphics g) {
		Image backImg = createImage(Constant.GAMEWIDTH, Constant.GAMEHEIGHT);
		Graphics backg = backImg.getGraphics();
		Color color = backg.getColor();
		backg.setColor(Color.WHITE);
		backg.fillRect(0, 0,Constant.GAMEHEIGHT,Constant.GAMEHEIGHT);
		backg.setColor(color);
		paint(backg);
		g.drawImage(backImg,0,0,null);
	}
    //生成窗口的方法
	public void lanuchFrame() {
		SoundPlayer soundPlayer = new SoundPlayer("com/sound/FEVER_BGM.mp3");
		soundPlayer.start();
		this.setSize(Constant.GAMEWIDTH,Constant.GAMEHEIGHT);
		this.setTitle("飞机大作战");
		this.setVisible(true);
		//不能最大化
		this.setResizable(false);
		//界面居中
		this.setLocationRelativeTo(null);
		Image img = GetImageUtil.getImg("com/img/icon/icon.jpg");
		this.setIconImage(img);
		//关闭窗口
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		//添加鼠标监听
		this.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				System.out.println("nindeda");
			}
		});
		//添加键盘监听
		this.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				mouse.keyPressed(e);
			}
			public void keyReleased(KeyEvent e) {
				mouse.keyReleased(e);
			}
		});	
		new Thread1().start();
		mouses.add(mouse);
		//敌人集合中加入
		enemys.add(enemy1);
		enemys.add(enemy2);
		enemys.add(enemy3);
		enemys.add(enemy4);
		booss.add(new Boos(1600,400,this,false));
		
	}
	//重写print()
	public void paint(Graphics g) {
		background.draw(g);
		g.drawLine(1200, 0, 1200, 800);
		for(int i = 0;i<mouses.size();i++) {
			mouses.get(i).draw(g);
			mouses.get(i).containItem(props);
		}
		//循环画子弹
        for(int i = 0;i<bullets.size();i++) {
        	Bullet bullet = bullets.get(i);
			bullet.draw(g);
			bullet.hitMonsters(enemys);
			bullet.hitMonsters(mouses);
			bullet.hitMonsters(booss);
        }
        g.drawString("当前子弹的数量："+bullets.size(), 10, 40);
        g.drawString("当前敌机的数量："+enemys.size(),10,70);
        g.drawString("当前爆炸的数量"+booms.size(), 10, 100);
        g.drawString("当前我方的数量"+mouses.size(), 10, 130);
        g.drawString("当前道具的数量"+props.size(), 10, 160);
        //循环画敌人
        for(int i = 0;i<enemys.size();i++) {
        	enemys.get(i).draw(g);
        }
        //循环画爆炸
        for(int i = 0;i<booms.size();i++) {
        	if(booms.get(i).isLive()== true) {
        	booms.get(i).draw(g);
        	}
        }
        //循环画道具
        for(int i = 0;i<props.size();i++) {
            props.get(i).draw(g);	
        } 
        //循环画boos
        if(enemys.size()==0){ 
            for(int i = 0;i<booss.size();i++) {
            	booss.get(i).draw(g);
            }	
        }
	}
	//循环调用paint（）方法
	class Thread1 extends Thread{
		public void run() {
			while (true) {
				repaint();
				try {
					Thread.sleep(40);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}