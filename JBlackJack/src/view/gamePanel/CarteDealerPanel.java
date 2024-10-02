package view.gamePanel;

import model.giocatore.GiocatoreDealer;
import view.gamePanel.bottombar.cartePanel.CartePanel;
import view.gamePanel.bottombar.cartePanel.ManoPanel;

public class CarteDealerPanel extends CartePanel
{
	@Override
	public void updateCarte()
	{
		removeAll();

		ManoPanel manoPanel = new ManoPanel(GiocatoreDealer.getInstance().getManoCorrente());
	    add(manoPanel); 	   
	    
	    revalidate();
        repaint();
	}
}
