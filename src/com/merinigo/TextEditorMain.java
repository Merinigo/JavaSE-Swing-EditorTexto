package com.merinigo;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
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

	private Font defaultFont;
	
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
	private JMenuItem jmItemFuentePredeterminada;
	private JMenuItem jmItemCourierNew;
	private JMenu jmnuTamaño;
	private JMenuItem jmItem24;
	private JMenuItem jmItemTamañoPredeterminado;
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
		jbtbarAbrir.addActionListener(menuItemActionListener);
		jbtbarAbrir.addChangeListener(menuItemChangeListener);
		jbtbarAbrir.setFocusable(false);
		jbtbarAbrir.setFocusPainted(false);
		jbtbarAbrir.setMargin(new Insets(0, 0, 0, 0));
		jbtbarAbrir.setToolTipText("Abrir");
		jbtbarAbrir.setIcon(new ImageIcon(TextEditorMain.class.getResource("/icons/Open.png")));
		jtbarBarraDeHerr.add(jbtbarAbrir);
		
		jbtbarGuardar = new JButton("");
		jbtbarGuardar.addChangeListener(menuItemChangeListener);
		jbtbarGuardar.addActionListener(menuItemActionListener);
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
		jbtbarCortar.addChangeListener(menuItemChangeListener);
		jbtbarCortar.addActionListener(menuItemActionListener);
		jbtbarCortar.setIcon(new ImageIcon(TextEditorMain.class.getResource("/icons/Cut.png")));
		jbtbarCortar.setToolTipText("Cortar");
		jbtbarCortar.setMargin(new Insets(0, 0, 0, 0));
		jbtbarCortar.setFocusable(false);
		jbtbarCortar.setFocusPainted(false);
		jtbarBarraDeHerr.add(jbtbarCortar);
		
		jbtbarCopiar = new JButton("");
		jbtbarCopiar.addChangeListener(menuItemChangeListener);
		jbtbarCopiar.addActionListener(menuItemActionListener);
		jbtbarCopiar.setIcon(new ImageIcon(TextEditorMain.class.getResource("/icons/Copy.png")));
		jbtbarCopiar.setToolTipText("Copiar");
		jbtbarCopiar.setMargin(new Insets(0, 0, 0, 0));
		jbtbarCopiar.setFocusable(false);
		jbtbarCopiar.setFocusPainted(false);
		jtbarBarraDeHerr.add(jbtbarCopiar);
		
		jbtnbarPegar = new JButton("");
		jbtnbarPegar.addChangeListener(menuItemChangeListener);
		jbtnbarPegar.addActionListener(menuItemActionListener);
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
		jmItemSalir.addActionListener(menuItemActionListener);
		
		jmItemAbrir = new JMenuItem("Abrir");
		jmItemAbrir.addActionListener(menuItemActionListener);
		jmItemAbrir.addChangeListener(menuItemChangeListener);
		jmnuArchivo.add(jmItemAbrir);
		
		jmItemGuardar = new JMenuItem("Guardar");
		jmItemGuardar.addActionListener(menuItemActionListener);
		jmItemGuardar.addChangeListener(menuItemChangeListener);
		jmItemGuardar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		jmnuArchivo.add(jmItemGuardar);
		jmnuArchivo.add(jmItemSalir);
		
		jmnuEdicion = new JMenu("Edicion");
		jmbarBarraDeMenus.add(jmnuEdicion);
		
		jmItemCortar = new JMenuItem("Cortar");
		jmItemCortar.addActionListener(menuItemActionListener);
		jmItemCortar.addChangeListener(menuItemChangeListener);
		jmItemCortar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_MASK));
		jmnuEdicion.add(jmItemCortar);
		
		jmItemCopiar = new JMenuItem("Copiar");
		jmItemCopiar.addActionListener(menuItemActionListener);
		jmItemCopiar.addChangeListener(menuItemChangeListener);
		jmItemCopiar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));
		jmnuEdicion.add(jmItemCopiar);
		
		jmItemPegar = new JMenuItem("Pegar");
		jmItemPegar.addActionListener(menuItemActionListener);
		jmItemPegar.addChangeListener(menuItemChangeListener);
		jmItemPegar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_MASK));
		jmnuEdicion.add(jmItemPegar);
		
		jmnuOpciones = new JMenu("Opciones");
		jmbarBarraDeMenus.add(jmnuOpciones);
		
		jmnuFuente = new JMenu("Fuente");
		jmnuFuente.addChangeListener(menuItemChangeListener);
		jmnuFuente.setMnemonic('F');
		jmnuOpciones.add(jmnuFuente);
		
		jmItemCourierNew = new JMenuItem("Courier New");
		jmItemCourierNew.addActionListener(menuItemActionListener);
		jmnuFuente.add(jmItemCourierNew);
		
		jmItemArial = new JMenuItem("Arial");
		jmItemArial.addActionListener(menuItemActionListener);
		jmnuFuente.add(jmItemArial);
		
		jmItemFuentePredeterminada = new JMenuItem("Predeterminada");
		jmItemFuentePredeterminada.addActionListener(menuItemActionListener);
		jmnuFuente.add(jmItemFuentePredeterminada);
		
		jmnuTamaño = new JMenu("Tama\u00F1o");
		jmnuTamaño.addChangeListener(menuItemChangeListener);
		jmnuTamaño.setMnemonic('T');
		jmnuOpciones.add(jmnuTamaño);
		
		jmItem16 = new JMenuItem("16");
		jmItem16.addActionListener(menuItemActionListener);
		jmnuTamaño.add(jmItem16);
		
		jmItem24 = new JMenuItem("24");
		jmItem24.addActionListener(menuItemActionListener);
		jmnuTamaño.add(jmItem24);
		
		jmItemTamañoPredeterminado = new JMenuItem("Predeterminado");
		jmItemTamañoPredeterminado.addActionListener(menuItemActionListener);
		jmnuTamaño.add(jmItemTamañoPredeterminado);
	}
	
	//                    ****************************************COMMON LISTENERS*********
	//                    *****************************************************************
	
	//*************************************CHANGELISTENER items menu and toolbar
	ChangeListener menuItemChangeListener = new ChangeListener() {		
		@Override
		public void stateChanged(ChangeEvent e) {
			Object eventItem = e.getSource();
			
			if(eventItem == jmItemSalir){
				jmItemSalirStateChanged(e);
			}			
			if(eventItem == jmItemAbrir || eventItem == jbtbarAbrir){		//Abrir			
				jmItemAbrirStateChanged(e);
			}
			if(eventItem == jmItemGuardar || eventItem == jbtbarGuardar){	//Guardar
				jmItemGuardarStateChanged(e);
			}			
			if(eventItem == jmItemCortar || eventItem == jbtbarCortar){		//Cortar
				jmItemCortarStateChanged(e);
			}			
			if(eventItem == jmItemCopiar || eventItem == jbtbarCopiar){		//Copiar
				jmItemCopiarStateChanged(e);
			}			
			if(eventItem == jmItemPegar || eventItem == jbtnbarPegar){		//Pegar
				jmItemPegarStateChanged(e);
			}
			if(eventItem == jmnuFuente){									//Fuente
				jmItemFuenteStateChanged(e);
			}
			if(eventItem == jmnuTamaño){									//Tamaño
				jmItemTamañoStateChanged(e);
			}
			
		}
	};	
	
	//*************************************ACTIONLISTERNER items menu and toolbar
	ActionListener menuItemActionListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			Object eventItem = e.getSource();
			
			if(eventItem == jmItemSalir){
				jmItemSalirActionPerformed(e);
			}			
			if(eventItem == jmItemAbrir || eventItem == jbtbarAbrir){		//Abrir				
				jmItemAbrirActionPerformed(e);
			}
			if(eventItem == jmItemGuardar || eventItem == jbtbarGuardar){	//Guardar
				jmItemGuardarActionPerformed(e);
			}			
			if(eventItem == jmItemCortar || eventItem == jbtbarCortar){		//Cortar
				jmItemCortarActionPerformed(e);
			}			
			if(eventItem == jmItemCopiar || eventItem == jbtbarCopiar){		//Copiar
				jmItemCopiarActionPerformed(e);
			}			
			if(eventItem == jmItemPegar || eventItem == jbtnbarPegar){		//Pegar 				
				jmItemPegarActionPerformed(e);
			}
			if(eventItem == jmItemCourierNew){								//CourierNew
				jmItemCourierNewActionPerformed(e);
			}
			if(eventItem == jmItemArial){									//Arial
				jmItemArialActionPerformed(e);
			}
			if(eventItem == jmItemFuentePredeterminada){					//FuentePredeterminada
				jmItemPredeterminadaActionPerformed(e);
			}
			if(eventItem == jmItem16){										//Tamaño16
				jmItemTamaño16ActionPerformed(e);
			}
			if(eventItem == jmItem24){										//Tamaño24
				jmItemTamaño24ActionPerformed(e);
			}
			if(eventItem == jmItemTamañoPredeterminado){					//TamañoPredeterminado
				jmItemTamañoPredeterminadoActionPerformed(e);
			}
		}
	};
	

	//                    *****************************************EVENT HANDLERS**********
	//                    *****************************************************************
	
	//Cambio de tamaño de ventana, cambiamos tamaño y posicion de barra de estado
	private void formComponentResized(ComponentEvent evt){		
		jBarraDeEstado.setBounds(0, frame.getSize().height-83, frame.getSize().width-14, 24);
		jscrpaneEditor.setSize(new Dimension(frame.getSize().width-15, frame.getSize().height-101));
		jtxtaEditor.revalidate();
	}
	
	//Al abrir la ventana poner el foco en el area de texto
	private void formWindowOpened(WindowEvent evt){
		jtxtaEditor.requestFocus();
		defaultFont = jtxtaEditor.getFont();
	}	
	
	//**************************ACTIONPERFORMED items menu and toolbar
	private void jmItemSalirActionPerformed(ActionEvent evt){
		System.exit(0);
	}
	
	private void jmItemAbrirActionPerformed(ActionEvent evt){
		System.out.println("SIN IMPLEMENTAR");
	}
	
	private void jmItemGuardarActionPerformed(ActionEvent evt){
		System.out.println("SIN IMPLEMENTAR");
	}
	
	private void jmItemCortarActionPerformed(ActionEvent evt){
		jtxtaEditor.cut();
	}
	
	private void jmItemCopiarActionPerformed(ActionEvent evt){
		jtxtaEditor.copy();
	}
	
	private void jmItemPegarActionPerformed(ActionEvent evt){
		jtxtaEditor.paste();
	}

	private void jmItemCourierNewActionPerformed(ActionEvent evt){
		setFontToEditor("Courier New");
	}
	
	private void jmItemArialActionPerformed(ActionEvent evt){
		setFontToEditor("Arial");
	}
	
	private void jmItemPredeterminadaActionPerformed(ActionEvent evt){
		jtxtaEditor.setFont(defaultFont);
	}
	
	private void jmItemTamaño16ActionPerformed(ActionEvent evt){
		setFontSizeToEditor(16);
	}
	
	private void jmItemTamaño24ActionPerformed(ActionEvent evt){
		setFontSizeToEditor(24);
	}
	
	private void jmItemTamañoPredeterminadoActionPerformed(ActionEvent evt){
		setFontSizeToEditor(defaultFont.getSize());
	}
	
	
	//**************************STATECHANGED items menu and toolbar
	private void jmItemSalirStateChanged(ChangeEvent evt){	
		setTextInStateBarDuringHover("Cierra la aplicacion");
	}
	
	private void jmItemAbrirStateChanged(ChangeEvent evt){	
		setTextInStateBarDuringHover("Abrir un fichero");
	}
	
	private void jmItemGuardarStateChanged(ChangeEvent evt){	
		setTextInStateBarDuringHover("Guardar un fichero");
	}
	
	private void jmItemCortarStateChanged(ChangeEvent evt){		
		setTextInStateBarDuringHover("Copia y elimina el texto seleccionado");
	}
	
	private void jmItemCopiarStateChanged(ChangeEvent evt){		
		setTextInStateBarDuringHover("Copia el texto seleccionado");
	}
	
	private void jmItemPegarStateChanged(ChangeEvent evt){
		setTextInStateBarDuringHover("Pega el texto seleccionado");	
	}
	
	private void jmItemFuenteStateChanged(ChangeEvent evt){
		setTextInStateBarDuringHover("Cambiar la fuente de texto");
	}

	private void jmItemTamañoStateChanged(ChangeEvent evt){
		setTextInStateBarDuringHover("Cambiar el tamaño de la fuente");		
	}		
	
	
					//********************OTHER FUNCTIONs*******************
					//******************************************************
	
	private void setTextInStateBarDuringHover(String s){
		if(jetbarestPpal.getText().equals("Listo")){			
			jetbarestPpal.setText(s);
		}else{			
			jetbarestPpal.setText("Listo");
		}
	}
	
	private void setFontToEditor(String fontName){
		Font oldFont = jtxtaEditor.getFont();
		Font newFont = new Font(fontName, oldFont.getStyle(), oldFont.getSize());
		jtxtaEditor.setFont(newFont);
	}
	
	private void setFontSizeToEditor(int size){		
		Font oldSize = jtxtaEditor.getFont();
		Font newSize = new Font(oldSize.getFamily(), oldSize.getStyle(), size);
		jtxtaEditor.setFont(newSize);
	}
	
}
