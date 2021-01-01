package menu;


import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.Random;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

class PanelMenu extends JPanel implements ActionListener, KeyListener
{
	private static final long serialVersionUID = 1L;													//Serialización.
	private int xEstrella, yEstrella;																	//Coordenadas de la estrella.
	private int yBoton = -100; 																			//Coordenada de los botones.
	static boolean habilitarMusica = true;
	static boolean habilitarSonido = true;
	private boolean teclaPulsada = false;																//Detectar que usuario presione tecla.
	private int controladorDeTransparencia = 255;														//Sirve para controlar la transparencia.
	private boolean verificadorDeTransparencia = false;													//Verifica si se llego al mínimo o máximo.
	private JLabel labelMenuFondo = new JLabel(new ImageIcon("src/disenio/menu/menuFondo.png")); 					//Imágenes.
	private JLabel labelMenuFondoElementos = new JLabel(new ImageIcon("src/disenio/menu/menuFondoElementos.png"));
	private JLabel labelEstrellaFugaz = new JLabel(new ImageIcon("src/disenio/menu/estrellaFugaz.png"));
	private JLabel labelPulsaTecla = new JLabel("Presiona cualquier tecla");
	private JLabel labelVersion = new JLabel("Versión 1.0");
	private JButton botonJugar = new JButton(new ImageIcon("src/disenio/menu/botonSinPresionar.png"));	//Botón para empezar el juego.
	private JButton botonOpciones = new JButton(new ImageIcon("src/disenio/menu/botonSinPresionar.png")); //Botón para ir a opciones.
	private Timer timerAnimaciones = new Timer(15, this);												//Controlar las animaciones
	static Clip musicaMenu = null;
	static Clip sonidoSeleccion = null;
	static Clip sonidoEstrella = null;
	static GUI intermediario = null;
	
	public PanelMenu() 							//Constructor 
	{
		setLayout(null);										//Desactivar el Layout por defecto.

		botonJugar.setBounds(200, yBoton, 100, 100);						//Establecer las coordenadas y la dimensión.
		botonJugar.setBorderPainted(false);									//Desactivar los bordes del botón.
		botonJugar.setContentAreaFilled(false);								//Desactivar imagen del botón.
		botonJugar.setRolloverIcon(new ImageIcon("src/disenio/menu/botonPresionado2.png")); //Cambiar imagen cuando se presione.
		botonJugar.setFocusable(false);										//Quitarle el foco de atención para detectar teclas.
		botonJugar.addActionListener(this);									//Añadirlo al evento.
		labelMenuFondo.add(botonJugar);										//Añadirlo al contenedor
		
		botonOpciones.setBounds(480, yBoton, 100, 100);							//Establecer las coordenadas y dimensiones.
		botonOpciones.setBorderPainted(false);									//Desactivar los bordes del botón.
		botonOpciones.setContentAreaFilled(false);								//Desactivar imagen del botón.
		botonOpciones.setRolloverIcon(new ImageIcon("src/disenio/menu/botonPresionado3.png")); //Cambiar imagen cuando se presione.
		botonOpciones.setFocusable(false);
		botonOpciones.addActionListener(this);
		labelMenuFondo.add(botonOpciones);									//Añadirlo al contenedor
		
		labelMenuFondoElementos.setBounds(0, 0, 800, 600);					//Establecer las coordenadas y dimensiones.
		labelMenuFondo.add(labelMenuFondoElementos);						//Agregar elemento para ser contenido de LabelMenuFondo.
		
		generarCoordenadas();												//Método para generar las coordenadas de estrella.
		labelEstrellaFugaz.setBounds(xEstrella, yEstrella, 440, 178);		//Establecer las coordenadas y dimensiones.
		labelMenuFondo.add(labelEstrellaFugaz);
		timerAnimaciones.start();											//Iniciar el tiempo.
		
		labelPulsaTecla.setBounds(280, 320, 230, 50);						//Establece las coordenadas y dimensiones.
		labelPulsaTecla.setFont(new Font("Arial", 1, 20));					//Establecer el tipo de fuente, estilo y tamaño.
	//	labelPulsaTecla.setForeground(Color.MAGENTA);						//Establecer el color de fuente.
	//	labelPulsaTecla.setOpaque(true);									//Método para habilitar el fondo de la imagen.
		labelMenuFondo.add(labelPulsaTecla);
		
		labelVersion.setBounds(350, 510, 70, 20);
		labelVersion.setFont(new Font("Arial", 1, 12));
		labelVersion.setForeground(Color.GREEN.darker());
	//	labelVersion.setOpaque(true);										
		labelMenuFondo.add(labelVersion);
		
		labelMenuFondo.setBounds(0, 0, 800, 600);
		this.add(labelMenuFondo);
	}
	
													//Métodos
	private void generarCoordenadas()
	{
		this.xEstrella = new Random().nextInt(500) + 1000;
		this.yEstrella = new Random().nextInt(600) - 300;
	}
	
