import javax.ejb.Local;
import java.util.List;

/**
 * @author Erik Tittel
 */
@Local
public interface SimpleDao {

    void addEntity(SimpleEntity se);

    void deleteEntity(SimpleEntity se);

    List<SimpleEntity> getEntities();
}
