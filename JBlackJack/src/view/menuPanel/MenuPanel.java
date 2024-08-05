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
	private BottomBar bottombar;
	
	public MenuPanel()
	{	
		setLayout(new BorderLayout());
		backgroundImage = new ImageIcon("src/resources/images/menu_background.jpg").getImage();
		
		topbar = new TopBar();
		bottombar = new BottomBar(); 
		
		add(topbar, BorderLayout.NORTH);
		add(bottombar, BorderLayout.SOUTH);
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		ModelManager model = ModelManager.getInstance();
		super.paintComponent(g);
		Graphics2D g2=(Graphics2D)g;
		
		//disegna background
		g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
		
		//disegna dati utente selezionato
		if(ModelManager.getInstance().getUtenteUsername() != null) 
		{			
			g.drawString(model.getUtenteUsername(), 100, 100);
			g.drawString(model.getUtenteChips()+"", 100, 120);
			g.drawString(model.getUtenteManiGiocate()+"", 100, 140);
			g.drawString(model.getUtenteManiVinte()+"", 100, 160);
			g.drawString(model.getUtenteManiPerse()+"", 100, 180);
			g.drawString(model.getUtenteLivello()+"", 100, 200);
		}
		
		//aggiorna topBar
		topbar.refreshComboBox();
	}
}