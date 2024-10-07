package view.gamePanel;

import java.awt.*;
import javax.swing.*;

import model.ModelManager;
import view.gamePanel.bottombar.BottomBarGamePanel;

/**
 * in questo panel si svolge la partita
 */
public class GamePanel extends JPanel
{
	private Image background;
	private TopBarGamePanel topbar;
	private BodyPanel bodyPanel;
	private BottomBarGamePanel bottombar;
	
	public GamePanel()
	{	
		setLayout(new BorderLayout());
		background = new ImageIcon("src/resources/images/game_background.jpg").getImage();
		
		topbar = new TopBarGamePanel(); 
		topbar.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		add(topbar, BorderLayout.NORTH);
		
		bodyPanel = new BodyPanel(); 
		bodyPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		add(bodyPanel, BorderLayout.CENTER);
		
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
	
	public void setActionsPanelVisible(boolean visible) 
    {
    	bottombar.setActionsPanelVisible(visible);
    }
	
	public void setPreBetPanelVisible(boolean visible) 
    {
		bottombar.setPreBetPanelVisible(visible);
    }
	
	public void setPostBetPanelVisible(boolean visible) 
    {
		bottombar.setPostBetPanelVisible(visible);
    }
	
	public void setSplitVisible(boolean visible) 
    {
		bottombar.setSplitVisible(visible);
    }
	
	public void updateGamePanel() 
	{	
		ModelManager model = ModelManager.getInstance();
		
		//se sto distribuendo le carte nessun pulsante è visibile 
		if(model.getDistribuzionePartita() || model.getTurnoPartita() != 0) setActionsPanelVisible(false);
		else setActionsPanelVisible(true);
		
		//se la partita è in fase di post-bet vedo le azioni, se no vedo il pulsante bet 
		if(model.isPartitaPostBet()) 
		{
			setPreBetPanelVisible(false); 
			setPostBetPanelVisible(true); 			
		}
		else 
		{
			setPreBetPanelVisible(true); 
			setPostBetPanelVisible(false); 
		}
		
		//se l'utente può splittare ed è il suo turno vedo il pulsante split
		if(model.getGiocatoriPartita().size() != 0 && model.getTurnoPartita() == 0 && model.getGiocatoreCorrente().canSplit()) setSplitVisible(true); 
		else setSplitVisible(false); 
		
		topbar.aggiornaDatiUtente();
		bodyPanel.updateDealer();
		bottombar.updateGiocatori();
    }
}