import java.util.Random;

public abstract class Zoo 
{
	private Random aleatorio = new Random(System.nanoTime());
	private String especie;
	private int edad;
	
	public Zoo(String especie)
	{
		this.especie = especie;
		this.edad = generarEdad();
	}
	
	public String getEspecie()
	{
		return especie;
	}

	public int getEdad()
	{
		return edad;
	}

	public void estadoEmocional()
	{
		System.out.println("\nEl " + especie + " se puso feliz.");
	}

	private int generarEdad()
	{
		return edad = aleatorio.nextInt(8) + 2; 
	}
	
	public abstract void getSonido();
	
	public abstract void isComer();
			
	
}
