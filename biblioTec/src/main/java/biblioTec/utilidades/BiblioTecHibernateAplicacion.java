package biblioTec.utilidades;

import static org.jboss.seam.InterceptionType.NEVER;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.Destroy;
import org.jboss.seam.annotations.Intercept;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.Startup;
import org.jboss.seam.annotations.Unwrap;
import org.jboss.seam.util.Naming;

@Scope(ScopeType.APPLICATION)
@Intercept(NEVER)
@Startup(depends = "org.jboss.seam.core.microcontainer")
public class BiblioTecHibernateAplicacion{

	private SessionFactory sessionFactory;
	private Configuration acfg;
	
	private String showSQL = "false"; 
	private String cacheProviderClass = "org.hibernate.cache.NoCacheProvider";
	private String dialect = "org.hibernate.dialect.MySQLDialect";
	private String transactionFlushBeforeCompletion = "true";
	private String conectionReleaseMode = "auto";
	private String transactionFactoryClass = "org.hibernate.transaction.JTATransactionFactory";
	private String sessionFactoryName ;
	private String driver = "com.mysql.jdbc.Driver" ;
//	private String connectionURL = "jdbc:mysql://localhost:3306/biblioTec";
//	private String user = "user";
//	private String pass = "user";
	private String autocommit = "true";
	private String dataSource = "java:comp/env/jdbc/hibernateDatasource";
	
	private static String pathMapeos = "mapeos/";

	@Create
	public void startup() throws Exception {
		acfg = new Configuration();
		configurarFactory();
		for(String mapeo : getMapeos())
			acfg.addResource(mapeo);
		sessionFactory = acfg.buildSessionFactory();
	}

	@SuppressWarnings("unchecked")
	protected void configurarFactory() {
		acfg.setProperty(Environment.CACHE_PROVIDER,            cacheProviderClass);
		acfg.setProperty(Environment.FLUSH_BEFORE_COMPLETION,   transactionFlushBeforeCompletion);
		acfg.setProperty(Environment.RELEASE_CONNECTIONS,       conectionReleaseMode);
		acfg.setProperty(Environment.SHOW_SQL,                  showSQL);
		acfg.setProperty(Environment.DIALECT,                   dialect);
		acfg.setProperty(Environment.TRANSACTION_STRATEGY,      transactionFactoryClass);
//		acfg.setProperty(Environment.URL,         				connectionURL);
		acfg.setProperty(Environment.DRIVER,       				driver);
		acfg.setProperty(Environment.SESSION_FACTORY_NAME,      sessionFactoryName);
//		acfg.setProperty(Environment.USER,         		   		user);
//		acfg.setProperty(Environment.PASS,         		   		pass);
		acfg.setProperty(Environment.AUTOCOMMIT,         		autocommit);
		acfg.setProperty(Environment.DATASOURCE,         		dataSource);
		acfg.setProperty(Environment.USE_SECOND_LEVEL_CACHE, 	(cacheProviderClass.toUpperCase().indexOf("EHCACHE")>-1?"true":"false"));

		// Prefix regular JNDI properties for Hibernate
		Hashtable<String, String> hash = Naming.getInitialContextProperties();
		Properties prefixed = new Properties();
		for (Map.Entry<String, String> entry : hash.entrySet()) {
			String jndiKey = Environment.JNDI_PREFIX + "." + entry.getKey();
			prefixed.setProperty(jndiKey, entry.getValue());
		}
		acfg.getProperties().putAll(prefixed);

	}

	@Destroy
	public void shutdown() {
		sessionFactory.close();
		acfg=null;
	}


	@Unwrap
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

    private List<String> getMapeos() {
        List<String> mapeos = new ArrayList<String>();
        
        mapeos.add(pathMapeos+"Ejemplo.hbm.xml");
        mapeos.add(pathMapeos+"Libro.hbm.xml");
        mapeos.add(pathMapeos+"Usuario.hbm.xml");
        mapeos.add(pathMapeos+"Prestamo.hbm.xml");
        mapeos.add(pathMapeos+"Perfil.hbm.xml");
        mapeos.add(pathMapeos+"PermisoPerfil.hbm.xml");
        
        return mapeos;
    }
    
    public String getSessionFactoryName() {
		return this.sessionFactoryName;
	}

	public void setSessionFactoryName(String sessionFactoryName) {
		this.sessionFactoryName = sessionFactoryName;
	}
	
}
