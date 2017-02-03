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

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
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
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.undo.UndoManager;

public class TextEditorMain{

	private Font defaultFont;
	private UndoManager jumDeshacerRehacer;
	
	private JFrame frame;
	private JScrollPane jscrpaneEditor;
	private JTextArea jtxtaEditor;
	private JMenuBar jmbarBarraDeMenus;
	private JMenu jmnuArchivo;
	private JMenuItem jmItemGuardar;
	private JMenuItem jmItemAbrir;
	private JMenuItem jmItemSalir;
	private JMenu jmnuEdicion;
	private JMenuItem jmItemCortar;
	private JMenuItem jmItemCopiar;
	private JMenuItem jmItemPegar;
	private JMenu jmnuOpciones;
	private JMenu jmnuFuente;
	private ButtonGroup jbgFuente;
	private JCheckBoxMenuItem jmItemCourierNew;
	private JCheckBoxMenuItem jmItemArial;
	private JCheckBoxMenuItem jmItemFuentePredeterminada;
	private JMenu jmnuTamaño;
	private ButtonGroup jbgTamano;
	private JRadioButtonMenuItem jmItem16;
	private JRadioButtonMenuItem jmItem24;
	private JRadioButtonMenuItem jmItemTamañoPredeterminado;
	private JToolBar jtbarBarraDeHerr;
	private JButton jbtbarAbrir;
	private JButton jbtbarGuardar;
	private JSeparator separator;
	private JButton jbtbarCortar;
	private JButton jbtbarCopiar;
	private JButton jbtnbarPegar;
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
		initOtherComponents();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();																			//JFrame 
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
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		
		jtbarBarraDeHerr = new JToolBar();																//Toolbar																			
		jtbarBarraDeHerr.setFloatable(false);
		jtbarBarraDeHerr.setBounds(0, 0, 484, 20);
		frame.getContentPane().add(jtbarBarraDeHerr);
		
		jbtbarAbrir = new JButton("");																	//Toolbar_Abrir
		jbtbarAbrir.addActionListener(menuItemActionListener);
		jbtbarAbrir.addChangeListener(menuItemChangeListener);
		jbtbarAbrir.setFocusable(false);
		jbtbarAbrir.setFocusPainted(false);
		jbtbarAbrir.setMargin(new Insets(0, 0, 0, 0));
		jbtbarAbrir.setToolTipText("Abrir");
		jbtbarAbrir.setIcon(new ImageIcon(TextEditorMain.class.getResource("/icons/Open.png")));
		jtbarBarraDeHerr.add(jbtbarAbrir);
		
		jbtbarGuardar = new JButton("");																//Toolbar_Guardar
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
		
		jbtbarCortar = new JButton("");																	//Toolbar_Cortar
		jbtbarCortar.addChangeListener(menuItemChangeListener);
		jbtbarCortar.addActionListener(menuItemActionListener);
		jbtbarCortar.setIcon(new ImageIcon(TextEditorMain.class.getResource("/icons/Cut.png")));
		jbtbarCortar.setToolTipText("Cortar");
		jbtbarCortar.setMargin(new Insets(0, 0, 0, 0));
		jbtbarCortar.setFocusable(false);
		jbtbarCortar.setFocusPainted(false);
		jtbarBarraDeHerr.add(jbtbarCortar);
		
		jbtbarCopiar = new JButton("");																	//Toolbar_Copiar
		jbtbarCopiar.addChangeListener(menuItemChangeListener);
		jbtbarCopiar.addActionListener(menuItemActionListener);
		jbtbarCopiar.setIcon(new ImageIcon(TextEditorMain.class.getResource("/icons/Copy.png")));
		jbtbarCopiar.setToolTipText("Copiar");
		jbtbarCopiar.setMargin(new Insets(0, 0, 0, 0));
		jbtbarCopiar.setFocusable(false);
		jbtbarCopiar.setFocusPainted(false);
		jtbarBarraDeHerr.add(jbtbarCopiar);
		
