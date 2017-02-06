package com.merinigo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class FileManager {	
	
	public static String readFile(File file) throws IOException {
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
			} catch (Exception e) {}
		}
		return fileText;
	}

	public static void writeFile(String texto, File file) throws FileNotFoundException {
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
	
	public static void newFile(File file){
		PrintWriter pw = null;

		try {
			pw = new PrintWriter(file.getPath());						
		} catch (FileNotFoundException e) {			
			System.out.println("NUEVO FICHERO CREADO");
		} finally {
			if (pw != null) {
				pw.flush();
				pw.close();				
			}
		}
	}

}
