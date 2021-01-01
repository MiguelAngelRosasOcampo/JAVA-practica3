package menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class PanelOpciones extends JPanel implements ActionListener, ChangeListener
{
	private static final long serialVersionUID = 1L;
	static boolean habilitarMusica = false;
	static boolean habilitarSonido = true;
	private JLabel labelFondo = new JLabel(new ImageIcon("src/disenio/menu/opciones.png"));
	private JLabel labelVolumen = new JLabel("Volumen");
	private JLabel labelSonido = new JLabel("Sonido");
	private JLabel labelFuente = new JLabel("Fuente");
	private JButton botonFlechaSinPresionar = new JButton(new ImageIcon("src/disenio/menu/flechaSinPresionar.png")) ;
	private JSlider sliderVolumen = new JSlider(0, 10, 10);
	private JSlider sliderSonido = new JSlider(0, 10, 10);
	private String[] fuentes = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();	//Obtener las fuentes del programa.
	private String letrasJuegoMinusculas = "a b c d e f g h i j k l m n o p q r s t u v w x y z.";
	private	String letrasJuegoMayusculas = "A B C D E F G H I J K L M N O P Q R S T U V W X Y Z.";
	private JComboBox<String> boxFuentes = new JComboBox<String>(fuentes);
	private JLabel labelLetrasMinusculas = new JLabel(letrasJuegoMinusculas);
	private JLabel labelLetrasMayusculas = new JLabel(letrasJuegoMayusculas);
	static Clip musicaOpciones = null;
	static Clip sonidoSeleccion = null;
	static Clip sonidoRegulador = null;
	static GUI intermediario = null;

	public PanelOpciones()
	{
		this.setLayout(null);
		
		labelFondo.setBounds(0, 0, 800, 600);			//Establecer el fondo de pantalla
		add(labelFondo);
		
		botonFlechaSinPresionar.setBounds(20, 60, 99, 58);
		botonFlechaSinPresionar.setRolloverIcon(new ImageIcon("src/disenio/menu/flechaPresionada.png"));
		botonFlechaSinPresionar.setBorderPainted(false);
		botonFlechaSinPresionar.setContentAreaFilled(false);
		botonFlechaSinPresionar.addActionListener(this);
		labelFondo.add(botonFlechaSinPresionar);
		
		labelVolumen.setBounds(50, 195, 120, 30);
		//labelVolumen.setOpaque(true);
		labelVolumen.setFont(new Font("Arial", 3, 28));
		labelVolumen.setForeground(Color.BLACK);
		labelFondo.add(labelVolumen);
		
		sliderVolumen.setMajorTickSpacing(1);						//Cada cuanto aparecen las marcas grandes.
		sliderVolumen.setPaintLabels(true);							//Habilitar la aparición de números.
		sliderVolumen.setBounds(250, 200, 500, 40);					//Establecer posición y dimensión
		sliderVolumen.setFont(new Font("Arial", 1, 12));
		sliderVolumen.setForeground(Color.BLACK);
		sliderVolumen.setPaintTrack(true);							//Pinta la linea donde se desliza el indicador
		sliderVolumen.setOpaque(false);
		sliderVolumen.addChangeListener(this);						//Ponerlo a la escucha.
		labelFondo.add(sliderVolumen);								//Agregarlo al contenedor "labelFondo".
		
		labelSonido.setBounds(50, 295, 120, 30);
		labelSonido.setFont(new Font("Arial", 3, 28));
		labelSonido.setForeground(Color.BLACK);
		labelFondo.add(labelSonido);

		sliderSonido.setBounds(250, 300, 500, 40);
		sliderSonido.setMajorTickSpacing(1);
		sliderSonido.setPaintLabels(true);
		sliderSonido.setFont(new Font("Arial", 1, 12));
		sliderSonido.setForeground(Color.BLACK);
		sliderSonido.setOpaque(false);
		sliderSonido.addChangeListener(this); 						//Ponerlo a la escucha.
		labelFondo.add(sliderSonido);
		
		labelFuente.setBounds(50, 395, 120, 30);
		labelFuente.setFont(new Font("Arial", 3, 28));
		labelFuente.setForeground(Color.BLACK);
		labelFondo.add(labelFuente);

		boxFuentes.setBounds(250, 400, 500, 40);
		boxFuentes.setFont(new Font("Arial", 1, 24));
		boxFuentes.addActionListener(this);
		labelFondo.add(boxFuentes);
		
		labelLetrasMinusculas.setBounds(10, 450, 790, 40);
		//labelLetrasMinusculas.setOpaque(true);
		labelLetrasMinusculas.setFont(new Font("Arial", 1, 18));
		labelLetrasMinusculas.setForeground(Color.BLACK);
		labelFondo.add(labelLetrasMinusculas);
		
		labelLetrasMayusculas.setBounds(10, 500, 790, 40);
		//labelLetrasMayusculas.setOpaque(true);
		labelLetrasMayusculas.setFont(new Font("Arial", 1, 18));
		labelLetrasMayusculas.setForeground(Color.BLACK);
		labelFondo.add(labelLetrasMayusculas);
		repaint();
	}
	
	static void empezarSonidos()
	{
		try
		{
			musicaOpciones = AudioSystem.getClip(); //Obtener el audio.
			File archivoMusica = new File("src/disenio/musica/musicaOpciones.wav"); 
			musicaOpciones.open(AudioSystem.getAudioInputStream(archivoMusica));	
			isMusica();
			
			sonidoSeleccion = AudioSystem.getClip();
			sonidoSeleccion.open(AudioSystem.getAudioInputStream(new File("src/disenio/sonidos/seleccionado.wav")));
			
			sonidoRegulador = AudioSystem.getClip();
			sonidoRegulador.open(AudioSystem.getAudioInputStream(new File("src/disenio/sonidos/regulador.wav")));
			
		} catch (Exception e) 
		{
			e.printStackTrace();
		}	 
	}
	
	static void isMusica()
	{
		if(PanelOpciones.habilitarMusica)
		{
			musicaOpciones.setMicrosecondPosition(0);
			musicaOpciones.loop(Clip.LOOP_CONTINUOUSLY);
		}
	
		else musicaOpciones.stop();
	}
	
	static void isSonido()
	{
		if(PanelOpciones.habilitarSonido)
		{
			sonidoSeleccion.setMicrosecondPosition(0);
			sonidoSeleccion.start();
		}
	}
	
	public String getFuente() //Regresa el tipo de fuente seleccionada.
	{
		return (String)boxFuentes.getSelectedItem();
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource() == botonFlechaSinPresionar)
		{
			isSonido();
			
			intermediario.setPanel(0);			//Argumento 0, representa el menú.
		}
		
		labelLetrasMinusculas.setFont(new Font((String)boxFuentes.getSelectedItem(), 1, 18));
		labelLetrasMayusculas.setFont(new Font((String)boxFuentes.getSelectedItem(), 1, 18));	
	}
	
	@Override
	public void stateChanged(ChangeEvent e) 
	{
		if(PanelOpciones.habilitarSonido)
		{
			sonidoRegulador.setMicrosecondPosition(0);
			sonidoRegulador.start();
		}
		
		if(e.getSource() == sliderVolumen)
			GUI.setVolumen(sliderVolumen.getValue());
		
		else if(e.getSource() == sliderSonido)
			GUI.setSonido(sliderSonido.getValue());
		
		System.out.println("modificando el slider."); //NO borrar, evita perder el foco. 
	}
}
