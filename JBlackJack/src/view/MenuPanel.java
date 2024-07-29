package view;

import java.awt.*;
import javax.swing.*;


import controller.CreateUserActionListener;

public class MenuPanel extends JPanel
{
	private JButton buttonCreateUser;
	private JTextField fieldUsername;
	
	public MenuPanel()
	{
		setLayout(new BorderLayout());
		
		fieldUsername = new JTextField();
		add(fieldUsername, BorderLayout.CENTER);
		
		buttonCreateUser = new JButton("+");	
		
		//posso usare una lambda, ActionListener è un interfaccai funzionale
		buttonCreateUser.addActionListener(e -> {
            String username = fieldUsername.getText().trim();
            if (!username.isEmpty()) {
                new CreateUserActionListener(username).actionPerformed(e);
            } else {
                JOptionPane.showMessageDialog(null, "Inserire un username.", "Errore", JOptionPane.ERROR_MESSAGE);
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
