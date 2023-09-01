package views;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Dao.HuespedDAO;
import Dao.ReservaDAO;
import modelo.huespedes;
import modelo.reservas;
import utils.JPAUtils;
import utils.conversionDeTipo;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.persistence.EntityManager;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTabbedPane;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.Date;
import java.util.List;

@SuppressWarnings("serial")
public class Busqueda extends JFrame {

	private JPanel contentPane;
	private JTextField txtBuscar;
	private JTable tbHuespedes;
	private JTable tbReservas;
	private DefaultTableModel modelo;
	private DefaultTableModel modeloHuesped;
	private JLabel labelAtras;
	private JLabel labelExit;
	int xMouse, yMouse;
	private List<reservas> listaReservas;
	private List<huespedes> listaHuespedes;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Busqueda frame = new Busqueda();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Busqueda() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Busqueda.class.getResource("/imagenes/lupa2.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 571);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setUndecorated(true);
		
		txtBuscar = new JTextField();
		txtBuscar.setBounds(536, 127, 193, 31);
		txtBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtBuscar);
		txtBuscar.setColumns(10);
		
		
		JLabel lblNewLabel_4 = new JLabel("SISTEMA DE BÚSQUEDA");
		lblNewLabel_4.setForeground(new Color(12, 138, 199));
		lblNewLabel_4.setFont(new Font("Roboto Black", Font.BOLD, 24));
		lblNewLabel_4.setBounds(331, 62, 280, 42);
		contentPane.add(lblNewLabel_4);
		
		JTabbedPane panel = new JTabbedPane(JTabbedPane.TOP);
		panel.setBackground(new Color(12, 138, 199));
		panel.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.setBounds(20, 169, 865, 328);
		contentPane.add(panel);

		
		
		
		tbReservas = new JTable();
		tbReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbReservas.setFont(new Font("Roboto", Font.PLAIN, 16));
		modelo = (DefaultTableModel) tbReservas.getModel();
		modelo.addColumn("Numero de Reserva");
		modelo.addColumn("Fecha De Entrada");
		modelo.addColumn("Fecha De Salida");
		modelo.addColumn("Valor");
		modelo.addColumn("Forma de Pago");
		JScrollPane scroll_table = new JScrollPane(tbReservas);
		panel.addTab("Reservas", new ImageIcon(Busqueda.class.getResource("/imagenes/reservado.png")), scroll_table, null);
		scroll_table.setVisible(true);
		
		
		
		tbHuespedes = new JTable();
		tbHuespedes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbHuespedes.setFont(new Font("Roboto", Font.PLAIN, 16));
		modeloHuesped = (DefaultTableModel) tbHuespedes.getModel();
		modeloHuesped.addColumn("Número de Huesped");
		modeloHuesped.addColumn("Nombre");
		modeloHuesped.addColumn("Apellido");
		modeloHuesped.addColumn("Fecha de Nacimiento");
		modeloHuesped.addColumn("Nacionalidad");
		modeloHuesped.addColumn("Telefono");
		modeloHuesped.addColumn("Número de Reserva");
		JScrollPane scroll_tableHuespedes = new JScrollPane(tbHuespedes);
		panel.addTab("Huéspedes", new ImageIcon(Busqueda.class.getResource("/imagenes/pessoas.png")), scroll_tableHuespedes, null);
		scroll_tableHuespedes.setVisible(true);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/Ha-100px.png")));
		lblNewLabel_2.setBounds(56, 51, 104, 107);
		contentPane.add(lblNewLabel_2);
		
