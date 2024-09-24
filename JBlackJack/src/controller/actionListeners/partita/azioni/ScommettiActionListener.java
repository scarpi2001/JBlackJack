package controller.actionListeners.partita.azioni;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

import controller.Controller;
import model.ModelManager;
import view.View;

/**
 * classe che definisce l'evento di "bet" che deve accadere al click di un componente swing
 */
public class ScommettiActionListener implements ActionListener
{
	private JTextField fieldScommessa;
	
	public ScommettiActionListener(JTextField fieldScommessa)
	{
		this.fieldScommessa = fieldScommessa;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		String scommessaString = fieldScommessa.getText();
		
		try 
		{
            //provo a convertire la stringa in intero
            int scommessa = Integer.parseInt(scommessaString);

            if (scommessa > 0) 
            {
                ModelManager.getInstance().getGiocatoreCorrente().scommetti(scommessa);  
                Controller.getInstance().distribuisciCarte();
                Controller.getInstance().gameloop();                    
            } 
            else 
            {
            	fieldScommessa.setText("");
                View.showError("La scommessa non può essere negativa");
            }
        } 
        catch (NumberFormatException ex) 
        {
        	//se la conversione fallisce
        	fieldScommessa.setText("");
            View.showError("La scommessa deve essere un numero");
        }	
	}
}
