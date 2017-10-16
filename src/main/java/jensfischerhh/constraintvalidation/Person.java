package jensfischerhh.constraintvalidation;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Access(AccessType.FIELD)
public class Person extends AbstractPersistable<Long> {

    private Person() {
        super();
    }

    public Person(String name) {
        this.name = name;
    }

    @NotNull
    @Length(min = 1, max = 20)
    private String name;
}
