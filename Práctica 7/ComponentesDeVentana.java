import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

class ComponentesDeVentana extends JPanel implements ActionListener
{
	private JLabel ultimoEstatus = new JLabel("ÚLTIMO ESTATUS: Todo bien");
	private JLabel procedimiento = new JLabel("");
	private JButton botonRobotManos = new JButton("Handhot");
	private JButton botonRobotChef = new JButton("Magnum Opus");
	private JButton botonRobotCuchillos = new JButton("KnifeChef");
	private JButton botonIngredientes = new JButton("Preparar");
	private JButton botonCocinar = new JButton("Cocinar");
	private JButton botonServir = new JButton("Servir");
	private JButton botonListo = new JButton("¡LISTO!");
	private JButton[] botones = {botonRobotManos, botonRobotChef, botonRobotCuchillos, botonIngredientes,
								botonCocinar, botonServir};
	private byte robotElegido = -1; //-1 será el valor de error.
	private byte accionElegida = -1;
	private boolean[] pedidoCumplido = {false, false, false};


	public ComponentesDeVentana(int anchura, int altura)
	{
		setLayout(null); //Desactivar layaout por defecto.
		
		setBackground(new Color(180, 160, 255)); //Color de fondo de ventana.
	
		ultimoEstatus.setBounds(0, 0, 450, 20);
		//ultimoEstatus.setOpaque(true);
		ultimoEstatus.setFont(new Font("Times New Roman", 0, 12));
		ultimoEstatus.setForeground(new Color(0, 60, 130));
		add(ultimoEstatus);
		
		JLabel tituloDentroDeGUI = new JLabel("Restaurant ROfooT"); //Juego de palabras (ROboT y FOOd)
		tituloDentroDeGUI.setBounds((int)anchura/5, 10, 420, 55);
		//tituloDentroDeGUI.setOpaque(true);
		tituloDentroDeGUI.setFont(new Font("Times New Roman", 3, 48));
		tituloDentroDeGUI.setForeground(new Color(255, 100, 180));
		add(tituloDentroDeGUI);
		
		procedimiento.setBounds(2, 80, 200, 20);
		procedimiento.setFont(new Font ("Times New Roman", 0, 12));
		add(procedimiento);
		
		JLabel cocinero = new JLabel("Elija su cocinero");
		cocinero.setBounds((int)anchura/2 - (int)anchura/10, (int)altura/5, 140, 30);
		//cocinero.setOpaque(true);
		cocinero.setFont(new Font("Times New Roman", 1, 20));
		cocinero.setForeground(new Color(120, 60, 200));
		add(cocinero);
		
		botonListo.setBounds(5, 50, 80, 30);
		botonListo.setFocusable(false);
		botonListo.setEnabled(false);
		botonListo.setFont(new Font("Times New Roman", 0, 12));
		botonListo.addActionListener(this);
		add(botonListo);
		
		crearBoton(botonRobotManos, (int)anchura/8, (int)altura/2 - (int)altura/6 );
		crearBoton(botonRobotChef, (int)anchura/2 - (int)anchura/8, (int)altura/2 - (int)altura/6);
		crearBoton(botonRobotCuchillos, (int)anchura/2 + (int)anchura/8, (int)altura/2 - (int)altura/6);
		
		JLabel acciones = new JLabel("Acciones");
		acciones.setBounds((int)anchura/2 - (int)anchura/20, 10 + (altura - (int)altura/2), 80, 30);
		//acciones.setOpaque(true);
		acciones.setFont(new Font("Times New Roman", 1, 20));
		acciones.setForeground(new Color(120, 60, 200));
		add(acciones);
		
		crearBoton(botonIngredientes, (int)anchura/8, altura - (int)altura/3);
		crearBoton(botonCocinar, (int)anchura/2 - (int)anchura/8, altura - (int)altura/3);
		crearBoton(botonServir, (int)anchura/2 + (int)anchura/8, altura - (int)altura/3);
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource() == botonRobotManos)
			robotElegido = cambiarColorBoton(robotElegido, (byte)0, (byte)1, (byte)2);
		
