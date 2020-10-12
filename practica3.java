import javax.swing.JOptionPane;
import java.util.Random;

public class practica3 
{
	public static void main(String[] args)
	{
		Persona[] p = new Persona[3];
	
		for(int i=0; i<p.length; i++)
		{
			p[i] = new Persona(); //crear el objeto.
			JOptionPane.showMessageDialog(null,"Ingrese los datos de la persona " + (i+1)); 
			p[i].setCurpNombreYRCF();
		}
		
		JOptionPane.showMessageDialog(null, p[0].getNombre() + "\nCURP: " + p[0].getCurp() + "\n\n" + p[1].getNombre() + 
			"\nRFC: " + p[1].getRFC() + "\n\n" + p[2].getNombre() + "\nCURP: " + p[2].getCurp() + "\nRFC:    " + p[2].getRFC());
	}
}

class Persona
{
	private String nombre; //Variables de instancia.
	private String curp;
	private String rfc;
	
	public Persona() //Constructor por defecto inicializado.
	{
		nombre = "Sin Nombre";
		curp = "Sin CURP";
		rfc = "Sin RFC";
	}
	
	public void setCurpNombreYRCF() //Método para dar valor a curp, nombre y al rfc.
	{
		int dia=0, mes=0, anio=0, i=0; //Variables locales.
		boolean salir = true;
		char[] ultimosCaracteres = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'B', 'C', 'F', 'G', 'H', 'J',
									'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'V', 'X', 'Y', 'Z'}; //Arreglo para los últimos caracteres.
		
		Random aleatorio = new Random(System.nanoTime()); //Variable para generar el número de la posición de los últimos caracteres del curp y rfc.
		String apellido1 = "X", apellido2 = "X", auxStr = "X"; 
		String[] sexo = {"Mujer", "Hombre"};
		
		String[] meses ={"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", 
						 "Septiembre", "Octubre", "Noviembre", "Diciembre"};
		
		String[] entidadF = {"Aguascalientes", "Baja California", "Baja California Sur", "Campeche", "Chiapas", "Chihuahua",
			    			 "Coahuila de Zaragoza", "Colima", "Ciudad De México", "Durango", "Guanajuato", "Guerrero", "Hidalgo", "Jalisco", "México",
			                 "Michoacán de Ocampo", "Morelos", "Nayarit", "Nuevo León", "Oaxaca", "Puebla", "Querétaro",	
			                 "Quintana Roo", "San Luis Potosí","Sinaloa", "Sonora", "Tabasco", "Tamaulipas", "Tlaxcala", "Veracruz", "Yucatán", "Zacatecas"};

		apellido1 = getCadena("Ingresa el primer apellido");
		curp = "" + apellido1.charAt(0); //concatenar en curp la primera letra del primer apellido.
		
		while(apellido1.length() != ++i && salir)//Bucle para concatenar la vocal más próxima del primer apellido a la curp.
			switch(apellido1.charAt(i))
			{
				case 'A':	curp += apellido1.charAt(i); salir = false;	break;
				
				case 'E': 	curp += apellido1.charAt(i); salir = false;	break; 
				
				case 'I': 	curp += apellido1.charAt(i); salir = false;	break;
				
				case 'O':	curp += apellido1.charAt(i); salir = false;	break;
				
				case 'U':	curp += apellido1.charAt(i); salir = false;	break;
				
				case ' ': 	curp += "X";	salir = false; //Si encuentra un espacio, que salga.
			}
		
		if(salir) //En caso de no encontrar alguna vocal nunca se asignó una vocal.
			curp += "X"; //Se asigna "X" debido a la ausencia de la primera vocal del apellido.
		
		apellido2 = getCadena("Ingresa el segundo apellido");
		curp += apellido2.charAt(0);
				
		nombre = getCadena("Ingresa el nombre(s)");
		curp += nombre.charAt(0);
		
		curp = getVerificarIniciales(curp); //Método para asegurarse que las 4 primeras inicialen no formen un mal nombre.
						
		do //Bucle para verificar que usuario ingrese un año mayor que 0.
		{
			salir = false; //Se asume que NO habrá error al momento que usuario ingrese el año de nacimiento.
			
			try
			{	
				anio = Integer.parseInt(JOptionPane.showInputDialog("Ingresa el año de nacimiento" )); //Perdirle el año y convertir la cadena a entero.
			
			}catch(Exception fallo) //Si no se pudo convertir a entero de manera correcta, entonces ocurrió una excepción.
			{
				salir = true; //En ese caso, SÍ hubo un error y variable cambia a verdadero.
			}
			
			if(anio < 1582 || salir) //1582 se introduce calendario actual (gregoriano).
				JOptionPane.showMessageDialog(null, "Ingresa un año mayor o igual a 1582", "ERROR", JOptionPane.WARNING_MESSAGE);
		
		}while(anio < 1582 || salir);
		
		auxStr = String.valueOf(anio); //convertir variable "anio" a string para manipularla.
		curp += auxStr.charAt(2); //concatenar los dos últimos dígitos del año en el curp.
		curp += auxStr.charAt(3);

		auxStr = (String)JOptionPane.showInputDialog(null, "Selecciona el mes de nacimiento", "MES", JOptionPane.DEFAULT_OPTION, null, meses, meses[0]);
		i = 0; //Inicializar variable i a 0.
		
		do //Bucle para buscar el número de mes elegido por el usuario.
		{
			if(auxStr.equals(meses[i])) //Verifica si la cadena guardada en la posición "i" del arreglo, sea la misma.
				mes = i; //Almacena la posición "i" del arreglo en la variable "mes".
			
		}while(!auxStr.equals(meses[i++]));
		
		if(i<10) //Para un mes menor a 10, complementarlo con 0 antes del número de mes.
			curp += "0" + i;
		
		else curp += i;
			
		String[] numeroDeDias = new String[getDiasDelMes(mes,anio)]; //Se crear vector de cantidad de días del mes escogido.

		for(i=0; i<numeroDeDias.length; i++) //llenar el arreglo con la cantidad de días.
			numeroDeDias[i] = String.valueOf(i+1); //Convertir la variable i + 1 en string.
		
		dia = Integer.parseInt((String)(JOptionPane.showInputDialog(null, "Selecciona el día de nacimiento", "DÍA", JOptionPane.DEFAULT_OPTION, null, 
																	numeroDeDias, numeroDeDias[0])));
		
		if(dia < 10)
			curp += "0" + dia;
		
		else curp += dia;
	
		rfc = curp; //Copiar el curp en el rfc 

		auxStr = (String)JOptionPane.showInputDialog(null, "Selecciona el sexo", "SEXO", JOptionPane.DEFAULT_OPTION, null, sexo, sexo[0]);
		
		if(auxStr.equals("Mujer")) //Concatenar la inicial del sexo del usuario
			curp += "M";
		
		else curp += "H";
				
		auxStr = (String)JOptionPane.showInputDialog(null, "Selecciona la entidad federativa", "ENTIDADES", JOptionPane.DEFAULT_OPTION, null, 
												   entidadF, entidadF[0]);
		
		curp += getAbreviacionEntidad(auxStr); //Concatena en la variable curp la abreviación de la entidad federativa.
		
		curp += getInicialesConsonantes(apellido1);
		curp += getInicialesConsonantes(apellido2);
		curp += getInicialesConsonantes(nombre);
		
		i = aleatorio.nextInt(ultimosCaracteres.length); //Generar los últimos 3 caracteres del RFC.
		rfc += ultimosCaracteres[i];
		i = aleatorio.nextInt(ultimosCaracteres.length);
		rfc += ultimosCaracteres[i];
		i = aleatorio.nextInt(ultimosCaracteres.length);
		rfc += ultimosCaracteres[i];
		
		i = aleatorio.nextInt(10); //Generar los últimos 2 dígitos del curp
		curp += ultimosCaracteres[i];
		i = aleatorio.nextInt(10);
		curp += ultimosCaracteres[i];
				
		nombre += " " + apellido1 + " " + apellido2; //concatenar en el nombre los apellidos.
	}
	
