package biblioTec.utilidades;

public class HerramientasCriteria {
	
    public static String getValorLike(String valor){
        String res = new String(valor).replace('*', '%').replace('?', '_');
        if(!res.contains("%") && !res.contains("_")) 
            res += "%";
        return res;
    }

}
