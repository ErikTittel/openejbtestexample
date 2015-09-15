import static javax.ejb.TransactionAttributeType.MANDATORY;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @author Erik Tittel
 */
@Stateless(name = "SimpleDao")
@TransactionAttribute(MANDATORY)
public class SimpleDaoImpl implements SimpleDao {

    @PersistenceContext(unitName = "simple-unit", type = PersistenceContextType.TRANSACTION)
    private EntityManager em;

    public void addEntity(SimpleEntity se) {
        em.persist(se);
    }

    public void deleteEntity(SimpleEntity se) {
        em.remove(se);
    }

    public List<SimpleEntity> getEntities() {
        Query query = em.createQuery("SELECT e from SimpleEntity as e");
        return query.getResultList();
    }
}
