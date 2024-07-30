package view;

import java.awt.*;
import javax.swing.*;

/**
 * panel che contiene il menu dell'applicazione
 */
public class MenuPanel extends JPanel
{
	private Image backgroundImage;
	private TopBar topbar;
	
	public MenuPanel()
	{
		setLayout(new BorderLayout());
		backgroundImage = new ImageIcon("src/resources/images/menu_background3.jpg").getImage();
		
		topbar = new TopBar();
		add(topbar, BorderLayout.NORTH);
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2=(Graphics2D)g;

		g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
	}
}
