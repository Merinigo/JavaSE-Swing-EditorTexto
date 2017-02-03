package com.merinigo;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class TextEditorMain{

	private JFrame frame;
	private JMenuBar jmbarBarraDeMenus;
	private JMenu jmnuArchivo;
	private JToolBar jtbarBarraDeHerr;
	private JMenuItem jmItemSalir;
	private JButton jbtbarAbrir;
	private JButton jbtbarGuardar;
	private JPanel jBarraDeEstado;
	private JLabel jetbarestPpal;
	private JScrollPane jscrpaneEditor;
	private JTextArea jtxtaEditor;
	private JMenu jmnuEdicion;
	private JMenu jmnuOpciones;
	private JMenuItem jmItemCopiar;
	private JMenuItem jmItemPegar;
	private JMenuItem jmItemCortar;
	private JMenu jmnuFuente;
	private JMenuItem jmItemArial;
	private JMenuItem jmItemFPredeterminada;
	private JMenuItem jmItemCourierNew;
	private JMenu jmnuTamaño;
	private JMenuItem jmItem24;
	private JMenuItem jmItemTPredeterminado;
	private JMenuItem jmItem16;
	private JButton jbtbarCortar;
	private JButton jbtbarCopiar;
	private JButton jbtnbarPegar;
	private JSeparator separator;
	private JMenuItem jmItemGuardar;
	private JMenuItem jmItemAbrir;

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
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				formWindowOpened(e);
			}
		});
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
		jtbarBarraDeHerr.setFloatable(false);
		jtbarBarraDeHerr.setBounds(0, 0, 484, 20);
		frame.getContentPane().add(jtbarBarraDeHerr);
		
		jbtbarAbrir = new JButton("");		
		jbtbarAbrir.addChangeListener(toolBarItemChangeListener);
		jbtbarAbrir.setFocusable(false);
		jbtbarAbrir.setFocusPainted(false);
		jbtbarAbrir.setMargin(new Insets(0, 0, 0, 0));
		jbtbarAbrir.setToolTipText("Abrir");
		jbtbarAbrir.setIcon(new ImageIcon(TextEditorMain.class.getResource("/icons/Open.png")));
		jtbarBarraDeHerr.add(jbtbarAbrir);
		
		jbtbarGuardar = new JButton("");
		jbtbarGuardar.addChangeListener(toolBarItemChangeListener);
		jbtbarGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		jbtbarGuardar.setFocusable(false);
		jbtbarGuardar.setFocusPainted(false);
		jbtbarGuardar.setToolTipText("Guardar");
		jbtbarGuardar.setMargin(new Insets(0, 0, 0, 0));
		jbtbarGuardar.setIcon(new ImageIcon(TextEditorMain.class.getResource("/icons/Save.png")));
		jtbarBarraDeHerr.add(jbtbarGuardar);
		
		separator = new JSeparator();
		separator.setMaximumSize(new Dimension(5, 32767));
		separator.setOrientation(SwingConstants.VERTICAL);
		jtbarBarraDeHerr.add(separator);
		
		jbtbarCortar = new JButton("");
		jbtbarCortar.addChangeListener(toolBarItemChangeListener);
		jbtbarCortar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		jbtbarCortar.setIcon(new ImageIcon(TextEditorMain.class.getResource("/icons/Cut.png")));
		jbtbarCortar.setToolTipText("Cortar");
		jbtbarCortar.setMargin(new Insets(0, 0, 0, 0));
		jbtbarCortar.setFocusable(false);
		jbtbarCortar.setFocusPainted(false);
		jtbarBarraDeHerr.add(jbtbarCortar);
		
		jbtbarCopiar = new JButton("");
		jbtbarCopiar.addChangeListener(toolBarItemChangeListener);
		jbtbarCopiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		jbtbarCopiar.setIcon(new ImageIcon(TextEditorMain.class.getResource("/icons/Copy.png")));
		jbtbarCopiar.setToolTipText("Copiar");
		jbtbarCopiar.setMargin(new Insets(0, 0, 0, 0));
		jbtbarCopiar.setFocusable(false);
		jbtbarCopiar.setFocusPainted(false);
		jtbarBarraDeHerr.add(jbtbarCopiar);
		
		jbtnbarPegar = new JButton("");
		jbtnbarPegar.addChangeListener(toolBarItemChangeListener);
		jbtnbarPegar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		jbtnbarPegar.setIcon(new ImageIcon(TextEditorMain.class.getResource("/icons/Paste.png")));
		jbtnbarPegar.setToolTipText("Pegar");
		jbtnbarPegar.setMargin(new Insets(0, 0, 0, 0));
		jbtnbarPegar.setFocusable(false);
		jbtnbarPegar.setFocusPainted(false);
		jtbarBarraDeHerr.add(jbtnbarPegar);
		
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
		
		jscrpaneEditor = new JScrollPane();
		jtxtaEditor = new JTextArea();		
