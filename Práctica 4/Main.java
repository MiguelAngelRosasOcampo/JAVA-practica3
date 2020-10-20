import java.util.Scanner;

public class Main 
{
	public static void main(String[] args)
	{
		byte personaje = 0, opcion = 4;
		Scanner entrada = new Scanner(System.in);
		String saltos = "\n\n\n\n\n\n\n\n\n\n\n\n\n"; //Variable para almacenar los saltos de línea
		Pajaro[] p = new Pajaro[3]; //Se reserva en memoria el espacio para 3 referencias tipo "Pajaro"
		
		p[0] = new Red(); //Se crean los objetos.
		p[1] = new Bomb();
		p[2] = new Blue();
		
		do //Bucle para que usuario ingrese la opción que desea ver.
		{
			if(opcion == 4) //Cuando opcion es igual a 4, se cambia de personaje.
				personaje = getEscogerPersonaje(p, entrada);
			 
			else //De lo contrario, hace una pausa (esto para dejar ver el mensaje de la opción escogida).
			{
				entrada.nextLine();
				entrada.nextLine();
			}
			
			System.out.print(saltos + saltos); //Se imprimen saltos.

			p[personaje].getPersonaje(); //Se muestra el personaje.
			
			System.out.print("\n\n\t\t1. Ver habilidad del personaje\n\n\t\t2. Ver saludo del personaje\n\n\t\t3. Ver peso del personaje"
					+ "\n\n\t\t4. Cambiar personaje" + "\n\n\t\t5. Salir");
			
			System.out.print("\n\n\n\t\tIngresa la opción correspondiente: ");
			opcion = entrada.nextByte(); //Se almacena la opción elegida por el usuario.
			
			switch(opcion) //Se selecciona la opción que usuario haya escogido
			{	
				case 1: p[personaje].getHabilidad();	break;
				
				case 2: p[personaje].getSaludo();		break;
				
				case 3:	p[personaje].getPeso();			
			}	
			
		}while(opcion != 5); //El bucle terminará¡ cuando la opcion sea igual a 5.
		
		entrada.close(); //Se cierra objeto tipo Scanner.
	}
	
	public static byte getEscogerPersonaje(Pajaro[] p, Scanner entrada) //Método para escoger el personaje.
	{
		byte personaje; //Se declara variable para almacenar el personaje.
		
		do //Bucle para que usuario escoga su personaje.
		{
			System.out.print("\n\n\t\t\tPersonajes\n\n" );
			
			System.out.println("\t\t\t1. " + p[0].getNombre() + "    🐦");
			System.out.println("\t\t\t2. " + p[1].getNombre() + "   🦆");
			System.out.println("\t\t\t3. " + p[2].getNombre() + "   🦅");
			
			System.out.print("\n\t\tEscoge tu personaje: ");
			personaje = entrada.nextByte();
			
		}while(personaje < 1 || personaje >3);
	
		return --personaje;  //Se le resta 1 debido a la forma del conteo de los arreglos
	}
}
