package view;

import java.util.Observer;
import java.util.Observable;
import java.awt.*;
import javax.swing.*;

import view.gamePanel.GamePanel;
import view.menuPanel.MenuPanel;

/**
 * classe che rappresenta la view, implementata tramite un JFrame
 */
@SuppressWarnings("deprecation")
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
		setSize(950, 650);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		
		cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        
        menuPanel = new MenuPanel();
        gamePanel = new GamePanel();
        
        cardPanel.add(menuPanel, "Menu");
        cardPanel.add(gamePanel, "Game");
        
        add(cardPanel, BorderLayout.CENTER);
        
        AudioManager.getInstance().play("src/resources/audio/background.wav", true);
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
		System.out.println("view: update ricevuto");
		menuPanel.updateMenuPanel();	
		gamePanel.updateGamePanel();
	}

	
	/**
	 * metodo per mostrare il panel del menu
	 */
	public void showMenuPanel() 
	{
        cardLayout.show(cardPanel, "Menu"); 
    }

	/**
	 * metodo per mostrare il panel della partita
	 */
    public void showGamePanel() 
    {
        cardLayout.show(cardPanel, "Game");
    }
    
    /**
     * metodo statico di utilità della view per mostrare un popup nel quale inserire l'username dell'utente che si vuole creare
     * @param primo booleano che mi informa se sto creando il primo utente 
     * @return l'username inserito nel prompt
     */
    public static String showUsernameInput(boolean primo) 
    {
    	if(primo) 
    	{
    		return JOptionPane.showInputDialog(null, "Per continuare, inserisci un username", "Creazione primo utente", JOptionPane.PLAIN_MESSAGE);
    	}
    	else
    	{
    		return JOptionPane.showInputDialog(null, "Inserire un username", "Creazione utente", JOptionPane.PLAIN_MESSAGE);
    	}
        
    }
    
    /**
     * metodo statico di utilità della view per mostrare un popup con errore
     * @param message messaggio di errore
     */
    public static void showError(String message) 
    {
        JOptionPane.showMessageDialog(null, message, "Errore", JOptionPane.ERROR_MESSAGE);
    }
    
}
