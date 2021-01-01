package menu;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import javax.sound.sampled.FloatControl;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

class GUI extends JFrame implements ActionListener
{
	private static final long serialVersionUID = 1L;				//Número de serialización.
	static boolean habilitarMusica = true;
	static boolean habilitarSonido = true;
	static int ID = 0;
	static PanelOpciones panelOpcion = new PanelOpciones();
	static PanelMenu panelInicio = new PanelMenu();
	static Nivel1 panelNivel1 = new Nivel1();
	private ImageIcon icono = new ImageIcon("src/menu/icono.png");	//Se crea un objeto que almacenará el ícono.
	private JTextArea creditos = new JTextArea("Consulta el siguiente enlace:\n"
			+ "\nhttps://drive.google.com/file/d/1hQ-FFoobfyP6JEfESVA9xrYgABe5GEU_/view?usp=sharing");
	private JMenuBar barMenu = new JMenuBar();
	private JMenu menuAyuda = new JMenu("Ayuda");
	private JMenu menuAcerca = new JMenu("Acerca de");
	private JMenu menuOpciones = new JMenu("Opciones");
	private JMenu opcionesMusica = new JMenu("Musica");
	private JMenu opcionesSonido = new JMenu("Sonido");
	static JMenu poderes = new JMenu("poderes");
	static JMenu poder1 = new JMenu("1. Regresión en el tiempo");
	static JMenu poder2 = new JMenu("2. Detención temporal");
	static JMenu poder3 = new JMenu("3. Recuperación");
	static JMenu mensajeJuego = new JMenu("Junta energía y pulsa la tecla: ");
	private JMenuItem info = new JMenuItem("Lista de poderes");
	private JMenuItem apagarMusica = new JMenuItem("Desactivar la música");
	private JMenuItem apagarSonido = new JMenuItem("Desactivar los sonidos");
	private JMenuItem ayudaHistoria = new JMenuItem("Historia");
	private JMenuItem ayudaRecords = new JMenuItem("Records");
	private JMenuItem ayudaInstrucciones = new JMenuItem("Instrucciones");
	private JMenuItem acercaMotivacion = new JMenuItem("Motivación");
	private JMenuItem acercaCreditos = new JMenuItem("Créditos");
	private JMenuItem acercaInformacion = new JMenuItem("MarcianoLand");
	private JMenuItem acercaAgradecimientos = new JMenuItem("Agradecimientos");
	private JMenuItem acercaDedicatoria = new JMenuItem("Dedicatoria");
	private FileInputStream ficheroRecords;
	static FloatControl volumen;
	
										//Constructor
	public GUI()		
	{
		PanelMenu.empezarSonidos();
		PanelOpciones.empezarSonidos();
		Nivel1.empezarMusica();
		
		this.add(panelInicio);										//Agrega el panel de menu.
		this.addKeyListener(panelInicio);
		this.addKeyListener(panelNivel1);
		
		setJMenuBar(barMenu);									//Agrega la barra de herramientas.

		menuOpciones.add(opcionesMusica);						//Agrega un submenu al apartado de "opciones".
		opcionesMusica.add(apagarMusica);						//Agrega el item al submenú.		
		apagarMusica.addActionListener(this);					//Agrega el item para un evento.
		
		menuOpciones.add(opcionesSonido);						//Agrega un submenu al apartado de "opciones".			
		opcionesSonido.add(apagarSonido);						//Agrega el item al submenú.
		apagarSonido.addActionListener(this);					//Agrega el item al evento.
		
		barMenu.add(menuOpciones);								//Agrega el apartado de "opciones" a la barra.
		
		menuAyuda.add(ayudaHistoria);
		menuAyuda.add(ayudaInstrucciones);
		menuAyuda.add(ayudaRecords);
		ayudaRecords.addActionListener(this);
		ayudaHistoria.addActionListener(this);
		ayudaInstrucciones.addActionListener(this);
		barMenu.add(menuAyuda);

		menuAcerca.add(acercaCreditos);
		menuAcerca.add(acercaInformacion);
		menuAcerca.add(acercaMotivacion);
		menuAcerca.add(acercaAgradecimientos);
		menuAcerca.add(acercaDedicatoria);
		acercaDedicatoria.addActionListener(this);
		acercaCreditos.addActionListener(this);
		acercaInformacion.addActionListener(this);
		acercaMotivacion.addActionListener(this);
		acercaAgradecimientos.addActionListener(this);
		barMenu.add(menuAcerca);
		
		poderes.add(info);
		info.addActionListener(this);
		barMenu.add(poderes);

		mensajeJuego.setEnabled(false);
		mensajeJuego.setVisible(false);
		barMenu.add(mensajeJuego);
		
		poder1.setEnabled(false);
		poder1.setVisible(false);
		barMenu.add(poder1);
		
		poder2.setEnabled(false);
		poder2.setVisible(false);
		barMenu.add(poder2);
		
		poder3.setEnabled(false);
		poder3.setVisible(false);
		barMenu.add(poder3);

		creditos.setEditable(false);
		creditos.setOpaque(false);
		creditos.setFont(new Font("Arial", 1, 12));
										//Características de la GUI.
	
		this.setSize(800, 600);								//Establece el tamaño.
		this.setLocationRelativeTo(null);					//Establece que aparezca centrada.
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//Establece que el programa finalice al presionar X.
		this.setResizable(false);							//Establece que no pueda ser redimensionada.
		this.setTitle("MarcianoLand");						//Establece el título.
		this.setIconImage(icono.getImage());				//Establece el ícono de la ventana.
		this.setVisible(true);
	}
	
