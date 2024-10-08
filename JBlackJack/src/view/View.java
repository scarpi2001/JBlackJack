package view;

import java.util.Observer;
import java.util.Observable;
import java.awt.*;
import javax.swing.*;

import view.gamePanel.GamePanel;
import view.menuPanel.MenuPanel;

/**
 * rappresenta la view, implementata tramite un JFrame, 
 * è composto da due pannelli: menuPanel e gamePanel, 
 * è singleton perchè utilizzo una sola finestra, 
 * implementa observer perchè viene aggiunta agli osservatori del model
 */
@SuppressWarnings("deprecation")
public class View extends JFrame implements Observer 
{
	public static final String TITOLO = "JBlackJack";
	private static View instance;
	
	/**
	 * pannello utilizzato per lo switch dei pannelli del menu e della partita
	 */
	private JPanel cardPanel;
	private CardLayout cardLayout;

	/**
	 * pannello del menu
	 */
	private MenuPanel menuPanel;
	
	/**
	 * pannello della partita
	 */
    private GamePanel gamePanel;
	
    /**
     * imposta il titolo della finestra,
     * l'apertura a schermo intero,
     * utilizza un cardLayout per gestire i pannelli del menu e della partita
     * e fa partire la clip audio di sottofondo
     */
	private View()
	{
		setTitle(TITOLO);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		setExtendedState(JFrame.MAXIMIZED_BOTH);
	    setUndecorated(true);
	    
		setSize(1280, 720);
		setLocationRelativeTo(null);
		
		cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        
        menuPanel = new MenuPanel();
        gamePanel = new GamePanel();
        
        cardPanel.add(menuPanel, "Menu");
        cardPanel.add(gamePanel, "Game");
        
        add(cardPanel);
        
        AudioManager.getInstance().play("src/resources/audio/background.wav", true);
        setVisible(true);        
	}
	
	public static View getInstance()
	{
		if (instance == null) instance = new View();
		return instance;
	}
	
	/**
	 * aggiorna la view quando riceve il comando dal model 
	 */
	@Override
	public void update(Observable o, Object arg) 
	{
		menuPanel.aggiornaMenuPanel();
		
		if(arg != null && (arg.equals("hit") || arg.equals("split"))) AudioManager.getInstance().play("src/resources/audio/carta.wav", false);		
		gamePanel.aggiornaGamePanel();
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
     * @return l'username inserito nel prompt
     */
    public static String showUsernameInput() 
    {
    	return JOptionPane.showInputDialog(null, "Inserire un username", "Creazione utente", JOptionPane.PLAIN_MESSAGE);      
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
