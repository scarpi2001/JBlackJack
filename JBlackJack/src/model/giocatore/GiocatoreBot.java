package model.giocatore;

public class GiocatoreBot extends Giocatore
{
	@Override
    public boolean gioca() 
	{
		return true;
		/*
        while (getManoCorrente().getConteggio() < 17)
        {
            hit();
            if(isFinito()) return true;
        }
        stay();
        return false;
        */
    }
}