		else if(e.getSource() == botonRobotChef)
			robotElegido = cambiarColorBoton(robotElegido, (byte)1, (byte)0, (byte)2);
		
		else if(e.getSource() == botonRobotCuchillos)
			robotElegido = cambiarColorBoton(robotElegido, (byte)2, (byte)1, (byte)0);
	
		escogerAccion(e);
	}
	
	private void escogerAccion(ActionEvent e)
	{
		if(e.getSource() == botonIngredientes)
			accionElegida = cambiarColorBoton(accionElegida, (byte)3, (byte)4, (byte)5);
			
		else if(e.getSource() == botonCocinar)
			accionElegida = cambiarColorBoton(accionElegida, (byte)4, (byte)3, (byte)5);
		
		else if(e.getSource() == botonServir)
			accionElegida = cambiarColorBoton(accionElegida, (byte)5, (byte)4, (byte)3);
		
		if(robotElegido == -1 || accionElegida == -1)
			botonListo.setEnabled(false);
		
		else botonListo.setEnabled(true);
		
		evaluarAccion(e);
	}
	
	private void evaluarAccion(ActionEvent e)
	{
		if(e.getSource() == botonListo)
		{
			if(robotElegido == 2 && accionElegida == 3)
			{
				ultimoEstatus.setText("ÚLTIMO ESTATUS: la comida y los vegetales ya están cortados, listos para cocinar.");
				pedidoCumplido[0] = true;
			}
			
			else if(robotElegido == 0 && accionElegida == 4 && pedidoCumplido[0])
			{
				ultimoEstatus.setText("ÚLTIMO ESTATUS: la comida ya está bien cocida, lista para ser servida.");
				pedidoCumplido[1] = true;				
			}
			
			else if(robotElegido == 0 && accionElegida == 4 && !pedidoCumplido[0])
				ultimoEstatus.setText("ÚLTIMO ESTATUS: la comida no se puede cocinar si no se ha preparado.");
			
			else if(robotElegido == 1 && accionElegida == 5 && pedidoCumplido[1])
			{
				ultimoEstatus.setText("ÚLTIMO ESTATUS: ¡URRA! El plato se ha servido, el cliente manda felicitaciones.");
				pedidoCumplido[2] = true;
			}
			
			else if(robotElegido == 1 && accionElegida == 5 && !pedidoCumplido[1])
				
				ultimoEstatus.setText("ÚLTIMO ESTATUS: para servir la comida, primero se tiene que cocinar.");
				
			else ultimoEstatus.setText("ÚLTIMO ESTATUS: ¡Oh no!, parece que ese robot no es capaz de realizar esa acción.");
			
			if(pedidoCumplido[2])
				procedimiento.setText("preparación -> cocinada -> servida");
			
			else if(pedidoCumplido[1])
				procedimiento.setText("preparación -> cocinada");
			
			else if(pedidoCumplido[0])
				procedimiento.setText("preparación");
		}
	}
	
	private void crearBoton(JButton boton, int anchura, int altura) //Método para crear los botones.
	{
		boton.setBounds(anchura, altura, 120, 60);
		boton.setFocusable(false);
		boton.setFont(new Font("Times New Roman", 0, 14));
		boton.setForeground(new Color(180, 20, 220));
		boton.setBackground(new Color(200, 220, 230));
		boton.addActionListener(this);
		add(boton);	
	}
	
	private byte cambiarColorBoton(byte botonElegido, byte numeroDeBoton, byte boton2, byte boton3) //Cambia el color del botón.
	{
		if(numeroDeBoton == botonElegido) //Si el botón seleccionado, es el mismo que el seleccionado anteriormente.
		{
			botones[numeroDeBoton].setBackground(new Color(200, 220, 230)); //regresa al color anterior (se deselecciona).
			botonElegido = -1; //El -1, indicará error al querer escoger una acción por no seleccionar ningún robot.	
		}
		
		else 
		{
			botonElegido = numeroDeBoton; 
			botones[numeroDeBoton].setBackground(new Color(150, 170, 180));
			botones[boton2].setBackground(new Color(200, 220, 230));
			botones[boton3].setBackground(new Color(200, 220, 230));
		}
		
		return botonElegido;
	}
}
