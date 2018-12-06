package br.com.sistema;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Convert {
	public static String toDate(String formato,Object valor) {
		 Date data = null;
		 String dataString = null;
		dataString = new SimpleDateFormat(formato).format(valor);
		//data =new SimpleDateFormat(formato).parse(dataString);

		return dataString;
	}
}
