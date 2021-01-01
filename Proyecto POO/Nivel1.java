package menu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.lang.reflect.GenericArrayType;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.Timer;

public class Nivel1 extends JPanel implements KeyListener, ActionListener, Serializable	//Este será el panel del juego.
{
	private static final long serialVersionUID = 1L;
	static Meteoro[] meteoro;																//Un atributo del panel, son los meteoros
	private JLabel labelFondo = new JLabel(new ImageIcon("src/disenio/nivel1/fondo.png")); //Otro atributo será, la imagen de fondo.
	static Random aleatorio = new Random(System.nanoTime());
	static JLabel indicadorMeteoros = new JLabel();
	static JLabel labelEnergia = new JLabel("Energía: 0");
	static JLabel salud = new JLabel("SALUD: 100%");
	static JLabel labelRonda = new JLabel("RONDA: 1");
	static JLabel labelGameOver = new JLabel ("¡GAME OVER!");
	static JLabel labelPresiona = new JLabel ("Presiona 0 para salir");
	static JLabel labelPuntos = new JLabel("PUNTOS: 0");
	static JLabel labelTeclas = new JLabel("Minúsculas");
	static int porcentajeSalud = 100;
	static boolean habilitarMusica = false;
	static int meteorosDestruidos;
	static int puntos;
	static int numeroDeRonda = 1;
	static volatile int numeroDeMeteorosDestructores;
	static volatile int numeroDeMeteorosDestruidos;
	static int numeroDeMeteoros = 10;
	static Clip musica; 
	boolean pausa = false;
	static int vecesJugadas = 1;
	private String fuente;
	static boolean gameOver = false;
	public Timer verificador; 
	
	
	public Nivel1()
	{
		this.setLayout(new BorderLayout());		//Cambiar el diseño del layout.
		
		indicadorMeteoros.setText("METEORITOS: " + Nivel1.numeroDeMeteorosDestruidos);
		indicadorMeteoros.setBounds(640, 10, 140, 20);
		indicadorMeteoros.setFont(new Font("Arial", 1, 14));
		indicadorMeteoros.setForeground(Color.BLACK);
		labelFondo.add(indicadorMeteoros);
		
		labelEnergia.setBounds(650, 30, 140, 20);
		labelEnergia.setFont(new Font("Arial", 1, 14));
		labelEnergia.setForeground(Color.BLACK);
		labelFondo.add(labelEnergia);
		
		Nivel1.salud.setBounds(650, 50, 140, 20);
		Nivel1.salud.setFont(new Font("Arial", 1, 14));
		Nivel1.salud.setForeground(Color.BLACK);
		labelFondo.add(salud);
		
		Nivel1.labelPuntos.setBounds(650, 70, 140, 20);
		Nivel1.labelPuntos.setFont(new Font("Arial", 1, 14));
		Nivel1.labelPuntos.setForeground(Color.BLACK);
		labelFondo.add(labelPuntos);
		
		Nivel1.labelRonda.setBounds(650, 90, 140, 20);
		Nivel1.labelRonda.setFont(new Font("Arial", 1, 14));
		Nivel1.labelRonda.setForeground(Color.BLACK);
		labelFondo.add(labelRonda);

		Nivel1.labelTeclas.setBounds(650, 110, 140, 20);
		Nivel1.labelTeclas.setFont(new Font("Arial", 1, 14));
		Nivel1.labelTeclas.setForeground(Color.BLACK);
		labelFondo.add(labelTeclas);
		
		labelGameOver.setBounds(190, 90, 600, 200);
		//labelGameOver.setOpaque(true);
		labelGameOver.setForeground(Color.BLACK);
		labelGameOver.setVisible(false);
		labelFondo.add(labelGameOver);
		
		labelPresiona.setBounds(290, 150, 600, 200);
		labelPresiona.setForeground(Color.BLACK);
		labelPresiona.setVisible(false);
		labelFondo.add(labelPresiona);
				
		this.add(labelFondo);					//Agregar la imagen de fondo.
		this.generarRonda(numeroDeMeteoros);
	}

	public void generarRonda(int cantidad)
	{
		meteoro = new Meteoro[cantidad];			//Crea una instancia con x cantidad de meteoros.

		for(int i=0; i<cantidad; i++)
		{
			meteoro[i] = new Meteoro(aleatorio.nextInt(650), ((aleatorio.nextInt(200*(vecesJugadas) + 100) * -1) ) - 50);
			meteoro[i].setSize(50, 90);
			labelFondo.add(meteoro[i]);
		}
	}
	
