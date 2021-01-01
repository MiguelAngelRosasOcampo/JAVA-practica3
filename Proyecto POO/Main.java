package menu;


public class Main 
{
	public static void main(String[] args)
	{
		GUI ventana = new GUI();
		
		PanelMenu.intermediario = ventana;
		PanelOpciones.intermediario = ventana;			
	}
}

