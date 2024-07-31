package view.menuPanel;

import java.awt.*;
import javax.swing.*;

import model.ModelManager;

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
		backgroundImage = new ImageIcon("src/resources/images/menu_background.jpg").getImage();
		
		topbar = new TopBar();
		add(topbar, BorderLayout.NORTH);
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2=(Graphics2D)g;
		
		//disegna background
		g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
		
		//disegna dati utente selezionato
		if(ModelManager.getInstance().getUtenteUsername() != null) {g.drawString(ModelManager.getInstance().getUtenteUsername(), 50, 50);}
	}
}