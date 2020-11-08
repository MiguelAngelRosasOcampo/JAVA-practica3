
public class Leon extends Zoo
{
	public Leon()
	{
		super("Leon");
	}
	
	@Override
	public void getSonido()
	{
		System.out.println("\nLeón: ¡ GRRR !");
	}
	
	@Override
	public void isComer()
	{
		System.out.println("\nEl León está comiendo carne.");
	}
	
	public void acostado()
	{
		System.out.println("\nEl león está acostado.");
	}
	

}
