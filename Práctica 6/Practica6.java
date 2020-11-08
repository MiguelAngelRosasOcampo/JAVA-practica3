import java.util.ArrayList;
import java.util.Scanner;

public class Practica6 
{
	public static void main(String[] args)
	{
		byte opcion = 0;
		Scanner entrada = new Scanner(System.in);
		ArrayList <Zoo> animales = new ArrayList<Zoo>();
		
		animales.ensureCapacity(4); //Establecer tama�o inicial de ArrayList de 4.
		
		animales.add(new Elefante()); //Agregar objetos de sus respectivas instancias.
		animales.add(new Mono());
		animales.add(new Leon());
		animales.add(new Serpiente());		
		
		do //Bucle para que usuario escoga qu� es lo que quiere hacer.
		{			
			System.out.println("\n\n\n\n\n\nBienvenido al Zool�gico de Java");
			
			System.out.println("\n\n1. �rea de Elefantes.");
			System.out.println("\n2. �rea de Monos.");
			System.out.println("\n3. �rea de Leones.");
			System.out.println("\n4. �rea de serpientes.");
			System.out.println("\n\n5. Salir.");
			
			
			System.out.print("\n\nIngresa la opci�n: ");
			
			try //Preever que la entrada del usuario sea v�lida.
			{
				opcion = entrada.nextByte();

				if(opcion >= 0 && opcion <= 4)
					menuDeAcciones(--opcion, entrada, animales);

			}catch(Exception e) //Capturar la excepci�n.
			{
				System.out.println("\n\nEsa opci�n no est� disponible.\n\n");
				entrada.nextLine(); //Vaciar el buffer.
			}
			
			
		}while(opcion != 5);
		
		System.out.println("\n\n�Gracias por visitar el zool�gico!");
		entrada.close();
	}
	
	static void menuDeAcciones(byte posicion, Scanner entrada, ArrayList<Zoo> animales)
	{
		byte accion = 3;
		
		
		System.out.println("\n\n\n\n\n\n\n");

		do
		{
			animales.get(posicion).getSonido();
			
			System.out.println("\n\n\n\nAcciones");

			System.out.println("\n\n1. Ver Datos.");
			System.out.println("\n2. Dar comida.");
			System.out.println("\n3. Acariciar.");
			System.out.println("\n4. Observar");
			System.out.println("\n5. Ir a otra �rea.");
			
			System.out.print("\n\nIngresa la opci�n: ");
			
			try
			{
				accion = entrada.nextByte();
				System.out.println("\n\n\n\n\n\n\n");

			}catch(Exception e) 
			{
				System.out.println("Esa opci�n no est� disponible.\n\n");
				entrada.nextLine();
			}
			
			if(accion > 0 && accion < 5)
				switch(accion)
				{
					case 1: System.out.println("\nLa edad del " + animales.get(posicion).getEspecie() + " es de " + animales.get(posicion).getEdad() + " a�os.");
							break;
						
					case 2: animales.get(posicion).isComer();	
							break;
					
					case 3: if( animales.get(posicion) instanceof Mono || animales.get(posicion).getEdad() <= 3 )
								animales.get(posicion).estadoEmocional();
							
							else System.out.println("\n�Cuidado!, ese " + animales.get(posicion).getEspecie() + " ya tiene " + animales.get(posicion).getEdad()
									+ " a�os, podr�a ser peligroso.");
							break;
					
					case 4: if(animales.get(posicion) instanceof Elefante) //Verificar que sean del tipo de referencia correspondiente.
								( (Elefante) animales.get(posicion) ).trompa(); //Cast de su respectivo tipo de referencia para acceder a sus m�todos propios.
							
							else if(animales.get(posicion) instanceof Mono)
								( (Mono) animales.get(posicion) ).examinar();
					
							else if(animales.get(posicion) instanceof Leon)
									( (Leon) animales.get(posicion) ).acostado();
					
							else if(animales.get(posicion) instanceof Serpiente)
									( (Serpiente) animales.get(posicion) ).hipnotizar();
				}
				
		}while(accion != 5);
	}
}

