package testeos.principal;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

public class objs_leidos {

	public void busca_lec(String f_input) {
		File fich_entrada = null;
		FileReader fr = null;
		FileWriter fw = null;
		PrintWriter pw = null;
		BufferedReader br = null;
		int p_ext = 0 ,i = 0;
		for (i=0; i < f_input.length(); i++) {
			if (f_input.charAt(i) == '.') {
				p_ext = i;
			}
		}
		String f_output = f_input.substring(0,p_ext) + "_input.txt";
		try {
			fich_entrada = new File(f_input);
			fw = new FileWriter(f_output);
			fr = new FileReader(fich_entrada);
			pw = new PrintWriter(fw);
			br = new BufferedReader(fr);
			String linea1 = "";
			
			while ((linea1=br.readLine()) != null) {
				String bbdd = "", tabla = "";
				String[] w_linea = linea1.split(" ");
				for (int i1 = 0;i1 < w_linea.length; i1++) {
					if ((w_linea[i1].contentEquals("JOIN") || w_linea[i1].contentEquals("FROM")) && w_linea[i1+1].contains(".")) {
						if (!w_linea[i1-1].contains("EXTRACT")) {
							bbdd = w_linea[i1+1].substring(0,w_linea[i1+1].indexOf("."));
							tabla = w_linea[i1+1].substring(w_linea[i1+1].indexOf(".")+1);
						}
					}
					if (!bbdd.contentEquals("") && !tabla.contentEquals("")) {
						pw.println(bbdd + "\t" + tabla);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != fr) {
					fr.close();
					fw.close();
				}
			}catch (Exception e2) {
				e2.printStackTrace();
			}
			
		}
	}

}
