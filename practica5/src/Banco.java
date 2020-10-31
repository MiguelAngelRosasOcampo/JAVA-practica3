import java.util.Scanner;

public class Banco 
{
	private String nombre; 
	private double saldo;
	private short pin; 
	private static byte i = 0; 
	private Cuenta[] usuario = new Cuenta[5]; //Reservar espacio en memoria.
	
	public void setCuenta(Scanner entrada) //Método para crear una cuenta.
	{
		if(i == 5) //Verifica que sólo se puedan crear 5 cuentas.
		{
			System.out.print("\n\n\tEstimado usuario, al parecer se ha llegado al límite de cuentas.");
			return;
		}
		
		entrada.nextLine(); //Consumir lo que se encuentra en el buffer de entrada.
		
		System.out.print("\n\n\t\tIngrese su nombre: ");
		nombre = entrada.nextLine();
		
		do //Bucle para que usuario ingrese una cantidad mayor a 0.
		{
			System.out.print("\n\t\tIngrese el monto con el que desea abrir tu cuenta: ");
			saldo = entrada.nextDouble();
		
		}while(saldo <= 0);
		
		do //Bucle para verificar que usuario ingrese un PIN de sólo 4 dígitos positivos.
		{

			System.out.print("\n\t\tIngrese tu PIN de seguridad (4 dígitos): ");
			pin = entrada.nextShort();
		
		}while(pin < 1000 || pin > 9999);
		
					
		usuario[i++] = new Cuenta(nombre, saldo, pin); //Instanciar el objeto de la posición "i".
	}
	
	public byte iniciarSesion(Scanner entrada)
	{
		if(i == 0)
		{
			System.out.println("\n\n\t\tEstimado cliente, no puede acceder a ninguna cuenta porque no se creado ninguna.");
			return -1;
		}
		
		boolean cuentaExiste = false; 
		byte j = -1;
		
		entrada.nextLine();
		System.out.print("\n\n\t\tPor favor, ingrese su nombre: ");
		nombre = entrada.nextLine();
		
		System.out.print("\n\n\t\tPor favor, ingrese su PIN: ");
		pin = entrada.nextShort();
		
		while(!cuentaExiste && ++j != i) //Bucle para verificar que el pin sea el correcto.
		{
			if(pin == usuario[j].getPin())
				cuentaExiste = true;
		}
		
		if( cuentaExiste && nombre.equals(usuario[j].getNombre()) ) //Si el nombre también coincide, que inice sesión.
			return j; //Regresa el valor de j en caso de que sí haya iniciado sesión, donde j es la posición de la cuenta.
		
		else System.out.println("\n\n\t\tEstimado usuario, al parecer algún dato no es válido, inténtelo más tarde.");
		
		return -1; //Regresa -1 si no ha podido iniciar sesión.
	}
	
	public void menuUsuario(byte posicion, Scanner entrada)
	{
		byte opcion; //Variable local.
				
		do
		{
			System.out.print("\n\n\n\n\n\t\tTe damos la bienvenida " + usuario[posicion].getNombre());

			System.out.print("\n\n\t\t1. Hacer un depósito.");
			System.out.print("\n\n\t\t2. Hacer un retiro.");
			System.out.print("\n\n\t\t3. Ver datos de cuenta.");
			System.out.print("\n\n\n\t\t4. Cerrar sesión.");
			
			System.out.print("\n\n\t\tIngresa la opción correspondiente: ");
			opcion = entrada.nextByte();
			
			switch(opcion)
			{
				case 1: System.out.print("\n\t\tIngresa la cantidad a depositar: ");
						saldo = entrada.nextDouble();
						usuario[posicion].setDeposito(saldo);
						break;
						
				case 2: System.out.print("\n\t\tIngresa la cantidad a retirar: ");
						saldo = entrada.nextDouble();
						usuario[posicion].setRetiro(saldo);
						break;
				
				case 3: getCuenta(posicion);
			}
			
		}while(opcion != 4);
		
	}
	
	private void getCuenta(byte posicion) //Método para ver la información de una cuenta.
	{
		System.out.println("\n\n\n\n\t\tNombre: " + usuario[posicion].getNombre());
		System.out.println("\n\t\tSaldo: " + usuario[posicion].getSaldo());
		System.out.println("\n\t\tPIN: " + usuario[posicion].getPin());

	}
}
