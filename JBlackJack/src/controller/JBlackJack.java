package controller;
import model.ModelManager;
import view.View;

public class JBlackJack 
{
	public static void main(String[] args) 
	{
		ModelManager model = ModelManager.getInstance();
		View view = View.getInstance();
		view.showMenuPanel();
		model.addObserver(view);
	}
}
