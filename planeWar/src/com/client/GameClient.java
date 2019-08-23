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
	//����һ��mouse����     
	Mouse mouse = new Mouse(100,200,"com/img/myPlane_01.png",this,true);
	//����һ���ӵ�����
//	Bullet bullet = new Bullet(500, 200, "com/img/bullet/bullet.png");
	//����һ���ӵ�����
	public List<Bullet> bullets = new ArrayList();
	//����һ���ҷ��ļ���
	public List<Mouse> mouses = new ArrayList<>();
	//����һ���о�һ��
	EnemyMouse enemy1 = new EnemyMouse(850,300,1,this,false);
	//�����о�����
	EnemyMouse enemy2 = new EnemyMouse(850,450,1,this,false);
	//�����о�����
	EnemyMouse enemy3 = new EnemyMouse(650,200,1,this,false);
	//�����о��ĺ�
	EnemyMouse enemy4 = new EnemyMouse(650,650,1,this,false);
	//����һ���о�����
	public List<Mouse> enemys = new ArrayList<>();
	//����һ����ը�ļ���
	public List<Boom> booms = new ArrayList<>();
	//��������ͼƬ
	BackGround background = new BackGround(0,0,"com/img/bg01.png");
	//����һ�����߼���
	public List<Prop> props = new ArrayList<>();
	//����һ��boos����
	public List<Mouse> booss = new ArrayList<>(); 
	//���ͼƬ��˸�ķ���
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
    //���ɴ��ڵķ���
	public void lanuchFrame() {
		SoundPlayer soundPlayer = new SoundPlayer("com/sound/FEVER_BGM.mp3");
		soundPlayer.start();
		this.setSize(Constant.GAMEWIDTH,Constant.GAMEHEIGHT);
		this.setTitle("�ɻ�����ս");
		this.setVisible(true);
		//�������
		this.setResizable(false);
		//�������
		this.setLocationRelativeTo(null);
		Image img = GetImageUtil.getImg("com/img/icon/icon.jpg");
		this.setIconImage(img);
		//�رմ���
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		//����������
		this.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				System.out.println("nindeda");
			}
		});
		//���Ӽ��̼���
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
		//���˼����м���
		enemys.add(enemy1);
		enemys.add(enemy2);
		enemys.add(enemy3);
		enemys.add(enemy4);
		booss.add(new Boos(1600,400,this,false));
		
	}
	//��дprint()
	public void paint(Graphics g) {
		background.draw(g);
		g.drawLine(1200, 0, 1200, 800);
		for(int i = 0;i<mouses.size();i++) {
			mouses.get(i).draw(g);
			mouses.get(i).containItem(props);
		}
		//ѭ�����ӵ�
        for(int i = 0;i<bullets.size();i++) {
        	Bullet bullet = bullets.get(i);
			bullet.draw(g);
			bullet.hitMonsters(enemys);
			bullet.hitMonsters(mouses);
			bullet.hitMonsters(booss);
        }
        g.drawString("��ǰ�ӵ���������"+bullets.size(), 10, 40);
        g.drawString("��ǰ�л���������"+enemys.size(),10,70);
        g.drawString("��ǰ��ը������"+booms.size(), 10, 100);
        g.drawString("��ǰ�ҷ�������"+mouses.size(), 10, 130);
        g.drawString("��ǰ���ߵ�����"+props.size(), 10, 160);
        //ѭ��������
        for(int i = 0;i<enemys.size();i++) {
        	enemys.get(i).draw(g);
        }
        //ѭ������ը
        for(int i = 0;i<booms.size();i++) {
        	if(booms.get(i).isLive()== true) {
        	booms.get(i).draw(g);
        	}
        }
        //ѭ��������
        for(int i = 0;i<props.size();i++) {
            props.get(i).draw(g);	
        } 
        //ѭ����boos
        if(enemys.size()==0){ 
            for(int i = 0;i<booss.size();i++) {
            	booss.get(i).draw(g);
            }	
        }
	}
	//ѭ������paint��������
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