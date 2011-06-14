package es.jcorralejo.contestTuenti;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Ejercicio2 {
	
	private static int MAX_PRUEBAS = 100; 
	
	public static void main (String[] args){
		try{
			BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
			int i=0;
			while(i<MAX_PRUEBAS){
				String linea = br.readLine();
				if(linea!=null){
					BigInteger res = null;
					while (!linea.isEmpty()){
						if(isOpBasica(linea)){
							res = calcular(linea);
							break;
						}else{
							int idx1 = linea.lastIndexOf('^');
							int ids2 = linea.substring(idx1).indexOf('$');
							String operacion = linea.substring(idx1, idx1+ids2+1);
							BigInteger resOp = calcular(operacion);
							linea = linea.replace(operacion, resOp.toString());
						}
					}
					System.out.println(res);
				}
				
				i++;
			}
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	

	private static boolean isOpBasica(String operacion){
		return operacion.matches("^\\^[=|#|@]\\s*[-]?[0-9]*\\s*[-]?[0-9]*\\s*\\$$");
	}
	
	private static BigInteger calcular(String calculo){
		
		char operador = calculo.charAt(1);
		calculo = calculo.replace('+', ' ').substring(2, calculo.length()-1);
		StringTokenizer tokens = new StringTokenizer(calculo);
		BigInteger op1 = new BigInteger(tokens.nextToken());
		BigInteger op2 = tokens.hasMoreTokens() ? new BigInteger(tokens.nextToken()) : null;
		
		BigInteger res = null;
		if(operador=='=')
			res = op1.add(op2);
		else if(operador=='#')
			res = op1.multiply(op2);
		else if (operador=='@'){
			res = op1;
			if(op2!=null)
				res = res.subtract(op2);
			else
				res = BigInteger.ZERO.subtract(res);
		}
		
		return res;
	}

}
