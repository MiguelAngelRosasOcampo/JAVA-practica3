
public class Elefante extends Zoo 
{
	
	public Elefante()
	{
		super("Elefante");
	}
	
	@Override
	public void getSonido()
	{
		System.out.println("\nElefante: � BRUUU !");
	}
	
	@Override
	public void isComer()
	{
		System.out.println("\nEl Elefante est� comiendo mani.");
	}
	
	public void trompa()
	{
		System.out.println("\nTe hech� agua por su trompa.");
	}
	
}
