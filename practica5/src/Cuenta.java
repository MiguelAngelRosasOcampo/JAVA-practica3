
public class Cuenta
{
	private String nombre; //Variables de instancia.
	private double saldo;
	private short pin;
	
	public Cuenta(String nombre, double saldo, short pin)
	{
		this.nombre = nombre;
		this.saldo = saldo;
		this.pin = pin;
	}
	
	public void setNombre(String nombre)
	{
		this.nombre = nombre;
	}
	
	public void setSaldo(double saldo)
	{
		this.saldo = saldo;
	}
	
	public void setPin(short pin)
	{
		this.pin = pin;
	}
	
	public String getNombre()
	{
		return nombre;
	}
	
	public double getSaldo()
	{
		return saldo;
	}
	
	public short getPin()
	{
		return pin;
	}
	
	public void setDeposito(double deposito)
	{
		if(deposito > 0)
			saldo += deposito;
		
		else System.out.print("\n\n\tEsa cantidad no es válida.");
	}
	
	public void setRetiro(double retiro)
	{
		if(retiro <= saldo)
			saldo -= retiro;
		
		else System.out.print("\n\n\tEsa cantidad no es válida.");
	}
}
