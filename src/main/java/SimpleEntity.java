import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author Erik Tittel
 */
@Entity
public class SimpleEntity {

    @Id
    private Long id;

    @Column
    private String name;

    public SimpleEntity() {
    }

    public SimpleEntity(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