	public String getNombre()
	{
		return nombre;
	}
	
	public String getCurp()
	{
		return curp;
	}
	
	public String getRFC()
	{
		return rfc;
	}
	
	//Métodos privados para auxiliar los métodos de la clase.
	private String getCadena(String mensaje) //Método que regresa una cadena.
	{
		boolean salir;
		String cadena = "X";
		
		do//Bucle para que usuario ingrese su primer apellido.
		{
			try
			{
				cadena = JOptionPane.showInputDialog(mensaje); //Pedir apellido.
				cadena = cadena.toUpperCase(); //Convertir apellido a mayúsculas.
				cadena.charAt(0); //Verificar que al menos usuario haya ingresado un caracter.
				salir = true;
	
			}catch(Exception a)
			{
				salir = false; //Si no se ingresa ningún apellido, entonces "salir" es verdadero y se repite el bucle.
			}
		
		}while(!salir || cadena.charAt(0) == ' ');
		
		return cadena;
	}
	
	private String getVerificarIniciales(String curp) //Método para evitar palabras inapropiadas en la curp.
	{
		boolean encontrada = false;
		byte i = 0;
		String[] noAceptadas = {"BUEI", "BUEY", "CACA", "CACO", "CAGA", "CAGO", "CAKA", "COGE", "COJA", "COJE", "COJI", 
								"COJO", "CULO", "FETO", "GUEY", "JOTO", "KACA", "KACO", "KAGA", "KAGO", "RUIN", "KOGE", 
								"KOJO", "KAKA", "KULO", "MAME", "MAMO", "MEAR", "MEON", "MION", "MOCO", "MULA", "PEDA", 
								"PEDO", "PENE", "PUTA", "PUTO", "QULO", "RATA"};
		
		while(!encontrada && i != noAceptadas.length)
			if(noAceptadas[i++].equals(curp))
				encontrada = true;
		
		if(encontrada)
			curp = "" + curp.charAt(0) + curp.charAt(1) + curp.charAt(2) + "X";
		
		return curp;
	}
	
