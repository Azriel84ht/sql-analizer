package testeos.principal;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Separa_querys {

	public String split_sql(String tmp_file) {
		String path = tmp_file.substring(0,tmp_file.lastIndexOf("\\")) + "\\temp";
		File tmp_dir = new File(path);
		tmp_dir.mkdir();
		File in_file = new File(tmp_file);
		String out_file = "";
		FileReader FlRd = null;
		BufferedReader BfRd = null;
		FileWriter FlWr = null;
		PrintWriter PrWr = null;
		String p = "PROCEDURE";
		try {
			FlRd = new FileReader(in_file);
			BfRd = new BufferedReader(FlRd);
			String SProcedure = "", line = "";
			while ((line=BfRd.readLine()) != null) {
				if (line.contains(p)) {
					SProcedure = line.trim().split(" ")[2];
					break;
				}
			}
			int ind_begin = 1;
			out_file = path + "\\" + SProcedure + ".txt";
			FlWr = new FileWriter(out_file);
			PrWr = new PrintWriter(FlWr);
			String line_out="";
			while ((line=BfRd.readLine()) != null) {
				if (ind_begin == 0 && !line.replace("\t", " ").trim().isEmpty()) {
					if (!line.replace("\t"," ").trim().split(" ")[0].contentEquals("IF") && !line.replace("\t"," ").trim().contentEquals("END IF;")) {
						line_out=line_out+" "+line;
					}
					if (line.contains(";") && !line_out.isEmpty() && !line.replace("\t"," ").trim().split(" ")[0].contentEquals("END;")) {
						StringTokenizer tokens = new StringTokenizer(line_out);
						StringBuilder buff = new StringBuilder();
						while (tokens.hasMoreTokens()) {
							buff.append(" ").append(tokens.nextToken());
						}
						line_out = buff.toString().trim();
						PrWr.println(line_out.replace("\t"," ").trim());
						line_out = "";
					}
				}
				if (line.trim().matches("BEGIN")){
					ind_begin = 0;
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != FlRd) {
					FlRd.close();
					FlWr.close();
				}
			}catch (Exception f) {
				f.printStackTrace();
			}
		}
		return(out_file);
	}

}
