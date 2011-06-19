package es.jcorralejo.contestTuenti;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import javax.imageio.ImageIO;

public class Ejercicio14 {

	private static int MAX_PRUEBAS = 100; 

	public static void main(String[] foo) {
		new Ejercicio14();
	}

	public int printPixelARGB(int pixel, char canal) {
		int res;
		
		int alpha = (pixel >> 24) & 0xff;
		
		if('R'==canal)
			res = (pixel >> 16) & 0xff;
		else if ('G'==canal)
			res = (pixel >> 8) & 0xff;
		else
			res = (pixel) & 0xff;
		
		return res;
	}

	private void sumarPixels (BufferedImage imagen, char canal, int linea) {
		int w = imagen.getWidth();
		int h = imagen.getHeight();
		
		int suma = 0;
		
		for (int j = 0; j < w; j++) {
			int pixel = imagen.getRGB(j, linea);
			suma += printPixelARGB(pixel, canal);
		}
		
		System.out.println(suma+1);
		
	}

	public Ejercicio14() {
		try{
			BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
			int i=0;
			BufferedImage imagen = ImageIO.read(this.getClass().getResource("trabaja.bmp"));
			while(i<MAX_PRUEBAS){
				String linea = br.readLine();
				if(linea!=null && !"".equals(linea)){
					StringTokenizer tokens = new StringTokenizer(linea);
					String cadena = ""; 
					if(tokens.hasMoreTokens())
						cadena = tokens.nextToken();

					if(!cadena.isEmpty()){
						char canal = cadena.charAt(0);
						int line = Integer.parseInt(cadena.substring(1));
						
						sumarPixels(imagen, canal, line);
					}

				}
				i++;
			}
		}catch (Exception e){
			e.printStackTrace();
		}
	}

}