	public void setPanel(int numeroDePanel)
	{
		GUI.ID = numeroDePanel;
		getContentPane().removeAll();			//Remover el panel anterior.
		this.requestFocus(); 					//Recuperar el foco.

		if(numeroDePanel == 0)	//El panel será el del menú principal.
		{
			if(GUI.habilitarMusica)			//Si es verdadero, significa que sólo puede sonar la música del menú principal.
			{
				if(!PanelMenu.sonidoEstrella.isRunning()) //Verifica que el sonido de la estrella se esté ejecutando.
				{
					PanelMenu.sonidoEstrella.setMicrosecondPosition(0);	//De lo contrario, que se ejecute.
					PanelMenu.sonidoEstrella.start();
				}	
				
				PanelOpciones.habilitarMusica = false;	//La variable cambia a falso, para que se deje de escuchar la
				PanelOpciones.isMusica();				//música del menú de opciones.
				
				PanelMenu.habilitarMusica = true;		//Empieza a sonar la música del menú principal.
				PanelMenu.isMusica();
			}
			
			else	//Si GUI.numeroDePanel es falsa, entonces NO suena la música.
			{
				if(!PanelMenu.sonidoEstrella.isRunning())			//Si no está reproduciendose, que se reproduzca el sonido.
				{
					PanelMenu.sonidoEstrella.setMicrosecondPosition(0);
					PanelMenu.sonidoEstrella.start();
				}
				
				PanelOpciones.musicaOpciones.stop();
				PanelMenu.musicaMenu.stop();
			}

			getContentPane().add(panelInicio);		//Agregar el nuevo panel.
		}
		
		else if(numeroDePanel == 1)
		{
			if(GUI.habilitarMusica) //Sí GUI.habilitarMusica es verdadera, entonces se deshabila una música.
			{
				PanelMenu.sonidoEstrella.stop(); 		//Se pasa al menú de opciones, y sonido se para.
				PanelMenu.habilitarMusica = false; 		//Variable para habilitar la música del menú se vuelve falsa.
				PanelMenu.isMusica();					//Se actualiza el estado.
				Nivel1.habilitarMusica = false;
				panelNivel1.isMusica();
				
				PanelOpciones.habilitarMusica = true;	//Variable para habilitar la música de las opciones se vuelve true.
				PanelOpciones.isMusica();				//Se actualiza para empezar a sonar en el menú de opciones.
			}
			
			else
			{
				PanelMenu.sonidoEstrella.stop();		//Aquí también se para.
				PanelMenu.musicaMenu.stop();
				PanelOpciones.musicaOpciones.stop();
			}

			getContentPane().add(panelOpcion);			//Agregar el nuevo panel.
		}
		
		else if(numeroDePanel == 2)
		{
			if(GUI.habilitarMusica)
			{
				PanelMenu.sonidoEstrella.stop(); 		//Se pasa al menú de opciones, y sonido se para.
				PanelMenu.habilitarMusica = false; 		//Variable para habilitar la música del menú se vuelve falsa.
				PanelMenu.isMusica();					//Se actualiza el estado.
			}
			
			else
			{
				PanelMenu.sonidoEstrella.stop();		//Aquí también se para.
				PanelMenu.musicaMenu.stop();
				PanelOpciones.musicaOpciones.stop();
			}
			
			poderes.setVisible(false);
			mensajeJuego.setVisible(true);
			poder1.setVisible(true);
			poder2.setVisible(true);
			poder3.setVisible(true);
			getContentPane().add(panelNivel1);
		//	Meteoro.pausarMeteoro = true;
			panelNivel1.empezarNivel(panelOpcion.getFuente());
			Nivel1.habilitarMusica = true;
			panelNivel1.isMusica();
		}
		
		getContentPane().revalidate();			//Revalidar el panel.
		repaint();								//Pintarlo.
	}

