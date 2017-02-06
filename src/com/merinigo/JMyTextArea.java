package com.merinigo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.text.Document;

public class JMyTextArea extends JTextArea {

	private static final long serialVersionUID = 1L;

	private File loadedFile;	

	public JMyTextArea() {
		super();
	}

	public JMyTextArea(Document doc, String text, int rows, int columns) {
		super(doc, text, rows, columns);
	}

	public JMyTextArea(Document doc) {
		super(doc);
	}

	public JMyTextArea(int rows, int columns) {
		super(rows, columns);
	}

	public JMyTextArea(String text, int rows, int columns) {
		super(text, rows, columns);
	}

	public JMyTextArea(String text) {
		super(text);
	}
	
	public String getFilePath(){
		String filePath = "Sin Guardar";
		
		if(this.loadedFile != null){
			filePath =this.loadedFile.getPath();
		}
		return filePath;
	}
	
	public boolean openFile() {
		boolean openOK = false;
			
		JFileChooser fc = new JFileChooser(new File("").getAbsoluteFile());
		int returnVal = fc.showOpenDialog(null);
	
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			loadedFile = fc.getSelectedFile();			
			loadToTextArea();
			System.out.println("FILE OPENED");
			openOK = true;
		} else {
			System.out.println("FILE OPENING CANCELED");
		}	
		return openOK;
	}
	
	public boolean saveFile() {
		boolean saveOK = false;

		if (loadedFile != null) {				
			saveFromTextArea();
			System.out.println("FILE SAVED");
			saveOK = true;
		} else {
			JFileChooser fc = new JFileChooser(new File("").getAbsoluteFile());
			int returnVal = fc.showSaveDialog(null);
	
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				saveFromTextArea();
				System.out.println("FILE SAVED");
				saveOK = true;
			} else {
				System.out.println("FILE SAVING CANCELLED");
			}
		}
		return saveOK;
	}

	public boolean newFile(){
		boolean newOK = false;
				
		if(loadedFile != null){		
			saveFromTextArea();
		}
		
		JFileChooser fc = new JFileChooser(new File("").getAbsoluteFile());
		int returnVal = fc.showOpenDialog(null);
							
		if(returnVal == JFileChooser.APPROVE_OPTION && !fc.getSelectedFile().exists()){
			loadedFile = fc.getSelectedFile();
			createNewFile(loadedFile);
			loadToTextArea();
			System.out.println("FILE CREATED");
			newOK = true;
		}else{ 
			System.out.println("FILE CREATING CANCELED");
		}	
		return newOK;
	}

	private void createNewFile(File file){
		FileManager.newFile(file);
	}
	 
	private void loadToTextArea(){
		try {
			this.setText(FileManager.readFile(loadedFile));
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "ERROR E/S", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void saveFromTextArea(){
		try {
			FileManager.writeFile(this.getText(), loadedFile);
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "ERROR FICHERO NO ENCONTRADO", JOptionPane.ERROR_MESSAGE);
		}
	}

}
