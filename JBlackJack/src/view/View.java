package view;

import java.util.Observer;
import java.util.Observable;
import java.awt.*;
import javax.swing.*;

import model.ModelManager;
import model.Utente;

/**
 * classe che rappresenta la view, implementata tramite un JFrame
 */
public class View extends JFrame implements Observer 
{
	public static final String TITOLO = "JBlackJack";
	private static View instance;
    private MenuPanel menuPanel;
    private GamePanel gamePanel;
    private JPanel cardPanel;
    private CardLayout cardLayout;
	
	private View()
	{
		setTitle(TITOLO);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(850, 650);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		
		cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        
        menuPanel = new MenuPanel();
        gamePanel = new GamePanel();
        
        cardPanel.add(menuPanel, "Menu");
        cardPanel.add(gamePanel, "Game");
        
        add(cardPanel, BorderLayout.CENTER);
		
		setVisible(true);
	}
	
	public static View getInstance()
	{
		if (instance == null) instance = new View();
		return instance;
	}
	
	@Override
	public void update(Observable o, Object arg) 
	{
		System.out.println("update ricevuto");
		menuPanel.repaint();
		gamePanel.repaint();
	}
	
	public void showMenuPanel() 
	{
        cardLayout.show(cardPanel, "Menu");
    }

    public void showGamePanel() 
    {
        cardLayout.show(cardPanel, "Game");
    }
}
