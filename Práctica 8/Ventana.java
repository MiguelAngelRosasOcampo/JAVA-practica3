import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Ventana extends JFrame //Clase principal. 
{
	private ComponentesVentana panel1 = new ComponentesVentana();

	public Ventana()
	{
		setTitle("Nómina");
		setSize(800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		add(panel1);
	}
	
	private class ComponentesVentana extends JPanel implements ActionListener //Clase anidada.
	{
		private String empleadoNormal = "Empleado asalariado, tiene un sueldo base semanal.";
		private JLabel labelInformacionEmpleado = new JLabel(empleadoNormal);
		private JLabel labelTipoEmpleado = new JLabel("Elige el tipo de empleado: ");
		private JLabel labelSalario = new JLabel("Establece el sueldo: ");
		private JLabel labelPorcentaje = new JLabel("Ingrese porcentaje adicional: ");
		private JLabel labelComision = new JLabel("Ingrese porcentaje de comisión: ");
		private JLabel labelVentas = new JLabel("Ingrese las ganancias en ventas: ");
		private JLabel labelHorasExtras = new JLabel("Ingrese cantidad de horas extras: ");
		private JLabel labelRecompensa = new JLabel("Porcentaje de bonificación");
		private JLabel labelMensaje = new JLabel("");
		private JComboBox<String> listaEmpleados = new JComboBox<String>();
		private JTextField ingresoSalario = new JTextField();
		private JTextField ingresoHoras = new JTextField();
		private JTextField ingresoComision = new JTextField();
		private JTextField ingreseVentas = new JTextField();
		private JTextField recompensa = new JTextField();
		private JButton botonCalcular = new JButton("Calcular");
		
		public ComponentesVentana() 
		{
			setLayout(null);
			setBackground(new Color(180, 180, 160));
			
			
			labelInformacionEmpleado.setBounds(200, 10, 500, 20);
			labelInformacionEmpleado.setBackground(new Color(180, 180, 160));
			labelInformacionEmpleado.setFont(new Font("Arial", 3, 15));
			labelInformacionEmpleado.setForeground(new Color(90, 80, 80));
			add(labelInformacionEmpleado);
			
			labelTipoEmpleado.setBounds(50, 50, 200, 30);
			labelTipoEmpleado.setFont(new Font("Arial", 3, 15));	
			add(labelTipoEmpleado);
			
			listaEmpleados.setBounds(300, 50, 300, 30);
			listaEmpleados.setFont(new Font("Arial", 3, 15));
			listaEmpleados.addItem("Empleados asalariados");
			listaEmpleados.addItem("Empleados por horas");
			listaEmpleados.addItem("Empleados por comisión");
			listaEmpleados.addItem("Empleados asalariados por comisión");
			listaEmpleados.addActionListener(this);
			add(listaEmpleados);
			
			labelSalario.setBounds(50, 200, 250, 30);
			labelSalario.setFont(new Font("Arial", 3, 15));
			add(labelSalario);
			
			ingresoSalario.setBounds(300, 200, 250, 30);
			ingresoSalario.setFont(new Font("Arial", 3, 15));
			add(ingresoSalario);			
			
			labelRecompensa.setBounds(570, 440, 160, 30);
			add(labelRecompensa);
			
			labelMensaje.setBounds(10, 100, 800, 30);
			labelMensaje.setFont(new Font("Arial", 0, 16));
			add(labelMensaje);
			
			recompensa.setBounds(600, 400, 100, 30);
			recompensa.setFont(new Font("Arial", 3, 15));
			add(recompensa);
			
			botonCalcular.setFocusable(false);
			botonCalcular.setBounds(600, 500, 100, 40);
			botonCalcular.addActionListener(this);
			add(botonCalcular);
			
			labelHorasExtras.setBounds(50, 300, 250, 30);
			labelHorasExtras.setFont(new Font("Arial", 3, 15));
			labelHorasExtras.setVisible(false);
			add(labelHorasExtras);
			
			ingresoHoras.setBounds(300, 300, 250, 30);
			ingresoHoras.setFont(new Font("Arial", 3, 15));
			ingresoHoras.setVisible(false);
			add(ingresoHoras);
			
			labelComision.setBounds(50, 200, 250, 30);
			labelComision.setFont(new Font("Arial", 3, 15));
			labelComision.setVisible(false);
			add(labelComision);
			
			ingresoComision.setBounds(300, 200, 250, 30);
			ingresoComision.setFont(new Font("Arial", 3, 15));
			ingresoComision.setVisible(false);
			add(ingresoComision);
			
			labelVentas.setBounds(50, 300, 250, 30);
			labelVentas.setFont(new Font("Arial", 3, 15));
			labelVentas.setVisible(false);
			add(labelVentas);
			
			ingreseVentas.setBounds(300, 300, 250, 30);
			ingreseVentas.setFont(new Font("Arial", 3, 15));
			ingreseVentas.setVisible(false);
			add(ingreseVentas);
		}
		
		@Override
		public void actionPerformed(ActionEvent e) 
		{					
			String seleccion = listaEmpleados.getSelectedItem().toString();

			if(e.getSource() == botonCalcular && seleccion.equals("Empleados asalariados"))
			{
				double salario = Double.parseDouble(ingresoSalario.getText());
				
				double bonificacion = Double.parseDouble(recompensa.getText());
				
				salario += (salario * bonificacion) / 100;
				
				if(bonificacion == 0)
					labelMensaje.setText("            Sin bonificación, sólo ganas el sueldo neto.");
				
				else labelMensaje.setText("            Lo que ganarás con un " + bonificacion + "% a tu sueldo es $" + salario);
			}
			
			else if(e.getSource() == botonCalcular && seleccion.equals("Empleados por horas"))
			{
				double salario = Double.parseDouble(ingresoSalario.getText());
				
				double bonificacion = Double.parseDouble(recompensa.getText());
				
				int horas = Integer.parseInt(ingresoHoras.getText());
				
				double horasExtras = horas * salario * 2;
				
				salario = (salario * bonificacion) / 100;
				salario += horasExtras;
				
				if(horas == 0 && bonificacion == 0)
					labelMensaje.setText("Sin horas extras, ni bonificación, sólo ganas tu sueldo neto.");
				
				else if(horas == 0 && bonificacion != 0)
					labelMensaje.setText("El " + bonificacion + "% de tu salario es $" + salario);
				
				else if(horas != 0 && bonificacion == 0)
					labelMensaje.setText("Las horas extras se pagan doble, con " + horas + " horas extras, ganas $" + horasExtras);
				
				else if(horas != 0 && bonificacion != 0)
					labelMensaje.setText("Con horas extras ganas doble, por " + horas + " horas extras, ganas $" + horasExtras
							+ " más " + bonificacion + "% del salario ganarás $" + salario);
			}
			
			else if(e.getSource() == botonCalcular && seleccion.equals("Empleados por comisión"))
			{
				double porcentaje = Double.parseDouble(ingresoComision.getText());
				double ventas = Double.parseDouble(ingreseVentas.getText());
				double bonificacion = Double.parseDouble(recompensa.getText());
				double ganancia, total = 0;
				
				ganancia = (ventas * porcentaje) / 100;
				total = (ganancia * bonificacion) / 100; 
			
				if(ventas == 0 )
					labelMensaje.setText("No se gana nada porque no hubo ventas.");
				
				else if(ventas != 0 && porcentaje == 0)
					labelMensaje.setText("¡OMG! te han robado.");
				
				else if(ventas != 0 && porcentaje != 0 && bonificacion == 0)
					labelMensaje.setText("Ganas el " + porcentaje + "% de " + ventas + ", o sea $" + ganancia);
				
				else if(ventas != 0 && porcentaje != 0 && bonificacion != 0)
					labelMensaje.setText("Ganas el " + porcentaje + "% de " + ventas + ", o sea $" + ganancia +
							" más el " + bonificacion + "% de esos $" + ganancia + " en total ganas $" + (ganancia+total));
				
			}
			
			else if(e.getSource() == botonCalcular && seleccion.equals("Empleados asalariados por comisión"))
			{
				double porcentaje = Double.parseDouble(ingresoComision.getText());
				double ventas = Double.parseDouble(ingreseVentas.getText());
				double bonificacion = Double.parseDouble(recompensa.getText());
				double sueldo = Double.parseDouble(ingresoSalario.getText());
				double ganancia, total = 0;
				
				ganancia = (ventas * porcentaje) / 100;
				total = (sueldo * bonificacion) / 100; 
			
				if(sueldo == 0)
					labelMensaje.setText("Al parecer no tienes sueldo.");
				
				else if(ventas == 0 && sueldo !=0)
					labelMensaje.setText("Ganas tu sueldo neto, o sea $" + sueldo);
				
				else if(ventas != 0 && porcentaje == 0)
					labelMensaje.setText("Ganas tu sueldo neto, o sea $" + sueldo);
				
				else if(ventas != 0 && porcentaje != 0 && bonificacion == 0)
					labelMensaje.setText("Ganas el " + porcentaje + "% de " + ventas + ", ($" + ganancia + ") más tu sueldo "
							+ "(" + sueldo + ") en total ganarás $" + (ganancia + sueldo));
				
				else if(ventas != 0 && porcentaje != 0 && bonificacion != 0)
					labelMensaje.setText("Ganas el " + porcentaje + "% de " + ventas + ", ($" + ganancia + ") más sueldo "
							+ "(" + sueldo + "), más " + bonificacion + "% de sueldo (" + total + "), "
									+ "en total ganas $" + (ganancia + sueldo + total));
				
			}
			
			if(e.getSource() == listaEmpleados)
			{	
				
				if(seleccion.equals("Empleados asalariados"))
				{
					labelInformacionEmpleado.setText(empleadoNormal);
					labelSalario.setText("Establece el sueldo: ");
					labelSalario.setBounds(50, 200, 250, 30);
					labelSalario.setVisible(true);
					ingresoSalario.setBounds(300, 200, 250, 30);
					ingresoSalario.setVisible(true);
					labelHorasExtras.setVisible(false);
					ingresoHoras.setVisible(false);
					ingreseVentas.setVisible(false);
					labelVentas.setVisible(false);
					ingresoComision.setVisible(false);
					labelComision.setVisible(false);
					labelComision.setVisible(false);

				}			
				
				else if(seleccion.equals("Empleados por horas"))
				{
					labelInformacionEmpleado.setText("Empleado por hora, tiempo extra después de 40 horas.");
					labelSalario.setText("Establece el salario (pesos/hora)");
					labelSalario.setBounds(50, 200, 250, 30);
					labelSalario.setVisible(true);
					ingresoSalario.setBounds(300, 200, 250, 30);
					ingresoSalario.setVisible(true);
					labelHorasExtras.setVisible(true);
					ingresoHoras.setVisible(true);
					ingreseVentas.setVisible(false);
					labelVentas.setVisible(false);
					ingresoComision.setVisible(false);
					labelComision.setVisible(false);
				}
				
				else if(seleccion.equals("Empleados por comisión"))
				{
					labelInformacionEmpleado.setText("Empleado por comisión, recibe porcentaje por sus ventas.");
					labelSalario.setVisible(false);
					ingresoSalario.setVisible(false);
					labelHorasExtras.setVisible(false);
					ingresoHoras.setVisible(false);
					ingreseVentas.setVisible(true);
					labelVentas.setVisible(true);
					ingresoComision.setVisible(true);
					labelComision.setVisible(true);
				}
				
				else
				{
					labelInformacionEmpleado.setText("Empleado asalariado por comisión, sueldo base más comisiones.");
					labelSalario.setBounds(50, 400, 250, 30);
					labelSalario.setVisible(true);
					ingresoSalario.setBounds(300, 400, 250, 30);
					ingresoSalario.setVisible(true);
					labelHorasExtras.setVisible(false);
					ingresoHoras.setVisible(false);
					ingreseVentas.setVisible(true);
					labelVentas.setVisible(true);
					ingresoComision.setVisible(true);
					labelComision.setVisible(true);

				}
			}
		}
	}
}

