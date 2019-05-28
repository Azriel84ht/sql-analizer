package testeos.principal;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

public class borraDuplis {

	public int dupliClean(String f_input) {
		File fich_entrada = null;
		FileReader fr = null;
		FileWriter fw = null;
		PrintWriter pw = null;
		BufferedReader br = null;
		int p_ext = f_input.lastIndexOf(".");
		String f_output = f_input.substring(0,p_ext) + "_nodup.txt";
		try {
			fich_entrada = new File(f_input);
			fw = new FileWriter(f_output);
			fr = new FileReader(fich_entrada);
			pw = new PrintWriter(fw);
			br = new BufferedReader(fr);
			String linea1 = null;
			String salida = "";
			while ((linea1=br.readLine()) != null) {
				if (!salida.contains(linea1)) {
					salida = linea1 + " " + salida;
				}
			}
			pw.print(salida);
			String[] out_lines = salida.split(" "); 
			for (int aa = 0; aa < out_lines.length; aa++) {
				pw.println(out_lines[aa]);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return(1);
		} finally {
			try {
				if (null != fr) {
					fr.close();
					fw.close();
				}
			}catch (Exception e2) {
				e2.printStackTrace();
				return(1);
			}
			
		}
		return(0);
	}
}
