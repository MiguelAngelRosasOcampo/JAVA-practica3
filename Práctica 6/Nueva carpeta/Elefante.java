
public class Elefante extends Zoo 
{
	
	public Elefante()
	{
		super("Elefante");
	}
	
	@Override
	public void getSonido()
	{
		System.out.println("\nElefante: ¡ BRUUU !");
	}
	
	@Override
	public void isComer()
	{
		System.out.println("\nEl Elefante está comiendo mani.");
	}
	
	public void trompa()
	{
		System.out.println("\nTe hechó agua por su trompa.");
	}
	
}
