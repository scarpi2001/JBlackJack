package view.menuPanel;

import java.awt.*;
import javax.swing.*;

import model.ModelManager;

/**
 * panel che contiene il menu dell'applicazione
 */
public class MenuPanel extends JPanel
{
	private Image background;
	private TopBar topbar;
	private BottomBar bottombar;
	
	public MenuPanel()
	{	
		setLayout(new BorderLayout());
		background = new ImageIcon("src/resources/images/menu_background.jpg").getImage();
		
		topbar = new TopBar();
		topbar.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		bottombar = new BottomBar(); 
		bottombar.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		
		add(topbar, BorderLayout.NORTH);
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
	
	public void updateMenuPanel() 
	{
        bottombar.aggiornaDatiUtente();
        topbar.aggiornaComboBox();
        repaint();
    }
}