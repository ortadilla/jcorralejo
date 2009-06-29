package utilidades.hibernate;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.Destroy;
import org.jboss.seam.annotations.Unwrap;
import org.jboss.seam.util.Naming;

public abstract class GeosHibernateAplicacion {

    private static final String VAR_AS_NAME = "GEOS_AS_NAME"; 

    protected SessionFactory sessionFactory;
    protected Configuration acfg;
    String showSQL = "false"; 
    //String cacheProviderClass = "org.hibernate.cache.TreeCacheProvider";
    //String cacheProviderClass = "org.hibernate.cache.OSCacheProvider";
    //String cacheProviderClass = "org.hibernate.cache.SwarmCacheProvider";
    //String cacheProviderClass = "org.hibernate.cache.HashtableCacheProvider";
    //String cacheProviderClass = "net.sf.ehcache.hibernate.EhCacheProvider";
    //String cacheProviderClass = "org.hibernate.cache.EhCacheProvider";
    String cacheProviderClass = "org.hibernate.cache.NoCacheProvider";
//    String dialect = GeosOracleDialect.class.getCanonicalName();
//    String dialect = "org.hibernate.dialect.Oracle9Dialect";
    String dialect = "org.hibernate.dialect.MySQLDialect";
    String transactionFlushBeforeCompletion = "true";
    String conectionReleaseMode = "auto";
    String nombreServidor = System.getenv(VAR_AS_NAME)!=null ? System.getenv(VAR_AS_NAME) : "JBoss";
    String transactionManagerLookupClass = "org.hibernate.transaction."+nombreServidor+"TransactionManagerLookup";
    String transactionFactoryClass = "org.hibernate.transaction.JTATransactionFactory";
    String sessionFactoryName ;
    String connectionDatasource;
    
    @Create
    public void startup() throws Exception {
        acfg = new Configuration();
        configurarFactory();
        for(String mapeo : cargarMapeos())
            acfg.addResource(mapeo);
        sessionFactory = acfg.buildSessionFactory();
    }

    @SuppressWarnings("unchecked")
    protected void configurarFactory() {
        acfg.setProperty(Environment.CACHE_PROVIDER,               getCacheProviderClass());
        acfg.setProperty(Environment.FLUSH_BEFORE_COMPLETION,      getTransactionFlushBeforeCompletion());
        acfg.setProperty(Environment.RELEASE_CONNECTIONS,          getConectionReleaseMode());
        acfg.setProperty(Environment.SHOW_SQL,                     getShowSQL());
        acfg.setProperty(Environment.DIALECT,                      getDialect());
        acfg.setProperty(Environment.TRANSACTION_MANAGER_STRATEGY, getTransactionManagerLookupClass());
        acfg.setProperty(Environment.TRANSACTION_STRATEGY,         getTransactionFactoryClass());
        acfg.setProperty(Environment.SESSION_FACTORY_NAME,         getSessionFactoryName());
        acfg.setProperty(Environment.DATASOURCE,                   getConnectionDatasource());
        acfg.setProperty(Environment.USE_SECOND_LEVEL_CACHE, 
        			(getCacheProviderClass().toUpperCase().indexOf("EHCACHE")>-1?"true":"false"));

        // Prefix regular JNDI properties for Hibernate
        Hashtable<String, String> hash = Naming.getInitialContextProperties();
        Properties prefixed = new Properties();
        for (Map.Entry<String, String> entry : hash.entrySet()) {
            String jndiKey = Environment.JNDI_PREFIX + "." + entry.getKey();
            prefixed.setProperty(jndiKey, entry.getValue());
        }
        acfg.getProperties().putAll(prefixed);

        // Establecemos el interceptor para la auditoría de modificaciones en la entidades
//		acfg.setInterceptor(InterceptorHibernateFactory.getInstance().createInterceptorAuditoria());
    }
    
    public abstract List<String> cargarMapeos();

    @Destroy
    public void shutdown() {
        sessionFactory.close();
        acfg=null;
    }


    @Unwrap
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public String getCacheProviderClass() {
        return this.cacheProviderClass;
    }
    public void setCacheProviderClass(String cacheProviderClass) {
        this.cacheProviderClass = cacheProviderClass;
    }

    public String getConectionReleaseMode() {
        return this.conectionReleaseMode;
    }
    public void setConectionReleaseMode(String conectionReleaseMode) {
        this.conectionReleaseMode = conectionReleaseMode;
    }

    public String getDialect() {
        return this.dialect;
    }
    public void setDialect(String dialect) {
        this.dialect = dialect;
    }

    public String getTransactionFlushBeforeCompletion() {
        return this.transactionFlushBeforeCompletion;
    }
    public void setTransactionFlushBeforeCompletion(String transactionFlushBeforeCompletion) {
        this.transactionFlushBeforeCompletion = transactionFlushBeforeCompletion;
    }

    public String getShowSQL() {
        return this.showSQL;
    }
    public void setShowSQL(String showSQL) {
        this.showSQL = showSQL;
    }
	
	public String getConnectionDatasource() {
		return this.connectionDatasource;
	}

	public void setConnectionDatasource(String connectionDatasource) {
		this.connectionDatasource = connectionDatasource;
	}

	public String getTransactionFactoryClass() {
		return this.transactionFactoryClass;
	}

	public void setTransactionFactoryClass(String transactionFactoryClass) {
		this.transactionFactoryClass = transactionFactoryClass;
	}
	public String getTransactionManagerLookupClass() {
		return this.transactionManagerLookupClass;
	}

	public void setTransactionManagerLookupClass(
			String transactionManagerLookupClass) {
		this.transactionManagerLookupClass = transactionManagerLookupClass;
	}

	public String getSessionFactoryName() {
		return this.sessionFactoryName;
	}

	public void setSessionFactoryName(String sessionFactoryName) {
		this.sessionFactoryName = sessionFactoryName;
	}
	
}
