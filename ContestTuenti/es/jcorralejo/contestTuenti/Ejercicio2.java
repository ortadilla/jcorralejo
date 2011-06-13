package es.jcorralejo.contestTuenti;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

import javax.print.attribute.standard.MediaSize.ISO;

public class Ejercicio2 {
	
	private static int MAX_PRUEBAS = 100; 

//	public static void main (String[] args){
//		try{
//			BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
//			int i=0;
//			while(i<MAX_PRUEBAS){
//				String linea = br.readLine();
//				if(linea!=null && !"".equals(linea)
//				&& linea.charAt(0)=='^' 
//				&& linea.charAt(linea.length()-1)=='$'){
//					
//					char op = linea.charAt(1);
//					linea = linea.replace('+', ' ').substring(2, linea.length()-1);
//					StringTokenizer tokens = new StringTokenizer(linea);
//					BigInteger res = null;
//					
//					if(op=='='){
//						res = BigInteger.ZERO;
//						while(tokens.hasMoreTokens())
//							res = res.add(new BigInteger(tokens.nextToken()));
//					}
//					
//					else if(op=='#'){
//						res = BigInteger.ONE;
//						while(tokens.hasMoreTokens())
//							res = res.multiply(new BigInteger(tokens.nextToken()));
//					}
//					
//					else if (op=='@'){
//						if(tokens.hasMoreTokens())
//							res = new BigInteger(tokens.nextToken());
//						while(tokens.hasMoreTokens())
//							res = res.subtract(new BigInteger(tokens.nextToken()));
//					}
//					
//					System.out.println (res);
//				}
//				i++;
//			}
//		}catch (Exception e){
//			e.printStackTrace();
//		}
//	}
	
	public static void main (String[] args){
		try{
			BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
			int i=0;
			while(i<MAX_PRUEBAS){
				String linea = br.readLine();
				
				BigInteger res = null;
				if(isOpBasica(linea)){
					char operador = linea.charAt(1);
					linea = linea.replace('+', ' ').substring(2, linea.length()-1);
					StringTokenizer tokens = new StringTokenizer(linea);
					res = calcular(operador, new BigInteger(tokens.nextToken()), new BigInteger(tokens.nextToken()));
				}
					
				System.out.println(res);
				
				i++;
			}
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	
	private static boolean isOpBasica(String operacion){
		return operacion!=null 
			&& operacion.startsWith("^") && operacion.endsWith("$")
			&& operacion.split("\\^").length==1 && operacion.split("\\$").length==1;
	}
	
	private static BigInteger calcular(char operacion, BigInteger op1, BigInteger op2){
		BigInteger res = null;
		
		if(operacion=='=')
			res = op1.add(op2);
		
		else if(operacion=='#')
			res = op1.multiply(op2);
		
		else if (operacion=='@'){
			res = op1;
			if(op2!=null)
			res = res.subtract(op2);
		}
		
		return res;
	}
}
