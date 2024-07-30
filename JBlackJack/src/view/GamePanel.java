package view;

import java.awt.*;
import javax.swing.*;


import controller.CreateUserActionListener;

/**
 * in questo panel si svolge la partita
 */
public class GamePanel extends JPanel
{
private JButton buttonCreateUser;
	
	public GamePanel()
	{
		//setBackground(Color.WHITE);
		setLayout(new BorderLayout());
		
		buttonCreateUser = new JButton("+2");	
		//buttonCreateUser.addActionListener(new CreateUserActionListener());
		add(buttonCreateUser,BorderLayout.NORTH);
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
