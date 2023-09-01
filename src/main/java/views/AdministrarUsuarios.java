package views;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import modelo.usuarios;
import Dao.UsuarioDAO;
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
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class AdministrarUsuarios extends JFrame {

	private JPanel contentPane;
	private JTextField txtBuscar;
	private JTable usuarios;
	private DefaultTableModel modelo;
	private JLabel labelAtras;
	private JLabel labelExit;
	int xMouse, yMouse;
	private List<usuarios> listaActual = new ArrayList<>();


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdministrarUsuarios frame = new AdministrarUsuarios();
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
	public AdministrarUsuarios() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(AdministrarUsuarios.class.getResource("/imagenes/lupa2.png")));
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
		
		
		JLabel lblNewLabel_4 = new JLabel("SISTEMA DE USUARIOS");
		lblNewLabel_4.setForeground(new Color(12, 138, 199));
		lblNewLabel_4.setFont(new Font("Roboto Black", Font.BOLD, 24));
		lblNewLabel_4.setBounds(331, 62, 280, 42);
		contentPane.add(lblNewLabel_4);
		
		JTabbedPane panel = new JTabbedPane(JTabbedPane.TOP);
		panel.setBackground(new Color(12, 138, 199));
		panel.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.setBounds(20, 169, 865, 328);
		contentPane.add(panel);

		
		
		
		usuarios = new JTable();
		usuarios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		usuarios.setFont(new Font("Roboto", Font.PLAIN, 16));
		modelo = (DefaultTableModel) usuarios.getModel();
		modelo.addColumn("Usuario");
		modelo.addColumn("Contraseña");
		JScrollPane scroll_table = new JScrollPane(usuarios);
		panel.addTab("Usuarios", new ImageIcon(AdministrarUsuarios.class.getResource("/imagenes/reservado.png")), scroll_table, null);
		scroll_table.setVisible(true);
		
		
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(AdministrarUsuarios.class.getResource("/imagenes/Ha-100px.png")));
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
		
	
		JPanel btnNuevoUsuario = new JPanel();
		btnNuevoUsuario.setLayout(null);
		btnNuevoUsuario.setBackground(new Color(12, 138, 199));
		btnNuevoUsuario.setBounds(335, 508, 160, 35);
		btnNuevoUsuario.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnNuevoUsuario);

		JLabel lblNuevoUsuario = new JLabel("NUEVO USUARIO");
		lblNuevoUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblNuevoUsuario.setForeground(Color.WHITE);
		lblNuevoUsuario.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblNuevoUsuario.setBounds(0, 0, 160, 35);
		btnNuevoUsuario.add(lblNuevoUsuario);

		btnNuevoUsuario.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		       nuevoUsuario usuario=new nuevoUsuario();
		       usuario.setVisible(true);
				dispose();
		    }
		});
	
		
		JPanel btnbuscar = new JPanel();
		btnbuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				 EntityManager em = JPAUtils.getEntityManager();
				 UsuarioDAO usuariodao = new UsuarioDAO(em);
				
				if(scroll_table.isVisible()) {
					List<usuarios> lista = usuariodao.buscarPorParametro( txtBuscar.getText());
					
					 modelo.setRowCount(0);

				        for (usuarios usuario : lista) {
				            Object[] rowData = {	
				                      usuario.getUser(),
				                      usuario.getPass()
				            };
				            modelo.addRow(rowData);
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
				 int reservasfila = usuarios.getSelectedRow();
			
				 EntityManager em = JPAUtils.getEntityManager();
		          UsuarioDAO usuriodao =new UsuarioDAO(em);
		         int opcion = JOptionPane.showConfirmDialog(
				            null,
				            "¿Estas seguro de eliminar este registro?",
				            "Confirmación",
				            JOptionPane.YES_NO_OPTION
				        );
				        
				      if (opcion == JOptionPane.YES_OPTION) {
				    	  if(reservasfila>=0) {
				    		  String usuario=  (String) usuarios.getValueAt(reservasfila, 0);
				    		  usuarios usuarioEliminar = usuriodao.consultarPorUsuario(usuario);
				    		  if(usuarioEliminar!=null) {
				    			  usuriodao.remover(usuarioEliminar);
				    			  actulizarTablas();
				    		  }
				    	  }
				            
				        	JOptionPane.showMessageDialog(null, "Registro eliminado");

		         }else {
		        		JOptionPane.showMessageDialog(null, "No se ha elimido ningun registro");
		         }
				      em.close();
			}
		});
		
		btnEditar.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e) {
				 int reservasfila = usuarios.getSelectedRow();
				 EntityManager em = JPAUtils.getEntityManager();
				 UsuarioDAO usuariodao = new UsuarioDAO(em);
		         conversionDeTipo conversion = new conversionDeTipo();
		         int opcion = JOptionPane.showConfirmDialog(
				            null,
				            "¿Estas seguro de modificar este registro?",
				            "Confirmación",
				            JOptionPane.YES_NO_OPTION
				        );
				        
				      if (opcion == JOptionPane.YES_OPTION) {
				    	  if(reservasfila>=0) {

				    		 
				    		  String usuario = conversion.convertToString( listaActual.get(reservasfila).getUser());
				    		  String pass = conversion.convertToString(  modelo.getValueAt(reservasfila, 1));
				    		 
				    		  usuarios usuarioModificar=usuariodao.consultarPorUsuario(usuario);
				    		  usuarios usuarioUdate =new usuarios(usuario,pass);
				   
				    		  usuariodao.actualizar(usuarioModificar,usuarioUdate);
  
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
		UsuarioDAO usuariodao = new UsuarioDAO(em);
		listaActual=usuariodao.consultagenera(txtBuscar.getText());
		
        // Limpiar tabla antes de agregar nuevos datos
        modelo.setRowCount(0);
        

        for (usuarios usuario : listaActual) {
            Object[] rowData = {
                usuario.getUser(),
                usuario.getPass(),
              
              
            };
            modelo.addRow(rowData);
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
