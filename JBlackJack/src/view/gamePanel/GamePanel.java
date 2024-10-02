package view.gamePanel;

import java.awt.*;
import javax.swing.*;

import model.ModelManager;
import model.carte.Carta;
import model.carte.Mano;
import model.giocatore.Giocatore;
import model.giocatore.GiocatoreDealer;
import view.gamePanel.bottombar.BottomBarGamePanel;
import view.gamePanel.topbar.TopBarGamePanel;


/**
 * in questo panel si svolge la partita
 */
public class GamePanel extends JPanel
{
	private Image background;
	private BottomBarGamePanel bottombar;
	private CarteDealerPanel dealerpanel;
	private TopBarGamePanel topbar;
	
	public GamePanel()
	{	
		setLayout(new BorderLayout());
		background = new ImageIcon("src/resources/images/game_background.jpg").getImage();
		
		topbar = new TopBarGamePanel(); 
		topbar.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		add(topbar, BorderLayout.NORTH);
		
		dealerpanel = new CarteDealerPanel(); 
		dealerpanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		add(dealerpanel, BorderLayout.CENTER);
		
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
		g2.drawImage(background, 0, 0, getWidth(), getHeight(), this);	
	}
	
	public void setBetPanelVisible(boolean visible) 
    {
		bottombar.setBetPanelVisible(visible);
    }
	
	public void setActionsPanelVisible(boolean visible) 
    {
		bottombar.setActionsPanelVisible(visible);
    }
	
	public void setSplitVisible(boolean visible) 
    {
		bottombar.setSplitVisible(visible);
    }
	
	public void updateGamePanel() 
	{	
		ModelManager model = ModelManager.getInstance();
		
		topbar.aggiornaDatiUtente();
		dealerpanel.updateCarte();
		bottombar.updateCarte();
		
		//se la partita è in fase di post-bet vedo le azioni, se no vedo il pulsante bet 
		if(model.isPartitaPostBet()) 
		{
			setBetPanelVisible(false); 
			setActionsPanelVisible(true); 			
		}
		else 
		{
			setBetPanelVisible(true); 
			setActionsPanelVisible(false); 
		}
		
		//se l'utente può splittare ed è il suo turno
		if(model.getGiocatoriPartita().size() != 0 && model.getTurnoPartita() == 0 && model.getGiocatoreCorrente().canSplit()) setSplitVisible(true); 
		else setSplitVisible(false); 
		
		
		//DEBUG		
		for(Giocatore giocatore : model.getGiocatoriPartita())
		{
			int numeroMano = 1;
			for (Mano mano : giocatore.getMani()) 
			{
		        for (Carta carta : mano.getCarte()) 
		        {
		            System.out.println(giocatore.getUsername() + ", mano n°:" + numeroMano + ", carta: " + carta.getImmagine() + " " + "soft hand: " + mano.isSoft());     
		        }
		        numeroMano++; 
		        System.out.println(mano.getConteggio());
		        
		        if(!(giocatore instanceof GiocatoreDealer))System.out.println(mano.getStato());		        
		    }
		}
		System.out.println("");
    }
}