	public void empezarNivel(String fuente)		//Una vez que empiece el juego, todos los meteoros tendrán el movimiento activado.
	{
		if(numeroDeMeteoros == 10)				//Entra aquí cada ronda.
		{
			this.fuente = fuente;
			verificador = new Timer(1000, this);
			verificador.start();
			labelGameOver.setFont(new Font(fuente, 1, 60));
			labelPresiona.setFont(new Font(fuente, 1, 21));
		}
				
		for(int i=0; i<meteoro.length; i++)
		{
			meteoro[i].setMovimiento(true); //Poner a los meteoros en movimiento.			
			meteoro[i].hilo.start();		//Desencadenar los hilos.			
			meteoro[i].setFuente(fuente);	//Establecer el tipo de fuente.
		}
	}
	
	static void actualizarJuego()
	{
		if(porcentajeSalud <= 0)
		{
			gameOver = true;
			porcentajeSalud = 0;
			labelGameOver.setVisible(true);
			labelPresiona.setVisible(true);
			
			for(int i=0; i<meteoro.length; i++)
				meteoro[i].setMovimiento(false);
		}
		
		salud.setText("SALUD: " + porcentajeSalud + "%");
		indicadorMeteoros.setText("METEORITOS: "+ ( meteorosDestruidos + Nivel1.numeroDeMeteorosDestruidos));
		labelEnergia.setText("Energía: " + meteorosDestruidos);
		labelPuntos.setText("PUNTOS: " + puntos);
		labelRonda.setText("RONDA: " + numeroDeRonda);
	}
		
