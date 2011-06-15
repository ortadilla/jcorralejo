package es.jcorralejo.contestTuenti;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Ejercicio4 {
	
	private static String NUMBER_OF_TASK = "#Number of tasks";
	private static String TASK_DURATION = "#Task duration";
	private static String TASK_DEPENDENCIES = "#Task dependencies";

	private static int MAX_PRUEBAS = 100;
	private static int numTask = 0;
	private static Map<Integer, Integer> duracion = new HashMap<Integer, Integer>();
	private static Map<Integer, List<Integer>> dependencias = new HashMap<Integer, List<Integer>>();


	public static void main (String[] args){
		try{
			
			cargarDatos();

			BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
			int i=0;
			while(i<MAX_PRUEBAS){
				String linea = br.readLine();
				if(linea!=null && !"".equals(linea)){
					
					StringTokenizer tokens = new StringTokenizer(linea, ",");
					while(tokens.hasMoreTokens()){
						int task = Integer.parseInt(tokens.nextToken());
						int duracion = calcularDuracion(task);
						System.out.println (task + " " + duracion);
					}
				}
				i++;
			}
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	private static int calcularDuracion(int task){
		int dur = 0;
		
		if(!dependencias.containsKey(task))
			dur = duracion.get(task);
		else{
			List<Integer> durDep = new ArrayList<Integer>();
			for(Integer dep : dependencias.get(task))
				durDep.add(calcularDuracion(dep));

			Collections.sort(durDep);
			dur = duracion.get(task) + durDep.get(durDep.size()-1);
		}
		
		
		return dur;
	}
	
	private static void cargarDatos(){

		FileReader fr = null;
		BufferedReader br = null;
		try {
			fr = new FileReader (new File ("C:\\in"));
			br = new BufferedReader(fr);

			boolean cargarNumTask = false;
			boolean cargarTaskDur = false;
			boolean cargarTaskDep = false;
			String linea;
			while((linea=br.readLine())!=null){
				if(!"".equals(linea)){
					if(cargarNumTask){
						numTask = Integer.parseInt(linea);
					}

					else if(cargarTaskDur){
						String[] dur = linea.split(",");
						duracion.put(Integer.parseInt(dur[0]), Integer.parseInt(dur[1]));
					}

					else if(cargarTaskDep){
						String[] dep = linea.split(",");
						List<Integer> dependecias = new ArrayList<Integer>();
						for(int i=1; i<dep.length;i++)
							dependecias.add(Integer.parseInt(dep[i]));
						dependencias.put(Integer.parseInt(dep[0]), dependecias);
					}


					if(NUMBER_OF_TASK.equals(linea))
						cargarNumTask = true;
					else if (TASK_DURATION.equals(linea))
						cargarTaskDur = true;
					else if (TASK_DEPENDENCIES.equals(linea))
						cargarTaskDep = true;
				}else{
					cargarNumTask = false;
					cargarTaskDur = false;
					cargarTaskDep = false;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{                    
				if( null != fr )   
					fr.close();     
			}catch (Exception e2){ 
				e2.printStackTrace();
			}
		}
	}
}
