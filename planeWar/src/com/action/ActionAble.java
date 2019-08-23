package com.action;

import java.awt.Graphics;

/**
* @ClassName:定义行为接口
* @Description: TODO(这里用一句话描述这个类的作用)
* @author guanguoliang
* @date 2019年8月19日 下午1:01:03
*
*/
public interface ActionAble {
    //移动
	void move();
	//画图片
	void draw(Graphics g);
}
