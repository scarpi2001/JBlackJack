package view.gamePanel;

import java.awt.*;
import javax.swing.*;

import controller.actionListeners.CreateUserActionListener;
import controller.actionListeners.SetUserActionListener;
import model.ModelManager;

/**
 * in questo panel si svolge la partita
 */
public class GamePanel extends JPanel
{
	private Image background;
	
	public GamePanel()
	{
		background = new ImageIcon("src/resources/images/game_background.jpg").getImage();
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2=(Graphics2D)g;

		//disegna background
		g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
	}
}