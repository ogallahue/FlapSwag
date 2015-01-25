package org.jointheleage.owengallahue.flappybird;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.swing.Timer;

public class Bird implements ActionListener, KeyListener
{
	int ypos = 100;// Read in later
	boolean isflapping = false;
	long startHop = 0;
	public BufferedImage Falling;
	public BufferedImage Flaping;
	private int height = 720;
	public int xpos = 250;
	public long lastTime = 0;
	int fwidth = FlappyPanel.FRAME_WIDTH;

	public static void main(String[] args)
	{

	}

	public Bird(BufferedImage Fall, BufferedImage Flap)
	{
		this.Falling = Fall;
		this.Flaping = Flap;
	}

	public void drawSelf(Graphics2D g2)
	{

		if (isflapping == true)
		{
			AffineTransform cached = g2.getTransform();

			g2.drawImage(Flaping, null, xpos, ypos);
			g2.setTransform(cached);
		} else
		{
			AffineTransform cached = g2.getTransform();

			g2.drawImage(Falling, null, xpos, ypos);
			g2.setTransform(cached);
		}

	}

	public void crash()
	{

	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		//System.out.println(lastTime + " " + startHop);
		if (startHop - lastTime < 1000
				&& System.currentTimeMillis() < startHop + 300 && isflapping)
		{
			ypos -= 10;
		} else
		{
			isflapping = false;

			// if(lastTime + 500 < System.currentTimeMillis()){
			// ypos+=1;
			// }
			// else{
			// System.out.println("YUPPPPPPP");
			ypos += 8;
			// }
		}
		lastTime = startHop;

	}
	boolean once = false;
	@Override
	public void keyPressed(KeyEvent e)
	{
		long time = System.currentTimeMillis();
		if (e.getKeyCode() == KeyEvent.VK_SPACE && (time - startHop > 250 || !once))
		{
			once = true;
			hop();
		}
	}

	private void hop()
	{
		isflapping = true;
		startHop = System.currentTimeMillis();

	}

	@Override
	public void keyReleased(KeyEvent e)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e)
	{
		// TODO Auto-generated method stub

	}

	public Rectangle getShape()
	{
		return new Rectangle(xpos, ypos, Falling.getWidth(),
				Falling.getHeight());
	}

}
