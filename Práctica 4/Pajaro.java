public abstract class Pajaro 
{
	private String nombre;
	private String habilidad;
	private int peso;
	
	public Pajaro(String nombre, String habilidad, int peso)
	{
		this.nombre = nombre;
		this.habilidad = habilidad;
		this.peso = peso;
	}
	
	public String getNombre()
	{
		return nombre;
	}
	
	public void getPeso()
	{
		System.out.print("\n\n\t\tMi peso es de " + peso + " kilos");

	}
	
	public abstract void getHabilidad(); 
	public abstract void getSaludo();
	public abstract void getPersonaje();
}