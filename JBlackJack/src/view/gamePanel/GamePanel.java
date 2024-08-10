package view.gamePanel;

import java.awt.*;
import javax.swing.*;

import view.menuPanel.BottomBar;
import view.menuPanel.TopBar;

/**
 * in questo panel si svolge la partita
 */
public class GamePanel extends JPanel
{
	private Image background;
	private BottomBarGamePanel bottombar;
	
	public GamePanel()
	{
		setLayout(new BorderLayout());
		background = new ImageIcon("src/resources/images/game_background.jpg").getImage();

		bottombar = new BottomBarGamePanel(); 
		bottombar.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		add(bottombar, BorderLayout.SOUTH);
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2=(Graphics2D)g;

		//disegna background
		g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
	}
	
	public void updateGamePanel() 
	{

    }
}