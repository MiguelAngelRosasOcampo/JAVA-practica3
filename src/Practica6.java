import java.util.ArrayList;
import java.util.Scanner;

public class Practica6 
{
	public static void main(String[] args)
	{
		byte opcion = 0;
		Scanner entrada = new Scanner(System.in);
		ArrayList <Zoo> animales = new ArrayList<Zoo>();
		
		animales.ensureCapacity(4); //Establecer tamaño inicial de ArrayList de 4.
		
		animales.add(new Elefante()); //Agregar objetos de sus respectivas instancias.
		animales.add(new Mono());
		animales.add(new Leon());
		animales.add(new Serpiente());		
		
		do //Bucle para que usuario escoga qué es lo que quiere hacer.
		{			
			System.out.println("\n\n\n\n\n\nBienvenido al Zoológico de Java");
			
			System.out.println("\n\n1. Área de Elefantes.");
			System.out.println("\n2. Área de Monos.");
			System.out.println("\n3. Área de Leones.");
			System.out.println("\n4. Área de serpientes.");
			System.out.println("\n\n5. Salir.");
			
			
			System.out.print("\n\nIngresa la opción: ");
			
			try //Preever que la entrada del usuario sea válida.
			{
				opcion = entrada.nextByte();

				if(opcion >= 0 && opcion <= 4)
					menuDeAcciones(--opcion, entrada, animales);

			}catch(Exception e) //Capturar la excepción.
			{
				System.out.println("\n\nEsa opción no está disponible.\n\n");
				entrada.nextLine(); //Vaciar el buffer.
			}
			
			
		}while(opcion != 5);
		
		System.out.println("\n\n¡Gracias por visitar el zoológico!");
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
			System.out.println("\n5. Ir a otra área.");
			
			System.out.print("\n\nIngresa la opción: ");
			
			try
			{
				accion = entrada.nextByte();
				System.out.println("\n\n\n\n\n\n\n");

			}catch(Exception e) 
			{
				System.out.println("Esa opción no está disponible.\n\n");
				entrada.nextLine();
			}
			
			if(accion > 0 && accion < 5)
				switch(accion)
				{
					case 1: System.out.println("\nLa edad del " + animales.get(posicion).getEspecie() + " es de " + animales.get(posicion).getEdad() + " años.");
							break;
						
					case 2: animales.get(posicion).isComer();	
							break;
					
					case 3: if( animales.get(posicion) instanceof Mono || animales.get(posicion).getEdad() <= 3 )
								animales.get(posicion).estadoEmocional();
							
							else System.out.println("\n¡Cuidado!, ese " + animales.get(posicion).getEspecie() + " ya tiene " + animales.get(posicion).getEdad()
									+ " años, podría ser peligroso.");
							break;
					
					case 4: if(animales.get(posicion) instanceof Elefante) //Verificar que sean del tipo de referencia correspondiente.
								( (Elefante) animales.get(posicion) ).trompa(); //Cast de su respectivo tipo de referencia para acceder a sus métodos propios.
							
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

