package com.merinigo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class FileManager {

	private JTextArea txtArea;
	private File loadedFile;

	public FileManager(JTextArea txtArea) {
		this.txtArea = txtArea;
	}

	public FileManager(File loadedFile, JTextArea txtArea) {
		this.loadedFile = loadedFile;
		this.txtArea = txtArea;
	}

	public void setFile(File file) {
		this.loadedFile = file;
	}

	public File getFile() {
		return this.loadedFile;
	}

	public void loadToTextArea() throws IOException {
		txtArea.setText(readFile(loadedFile));
	}

	public void loadFileToTextArea(File file) throws IOException {
		txtArea.setText(readFile(file));
	}

	public void saveFromTextArea() throws FileNotFoundException {
		saveFile(txtArea.getText(), loadedFile);
	}

	public void saveFileFromTextArea(File file) throws FileNotFoundException {
		saveFile(txtArea.getText(), file);
	}

	public boolean openFile(){
		boolean selectionOK = false;		
				
		try{						
			JFileChooser fc = new JFileChooser(new File("").getAbsoluteFile());		
			int returnVal = fc.showOpenDialog(null);
			
			if(returnVal == JFileChooser.APPROVE_OPTION){
				loadedFile = fc.getSelectedFile();
				loadToTextArea();
				System.out.println("FILE OPENED");
				selectionOK = true;
			}else{
				System.out.println("FILE OPENING CANCELED");
			}
		}catch (Exception e) {
			JOptionPane.showMessageDialog(txtArea, e.getMessage(), "ERROR E/S", JOptionPane.ERROR_MESSAGE);
		}
						
		return selectionOK;
	}
	
	public boolean saveFile() {
		boolean saveOK = false;
		
		try{
			if(loadedFile != null){
				saveFromTextArea();
				System.out.println("FILE SAVED");
				saveOK = true;
			}else{					
				JFileChooser fc = new JFileChooser(new File("").getAbsoluteFile());
				int returnVal = fc.showSaveDialog(null);
				
				if(returnVal == JFileChooser.APPROVE_OPTION){
					saveFileFromTextArea(fc.getSelectedFile());
					System.out.println("FILE SAVED");
					saveOK = true;
				}else{
					System.out.println("FILE SAVING CANCELLED");
				}					
			}			
		}catch (Exception e) {
			JOptionPane.showMessageDialog(txtArea, e.getMessage(), "ERROR FICHERO NO ENCONTRADO", JOptionPane.ERROR_MESSAGE);
		}
		return saveOK;
	}
		
	
	private String readFile(File file) throws IOException {
		String fileText = "";
		String line;
		BufferedReader br = null;

		try {
			br = new BufferedReader(new FileReader(file));			

			line = br.readLine();
			while (line != null) {				
				fileText += line + "\n";
				line = br.readLine();
			}

		} catch (IOException e) {
			throw e;
		} finally {
			try {
				if (br != null) {
					br.close();					
				}
			} catch (Exception e) {
			}
		}
		return fileText;
	}

	private void saveFile(String texto, File file) throws FileNotFoundException {
		PrintWriter pw = null;

		try {
			pw = new PrintWriter(file.getPath());			
			pw.print(texto);			
		} catch (FileNotFoundException e) {
			throw e;
		} finally {
			if (pw != null) {
				pw.flush();
				pw.close();				
			}
		}
	}

}
