package model.giocatore;

public class GiocatoreBot extends Giocatore
{
	@Override
    public void gioca() 
	{	
        while (getManoCorrente().getConteggio() < 17)
        {
        	hit(); 
        }
        if(!manoTerminata()) stay(); 
    }
}
