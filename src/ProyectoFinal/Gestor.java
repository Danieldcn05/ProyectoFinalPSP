package ProyectoFinal;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class Gestor {

	// Funcionalidades extra
	// Poner nombre de archivo a abrir con las aplicaciones
	// Mensaje de que no existe el archivo
	// Borrar historial
	// Que no salgan url repetidas en el historial

	public void abrirPrograma(String program, String archivo) {
		File file = new File(archivo);
		if (archivo.isEmpty()) {
			ProcessBuilder pb = new ProcessBuilder(program); // notepad, mspaint.exe, explorer.exe
			try {
				Process p = pb.start();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if(file.exists()) {
			
			ProcessBuilder pb = new ProcessBuilder(program, archivo); // notepad, mspaint.exe, explorer.exe
			try {
				Process p = pb.start();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			JOptionPane.showMessageDialog(null, "El archivo no existe.", "Error", JOptionPane.ERROR_MESSAGE);
		}

	}

	public void abrirWeb(String url) {
		try {
			String chromePath = "C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe";// C:\\Program\\Files\\Google\\Chrome\\Application\\chrome.exe
																									

			ProcessBuilder processBuilder = new ProcessBuilder(chromePath, url);
			processBuilder.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void guardar(String url) {
		String filePath = "urls.txt";
		Set<String> urlsExistentes = filtrarUrlsExistentes();

		if (!urlsExistentes.contains(url)) {
			try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
				writer.write(url);
				writer.newLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void navegar(String url) {
		abrirWeb(url);
		guardar(url);
	}

	public void leer(JTextArea textArea) {
		// Nombre del archivo en el directorio por defecto del proyecto
		String filePath = "urls.txt";
		File archivo = new File(filePath);

		if (archivo.exists()) {
			try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
				String line;
				textArea.setText("");
				while ((line = reader.readLine()) != null) {
					System.out.println(line);
					textArea.append(line + "\n");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			try {
				archivo.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	private Set<String> filtrarUrlsExistentes() {
		String filePath = "urls.txt";
		Set<String> urlsExistentes = new HashSet<>();
		try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
			String line;
			while ((line = reader.readLine()) != null) {
				urlsExistentes.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return urlsExistentes;
	}

	public void borrarHistorial(JTextArea textArea) {
		String filePath = "urls.txt";
		File archivo = new File(filePath);
		if (archivo.exists()) {
			if (archivo.delete()) {
				textArea.setText(""); // Limpiar el JTextArea después de borrar
				System.out.println("Historial borrado exitosamente.");
			} else {
				System.out.println("No se pudo borrar el historial.");
			}
		} else {
			JOptionPane.showMessageDialog(null, "El archivo de historial no existe.", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

}