		jbtnbarPegar = new JButton("");																	//Toolbar_Pegar
		jbtnbarPegar.addChangeListener(menuItemChangeListener);
		jbtnbarPegar.addActionListener(menuItemActionListener);
		jbtnbarPegar.setIcon(new ImageIcon(TextEditorMain.class.getResource("/icons/Paste.png")));
		jbtnbarPegar.setToolTipText("Pegar");
		jbtnbarPegar.setMargin(new Insets(0, 0, 0, 0));
		jbtnbarPegar.setFocusable(false);
		jbtnbarPegar.setFocusPainted(false);
		jtbarBarraDeHerr.add(jbtnbarPegar);
		
		jBarraDeEstado = new JPanel();																	//StateBar
		FlowLayout fl_jBarraDeEstado = (FlowLayout) jBarraDeEstado.getLayout();
		fl_jBarraDeEstado.setVgap(0);
		fl_jBarraDeEstado.setAlignment(FlowLayout.LEFT);
		jBarraDeEstado.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		jBarraDeEstado.setBounds(0, 216, 485, 25);
		frame.getContentPane().add(jBarraDeEstado);
		
		jetbarestPpal = new JLabel("");																	//StateBar_Label
		jetbarestPpal.setPreferredSize(new Dimension(470, 20));
		jBarraDeEstado.add(jetbarestPpal);
		
		jscrpaneEditor = new JScrollPane();																//ScrollPane
		jtxtaEditor = new JTextArea();																	//TextArea
//		jtxtaEditor.setLineWrap(true);
//		jtxtaEditor.setWrapStyleWord(true);
		jscrpaneEditor.setViewportView(jtxtaEditor);
		jscrpaneEditor.setBounds(0, 20, 484, 198);
		frame.getContentPane().add(jscrpaneEditor);
		
		jmbarBarraDeMenus = new JMenuBar();																//MenuBar																	
		frame.setJMenuBar(jmbarBarraDeMenus);
		
		jmnuArchivo = new JMenu("Archivo");																//MenuBar_Archivo
		jmnuArchivo.setMnemonic('A');
		jmbarBarraDeMenus.add(jmnuArchivo);
		
		jmItemSalir = new JMenuItem("Salir");															//MenuBar_Archivo_Salir
		jmItemSalir.addChangeListener(menuItemChangeListener);	
		jmItemSalir.setMnemonic('S');
		jmItemSalir.addActionListener(menuItemActionListener);
		
		jmItemAbrir = new JMenuItem("Abrir");															//MenuBar_Archivo_Abrir
		jmItemAbrir.addActionListener(menuItemActionListener);
		jmItemAbrir.addChangeListener(menuItemChangeListener);
		jmnuArchivo.add(jmItemAbrir);
		
		jmItemGuardar = new JMenuItem("Guardar");														//MenuBar_Archivo_Guardar															
		jmItemGuardar.addActionListener(menuItemActionListener);
		jmItemGuardar.addChangeListener(menuItemChangeListener);
		jmItemGuardar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		jmnuArchivo.add(jmItemGuardar);
		jmnuArchivo.add(jmItemSalir);
		
		jmnuEdicion = new JMenu("Edicion");																//MenuBar_Edicion
		jmnuEdicion.addMenuListener(new MenuListener() {
			public void menuSelected(MenuEvent arg0) {
				boolean hayTextoSeleccionado = jtxtaEditor.getSelectedText() != null;
				jmItemCopiar.setEnabled(hayTextoSeleccionado);
				jmItemCortar.setEnabled(hayTextoSeleccionado);
			}
			public void menuCanceled(MenuEvent arg0) {}
			public void menuDeselected(MenuEvent arg0) {}
		});
		jmbarBarraDeMenus.add(jmnuEdicion);
		
