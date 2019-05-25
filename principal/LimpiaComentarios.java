package testeos.principal;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

public class LimpiaComentarios {

	public String comclean(String f_input) {
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
		String f_output = f_input.substring(0,p_ext) + "_tmp1.txt";
		try {
			fich_entrada = new File(f_input);
			fw = new FileWriter(f_output);
			fr = new FileReader(fich_entrada);
			pw = new PrintWriter(fw);
			br = new BufferedReader(fr);
			String linea1 = null;
			int ind_lectura = 0;
			while ((linea1=br.readLine()) != null) {
				if (!linea1.trim().isEmpty()) {
					if (linea1.contains("/*")) {
						if (linea1.contains("*/")){
							if (linea1.indexOf("/*") < linea1.indexOf("*/")) {
								linea1 = linea1.substring(0,linea1.indexOf("/*")) + linea1.substring(linea1.indexOf("*/")+2);
								ind_lectura = 0;
							}else if (linea1.indexOf("/*") > linea1.indexOf("*/")) {
								linea1 = linea1.substring(linea1.indexOf("*/")+2,linea1.indexOf("/*"));
								ind_lectura = 1;
							}
						}else {
							linea1=linea1.substring(0,linea1.indexOf("/*"));
							ind_lectura = 1;
						}
					}else if (linea1.contains("*/")) {
						linea1 = linea1.substring(linea1.indexOf("*/")+2);
						ind_lectura = 0;
					}else if (linea1.contains("--")) {
						linea1 = linea1.substring(0,linea1.indexOf("--"));
					}
					if (ind_lectura == 0) {
						pw.println(linea1);
					}else if (ind_lectura == 1) {
						pw.println(linea1);
						ind_lectura = 2;
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
		return(f_output);
	}

}