	public static void empezarSonidos()
	{
		try
		{
			musicaMenu = AudioSystem.getClip(); //Obtener el audio.
			File archivoMusica = new File("src/disenio/musica/musicaMenu.wav"); 
			musicaMenu.open(AudioSystem.getAudioInputStream(archivoMusica));	
			isMusica();
			
			sonidoSeleccion = AudioSystem.getClip();
			sonidoSeleccion.open(AudioSystem.getAudioInputStream(new File("src/disenio/sonidos/seleccionado.wav")));
			
			sonidoEstrella = AudioSystem.getClip();
			sonidoEstrella.open(AudioSystem.getAudioInputStream(new File("src/disenio/sonidos/estrellaFugaz.wav"))); 
			
		} catch (Exception e) 
		{
			e.printStackTrace();
		}	 
	}
	public static void isMusica()
	{
		if(PanelMenu.habilitarMusica)								//Condicional para saber si la música debe de empezar a sonar.
		{
			musicaMenu.setMicrosecondPosition(0); 		//Empezar desde el microsegundo 0.
			musicaMenu.loop(Clip.LOOP_CONTINUOUSLY);	//Repetirse de manera continua.
			//musicaMenu.start();						//Empezar (ya no hace falta con la instrucción anterior).
		}
		
		else musicaMenu.stop();
	}

	public static void isSonido()
	{
		if(habilitarSonido)
		{	
			if(GUI.ID == 0)
			{
				sonidoEstrella.setMicrosecondPosition(0);
				sonidoEstrella.start(); 	
			}
			
			sonidoSeleccion.setMicrosecondPosition(0);
			sonidoSeleccion.start();		
		}
		

	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{	
		if(e.getSource() == botonJugar || e.getSource() == botonOpciones)
		{
			isSonido();
			
			if(e.getSource() == botonOpciones)
				intermediario.setPanel(1);			//Lo envía al menú de opciones.
			
			else intermediario.setPanel(2);			//Lo envía al juego. 
		}
		
		if(xEstrella <= -500) //Cuando la coordenada x llegue a -500, se vuelven a generar otras coordenadas.
			generarCoordenadas();
		
		if(xEstrella >= 795 && xEstrella <= 800 && habilitarSonido && GUI.ID == 0) //Cuando la estrella esté entre estas coordenadas, está entrando en pantalla.
		{
			sonidoEstrella.setMicrosecondPosition(0);	//Se ejecuta el efecto desde el instante 0.
			sonidoEstrella.start();						//El efecto empieza a sonar.
		} 
		
		labelEstrellaFugaz.setLocation(xEstrella, yEstrella);	//Se establecen las nuevas coordenadas.
		this.xEstrella -= 5;									//Disminuimos x (Para que vaya la izquierda).
		this.yEstrella++;										//Aumentamos y (para que descienda).

		if(teclaPulsada) //Condicional que verifica si el usuario ya pulsó la tecla.
		{
			if(yBoton <= 370) //Si usuario pulsó la tecla, sale "animación" de los botones.
			{
				if( (controladorDeTransparencia-=5)  >  10)
					labelPulsaTecla.setForeground(new Color(170, 0, 240, controladorDeTransparencia));
				
				else labelPulsaTecla.setVisible(false); 	//El texto ya no se mostrará.

				yBoton += 8;
				botonJugar.setFocusable(true);				//Vuelven a tener el foco.
				botonOpciones.setEnabled(true);
				botonJugar.setLocation(200, yBoton);
				botonOpciones.setLocation(480, yBoton);
			}
		}
		
		else		//Si usuario no a presionado nada, se muestra el texto "presiona cualquier tecla".
		{
			if(controladorDeTransparencia < 3)			//Cuando el controladorDeTransparencia sea menor de 3, la variable
				verificadorDeTransparencia = true;		//cambia a verdadero, para indicar que ahora debe aumentar el valor (máximo 255).
			
			else if(controladorDeTransparencia > 250) 	//Si llega hacer mayor que 250, ahora la variable será falsa
				verificadorDeTransparencia = false;		//eso quiere decir que el valor de controladorDeTransparecia debe de bajar.
			
			controladorDeTransparencia = verificadorDeTransparencia ? (controladorDeTransparencia+=2) : (controladorDeTransparencia-=2);
		
			labelPulsaTecla.setForeground(new Color(170, 0, 240, controladorDeTransparencia));
		}
			
	}
	
	@Override
	public void keyPressed(KeyEvent e) 
	{
		teclaPulsada = true; 				//Ya no se mostrará la "animación" del texto de "Presiona cualquier tecla".
	}
	@Override
	public void keyReleased(KeyEvent e) {}	//Necesarios de definir, pero no necesitamos su implementación por el momento.
	
	@Override
	public void keyTyped(KeyEvent e) {}
}