		JPanel header = new JPanel();
		header.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				headerMouseDragged(e);
			     
			}
		});
		header.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				headerMousePressed(e);
			}
		});
		header.setLayout(null);
		header.setBackground(Color.WHITE);
		header.setBounds(0, 0, 910, 36);
		contentPane.add(header);
		
		JPanel btnAtras = new JPanel();
		btnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();				
			} 
			@Override
			public void mouseEntered(MouseEvent e) {
				btnAtras.setBackground(new Color(12, 138, 199));
				labelAtras.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) {
				 btnAtras.setBackground(Color.white);
			     labelAtras.setForeground(Color.black);
			}
		});
		btnAtras.setLayout(null);
		btnAtras.setBackground(Color.WHITE);
		btnAtras.setBounds(0, 0, 53, 36);
		header.add(btnAtras);
		
		labelAtras = new JLabel("<");
		labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
		labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
		labelAtras.setBounds(0, 0, 53, 36);
		btnAtras.add(labelAtras);
		
		JPanel btnexit = new JPanel();
		btnexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) { //Al usuario pasar el mouse por el botón este cambiará de color
				btnexit.setBackground(Color.red);
				labelExit.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) { //Al usuario quitar el mouse por el botón este volverá al estado original
				 btnexit.setBackground(Color.white);
			     labelExit.setForeground(Color.black);
			}
		});
		btnexit.setLayout(null);
		btnexit.setBackground(Color.WHITE);
		btnexit.setBounds(857, 0, 53, 36);
		header.add(btnexit);
		
		labelExit = new JLabel("X");
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setForeground(Color.BLACK);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);
		
		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setForeground(new Color(12, 138, 199));
		separator_1_2.setBackground(new Color(12, 138, 199));
		separator_1_2.setBounds(539, 159, 193, 2);
		contentPane.add(separator_1_2);
		
		JPanel btnRefrescar = new JPanel();
		btnRefrescar.setLayout(null);
		btnRefrescar.setBackground(new Color(12, 138, 199));
		btnRefrescar.setBounds(503, 508, 122, 35);
		btnRefrescar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnRefrescar);
		
		JLabel lblRefrescar = new JLabel("REFRESCAR");
		lblRefrescar.setHorizontalAlignment(SwingConstants.CENTER);
		lblRefrescar.setForeground(Color.WHITE);
		lblRefrescar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblRefrescar.setBounds(0, 0, 122, 35);
		btnRefrescar.add(lblRefrescar);
		
		btnRefrescar.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        actulizarTablas();
		    }
		});
		
	
		
		JPanel btnbuscar = new JPanel();
		btnbuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				 EntityManager em = JPAUtils.getEntityManager();
				HuespedDAO huespedDao =new HuespedDAO(em);
				ReservaDAO reservaDao=new ReservaDAO(em);
				conversionDeTipo con =new conversionDeTipo();
				if(scroll_table.isVisible()) {
					
					List<reservas> lista=reservaDao.buscarPorParametro(con.convertir( txtBuscar.getText()));
					
			
			        modelo.setRowCount(0);

			        for (reservas reserva : lista) {
			            Object[] rowData = {
			            		  reserva.getId(),
			                      reserva.getFechaDeEntrada(),
			                      reserva.getFechaDeSalida(),
			                      reserva.getValor(),
			                      reserva.getFormaDePago()
			            };
			            modelo.addRow(rowData);
			        }
				}
				else if(scroll_tableHuespedes.isVisible()) {
					List<huespedes> lista=huespedDao.buscarHuespedPorParametro(con.convertir( txtBuscar.getText()));
					
			    
			        modeloHuesped.setRowCount(0);

			        for (huespedes huesped : lista) {
			            Object[] rowData = {
			            		 huesped.getId(),
			                     huesped.getNombre(),
			                     huesped.getApellido(),
			                     huesped.getFechaDeNacimiento(),
			                     huesped.getNacionalidad(),
			                     huesped.getTelefeno(),
			                     huesped.getReserva().getId()
			            };
			            modeloHuesped.addRow(rowData);
			        }
				}

				
				em.close();
			}
		});
		btnbuscar.setLayout(null);
		btnbuscar.setBackground(new Color(12, 138, 199));
		btnbuscar.setBounds(748, 125, 122, 35);
		btnbuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnbuscar);
		
		JLabel lblBuscar = new JLabel("BUSCAR");
		lblBuscar.setBounds(0, 0, 122, 35);
		btnbuscar.add(lblBuscar);
		lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscar.setForeground(Color.WHITE);
		lblBuscar.setFont(new Font("Roboto", Font.PLAIN, 18));
		
		JPanel btnEditar = new JPanel();
		btnEditar.setLayout(null);
		btnEditar.setBackground(new Color(12, 138, 199));
		btnEditar.setBounds(635, 508, 122, 35);
		btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEditar);
		
		JLabel lblEditar = new JLabel("EDITAR");
		lblEditar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditar.setForeground(Color.WHITE);
		lblEditar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEditar.setBounds(0, 0, 122, 35);
		btnEditar.add(lblEditar);
		
		JPanel btnEliminar = new JPanel();
		btnEliminar.setLayout(null);
		btnEliminar.setBackground(new Color(12, 138, 199));
		btnEliminar.setBounds(767, 508, 122, 35);
		btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEliminar);
		
		JLabel lblEliminar = new JLabel("ELIMINAR");
		lblEliminar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEliminar.setForeground(Color.WHITE);
		lblEliminar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEliminar.setBounds(0, 0, 122, 35);
		btnEliminar.add(lblEliminar);
		setResizable(false);
		
		btnEliminar.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e) {
				 int reservasfila = tbReservas.getSelectedRow();
				 int huespedesfila = tbHuespedes.getSelectedRow();
			
				 EntityManager em = JPAUtils.getEntityManager();
		         HuespedDAO huespedDao = new HuespedDAO(em);
		         ReservaDAO reservaDao = new ReservaDAO(em);
		         int opcion = JOptionPane.showConfirmDialog(
				            null,
				            "¿Estas seguro de eliminar este registro?",
				            "Confirmación",
				            JOptionPane.YES_NO_OPTION
				        );
				        
				      if (opcion == JOptionPane.YES_OPTION) {
		        
			         
			         if (huespedesfila >= 0) {
				            long idReserva = (long) tbHuespedes.getValueAt(huespedesfila, 0);
				            huespedes reservaAEliminar = huespedDao.consultarPorId(idReserva);
				            System.out.println("id de la reserva = "+idReserva + " reserva = "+reservaAEliminar.getId());
				            if (reservaAEliminar != null) {
				                huespedDao.eliminar(reservaAEliminar);
				                actulizarTablas();
				            }
				        	JOptionPane.showMessageDialog(null, "Registro eliminado");
				        }else if (reservasfila>=0) {
				        	long idreserva = (long) tbReservas.getValueAt(reservasfila, 0);
				            reservas reserva = reservaDao.consultarPorId(idreserva);
				            System.out.println("id de la reserva = "+idreserva + " reserva = "+reserva.getId());
				            if (reserva != null) {
				                reservaDao.eliminarReservaYDesvincularHuesped(reserva);
				                actulizarTablas();
				            }
				        	JOptionPane.showMessageDialog(null, "Registro eliminado");
				        	
				        }
		         }else {
		        		JOptionPane.showMessageDialog(null, "No se ha elimido ningun registro");
		         }
			}
		});
		
		btnEditar.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e) {
				 int reservasfila = tbReservas.getSelectedRow();
				 int huespedesfila = tbHuespedes.getSelectedRow();
			
				 EntityManager em = JPAUtils.getEntityManager();
		         HuespedDAO huespedDao = new HuespedDAO(em);
		         ReservaDAO reservaDao = new ReservaDAO(em);
		         conversionDeTipo conversion = new conversionDeTipo();
		         int opcion = JOptionPane.showConfirmDialog(
				            null,
				            "¿Estas seguro de modificar este registro?",
				            "Confirmación",
				            JOptionPane.YES_NO_OPTION
				        );
				        
				      if (opcion == JOptionPane.YES_OPTION) {
				    	  if(reservasfila>=0) {

				    		  Long idreserva =  listaReservas.get(reservasfila).getId();
				    		  Date fechaEntrada = conversion.convertToDate(  modelo.getValueAt(reservasfila, 1));
				    		  Date fechaSalida = conversion.convertToDate(  modelo.getValueAt(reservasfila, 2));
				    		  Double valor = conversion.convertToDouble(  modelo.getValueAt(reservasfila, 3));
				    		  String formaPago = conversion.convertToString( modelo.getValueAt(reservasfila, 4));
				    		  reservas reservaModificar=reservaDao.consultarPorId(idreserva);
				    		  reservas reservaUdate =new reservas(fechaEntrada,fechaSalida,valor,formaPago);
				    		  System.out.println(reservaUdate.getFechaDeEntrada());
				    		  reservaDao.actualizar(reservaModificar,reservaUdate);
				    		  
				    	  }else if(huespedesfila>=0) {

				    		  Long idHuesped = listaHuespedes.get(huespedesfila).getId();
				    		  String nombre = conversion.convertToString(modeloHuesped.getValueAt(huespedesfila, 1));
				    		  String apellido = conversion.convertToString(modeloHuesped.getValueAt(huespedesfila, 2));
				    		  Date fechaDeNacimiento = conversion.convertToDate(modeloHuesped.getValueAt(huespedesfila, 3));
				    		  String nacionalidad = conversion.convertToString(modeloHuesped.getValueAt(huespedesfila, 4));
				    		  String telefono = conversion.convertToString(modeloHuesped.getValueAt(huespedesfila, 5));
				    		  long reserva_id=conversion.convertToLong( modeloHuesped.getValueAt(huespedesfila, 6));
				    		  reservas reserva = reservaDao.consultarPorId(reserva_id);

				    		  huespedes huespedModificar = huespedDao.consultarPorId(idHuesped);
				    		  huespedes huespedUpdate = new huespedes(nombre, apellido, fechaDeNacimiento, nacionalidad, telefono,reserva);
				    		  huespedDao.actualizarHuesped(huespedModificar, huespedUpdate);
				    		  
				    		  
				    	  }
				    	 
				    		
				        	JOptionPane.showMessageDialog(null, "Registro modificado");
				      
		         }else {
		        		JOptionPane.showMessageDialog(null, "No se ha modificado ningun registro");
		         }
				      actulizarTablas();
			}
		});
		
		actulizarTablas();
		

        
        

	}
	
	private void actulizarTablas() {
	    EntityManager em = JPAUtils.getEntityManager();
	    HuespedDAO huespedDao = new HuespedDAO(em);
	    ReservaDAO reservaDao = new ReservaDAO(em);

	    // Obtener datos de las reservas
	    listaReservas = reservaDao.consultagenera(txtBuscar.getText());

	    // Limpiar la tabla antes de agregar nuevos datos
	    modelo.setRowCount(0);

	    for (reservas reserva : listaReservas) {
	        Object[] rowData = {
	            reserva.getId(),
	            reserva.getFechaDeEntrada(),
	            reserva.getFechaDeSalida(),
	            reserva.getValor(),
	            reserva.getFormaDePago()
	        };
	        modelo.addRow(rowData);
	    }

	    // Obtener datos de los huéspedes
	    listaHuespedes = huespedDao.consultagenera(txtBuscar.getText());

	    // Limpiar la tabla antes de agregar nuevos datos
	    modeloHuesped.setRowCount(0);

	    for (huespedes huesped : listaHuespedes) {
	        Object[] rowData = {
	            huesped.getId(),
	            huesped.getNombre(),
	            huesped.getApellido(),
	            huesped.getFechaDeNacimiento(),
	            huesped.getNacionalidad(),
	            huesped.getTelefeno(),
	            huesped.getReserva() != null ? huesped.getReserva().getId() : "Sin reserva"
	        };
	        modeloHuesped.addRow(rowData);
	    }
	}
	
//Código que permite mover la ventana por la pantalla según la posición de "x" y "y"
	 private void headerMousePressed(java.awt.event.MouseEvent evt) {
	        xMouse = evt.getX();
	        yMouse = evt.getY();
	    }

	    private void headerMouseDragged(java.awt.event.MouseEvent evt) {
	        int x = evt.getXOnScreen();
	        int y = evt.getYOnScreen();
	        this.setLocation(x - xMouse, y - yMouse);
}
	    
	    
	    
}
