package com.merinigo;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;
import javax.swing.UIManager;
import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Insets;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.JLabel;
import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class TextEditorMain {

	private JFrame frame;
	private JMenuBar jmbarBarraDeMenus;
	private JMenu jmnuArchivo;
	private JMenuItem jmItemAbrir;
	private JMenu jmnuGuardar;
	private JToolBar jtbarBarraDeHerr;
	private JMenuItem jmItemSalir;
	private JMenuItem jmItemMismoNombre;
	private JMenuItem jmItemOtroNombre;
	private JButton jbtbarAbrir;
	private JButton jbtbarGuardar;
	private JPanel jBarraDeEstado;
	private JLabel jetbarestPpal;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TextEditorMain window = new TextEditorMain();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TextEditorMain() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setMinimumSize(new Dimension(200, 200));
		frame.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				formComponentResized(e);
			}
		});
		frame.setBounds(100, 100, 500, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		jtbarBarraDeHerr = new JToolBar();
		jtbarBarraDeHerr.setBounds(0, 0, 484, 20);
		frame.getContentPane().add(jtbarBarraDeHerr);
		
		jbtbarAbrir = new JButton("");
		jbtbarAbrir.setFocusable(false);
		jbtbarAbrir.setFocusPainted(false);
		jbtbarAbrir.setMargin(new Insets(2, 2, 2, 0));
		jbtbarAbrir.setToolTipText("Abrir");
		jbtbarAbrir.setIcon(new ImageIcon(TextEditorMain.class.getResource("/icons/Abrir.png")));
		jtbarBarraDeHerr.add(jbtbarAbrir);
		
		jbtbarGuardar = new JButton("");
		jbtbarGuardar.setFocusable(false);
		jbtbarGuardar.setFocusPainted(false);
		jbtbarGuardar.setToolTipText("Guardar");
		jbtbarGuardar.setMargin(new Insets(2, 2, 2, 0));
		jbtbarGuardar.setIcon(new ImageIcon(TextEditorMain.class.getResource("/icons/Guardar.png")));
		jtbarBarraDeHerr.add(jbtbarGuardar);
		
		jBarraDeEstado = new JPanel();
		FlowLayout fl_jBarraDeEstado = (FlowLayout) jBarraDeEstado.getLayout();
		fl_jBarraDeEstado.setVgap(0);
		fl_jBarraDeEstado.setAlignment(FlowLayout.LEFT);
		jBarraDeEstado.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		jBarraDeEstado.setBounds(0, 216, 485, 25);
		frame.getContentPane().add(jBarraDeEstado);
		
		jetbarestPpal = new JLabel("");
		jetbarestPpal.setPreferredSize(new Dimension(470, 20));
		jBarraDeEstado.add(jetbarestPpal);
		
		jmbarBarraDeMenus = new JMenuBar();
		frame.setJMenuBar(jmbarBarraDeMenus);
		
		jmnuArchivo = new JMenu("Archivo");
		jmnuArchivo.setMnemonic('A');
		jmbarBarraDeMenus.add(jmnuArchivo);
		
		jmItemAbrir = new JMenuItem("Abrir...");
		jmItemAbrir.addChangeListener(menuItemChangeListener);
		jmItemAbrir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK));
		jmItemAbrir.setMnemonic('A');
		jmnuArchivo.add(jmItemAbrir);
		
		jmnuGuardar = new JMenu("Guardar");
		jmnuGuardar.addChangeListener(menuItemChangeListener);
		jmnuGuardar.setMnemonic('G');
		jmnuArchivo.add(jmnuGuardar);
		
		jmItemMismoNombre = new JMenuItem("Mismo nombre");
		jmnuGuardar.add(jmItemMismoNombre);
		
		jmItemOtroNombre = new JMenuItem("Otro nombre");
		jmnuGuardar.add(jmItemOtroNombre);
		
		JSeparator jSeparador1 = new JSeparator();
		jmnuArchivo.add(jSeparador1);
		
		jmItemSalir = new JMenuItem("Salir");
		jmItemSalir.addChangeListener(menuItemChangeListener);	
		jmItemSalir.setMnemonic('S');
		jmItemSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				jmItemSalirActionPerformed(evt);
			}
		});
		jmnuArchivo.add(jmItemSalir);
	}
	
	//----------COMMON LISTENERS
	
	ChangeListener menuItemChangeListener = new ChangeListener() {		
		@Override
		public void stateChanged(ChangeEvent e) {
			Object eventItem = e.getSource();
			if(eventItem == jmItemAbrir || eventItem == jbtbarAbrir){
				jmItemAbrirStateChanged(e);
			}else if(eventItem == jmnuGuardar || eventItem == jbtbarGuardar){
				jmItemGuardarStateChanged(e);
			}else if(eventItem == jmItemSalir){
				jmItemSalirStateChanged(e);
			}			
		}
	};
	
	
	
	
	
	
	//-----------EVENT HANDLERS
	
	//Menu Archivo Salir
	private void jmItemSalirActionPerformed(ActionEvent evt){
		System.exit(0);
	}
	
	//Cambio de tamaño de ventana, cambiamos tamaño y posicion de barra de estado
	private void formComponentResized(ComponentEvent evt){
		jBarraDeEstado.setBounds(0, frame.getSize().height-83, frame.getSize().width-15, 24);
	}
	
	//Cambios en la barra de estado al cuando cambia el estado del menu salir
	private void jmItemSalirStateChanged(ChangeEvent evt){		
		if(jetbarestPpal.getText().equals("Listo")){			
			jetbarestPpal.setText("Cierra la aplicacion");
		}else{			
			jetbarestPpal.setText("Listo");
		}
	}
	
	private void jmItemAbrirStateChanged(ChangeEvent evt){		
		if(jetbarestPpal.getText().equals("Listo")){			
			jetbarestPpal.setText("Abrir un fichero");
		}else{			
			jetbarestPpal.setText("Listo");
		}
	}
	
	private void jmItemGuardarStateChanged(ChangeEvent evt){		
		if(jetbarestPpal.getText().equals("Listo")){			
			jetbarestPpal.setText("Guardar un fichero");
		}else{			
			jetbarestPpal.setText("Listo");
		}
	}
	
}
