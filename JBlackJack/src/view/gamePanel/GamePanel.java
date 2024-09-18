package view.gamePanel;

import java.awt.*;
import javax.swing.*;
import java.util.List;

import model.ModelManager;
import model.carte.Carta;
import model.carte.Mano;
import model.giocatore.Giocatore;
import model.giocatore.GiocatoreDealer;
import view.gamePanel.bottombar.BottomBarGamePanel;
import view.gamePanel.topbar.TopBarGamePanel;
import view.menuPanel.bottombar.BottomBarMenuPanel;
import view.menuPanel.topbar.TopBarMenuPanel;

/**
 * in questo panel si svolge la partita
 */
public class GamePanel extends JPanel
{
	private Image background;
	private BottomBarGamePanel bottombar;
	private TopBarGamePanel topbar;
	
	public GamePanel()
	{	
		setLayout(new BorderLayout());
		background = new ImageIcon("src/resources/images/game_background.jpg").getImage();
		
		topbar = new TopBarGamePanel(); 
		topbar.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		add(topbar, BorderLayout.NORTH);
		
		bottombar = new BottomBarGamePanel(); 
		bottombar.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		add(bottombar, BorderLayout.SOUTH);
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		ModelManager model = ModelManager.getInstance();
		
		super.paintComponent(g);
		Graphics2D g2=(Graphics2D)g;

		//disegna background
		g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
		
		//voglio una lista di elementi "SpazioCarta" 
		//per ogni giocatore aggiungo un oggetto SpazioCarta
		for(int i = 0; i <= model.getNumeroGiocatoriPartita(); i++)
		{
			
		}		
	}
	
	public void setSplitVisible(boolean visible) 
    {
		bottombar.setSplitVisible(visible);
    }
	
	public void updateGamePanel() 
	{	
		ModelManager model = ModelManager.getInstance();
		repaint();
		
		for(Giocatore giocatore : model.getGiocatoriPartita())
		{
			int numeroMano = 1;
			for (Mano mano : giocatore.getMani()) 
			{
		        for (Carta carta : mano.getCarte()) 
		        {
		            System.out.println(giocatore.getUsername() + ", mano n°:" + numeroMano + ", carta: " + carta.toString() + " " + "soft hand: " + mano.isSoft());     
		        }
		        numeroMano++; 
		        System.out.println(mano.getConteggio());
		        
		        if(!(giocatore instanceof GiocatoreDealer))System.out.println(giocatore.getManoCorrente().getStato());		        
		    }
		}
		System.out.println("");
    }
}