	public static void empezarMusica()
	{			
		try 
		{
			musica = AudioSystem.getClip();
			musica.open(AudioSystem.getAudioInputStream(new File("src/disenio/musica/musicaNivel1.wav")));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void isMusica()
	{
		if(Nivel1.habilitarMusica && vecesJugadas == 1)
			musica.loop(Clip.LOOP_CONTINUOUSLY);
		
		else if(Nivel1.habilitarMusica && vecesJugadas >= 2)
		{
			musica.setMicrosecondPosition(0);
			musica.loop(Clip.LOOP_CONTINUOUSLY);
		} 
		
		else musica.stop();
	}
	
	synchronized public void teclaPresionada(KeyEvent e)
	{
		boolean teclaEncontrada = true;
		
		if(e.getKeyCode() >= 65 && e.getKeyCode() <= 90  || e.getKeyCode() >= 97 && e.getKeyCode() <= 122)
		{
			for(int i=0; i<meteoro.length - Nivel1.numeroDeMeteorosDestruidos; i++)
			if(e.getKeyChar() == meteoro[i].getLetra() )
			{
				if(meteoro[i].getCoorY() < - 30)
					continue;
				
				meteoro[i].setMovimiento(false);		//"mover" = a falso, sale del while el hilo y se destruye
				meteoro[i].setVisible(true);			//Hacer invisible el meteoro (ya no se moverá).
				meteoro[i].setText(""); 				//Eliminar la letra de la pantalla.
				teclaEncontrada = false;
				
				Meteoro mAux = meteoro[i]; 				//Se resguarda el meteoro que el usuario presionó.
				meteoro[i] = meteoro[meteoro.length - Nivel1.numeroDeMeteorosDestruidos - 1]; //la última posicion válida, se pone en lugar anterior.
				meteoro[meteoro.length - Nivel1.numeroDeMeteorosDestruidos - 1] = mAux; //El meteoro que el usuario presionó, se va a la última posición.
				++Nivel1.numeroDeMeteorosDestruidos;
				break;				
			}
		
		if(teclaEncontrada) //Sólo entra sí no fue encontrada y diferente de shift.
			for(int i=0; i<meteoro.length; i++)
				meteoro[i].setCoorY(meteoro[i].getCoorY() + 20);
		}		
	}
	
	@Override
	public void keyPressed(KeyEvent e) 
	{		
		if(e.getKeyChar() == '1' && meteorosDestruidos >= 50)
		{
			HiloMeteoro.retroceso = true;
			meteorosDestruidos -= 50;
			
			for(int i=0; i<meteoro.length; i++)
				meteoro[i].hilo.setContador(0);
		}
		
		else if(e.getKeyChar() == '2' && meteorosDestruidos >= 50)
		{
			porcentajeSalud += 50;
			meteorosDestruidos -= 50;
		}
		
		else if(e.getKeyChar() == '3' && meteorosDestruidos >= 50)
		{
			HiloMeteoro.pararTiempo = true;
			meteorosDestruidos -= 50;
			
			for(int i=0; i<meteoro.length; i++)
				meteoro[i].hilo.setContador(0);	
		}
			
		if(!gameOver)	//Sólo entra si el juego no se ha acabado.
			teclaPresionada(e);
		
		else if(GUI.ID == 2 && gameOver && e.getKeyCode() == '0')
		{		
			FileOutputStream records;
			LocalDate fecha = LocalDate.now();
			String nombre = JOptionPane.showInputDialog(this, "Escribe tu nombre\n(máximo 12 caracteres).\nEscribe 0 para omitir\n",
					"Record", JOptionPane.DEFAULT_OPTION); //Pedir Nombre

			if(nombre.length() >= 2) //Si no ingresa por lo menos 2 letras, no se graba (para evitar que se almacene cada que juega).
				try 
				{
					String record = "";
					records = new FileOutputStream("src/menu/records.marcianoLand", true);					//Abrir el fichero. 
					String nombreAux = "";
					
					if(nombre.length() >= 12)
						for(int i=0; i<12; i++)
							nombreAux += nombre.charAt(i);
					
					else 
					{
						for(int i=nombre.length(); i<12; i++)
							nombre += " ";
						
						nombreAux = nombre;
					}
					
					if(puntos <= 9)		//Para evitar los espacios.   
						record = fecha + "                            " + numeroDeRonda + "                            " + puntos +
                            "                            " + nombreAux + "\n";
					
					else if(puntos <= 99)
						record = fecha + "                            " + numeroDeRonda + "                            " + puntos +
                        "                          " + nombreAux + "\n";
					
					else if(puntos <= 999 )
						record = fecha + "                            " + numeroDeRonda + "                          " + puntos +
                        "                        " + nombreAux + "\n";
					
					else if(puntos <= 9999)
						record = fecha + "                            " + numeroDeRonda + "                          " + puntos +
                        "                      " + nombreAux + "\n";
					
					records.write(record.getBytes()); 												//Salto de línea.
					records.close();						//Cerrar el fichero.
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}			
			
			verificador.stop();						//El timer se para.
			gameOver = false;						//ahora que terminó la partida, variable es falsa, así puede volver a jugar.
			numeroDeMeteoros = 10;					//La primera ronda siempre son de 10 meteoros.
			meteorosDestruidos = 0;					//Los contadores se reincian.
			numeroDeMeteorosDestructores = 0;
			porcentajeSalud = 100;					//La salud regresa a 100 para cuando vuelva a jugar.
			musica.stop();							//La música del juego se para.
			labelGameOver.setVisible(false);		//Las etiquetas dejan de verse.
			labelPresiona.setVisible(false);
			numeroDeRonda = 1;
			puntos = 0;

			for(int i=0; i<meteoro.length; i++)		//Todos los meteros dejan de moverse, y así salen del hilo.
				meteoro[i].setMovimiento(false);
			
			vecesJugadas++;							//Se almacena las veces que el usuario a jugado en una partida.				
			actualizarJuego();
			generarRonda(numeroDeMeteoros);			//Se genera la ronda de inicio para cuando el usuario vuelva a jugar.
			GUI.poderes.setVisible(true);
			GUI.mensajeJuego.setVisible(false);
			GUI.poder1.setVisible(false);
			GUI.poder2.setVisible(false);
			GUI.poder3.setVisible(false);
			PanelMenu.intermediario.setPanel(0);	//Se regresa al panel de inicio.
		}  
		
		actualizarJuego();
	}

	@Override
	public void keyReleased(KeyEvent e) {	}

	@Override
	public void keyTyped(KeyEvent e) { }

	@Override
	public void actionPerformed(ActionEvent e) //Método para el timer.
	{
		int contador = 0;
		actualizarJuego();

		if(meteoro[0].hilo.getContador() >= 19) //se deshabilita el uso del poder.
			HiloMeteoro.retroceso = false;
		
		if(meteoro[0].hilo.getContador() >= 39)
			HiloMeteoro.pararTiempo = false;
		
		if(gameOver)			//Si el jugador pierde, el timer se debe de parar.
			verificador.stop();

		if(porcentajeSalud < 100 && !gameOver)
		{
			porcentajeSalud++;
			actualizarJuego();
		}
		
		if(Nivel1.numeroDeMeteorosDestructores + Nivel1.numeroDeMeteorosDestruidos >= numeroDeMeteoros - vecesJugadas && !gameOver)
		{
			for(int i=0; i<meteoro.length; i++)	//Bucle para verificar que los meteoros de esa ronda no se encuentren en movimiento.
				if(!meteoro[i].getMovimiento())
					++contador;
			
			if(contador == numeroDeMeteoros)	//Si todos dejaron de moverse, cambiar de ronda.
			{
				meteorosDestruidos += Nivel1.numeroDeMeteorosDestruidos;
				puntos += Nivel1.numeroDeMeteorosDestruidos;
				Nivel1.numeroDeMeteorosDestructores = 0;
				Nivel1.numeroDeMeteorosDestruidos = 0;
				numeroDeMeteoros += 10;
				++numeroDeRonda;
				
				if(Nivel1.numeroDeRonda == 10)	//Si llega a la ronda 10, empieza a salir de manera mixta.
					Nivel1.numeroDeMeteoros = 20; //baja la cantidad para que se acostumbre.
							
				generarRonda(numeroDeMeteoros);
				labelRonda.setText("RONDA: " + numeroDeRonda);
				empezarNivel(fuente);
			}
		}  
	}
}

class Meteoro extends JLabel
{									
	private static final long serialVersionUID = 1L;
	private char letra;											//El meteoro tendrá como atributo una letra (será una de sus "propiedades").
	private int coorX; 											//Coordenadas x & y del meteoro.
	private int coorY;											
	private int numeroImagen;
	private boolean movimiento = false;							//Tendrá una variable para saber si el meteoro debe de seguir cayendo.
	private final String rutaAux = "src/disenio/nivel1/";		//Variable auxiliar para agregarle la ruta al arreglo.
	private String[] rutaMeteoro = {rutaAux+"meteoro1.png", rutaAux+"meteoro2.png", rutaAux+"meteoro3.png", rutaAux+"meteoro4.png", 
									rutaAux+"meteoro5.png"}; //Arreglo de direcciones de las imágenes del meteorito.
	private String[] rutaExplosion = {rutaAux+"explosion1.png", rutaAux+"explosion2.png", rutaAux+"explosion3.png", rutaAux+"explosion4.png",
			rutaAux+"explosion5.png", rutaAux+"explosion6.png", rutaAux+"explosion7.png", rutaAux+"explosion8.png", rutaAux+"explosion9.png"};
	public HiloMeteoro hilo;
	Clip soundMeteoro;
												//Constructor.
	public Meteoro(int coorX, int coorY)						
	{
		this.coorX = coorX;
		this.coorY = coorY;
		this.setIcon(new ImageIcon(rutaMeteoro[0]));			//Establece la imagen del meteoro.
		this.setText("" + setLetra());
		
		hilo = new HiloMeteoro();
		hilo.setMeteoro(this); 				//Mandar como referencia al hilo.
	}
												//Métodos.
	public void setMovimiento(boolean movimiento) { this.movimiento = movimiento; }
	public void setNumeroImagen(int numeroImagen) { this.numeroImagen = numeroImagen; }
	public void setCoorY(int coorY) { this.coorY = coorY; }
	
	public void setFuente(String fuente)
	{
		this.setFont(new Font(fuente, 1, 40));		//Tipo de letra, estilo y tamaño.
	}
	
	public char setLetra() 										//Se obtiene la letra de manera pseudoaleatoria y se le asigna al meteoro.
	{	
		if(Nivel1.numeroDeRonda >= 10) //Sí llega al nivel 10, sale de forma mixta.
		{
			Nivel1.labelTeclas.setText("Mixtas");

			if( (new Random().nextInt(2) % 2) == 0) 				//Si el número pseudoaleatorio es divisible  entre dos, letra mayuscula.
			{
				letra = (char) (new Random().nextInt(90 - 65) + 65); //Límites del 65 al 90 para obtener el caracter (tabla ASCII).
				this.setForeground(Color.BLACK); 					//Color de la letra (negro significará mayúscula).
			}
			
			else 
			{
				letra = (char) (new Random().nextInt(122 - 97) + 97);
				this.setForeground(new Color(0, 20, 225));
			}
			
		}
		
		else //Mientras no llegue al nievl 10, salen sólo minúsculas o mayúsculas.
		{
			if(Nivel1.numeroDeRonda % 2 == 0)
			{
				letra = (char) (new Random().nextInt(90 - 65) + 65); //Límites del 65 al 90 para obtener el caracter (tabla ASCII).
				this.setForeground(Color.BLACK); 					//Color de la letra (negro significará mayúscula).
				Nivel1.labelTeclas.setText("Mayúsculas");
			}
			
			else 
			{
				letra = (char) (new Random().nextInt(122 - 97) + 97);
				this.setForeground(new Color(0, 20, 225));
				Nivel1.labelTeclas.setText("Minúsculas");
			}
		}
		this.setHorizontalTextPosition(JLabel.CENTER);			//Se establece la posición horizontal			
		this.setVerticalTextPosition(JLabel.CENTER); 			//Se establece la posición vertical, argumentos TOP, CENTER or BOTTOM.
		
		return letra;		
	}
	
	public void destruirMeteoro()
	{
		try {
			
			if(GUI.habilitarSonido)
			{
				soundMeteoro = AudioSystem.getClip();
				soundMeteoro.open(AudioSystem.getAudioInputStream(new File("src/disenio/sonidos/explosionMeteoro.wav")));
				soundMeteoro.start();
				FloatControl volumen = (FloatControl) soundMeteoro.getControl(FloatControl.Type.MASTER_GAIN);
				float distancia = Math.abs(volumen.getMinimum() - volumen.getMaximum()) / 10;
				float dB = volumen.getMinimum() - (distancia * -8);
				volumen.setValue(dB);
			}

		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		this.setText(""); //Eliminar la letra del texto.

		for(int i=0; i<rutaExplosion.length; i++)
		{
			this.setSize(73, 70);
			this.setIcon(new ImageIcon(rutaExplosion[i]));
			
			try {
				Thread.sleep(120);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}		
	}
	
	public void isHilo() //Pone en marcha el hilo del objeto.
	{
		if(this.movimiento)
		{
			this.hilo.start();
		}
	}
	
	public boolean getMovimiento() {return movimiento;}
	public int getCoorX() { return coorX; }	
	public int getCoorY() { return coorY; }
	public char getLetra() { return letra; }
	public void actualizarImagen()
	{
		this.setIcon(new ImageIcon(rutaMeteoro[numeroImagen]));
	}	
}

class HiloMeteoro extends Thread
{
	private Meteoro meteoro = null;	//Variable auxiliar para recibir los objetos meteoros y ponerlos en un hilo.	
	static boolean retroceso;
	static boolean pararTiempo;
	private int contador;
	
	public void setMeteoro(Meteoro meteoro) { this.meteoro = meteoro; }
	
	public void setContador(int contador) { this.contador = contador; }
	
	public int getContador() { return contador; }
		
	@Override
	public void run()
	{
		while(meteoro.getMovimiento())		//Si el meteoro se está moviendo, entrará.
		{
			meteoro.setNumeroImagen(Nivel1.aleatorio.nextInt(5)); //Sólo pueden existir 5 posibilidades (del 0 al 4).
			
			if(!retroceso && !pararTiempo)
			{
				meteoro.setCoorY(meteoro.getCoorY() + Nivel1.aleatorio.nextInt(2) + 1);
				meteoro.actualizarImagen();
			}

			if(retroceso && contador < 20)
			{
				meteoro.setCoorY(meteoro.getCoorY() - Nivel1.aleatorio.nextInt(5));
				contador++;
				meteoro.actualizarImagen();

			}
			
			else if(pararTiempo && contador < 40)
			{
				meteoro.setCoorY(meteoro.getCoorY());	//No avanza porque se para el tiempo
				contador++;
			}
						
			meteoro.setLocation(meteoro.getCoorX(), meteoro.getCoorY());
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			if(meteoro.getCoorY() >= 500) //Si el meteoro llega a 500 en Y, explota
			{
				meteoro.setMovimiento(false);			//Ahora ya no se moverá el meteoro.
				++Nivel1.numeroDeMeteorosDestructores;	//Contará como un meteoro que dañó al jugador.
				
				if(!Nivel1.gameOver)
				{
					Nivel1.porcentajeSalud -= 10;			//se le baja 10 unidades de salud.				
					Nivel1.actualizarJuego();
					break;
				}
			}	
		}
		
		if(GUI.ID == 2) 					//Para destruirlos, debe de salir del while y estar en el panel del juego
			meteoro.destruirMeteoro();		//Sí sale del while, significa que el meteoro debe de destruirse.
	}
}