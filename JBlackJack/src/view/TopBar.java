package view;

import javax.swing.*;

import controller.CreateUserActionListener;

import java.awt.*;

public class TopBar extends JPanel 
{
	private JButton buttonCreateUser;
	private JComboBox<String> comboBoxOptions;
	
	public TopBar() 
	{
		setLayout(new FlowLayout(FlowLayout.RIGHT, 15,15));
		setBackground(new Color(0, 0, 0, 0));
		
		//bottone crea utente
		buttonCreateUser = new JButton("Crea nuovo utente");
		//posso usare una lambda, ActionListener è un interfaccia funzionale
		buttonCreateUser.addActionListener(e -> {
			String username = JOptionPane.showInputDialog(null, "Inserire un username", "Creazione utente", JOptionPane.PLAIN_MESSAGE);

			//se clicco su OK
			if (username != null) {
				//controlla la validita dell'input (se il campo non è vuoto fai l'actionPerformed del CreateUserActionListener, altrimenti manda un errore)
				if (!username.isEmpty()) {
	                new CreateUserActionListener(username.trim()).actionPerformed(e);
	            } else {	
	                JOptionPane.showMessageDialog(null, "l'username non può essere vuoto!", "Errore", JOptionPane.ERROR_MESSAGE);
	            }
	        } 
		});
		add(buttonCreateUser);
		
		//select
        String[] options = {"Opzione 1", "Opzione 2", "Opzione 3"};
        comboBoxOptions = new JComboBox<>(options);
        add(comboBoxOptions);
	}
}