//		jtxtaEditor.setLineWrap(true);
//		jtxtaEditor.setWrapStyleWord(true);
		jscrpaneEditor.setViewportView(jtxtaEditor);
		jscrpaneEditor.setBounds(0, 20, 484, 198);
		frame.getContentPane().add(jscrpaneEditor);
		
		jmbarBarraDeMenus = new JMenuBar();
		frame.setJMenuBar(jmbarBarraDeMenus);
		
		jmnuArchivo = new JMenu("Archivo");
		jmnuArchivo.setMnemonic('A');
		jmbarBarraDeMenus.add(jmnuArchivo);
		
		jmItemSalir = new JMenuItem("Salir");
		jmItemSalir.addChangeListener(menuItemChangeListener);	
		jmItemSalir.setMnemonic('S');
		jmItemSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				jmItemSalirActionPerformed(evt);
			}
		});
		
		jmItemAbrir = new JMenuItem("Abrir");
		jmnuArchivo.add(jmItemAbrir);
		
		jmItemGuardar = new JMenuItem("Guardar");
		jmItemGuardar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		jmnuArchivo.add(jmItemGuardar);
		jmnuArchivo.add(jmItemSalir);
		
		jmnuEdicion = new JMenu("Edicion");
		jmbarBarraDeMenus.add(jmnuEdicion);
		
		jmItemCortar = new JMenuItem("Cortar");
		jmItemCortar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_MASK));
		jmnuEdicion.add(jmItemCortar);
		
		jmItemCopiar = new JMenuItem("Copiar");
		jmItemCopiar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));
		jmnuEdicion.add(jmItemCopiar);
		
		jmItemPegar = new JMenuItem("Pegar");
		jmItemPegar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_MASK));
		jmItemPegar.setMnemonic('P');
		jmnuEdicion.add(jmItemPegar);
		
		jmnuOpciones = new JMenu("Opciones");
		jmbarBarraDeMenus.add(jmnuOpciones);
		
		jmnuFuente = new JMenu("Fuente");
		jmnuFuente.setMnemonic('F');
		jmnuOpciones.add(jmnuFuente);
		
		jmItemCourierNew = new JMenuItem("Courier New");
		jmnuFuente.add(jmItemCourierNew);
		
		jmItemArial = new JMenuItem("Arial");
		jmnuFuente.add(jmItemArial);
		
		jmItemFPredeterminada = new JMenuItem("Predeterminada");
		jmnuFuente.add(jmItemFPredeterminada);
		
		jmnuTamaño = new JMenu("Tama\u00F1o");
		jmnuTamaño.setMnemonic('T');
		jmnuOpciones.add(jmnuTamaño);
		
		jmItem16 = new JMenuItem("16");
		jmnuTamaño.add(jmItem16);
		
		jmItem24 = new JMenuItem("24");
		jmnuTamaño.add(jmItem24);
		
		jmItemTPredeterminado = new JMenuItem("Predeterminado");
		jmnuTamaño.add(jmItemTPredeterminado);
	}
	
	//----------COMMON LISTENERS
	
	ChangeListener menuItemChangeListener = new ChangeListener() {		
		@Override
		public void stateChanged(ChangeEvent e) {
			Object eventItem = e.getSource();
			
			if(eventItem == jmItemSalir){
				jmItemSalirStateChanged(e);
			}			
		}
	};	
	
	ChangeListener toolBarItemChangeListener = new ChangeListener() {		
		@Override
		public void stateChanged(ChangeEvent e) {
			Object eventItem = e.getSource();
			
			if(eventItem == jbtbarAbrir){				
				jmItemAbrirStateChanged(e);
			}
			if(eventItem == jbtbarGuardar){
				jmItemGuardarStateChanged(e);
			}			
			if(eventItem == jbtbarCortar){
				jmItemCortarStateChanged(e);
			}			
			if(eventItem == jbtbarCopiar){
				jmItemCopiarStateChanged(e);
			}			
			if(eventItem == jbtnbarPegar){
				jmItemPegarStateChanged(e);
			}
		}
	};	



	//*****************************************EVENT HANDLERS**********
	//*****************************************************************
	
	//Menu Archivo Salir
	private void jmItemSalirActionPerformed(ActionEvent evt){
		System.exit(0);
	}
	
	//Cambio de tamaño de ventana, cambiamos tamaño y posicion de barra de estado
	private void formComponentResized(ComponentEvent evt){		
		jBarraDeEstado.setBounds(0, frame.getSize().height-83, frame.getSize().width-14, 24);
		jscrpaneEditor.setSize(new Dimension(frame.getSize().width-15, frame.getSize().height-101));
	}
	
	//Al abrir la ventana poner el foco en el area de texto
	private void formWindowOpened(WindowEvent evt){
		jtxtaEditor.requestFocus();
	}	
	
	//**************************LISTERNERs cambio estado items menu
	private void jmItemSalirStateChanged(ChangeEvent evt){		
		if(jetbarestPpal.getText().equals("Listo")){			
			jetbarestPpal.setText("Cierra la aplicacion");
		}else{			
			jetbarestPpal.setText("Listo");
		}
	}
	
	//**************************LISTENERs cambio estado items toolbar
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
	
	private void jmItemCortarStateChanged(ChangeEvent evt){		
		if(jetbarestPpal.getText().equals("Listo")){			
			jetbarestPpal.setText("Copia y elimina el texto seleccionado");
		}else{			
			jetbarestPpal.setText("Listo");
		}
	}
	
	private void jmItemCopiarStateChanged(ChangeEvent evt){		
		if(jetbarestPpal.getText().equals("Listo")){			
			jetbarestPpal.setText("Copia el texto seleccionado");
		}else{			
			jetbarestPpal.setText("Listo");
		}
	}
	
	private void jmItemPegarStateChanged(ChangeEvent evt){		
		if(jetbarestPpal.getText().equals("Listo")){			
			jetbarestPpal.setText("Pega el texto del portapapeles");
		}else{			
			jetbarestPpal.setText("Listo");
		}		
	}

	
}