	public void setMusica()
	{	
		if(GUI.ID == 0) //Sólo podrá entrar aquí sí se encuentra en el menú principal.
		{	
			apagarMusica.setText("" + (GUI.habilitarMusica ? "Activar la música" : "Desactivar la música"));
			
			GUI.habilitarMusica = PanelMenu.habilitarMusica = !PanelMenu.habilitarMusica; //Deben de ser iguales para saber si
			PanelMenu.isMusica();	//se desactivo la música, después se actualizar el estado de la música.
		}
		
		else if(GUI.ID == 1) //Sólo podrá entrar aquí si se encuentra en el menú de opciones.
		{
			apagarMusica.setText("" + (GUI.habilitarMusica ? "Activar la música" : "Desactivar la música"));

			GUI.habilitarMusica = PanelOpciones.habilitarMusica = !PanelOpciones.habilitarMusica;	
			PanelOpciones.isMusica();			
		}
		
		else if(GUI.ID == 2)
		{
			apagarMusica.setText("" + (GUI.habilitarMusica ? "Activar la música" : "Desactivar la música"));
			
			GUI.habilitarMusica = Nivel1.habilitarMusica = !Nivel1.habilitarMusica; //Deben de ser iguales para saber si
			panelNivel1.isMusica();	//se desactivo la música, después se actualizar el estado de la música.
		}
	} 
	
	public void isSonido()
	{
		
		if(GUI.habilitarSonido)
		{
			apagarSonido.setText("Activar el sonido");		//Cambiar el texto del item.
			PanelMenu.sonidoEstrella.stop();				//Se para el sonido de la estrella fugaz.
			PanelMenu.habilitarSonido = false;				//Variable cambia a falsa para deshabilitar el sonido.
			PanelOpciones.habilitarSonido = false;
			GUI.habilitarSonido = false;

			PanelMenu.isSonido();							//Actualizar el estado del sonido.
			PanelOpciones.isSonido();
		}
		
		else
		{
			apagarSonido.setText("Desactivar el sonido");
			PanelMenu.habilitarSonido = true;
			PanelOpciones.habilitarSonido = true;
			GUI.habilitarSonido = true;
			
			PanelMenu.isSonido();
			PanelOpciones.isSonido();
		}
	}
	
	static void setVolumen(int nivelVolumen)
	{	
		volumen = (FloatControl) PanelOpciones.musicaOpciones.getControl(FloatControl.Type.MASTER_GAIN);
		
		float distancia = Math.abs(volumen.getMinimum() - volumen.getMaximum()) / 10;	//Obtener la distancia y hacer particiones.		
		float dB = volumen.getMinimum() - (distancia * -nivelVolumen);					//Se obtiene el nivel del volumen.
		
		volumen.setValue(dB);	//Se establece el nivel de volumen según el usuario.
		
		volumen = (FloatControl) PanelMenu.musicaMenu.getControl(FloatControl.Type.MASTER_GAIN);
		
		volumen.setValue(dB); 
	}
	
	static void setSonido(int nivelVolumen)
	{	
		volumen = (FloatControl) PanelOpciones.sonidoSeleccion.getControl(FloatControl.Type.MASTER_GAIN);
		
		float distancia = Math.abs(volumen.getMinimum() - volumen.getMaximum()) / 10;	//Obtener la distancia y hacer particiones.		
		float dB = volumen.getMinimum() - (distancia * -nivelVolumen);					//Se obtiene el nivel del volumen.
		
		volumen.setValue(dB);	//Se establece el nivel de volumen según el usuario.
		
		volumen = (FloatControl) PanelMenu.sonidoSeleccion.getControl(FloatControl.Type.MASTER_GAIN);		
		volumen.setValue(dB);
		
		volumen = (FloatControl) PanelOpciones.sonidoRegulador.getControl(FloatControl.Type.MASTER_GAIN);
		volumen.setValue(dB);
		
		volumen = (FloatControl) PanelMenu.sonidoEstrella.getControl(FloatControl.Type.MASTER_GAIN);
		volumen.setValue(dB);  
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == apagarMusica)
			setMusica();
		
		else if(e.getSource() == apagarSonido)
			isSonido();
		
		else if(e.getSource() == ayudaHistoria)
			JOptionPane.showMessageDialog(this, "Eres un marcianito que protege al planeta tierra \nde una lluvia de meteoros, usa "
					+ "tu tecnología y \nacaba con todas las amenzas y salva al planeta.", "Historia", 
					JOptionPane.DEFAULT_OPTION, icono);
		
