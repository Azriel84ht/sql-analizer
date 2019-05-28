package testeos.principal;

import java.util.Scanner;

public class C_Main {

	public static void main(String[] args) {
		String f_input = "";
		Scanner sc = new Scanner(System.in);
		System.out.println("Fichero de entrada:");
		f_input = sc.nextLine();
		sc.close();
		LimpiaComentarios a = new LimpiaComentarios();
		String f_temp1 = a.comclean(f_input);
		String f_toclean = "";
		
		Separa_querys b = new Separa_querys();
		String f_temp2 = b.split_sql(f_temp1);
		
		objs_modifs c = new objs_modifs();
		c.busca_wri(f_temp2);
		
		objs_leidos d = new objs_leidos();
		f_toclean = d.busca_lec(f_temp2);
		
		borraDuplis e = new borraDuplis();
		e.dupliClean(f_toclean);
		
	}
}
