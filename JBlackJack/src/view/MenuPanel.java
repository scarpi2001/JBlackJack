package view;

import java.awt.*;
import javax.swing.*;


import controller.CreateUserActionListener;

public class MenuPanel extends JPanel
{
	private JButton buttonCreateUser;
	
	public MenuPanel()
	{
		setLayout(new BorderLayout());
		
		buttonCreateUser = new JButton("Crea nuovo utente");
		//posso usare una lambda, ActionListener è un interfaccia funzionale
		buttonCreateUser.addActionListener(e -> {
			String username = JOptionPane.showInputDialog(null, "Inserire un username", "Creazione utente", JOptionPane.PLAIN_MESSAGE);
			
			//se clicco su OK
			if (username != null) {
				//controlla la validita dell'input (se il campo non è vuoto fai l'actionPerformed del CreateUserActionListener, altrimenti manda un errore)
				if (!username.isEmpty()) {
	                new CreateUserActionListener(username).actionPerformed(e);
	            } else {	
	                JOptionPane.showMessageDialog(null, "l'username non può essere vuoto!", "Errore", JOptionPane.ERROR_MESSAGE);
	            }
	        } 
		});
		add(buttonCreateUser,BorderLayout.NORTH);
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2=(Graphics2D)g;
				
		this.setForeground(Color.BLACK);
		g2.drawString("ciao", 50, 50);
	}
}