		else if(e.getSource() == ayudaInstrucciones)
			JOptionPane.showMessageDialog(this, "Durante cada ronda saldrán meteoros con una letra, \nusa tu teclado para destruir cada"
					+ " meteoro, cada ronda \nsaldrán más y más meteoros, puedes usar tus poderes \npara destruir todos"
					+ " los meteoros y así evitar una catástrofe. \n\nEn caso de salir meteoros con la misma letra"
					+ "\nse destruirá uno de ellos al presionar la tecla, \ntú no escoges cuál, se escoge de manera automática."
					+ "\n\nCada que pulsas una tecla erronea, los meteoros \nse adelantarán si los meteoros caen lento, puedes "
					+ "\nequivocarte para que acelerar su llegada.\n\nPersonaliza el tipo de fuente para los meteoros, pero, elige \nuna fuente"
					+ " que te permita diferenciar entre las letras\ni, j, l (L), tanto mayúsculas, como minúsculas.", 
					"Instrucciones", JOptionPane.DEFAULT_OPTION, icono);
		
		else if(e.getSource() == ayudaRecords)
		{
			try 
			{
				String guardarNombres = "Fecha                                 Ronda                    Puntaje                    Nombre \n\n";
				ficheroRecords = new FileInputStream("src/menu/records.marcianoLand");
				int numeroDeByte = ficheroRecords.read();
				
				while(numeroDeByte != -1 )
				{
					System.out.print((char) numeroDeByte);
					guardarNombres += (char)numeroDeByte;
					numeroDeByte = ficheroRecords.read();
				}
				
				JTextArea area = new JTextArea(guardarNombres);
				area.setOpaque(false);
				area.setEditable(false);
				area.setFont(new Font(panelOpcion.getFuente(), 1, 12));
				
				JOptionPane.showMessageDialog(this, area, "Tabla de records", JOptionPane.DEFAULT_OPTION, icono);

				ficheroRecords.close();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
			
		else if(e.getSource() == acercaCreditos)
			JOptionPane.showMessageDialog(this, creditos, "Créditos", JOptionPane.DEFAULT_OPTION, icono);
		
		else if(e.getSource() == acercaInformacion)
			JOptionPane.showMessageDialog(this, "                 Creadores\n\nLuis Fernando Esqueda García\n" + "Luis Eloy Lazcano Ortiz\n"
			+ "Miguel Angel Rosas Ocampo\n\n\n\t    Versión 1.0          29/12/2020", "MarcianoLand", JOptionPane.DEFAULT_OPTION, icono);
		
		else if(e.getSource() == acercaMotivacion)
			JOptionPane.showMessageDialog(this, "Este fue el proyecto final de la materia de \nProgramación Orientada a Objetos de la "
					+ "carrera\nIngeniería en computación, de la Universidad \nAutónoma de Baja California, sin embargo, \nmás allá de "
					+ "haberlo creado por ser un proyecto, \nse elaboró con todo el entusiasmo del mundo \nsin duda aquí empieza nuestra "
					+ "historia de POO.", "Motivación", JOptionPane.DEFAULT_OPTION, icono);
		
		else if(e.getSource() == acercaAgradecimientos)
				JOptionPane.showMessageDialog(this, "Agradecemos enormemente a todos los colaboradores \nimplicitos "
						+ "en este proyecto, es decir, a todos aquellos \nde los que hicimos uso de sus obras, como, música, sonidos,\nimágenes"
						+ " y cualquier otro elemento aquí presente.\n\nAsí mismo, agradecer a nuestra profesora de laboratorio \n"
						+ "Claudia Gabriel Tona Castro, por su tiempo y compromiso \nasí mismo como al Dr. J. Reyes Juarez Ramirez por su"
						+ "\nsiempre entusiasmo y su motivación por la mejora continua.", "Agradecimientos", JOptionPane.DEFAULT_OPTION, icono);
		
		else if(e.getSource() == acercaDedicatoria)
				JOptionPane.showMessageDialog(this, "Este juego va dedicado a aquellos que quieran \nentrenar sus habilidades en "
						+ "mecanografía, \nen especial para los niños, ya que es necesario \nfomentar la adaptación a las nuevas tecnologías."
						,"Dedicatoria", JOptionPane.DEFAULT_OPTION, icono);
		
		else if(e.getSource() == info)
				JOptionPane.showMessageDialog(this, "Reune energía através de los meteoros para estos poderes\n"
						+ "\n 1. Regresión en el tiempo (retrocede los meteoros) \n 2. Detención temporal (detiene el tiempo)"
						+ "\n 3. Recuperación (50 puntos de salud, no importa si tienes 100%)\n\nPulsa la tecla correspondiente mientras juegas"
						+ "\n(cada ronda la cantidad de meteoros destruidos, se volverán energía).", 
						"Poderes", JOptionPane.DEFAULT_OPTION, icono );
	}	
}