	private int getDiasDelMes(int mes, int anio) //Método que regresa la cantidad de días del mes escogido.
	{
		switch(mes)// Selecciona la cantidad de días del mes que el usuario ingresó.
		{
			case 0: mes = 31;	break; //Enero tiene 31 días.
			
			case 1: 
					if( anio % 4 == 0 ) //En caso de ser año bisiesto, serán 29 días los que tendrá febrero.
						mes = 29;
					
					else mes = 28; //De lo contario sólo los 28 días que tiene febrero.
					
					break;
			
			case 2: mes = 31;	break; //Marzo
			
			case 3: mes = 30;	break; //Abril
			
			case 4: mes = 31;	break; //Mayo

			case 5: mes = 30;	break; //Junio
			
			case 6: mes = 31;	break; //Julio
			
			case 7: mes = 31;	break; //Agosto
			
			case 8: mes = 30;	break; //Septiembre
			
			case 9: mes = 31;	break; //Octumbre
			
			case 10: mes = 30;	break; //Noviembre

			case 11: mes = 31;	break; //Diciembre
		}
		
		return mes; //regresa la cantidad de días del mes escogido.
	}
	
	private String getAbreviacionEntidad(String entidad) //Método que regresa la abreviación de la entidad federativa.
	{	
		switch(entidad)
		{
			case "Aguascalientes":			entidad = "AS"; break;
			case "Baja California":			entidad = "BC";	break;			
			case "Baja California Sur": 	entidad = "BS";	break;
			case "Campeche":				entidad = "CC"; break;
			case "Chiapas":					entidad = "CS"; break;
			case "Chihuahua":				entidad = "CH";	break;
			case "Coahuila de Zaragoza":	entidad = "CL"; break; 
			case "Colima":					entidad = "CM"; break;
			case "Ciudad De México":		entidad = "DF"; break; 
			case "Durango":					entidad = "DG"; break;
			case "Guanajuato":				entidad = "GT";	break; 
			case "Guerrero":				entidad = "GR"; break;
			case "Hidalgo":					entidad = "HG"; break; 
			case "Jalisco":					entidad = "JC"; break;
			case "México":					entidad = "MC"; break;
			case "Michoacán de Ocampo":		entidad = "MN";	break; 
			case "Morelos":					entidad = "MS"; break;
			case "Nayarit":					entidad = "NT"; break;
			case "Nuevo León":				entidad = "NL"; break;
			case "Oaxaca":					entidad = "OC"; break;
			case "Puebla":					entidad = "PL"; break;
			case "Querétaro":				entidad = "QO"; break;
			case "Quintana Roo":			entidad = "QR"; break;
			case "San Luis Potosí":			entidad = "SP"; break;
			case "Sinaloa":					entidad = "SL"; break;
			case "Sonora":					entidad = "SR"; break;
			case "Tabasco":					entidad = "TC"; break;
			case "Tamaulipas":				entidad = "TS"; break;
			case "Tlaxcala":				entidad = "TL"; break;
			case "Veracruz":				entidad = "VZ"; break;
			case "Yucatán": 				entidad = "YN"; break;
			case "Zacatecas":				entidad = "ZS"; break;
		}
		
		return entidad;
	}
	
	
	private char getInicialesConsonantes(String cadena) //Método para determinar la consolante más proxina de la cadena. (sin contar la inicial).
	{
		boolean encontrado = false; 
		short j = 0;
		char[] consonantes = {'B', 'C', 'F', 'G', 'H', 'J', 'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'V', 'X', 'Y', 'Z'};
				
		while(!encontrado && ++j != cadena.length())
			for(int i=0; i<consonantes.length; i++) //ciclo para verificar si el caracter en la posición "j" es igual a uno de los caracteres del arreglo.
				if(cadena.charAt(j) == consonantes[i]) //En caso de encontrar una consonate, la variable "encontrado" cambia a verdadero.
					encontrado = true;
		
		if(encontrado) //Si se encontró consonante, la enviará.
			return cadena.charAt(j);
		
		return 'X'; //De lo contrario enviará una "X".
	}
}
