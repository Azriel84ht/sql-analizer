package testeos.principal;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

public class objs_modifs {

	public void busca_wri(String f_input) {
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
		String f_output = f_input.substring(0,p_ext) + "_output.txt";
		try {
			fich_entrada = new File(f_input);
			fw = new FileWriter(f_output);
			fr = new FileReader(fich_entrada);
			pw = new PrintWriter(fw);
			br = new BufferedReader(fr);
			String linea1 = "";
			
			while ((linea1=br.readLine()) != null) {
				String[] w_linea = linea1.replace(";","").split(" ");
				String query = "",bbdd = "", tabla = "";
				switch (w_linea[0]) {
					case "INSERT":
						query = w_linea[0];
						bbdd = w_linea[2].substring(0,w_linea[2].indexOf("."));
						tabla = w_linea[2].substring(w_linea[2].indexOf(".")+1);
						break;
					case "UPDATE":
						query = w_linea[0];
						if (!w_linea[1].contains(".") && w_linea[2].contentEquals("FROM")) {
							String alias = w_linea[1];
							for (i=2;i < w_linea.length;i++) {
								if (w_linea[i].replace(",", "").contentEquals(alias)) {
									bbdd = w_linea[i-1].substring(0,w_linea[i-1].indexOf("."));
									tabla = w_linea[i-1].substring(w_linea[i-1].indexOf(".")+1);
								}
							}
						}else{
							bbdd = w_linea[1].substring(0,w_linea[1].indexOf("."));
							tabla = w_linea[1].substring(w_linea[1].indexOf(".")+1);
						}
						break;
					case "DELETE":
						query = w_linea[0];
						bbdd = w_linea[2].substring(0,w_linea[2].indexOf("."));
						tabla = w_linea[2].substring(w_linea[2].indexOf(".")+1);
						break;
				}
				if (!query.contentEquals(""))
				pw.println(query + "\t" + bbdd + "\t" + tabla);
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