		jmItemCortar = new JMenuItem("Cortar");															//MenuBar_Edicion_Cortar
		jmItemCortar.addActionListener(menuItemActionListener);
		jmItemCortar.addChangeListener(menuItemChangeListener);
		jmItemCortar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_MASK));
		jmnuEdicion.add(jmItemCortar);
		
		jmItemCopiar = new JMenuItem("Copiar");															//MenuBar_Edicion_Copiar																
		jmItemCopiar.addActionListener(menuItemActionListener);
		jmItemCopiar.addChangeListener(menuItemChangeListener);
		jmItemCopiar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));
		jmnuEdicion.add(jmItemCopiar);
		
		jmItemPegar = new JMenuItem("Pegar");															//MenuBar_Edicion_Pegar
		jmItemPegar.addActionListener(menuItemActionListener);
		jmItemPegar.addChangeListener(menuItemChangeListener);
		jmItemPegar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_MASK));
		jmnuEdicion.add(jmItemPegar);
		
		jmnuOpciones = new JMenu("Opciones");															//MenuBar_Opciones
		jmbarBarraDeMenus.add(jmnuOpciones);
		
		jmnuFuente = new JMenu("Fuente");																//MenuBar_Opciones_Fuente
		jmnuFuente.addChangeListener(menuItemChangeListener);
		jmnuFuente.setMnemonic('F');
		jmnuOpciones.add(jmnuFuente);
		
		jbgFuente = new ButtonGroup();
		
		jmItemCourierNew = new JCheckBoxMenuItem("Courier New");										//MenuBar_Opciones_Fuente_CourierNew
		jmItemCourierNew.addActionListener(menuItemActionListener);
		jbgFuente.add(jmItemCourierNew);
		jmnuFuente.add(jmItemCourierNew);
		
		jmItemArial = new JCheckBoxMenuItem("Arial");													//MenuBar_Opciones_Fuente_Arial
		jmItemArial.addActionListener(menuItemActionListener);
		jbgFuente.add(jmItemArial);
		jmnuFuente.add(jmItemArial);
		
		jmItemFuentePredeterminada = new JCheckBoxMenuItem("Predeterminada");							//MenuBar_Opciones_Fuente_Pred
		jmItemFuentePredeterminada.addActionListener(menuItemActionListener);
		jbgFuente.add(jmItemFuentePredeterminada);
		jmItemFuentePredeterminada.setSelected(true);
		jmnuFuente.add(jmItemFuentePredeterminada);
				
		jmnuTamaño = new JMenu("Tama\u00F1o");															//MenuBar_Opciones_Tamaño
		jmnuTamaño.addChangeListener(menuItemChangeListener);
		jmnuTamaño.setMnemonic('T');
		jmnuOpciones.add(jmnuTamaño);
		
		jbgTamano = new ButtonGroup();
		
		jmItem16 = new JRadioButtonMenuItem("16");														//MenuBar_Opciones_Tamaño_16
		jmItem16.addActionListener(menuItemActionListener);
		jbgTamano.add(jmItem16);
		jmnuTamaño.add(jmItem16);
		
		jmItem24 = new JRadioButtonMenuItem("24");														//MenuBar_Opciones_Tamaño_24
		jmItem24.addActionListener(menuItemActionListener);
		jbgTamano.add(jmItem24);
		jmnuTamaño.add(jmItem24);
		
		jmItemTamañoPredeterminado = new JRadioButtonMenuItem("Predeterminado");						//MenuBar_Opciones_Tamaño_Pred
		jmItemTamañoPredeterminado.addActionListener(menuItemActionListener);
		jmItemTamañoPredeterminado.setSelected(true);
		jbgTamano.add(jmItemTamañoPredeterminado);
		jmnuTamaño.add(jmItemTamañoPredeterminado);
	}
	
	//Iniciamos otros componentes no visuales
	private void initOtherComponents(){
		jumDeshacerRehacer = new UndoManager();
		jtxtaEditor.getDocument().addUndoableEditListener(jumDeshacerRehacer);
	}
		
	
	//                    ****************************************COMMON LISTENERS*********
	//                    *****************************************************************
	
	//-------------------------CHANGELISTENER items menu and toolbar
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
	
	//-------------------------ACTIONLISTERNER items menu and toolbar
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
	
	//-------------------------ACTIONPERFORMED items menu and toolbar
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
	
	
	//-------------------------STATECHANGED items menu and toolbar
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
