package org.jointheleage.owengallahue.flappybird;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Pipe implements ActionListener
{
	int fwidth = FlappyPanel.FRAME_WIDTH;
	int fheight = FlappyPanel.FRAME_HEIGHT;
	private static final Random RNG = new Random();
	int ypos = 200 + RNG.nextInt(fheight / 2);
	int xpos = fwidth;
	long lastTime = System.currentTimeMillis();
	private FlappyPanel panal;
	int speed = 40;

	BufferedImage pipe;

	Pipe(BufferedImage pipe, int xpos, FlappyPanel panal)
	{
		this.pipe = pipe;
		this.xpos = xpos;
		this.panal = panal;
	}

	public void drawSelf(Graphics2D g2)
	{

		if (xpos >= pipe.getWidth() - 100)
		{

			g2.drawImage(pipe, null, xpos, ypos);

		} else
		{

			ypos = 230 + RNG.nextInt(fheight / 2);
			xpos = fwidth;
			panal.scoreCounter();
		}

	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		long thisTime = System.currentTimeMillis();
		if (thisTime - lastTime > 5000)
		{
			speed += 10;
			lastTime = thisTime;

		}
		xpos -= speed;

	}

	public Rectangle getShape()
	{
		return new Rectangle(xpos, ypos, pipe.getWidth(), pipe.getHeight());

	}
}
