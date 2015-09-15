import org.apache.openejb.jee.EnterpriseBean;
import org.apache.openejb.jee.StatelessBean;
import org.junit.Test;

import javax.ejb.EJB;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * @author Erik Tittel
 */
public class OpenEJBTest extends AbstractOpenEJBTest {

    @EJB
    private SimpleDao dao;

    @Override
    protected List<String> getEntityNames() {
        return Arrays.asList(SimpleEntity.class.getName());
    }

    @Override
    protected List<EnterpriseBean> getBeans() {
        return Arrays.asList((EnterpriseBean)new StatelessBean(SimpleDaoImpl.class));
    }

    @Test
    public void testDao() throws Exception {

        userTransaction.begin();

        try {
            em.persist(new SimpleEntity(1L, "Alice"));
            em.persist(new SimpleEntity(2L, "Bob"));
            em.persist(new SimpleEntity(3L, "Carol"));

            List<SimpleEntity> entities = dao.getEntities();
            assertThat(entities.size(), is(3));

            for (SimpleEntity e : entities) {
                dao.deleteEntity(e);
            }

            assertThat(dao.getEntities().size(), is(0));
        } finally {
            userTransaction.commit();
        }
    }
}
