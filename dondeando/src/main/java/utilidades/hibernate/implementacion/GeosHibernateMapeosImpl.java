package utilidades.hibernate.implementacion;

import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import utilidades.hibernate.GeosHibernateMapeos;

/**
 * Lee los mapeos y dataset a cargar a partir del fichero de configuración. Este fichero debe cumplir:
 * 
 * http://www.hp-cda.com/productos/geos/dtd/entidadesBD.dtd
 * 
 * @author jmarjona
 */
public class GeosHibernateMapeosImpl implements GeosHibernateMapeos {
    private Log log = LogFactory.getLog(GeosHibernateMapeosImpl.class);
    
    private String configXML;
    
    
    public GeosHibernateMapeosImpl(){
        //Habrá que setear aparte el configXML;
    }
    public GeosHibernateMapeosImpl(String configXML){
        this.configXML = configXML;
    }

    /* (non-Javadoc)
     * @see com.hp.geos.core.dao.hibernate.GeosHibernateMapeos#dameMapeosModulo()
     */
    public List<String> dameMapeosModulo() {
        
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see com.hp.geos.core.dao.hibernate.GeosHibernateMapeos#dameMapeosModuloyDependientes()
     */
    public List<String> dameMapeosModuloyDependientes() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * 
     * @return lista de xml que deben estar contenidos en directorio
     *         src/main/resources/datasets
     * 
     */
    public List<String> dameXMLModulo(){
        return cargarDatasets(configXML);
    }
    /**
     * 
     * @return lista de xml que deben estar contenidos en directorio
     *         src/main/resources/datasets
     * 
     */
    public List<String> dameXMLModuloyDependientes(){
        List<String> datasets = new ArrayList<String>();
        
        List<String> modulos = cargarDependencias(configXML);
        
        for(String modulo : modulos)
            datasets.addAll(cargarDatasets(modulo));

        return datasets;
    }

    public void setConfigXML(String configXML) {
        this.configXML = configXML;
    }
    
    private List<String> cargarDatasets(String fichero){
        return cargarLista(fichero, "entidad", "dataset");
    }

    /** Devuelve las dependencias (recursivamente), más el propio fichero indicado */
    private List<String> cargarDependencias(String fichero){
        List<String> res = new ArrayList<String>();
        //Para cada dependencia, cargamos añadimos sus depenedencias recursivamente antes de ella
        for(String dep : cargarLista(fichero, "dependencia", "ficheroConf")){
            List<String> recursivas = cargarDependencias(dep);
            //Las añadimos al resultado si no las contiene ya, para evitar repetirlas
            for(String rec : recursivas)
                if(!res.contains(rec))
                    res.add(rec);
        }
        //Añadimos el propio fichero, si no está ya
        if(!res.contains(fichero))
            res.add(fichero);
        return res;
    }
    
    private List<String> cargarLista(String fichero, String lista, String atributo){
        List<String> res = new ArrayList<String>();
        
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        try {
            Enumeration e = cl.getResources(fichero);
            if(e.hasMoreElements()){
                URL url = (URL)e.nextElement();
                log.info("Escaneando "+url);
                Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder()
                                                     .parse(url.toURI().toString());
                NodeList listaDep = doc.getElementsByTagName("entidades");
                if(listaDep.getLength()>0){
                    NodeList hijos = listaDep.item(0).getChildNodes();
                    for(int i=0; i<hijos.getLength(); i++){
                        Node nodo = hijos.item(i);
                        if(nodo.getNodeName().equals(lista) && nodo.getAttributes()!=null){
                            Node valor = nodo.getAttributes().getNamedItem(atributo);
                            if(valor!=null){
                                log.debug("Añadiendo "+atributo+" "+valor.getNodeValue());
                                res.add(valor.getNodeValue());
                            }
                        }
                    }
                }

            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

        return res;
    }

}
