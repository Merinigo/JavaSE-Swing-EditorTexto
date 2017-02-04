package com.merinigo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JTextArea;

public class FileManager {

	private File loadedFile;

	public FileManager(File loadedFile) {		
		this.loadedFile = loadedFile;
	}
	
	public void setFile(File file){
		this.loadedFile = file;
	}
	
	public File getFile(){
		return this.loadedFile;
	}
	
	public void loadFileToTextArea(JTextArea txtArea) throws IOException{
		txtArea.setText(readFile());
	}
	
	public void saveFileToTextArea(JTextArea txtArea) throws FileNotFoundException{
		saveFile(txtArea.getText());
	}
	
	private String readFile() throws IOException{
		String fileText = "";
		String line;
		BufferedReader br = null;
		
		try {
			br = new BufferedReader(new FileReader(loadedFile));
			System.out.println("FILE LOADED");
			
			line = br.readLine();
			while(line != null){
				System.out.println("READING FILE");
				fileText += line + "\n";
				line = br.readLine();
			}
			
		} catch (IOException e) {
			throw e;
		} finally{
			try{
				if(br != null){
					br.close();
					System.out.println("FILE CLOSED");
				}				
			}catch (Exception e) {}
		}						
		return fileText;
	}
	
	private void saveFile(String texto) throws FileNotFoundException{
		PrintWriter pw = null;
		
		try {
			pw = new PrintWriter(loadedFile.getPath());
			System.out.println("FILE LOADED");
			pw.print(texto);
			System.out.println("WRITTING FILE");
		} catch (FileNotFoundException e) {
			throw e;
		} finally{
			if(pw != null){
				pw.flush();
				pw.close();
				System.out.println("FILE CLOSED");
			}
		}				
	}
	
	
	
}
