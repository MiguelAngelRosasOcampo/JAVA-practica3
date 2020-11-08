
public class Mono extends Zoo 
{
	public Mono()
	{
		super("Mono");
	}
	
	@Override
	public void getSonido()
	{
		System.out.println("\nMono: ¡ UH AH AH !");
	}
	
	@Override
	public void isComer()
	{
		System.out.println("\nEl mono está comiendo platanos.");
	}
	
	public void examinar()
	{
		System.out.println("\nEl mono te está examiando.");
	}
	
}
