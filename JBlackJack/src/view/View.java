package view;
import java.util.Observer;
import java.awt.BorderLayout;
import java.awt.Panel;
import java.awt.Rectangle;
import java.util.Observable;
import javax.swing.JFrame;

public class View extends JFrame implements Observer 
{
	public static final String TITLE = "JBlackJack";
	public Panel panel;
	public View()
	{
		super(TITLE);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(new Rectangle(50, 50, 850, 650));
		panel = new Panel();
		add(panel, BorderLayout.CENTER);
	}
	
	@Override
	public void update(Observable o, Object arg) 
	{
		System.out.println("update ricevuto");
		panel.repaint();
	}
}
