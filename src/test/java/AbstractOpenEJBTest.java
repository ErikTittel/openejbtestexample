import org.apache.openejb.jee.EjbJar;
import org.apache.openejb.jee.EnterpriseBean;
import org.apache.openejb.jee.jpa.unit.PersistenceUnit;
import org.apache.openejb.junit.ApplicationComposer;
import org.apache.openejb.testing.Configuration;
import org.apache.openejb.testing.Module;
import org.junit.runner.RunWith;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import java.util.List;
import java.util.Properties;

/**
 * @author Erik Tittel
 */
@RunWith(ApplicationComposer.class)
public abstract class AbstractOpenEJBTest {


    @Resource
    protected UserTransaction userTransaction;

    @PersistenceContext
    protected EntityManager em;

    @Module
    public PersistenceUnit persistence() {
        PersistenceUnit unit = new PersistenceUnit("simple-unit");
        List<String> entityNames = getEntityNames();
        for (String entityName : entityNames) {
            unit.getClazz().add(entityName);
        }
        unit.setProperty("openjpa.jdbc.SynchronizeMappings", "buildSchema(ForeignKeys=true)");
        return unit;
    }

    protected abstract List<String> getEntityNames();

    @Module
    public EjbJar beans() {
        EjbJar ejbJar = new EjbJar("simple-beans");
        List<EnterpriseBean> beans = getBeans();
        for (EnterpriseBean bean : beans) {
            ejbJar.addEnterpriseBean(bean);
        }
        return ejbJar;
    }

    protected abstract List<EnterpriseBean> getBeans();

    @Configuration
    public Properties config() {
        Properties p = new Properties();
        p.put("simpleDS", "new://Resource?type=DataSource");
        p.put("simpleDS.JdbcDriver", "org.hsqldb.jdbcDriver");
        p.put("simpleDS.JdbcUrl", "jdbc:hsqldb:mem:simpledb");
        return p;
    }

}
