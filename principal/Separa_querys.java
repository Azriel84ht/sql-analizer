package testeos.principal;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Separa_querys {

	public String split_sql(String tmp_file) {
		String ruta = tmp_file.substring(0,tmp_file.lastIndexOf("\\")) + "\\temp";
		File tmp_dir = new File(ruta);
		tmp_dir.mkdir();
		File fich_entrada = new File(tmp_file);
		String fich_salida = "";
		FileReader fr = null;
		BufferedReader br = null;
		FileWriter fw = null;
		PrintWriter pw = null;
		try {
			fr = new FileReader(fich_entrada);
			br = new BufferedReader(fr);
			String SProcedure = "", linea = "";
			while ((linea=br.readLine()) != null) {
				if (linea.contains("PROCEDURE")) {
					SProcedure = linea.trim().split(" ")[2];
					break;
				}
			}
			int ind_begin = 1;
			fich_salida = ruta + "\\" + SProcedure + ".txt";
			fw = new FileWriter(fich_salida);
			pw = new PrintWriter(fw);
			String linea_out="";
			while ((linea=br.readLine()) != null) {
				if (ind_begin == 0 && !linea.replace("\t", " ").trim().isEmpty()) {
					if (!linea.replace("\t"," ").trim().split(" ")[0].contentEquals("IF") && !linea.replace("\t"," ").trim().contentEquals("END IF;")) {
						linea_out=linea_out+" "+linea;
					}
					if (linea.contains(";") && !linea_out.isEmpty() && !linea.replace("\t"," ").trim().split(" ")[0].contentEquals("END;")) {
						StringTokenizer tokens = new StringTokenizer(linea_out);
						StringBuilder buff = new StringBuilder();
						while (tokens.hasMoreTokens()) {
							buff.append(" ").append(tokens.nextToken());
						}
						linea_out = buff.toString().trim();
						pw.println(linea_out.replace("\t"," ").trim());
						linea_out = "";
					}
				}
				if (linea.trim().matches("BEGIN")){
					ind_begin = 0;
				}
			}
		}catch (Exception e) {
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
		return(fich_salida);
	}

}
