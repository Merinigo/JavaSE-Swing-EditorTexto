package com.merinigo;

import java.awt.Component;
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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.Box;
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
import javax.swing.JPopupMenu;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
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
	
	private JPopupMenu jpmuEdicion;
	private JMenuItem jpmItemCortar;
	private JMenuItem jpmItemCopiar;
	private JMenuItem jpmItemPegar;
	
	private JFrame frame;
	private JScrollPane jscrpaneEditor;
	private JTextArea jtxtaEditor;
	private JMenuBar jmbarBarraDeMenus;
	private JMenu jmnuArchivo;
	private JMenuItem jmItemGuardar;
	private JMenuItem jmItemAbrir;
	private JMenuItem jmItemSalir;
	private JMenu jmnuEdicion;
	private JMenuItem jmItemDeshacer;	
	private JMenuItem jmItemRehacer;
	private JMenuItem jmItemCortar;
	private JMenuItem jmItemCopiar;
	private JMenuItem jmItemPegar;
	private JMenu jmnuOpciones;
	private JMenu jmnuFuente;
	private ButtonGroup jbgFuente;
	private JCheckBoxMenuItem jmItemCourierNew;
	private JCheckBoxMenuItem jmItemArial;
	private JCheckBoxMenuItem jmItemFuentePredeterminada;
	private JMenu jmnuTamano;
	private ButtonGroup jbgTamano;
	private JRadioButtonMenuItem jmItem16;
	private JRadioButtonMenuItem jmItem24;
	private JRadioButtonMenuItem jmItemTamanoPredeterminado;
	private JToolBar jtbarBarraDeHerr;
	private JButton jbtbarAbrir;
	private JButton jbtbarGuardar;
	private JButton jbtbarCortar;
	private JButton jbtbarCopiar;
	private JButton jbtnbarPegar;
	private JButton jbtbarDeshacer;
	private JButton jbtbarRehacer;
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
		
		jpmuEdicion = new JPopupMenu();																	//PopUpMenu
		
		jpmItemCortar = new JMenuItem("Cortar");
		jpmItemCortar.addActionListener(menuItemActionListener);
		jpmItemCortar.addChangeListener(menuItemChangeListener);
		jpmItemCortar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_MASK));				
		jpmuEdicion.add(jpmItemCortar);
		   
		jpmItemCopiar = new JMenuItem("Copiar");
		jpmItemCopiar.addActionListener(menuItemActionListener);
		jpmItemCopiar.addChangeListener(menuItemChangeListener);
		jpmItemCopiar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_MASK));				
		jpmuEdicion.add(jpmItemCopiar);
		
		jpmItemPegar = new JMenuItem("Pegar");
		jpmItemPegar.addActionListener(menuItemActionListener);
		jpmItemPegar.addChangeListener(menuItemChangeListener);
		jpmItemPegar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_MASK));				
		jpmuEdicion.add(jpmItemPegar);
		
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
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		horizontalStrut.setPreferredSize(new Dimension(5, 0));
		horizontalStrut.setMinimumSize(new Dimension(5, 0));
		horizontalStrut.setMaximumSize(new Dimension(5, 32767));
		jtbarBarraDeHerr.add(horizontalStrut);
		
		jtbarBarraDeHerr.add(new JToolBar.Separator());

		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		horizontalStrut_1.setPreferredSize(new Dimension(5, 0));
		horizontalStrut_1.setMinimumSize(new Dimension(5, 0));
		horizontalStrut_1.setMaximumSize(new Dimension(5, 32767));
		jtbarBarraDeHerr.add(horizontalStrut_1);
		
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
		
		Component horizontalStrut_2 = Box.createHorizontalStrut(20);
		horizontalStrut_2.setPreferredSize(new Dimension(5, 0));
		horizontalStrut_2.setMinimumSize(new Dimension(5, 0));
		horizontalStrut_2.setMaximumSize(new Dimension(5, 32767));
		jtbarBarraDeHerr.add(horizontalStrut_2);
		
		jtbarBarraDeHerr.add(new JToolBar.Separator());
		
		Component horizontalStrut_3 = Box.createHorizontalStrut(20);
		horizontalStrut_3.setPreferredSize(new Dimension(5, 0));
		horizontalStrut_3.setMinimumSize(new Dimension(5, 0));
		horizontalStrut_3.setMaximumSize(new Dimension(5, 32767));
		jtbarBarraDeHerr.add(horizontalStrut_3);
		
		jbtbarDeshacer = new JButton("");																//Toolbar_Deshacer
		jbtbarDeshacer.addChangeListener(menuItemChangeListener);
		jbtbarDeshacer.addActionListener(menuItemActionListener);
		jbtbarDeshacer.setIcon(new ImageIcon(TextEditorMain.class.getResource("/icons/Undo.png")));
		jbtbarDeshacer.setToolTipText("Deshacer");
		jbtbarDeshacer.setMargin(new Insets(0, 0, 0, 0));
		jbtbarDeshacer.setFocusable(false);
		jbtbarDeshacer.setFocusPainted(false);
		jtbarBarraDeHerr.add(jbtbarDeshacer);
		
		jbtbarRehacer = new JButton("");																//Toolbar_Rehacer
		jbtbarRehacer.addChangeListener(menuItemChangeListener);
		jbtbarRehacer.addActionListener(menuItemActionListener);
		jbtbarRehacer.setIcon(new ImageIcon(TextEditorMain.class.getResource("/icons/Redo.png")));
		jbtbarRehacer.setToolTipText("Rehacer");
		jbtbarRehacer.setMargin(new Insets(0, 0, 0, 0));
		jbtbarRehacer.setFocusable(false);
		jbtbarRehacer.setFocusPainted(false);
		jtbarBarraDeHerr.add(jbtbarRehacer);
		
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
		jtxtaEditor.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				jtxtaEditorMousePressed(e);
			}
		});
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
			public void menuSelected(MenuEvent e) {
				jmnuEdicionSelected(e);
			}
			public void menuCanceled(MenuEvent e) {}
			public void menuDeselected(MenuEvent e) {}
		});
		jmbarBarraDeMenus.add(jmnuEdicion);
		
		jmItemDeshacer = new JMenuItem("Deshacer");														//MenuBar_Edicion_Deshacer
		jmItemDeshacer.addChangeListener(menuItemChangeListener);
		jmItemDeshacer.addActionListener(menuItemActionListener);
		jmItemDeshacer.setMnemonic('D');
		jmItemDeshacer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_MASK));
		jmnuEdicion.add(jmItemDeshacer);
		
		jmItemRehacer = new JMenuItem("Rehacer");														//MenuBar_Edicion_Rehacer
		jmItemRehacer.addChangeListener(menuItemChangeListener);
		jmItemRehacer.addActionListener(menuItemActionListener);
		jmItemRehacer.setMnemonic('R');
		jmItemRehacer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_MASK));
		jmnuEdicion.add(jmItemRehacer);
		
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
				
		jmnuTamano = new JMenu("Tama\u00F1o");															//MenuBar_Opciones_Tama�o
		jmnuTamano.addChangeListener(menuItemChangeListener);
		jmnuTamano.setMnemonic('T');
		jmnuOpciones.add(jmnuTamano);
		
		jbgTamano = new ButtonGroup();
		
		jmItem16 = new JRadioButtonMenuItem("16");														//MenuBar_Opciones_Tama�o_16
		jmItem16.addActionListener(menuItemActionListener);
		jbgTamano.add(jmItem16);
		jmnuTamano.add(jmItem16);
		
		jmItem24 = new JRadioButtonMenuItem("24");														//MenuBar_Opciones_Tama�o_24
		jmItem24.addActionListener(menuItemActionListener);
		jbgTamano.add(jmItem24);
		jmnuTamano.add(jmItem24);
		
		jmItemTamanoPredeterminado = new JRadioButtonMenuItem("Predeterminado");						//MenuBar_Opciones_Tama�o_Pred
		jmItemTamanoPredeterminado.addActionListener(menuItemActionListener);
		jmItemTamanoPredeterminado.setSelected(true);
		jbgTamano.add(jmItemTamanoPredeterminado);
		jmnuTamano.add(jmItemTamanoPredeterminado);
				
	}
	
	//Iniciamos otros componentes no visuales ***********************************************************DESHABILITADOS HASTA IMPLEMENTAR FUNCION
	private void initOtherComponents(){
		jumDeshacerRehacer = new UndoManager();
		jtxtaEditor.getDocument().addUndoableEditListener(jumDeshacerRehacer);
		
		jbtbarAbrir.setEnabled(false);
		jbtbarGuardar.setEnabled(false);
		jmItemAbrir.setEnabled(false);
		jmItemGuardar.setEnabled(false);
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
			if(eventItem == jmItemDeshacer||eventItem == jbtbarDeshacer){	//Deshacer				
				jmItemDeshacerStateChanged(e);
			}	
			if(eventItem == jmItemRehacer||eventItem == jbtbarRehacer){		//Rehacer
				jmItemRehacerStateChanged(e);
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
			if(eventItem == jmnuTamano){									//Tama�o
				jmItemTamanoStateChanged(e);
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
			if(eventItem == jmItemDeshacer||eventItem == jbtbarDeshacer){	//Deshacer
				jmItemDeshacerActionPerformed(e);
			}	
			if(eventItem == jmItemRehacer||eventItem == jbtbarRehacer){		//Rehacer
				jmItemRehacerActionPerformed(e);
			}	
			if(eventItem == jmItemCortar || eventItem == jbtbarCortar || eventItem == jpmItemCortar){		//Cortar
				jmItemCortarActionPerformed(e);
			}			
			if(eventItem == jmItemCopiar || eventItem == jbtbarCopiar || eventItem == jpmItemCopiar){		//Copiar
				jmItemCopiarActionPerformed(e);
			}			
			if(eventItem == jmItemPegar || eventItem == jbtnbarPegar || eventItem == jpmItemPegar){		//Pegar 				
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
			if(eventItem == jmItem16){										//Tama�o16
				jmItemTamano16ActionPerformed(e);
			}
			if(eventItem == jmItem24){										//Tama�o24
				jmItemTamano24ActionPerformed(e);
			}
			if(eventItem == jmItemTamanoPredeterminado){					//Tama�oPredeterminado
				jmItemTamanoPredeterminadoActionPerformed(e);
			}
		}
	};
	

	//                    *****************************************EVENT HANDLERS**********
	//                    *****************************************************************
	
	//Cambio de tama�o de ventana, cambiamos tama�o y posicion de barra de estado
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
	
	//Inhabilita copiar y cortar si no hay texto seleccionado (barra de menus)
	private void jmnuEdicionSelected(MenuEvent evt){
		boolean hayTextoSeleccionado = jtxtaEditor.getSelectedText() != null;
		jmItemCopiar.setEnabled(hayTextoSeleccionado);
		jmItemCortar.setEnabled(hayTextoSeleccionado);
	}
	
	//Inhabilita copiar y cortar si no hay texto seleccionado (menu emergente)
	private void jtxtaEditorMousePressed(MouseEvent evt){
		boolean hayTextoSeleccionado = jtxtaEditor.getSelectedText() != null;
		jpmItemCopiar.setEnabled(hayTextoSeleccionado);
		jpmItemCortar.setEnabled(hayTextoSeleccionado);
		
		if(evt.getButton() == MouseEvent.BUTTON3){ //Button3 para boton derecho del raton
			jpmuEdicion.show(jtxtaEditor, evt.getX(), evt.getY());
		}
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
	
	private void jmItemDeshacerActionPerformed(ActionEvent evt){
		if(jumDeshacerRehacer.canUndo()){
			jumDeshacerRehacer.undo();
		}
	}
	
	private void jmItemRehacerActionPerformed(ActionEvent evt){
		if(jumDeshacerRehacer.canRedo()){
			jumDeshacerRehacer.redo();;
		}
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
	
	private void jmItemTamano16ActionPerformed(ActionEvent evt){
		setFontSizeToEditor(16);
	}
	
	private void jmItemTamano24ActionPerformed(ActionEvent evt){
		setFontSizeToEditor(24);
	}
	
	private void jmItemTamanoPredeterminadoActionPerformed(ActionEvent evt){
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
	
	private void jmItemDeshacerStateChanged(ChangeEvent evt){	
		setTextInStateBarDuringHover("Deshace ultimo paso");
	}
	
	private void jmItemRehacerStateChanged(ChangeEvent evt){	
		setTextInStateBarDuringHover("Rehace ultimo paso");
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

	private void jmItemTamanoStateChanged(ChangeEvent evt){
		setTextInStateBarDuringHover("Cambiar el tama�o de la fuente");		
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
