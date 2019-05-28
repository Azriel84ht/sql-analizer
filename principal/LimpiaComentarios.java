package testeos.principal;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

public class LimpiaComentarios {

	public String ClnCmm(String f_input) {
		File in_file = null;
		FileReader FlRd = null;
		FileWriter FlWr = null;
		PrintWriter PrWr = null;
		BufferedReader BfRd = null;
		int p_ext = 0 ,i = 0;
		for (i=0; i < f_input.length(); i++) {
			if (f_input.charAt(i) == '.') {
				p_ext = i;
			}
		}
		String out_file = f_input.substring(0,p_ext) + "_tmp1.txt";
		try {
			in_file = new File(f_input);
			FlWr = new FileWriter(out_file);
			FlRd = new FileReader(in_file);
			PrWr = new PrintWriter(FlWr);
			BfRd = new BufferedReader(FlRd);
			String line1 = null;
			int RdIx = 0; //Reading index
			while ((line1=BfRd.readLine()) != null) {
				if (!line1.trim().isEmpty()) {
					if (line1.contains("/*")) {
						if (line1.contains("*/")){
							if (line1.indexOf("/*") < line1.indexOf("*/")) {
								line1 = line1.substring(0,line1.indexOf("/*")) + line1.substring(line1.indexOf("*/")+2);
								RdIx = 0;
							}else if (line1.indexOf("/*") > line1.indexOf("*/")) {
								line1 = line1.substring(line1.indexOf("*/")+2,line1.indexOf("/*"));
								RdIx = 1;
							}
						}else {
							line1=line1.substring(0,line1.indexOf("/*"));
							RdIx = 1;
						}
					}else if (line1.contains("*/")) {
						line1 = line1.substring(line1.indexOf("*/")+2);
						RdIx = 0;
					}else if (line1.contains("--")) {
						line1 = line1.substring(0,line1.indexOf("--"));
					}
					if (RdIx == 0) {
						PrWr.println(line1);
					}else if (RdIx == 1) {
						PrWr.println(line1);
						RdIx = 2;
					}
				}
			}
		} catch (Exception e) {
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
