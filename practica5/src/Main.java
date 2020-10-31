import java.util.Scanner;

public class Main 
{
	public static void main(String[] args)
	{
		byte opcion, posicion;
		Banco cuenta = new Banco();
		Scanner entrada = new Scanner(System.in);
		
		do //Bucle que muestra el menú principal.
		{
			System.out.println("\n\n\n\n\t\tB A N C O   J A V A");
			System.out.print("\n\n\n\t\t1. Crear cuenta.");
			System.out.print("\n\n\t\t2. Acceder a cuenta.");
			System.out.print("\n\n\t\t3. Salir.");
			
			System.out.print("\n\n\n\t\tIngrese la opción deseada: ");
			opcion = entrada.nextByte();
			
			switch(opcion)
			{
				case 1: cuenta.setCuenta(entrada); 				break;
				
				case 2: posicion = cuenta.iniciarSesion(entrada);
						
						if(posicion != -1)
							cuenta.menuUsuario(posicion, entrada);	break;
							
				case 3: System.out.println("\n\n\tGracias por usar nuestro banco. ");
			}
			
		}while(opcion != 3);
		
		entrada.close();	
	}
}

