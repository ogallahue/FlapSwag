package org.jointheleage.owengallahue.flappybird;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class FlappyPanel extends JPanel implements Runnable, ActionListener
{
	public static int FRAME_WIDTH = 2000;
	public static int FRAME_HEIGHT = 720;
	// Color gameColor = Color.CYAN;
	// Color infoColor = Color.RED;
	public int score = 0;
	int time = 60;
	JLabel scoreLabel = new JLabel("Score: " + score);
	private BufferedImage BG;
	private BufferedImage birdFall;
	private BufferedImage birdFlap;
	private BufferedImage pipe;
	Bird flappy;
	Pipe pipes;
	Pipe pipes2;
	Pipe pipes3;
	Timer t;

	public static void main(String[] args)
	{
		SwingUtilities.invokeLater(new FlappyPanel());

	}

	@Override
	public void run()
	{
		try
		{
			initalizeImages();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		JFrame myFrame = new JFrame("Flappy");
		new BoxLayout(myFrame, BoxLayout.Y_AXIS);
		FlowLayout labelLayout = new FlowLayout(FlowLayout.CENTER, 300, 30);
		// labelLayout.setAlignment(FlowLayout.LEFT);

		Font labelFont = new Font("Times New Roman", Font.PLAIN, 24);
		scoreLabel.setFont(labelFont);

		myFrame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		this.setSize(FRAME_WIDTH, FRAME_HEIGHT - 100);
		scoreLabel.setSize(100, 80);

		// this.setBackground(gameColor);

		myFrame.add(this);
		this.add(scoreLabel);

		myFrame.setVisible(true);
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myFrame.setResizable(false);
		flappy = new Bird(birdFall, birdFlap);
		pipes = new Pipe(pipe, 0, this);
		pipes2 = new Pipe(pipe, FRAME_WIDTH + FRAME_WIDTH / 3, this);
		pipes3 = new Pipe(pipe, FRAME_WIDTH + 2 * FRAME_WIDTH / 3, this);
		myFrame.addKeyListener(flappy);
		t = new Timer(40, flappy);
		t.addActionListener(this);
		t.addActionListener(pipes);
		t.addActionListener(pipes2);
		t.addActionListener(pipes3);
		t.start();

	}

	private void initalizeImages() throws IOException
	{
		BG = ImageIO.read(getClass().getResourceAsStream(
				"images/Background.gif"));
		birdFall = ImageIO.read(getClass().getResourceAsStream(
				"images/BirdFalling.gif"));
		birdFlap = ImageIO.read(getClass().getResourceAsStream(
				"images/BirdFlapping.gif"));
		pipe = ImageIO.read(getClass().getResourceAsStream("images/Pipe.gif"));

	}

	private void tileBackground(Graphics2D g2)
	{

		int xpos = 0; // image width
		int w = BG.getWidth();

		while (xpos < FRAME_WIDTH)
		{
			g2.drawImage(BG, null, xpos, 0);
			xpos += 2 * w;
		}

		AffineTransform cached = g2.getTransform();
		g2.scale(-1.0, 1.0);
		xpos = -2 * w;
		while (-xpos < FRAME_WIDTH * 2)
		{
			g2.drawImage(BG, null, xpos, 0);
			xpos -= 2 * w;
		}

		g2.setTransform(cached);

	}

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		bumpTest();
		repaint();

	}

	@Override
	public void paintComponent(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
		tileBackground(g2);

		pipes.drawSelf(g2);
		pipes2.drawSelf(g2);
		pipes3.drawSelf(g2);
		flappy.drawSelf(g2);
	}

	public void bumpTest()
	{

		if (pipes.getShape().intersects(flappy.getShape())
				|| pipes2.getShape().intersects(flappy.getShape())
				|| pipes3.getShape().intersects(flappy.getShape())
				|| flappy.ypos < 0 || flappy.ypos > FRAME_HEIGHT)
		{
			t.stop();
			reset();

		}

	}

	public void scoreCounter()
	{
		score += 1;
		scoreLabel.setText("Score: " + score);

	}

	public void reset()
	{
		// JOptionPane popup =
	}

}
