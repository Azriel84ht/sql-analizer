package testeos.principal;

import java.util.Scanner;

public class Principal {

	public static void main(String[] args) {
		String f_input = "";
		if (!args[1].contentEquals("")) {
			Scanner sc = new Scanner(System.in);
			System.out.println("Fichero de entrada:");
			f_input = sc.nextLine();
			sc.close();
		}else {
			f_input = args[1];
		}
		//String f_temp1 = "";
		LimpiaComentarios a = new LimpiaComentarios();
		String f_temp1 = a.comclean(f_input);
		
		Separa_querys b = new Separa_querys();
		String f_temp2 = b.split_sql(f_temp1);
		
		objs_modifs c = new objs_modifs();
		c.busca_wri(f_temp2);
		
		objs_leidos d = new objs_leidos();
		d.busca_lec(f_temp2);
		//F_destinos b = new F_destinos();
		//b.f_dest(f_temp1);
	}
}
