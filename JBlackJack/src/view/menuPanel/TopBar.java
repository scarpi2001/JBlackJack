package view.menuPanel;

import javax.swing.*;
import java.awt.*;

import controller.actionListeners.CreateUserActionListener;
import controller.actionListeners.DeleteUserActionListener;
import controller.actionListeners.SetUserActionListener;

import model.ModelManager;


/**
 * panel che rappresenta la topBar del menu
 */
public class TopBar extends JPanel 
{
	private JButton buttonDeleteUser;
	private JButton buttonCreateUser;
	private JComboBox<String> comboBoxUtenti;
	
	public TopBar() 
	{
		ModelManager model = ModelManager.getInstance();
		setLayout(new FlowLayout(FlowLayout.RIGHT, 15,15));
		setBackground(new Color(0, 0, 0, 0));
		
		//bottone crea utente
		buttonCreateUser = new JButton("Crea nuovo utente");
		buttonCreateUser.addActionListener(new CreateUserActionListener());
		add(buttonCreateUser);
		
		//bottone elimina utente
		buttonDeleteUser = new JButton("elimina utente");
		buttonDeleteUser.addActionListener(new DeleteUserActionListener());
		add(buttonDeleteUser);
		
		//select utenti
        comboBoxUtenti = new JComboBox<>(model.getUtenti("src/resources/data/utenti.txt"));
        comboBoxUtenti.addActionListener(new SetUserActionListener(comboBoxUtenti));
        add(comboBoxUtenti);
	}
	
	
	
}