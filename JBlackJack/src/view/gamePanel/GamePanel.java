package view.gamePanel;

import java.awt.*;
import javax.swing.*;

import controller.actionListeners.CreateUserActionListener;
import controller.actionListeners.SetUserActionListener;
import model.ModelManager;

/**
 * in questo panel si svolge la partita
 */
public class GamePanel extends JPanel
{
	private JButton buttonCreateUser;
	private JComboBox<String> comboBoxUtenti;
	
	public GamePanel()
	{
		setLayout(new BorderLayout());	
		buttonCreateUser = new JButton("+2");	
		add(buttonCreateUser,BorderLayout.NORTH);
		
		//select utenti
		ModelManager model = ModelManager.getInstance();
        comboBoxUtenti = new JComboBox<>(model.getUtenti("src/resources/data/utenti.txt"));
        add(comboBoxUtenti);
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2=(Graphics2D)g;
				
		this.setForeground(Color.BLACK);
		g2.drawString("ciao2", 50, 50);
	}
}