
public class Serpiente extends Zoo 
{
	public Serpiente()
	{
		super("Serpiente");
	}

	@Override
	public void getSonido() 
	{
		System.out.println("\nSerpiente: SSSSH");
	}

	@Override
	public void isComer() 
	{
		System.out.println("\nLa serpiente no tiene hambre.");
	}
	
	public void hipnotizar()
	{
		System.out.println("\nLa serpiente intenta hipnotizarte.");
	}
	
}
