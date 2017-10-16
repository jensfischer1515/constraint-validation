package jensfischerhh.constraintvalidation;

import static org.assertj.core.api.Assertions.catchThrowable;
import static org.assertj.core.api.BDDAssertions.then;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest(properties = {
        "spring.jpa.generate-ddl=true",
        "spring.jpa.show-sql=true",
        "logging.level.ROOT=WARN"
})
@RunWith(SpringRunner.class)
public class ApplicationTests {

    @Autowired
    private PersonRepository repository;

    @Test
    public void should_save_valid_entity() {
        // GIVEN
        String name = "Max Mustermann";

        // WHEN
        Person person = repository.saveAndFlush(new Person(name));

        // THEN
        then(person.getId()).isPositive();
    }

    @Test
    public void should_provide_validation_details_on_invalid_entity() {
        // GIVEN
        String name = "Name-Which-Is-Way-Too-Long";

        // WHEN
        ConstraintViolationException cause = (ConstraintViolationException) catchThrowable(() ->
                repository.saveAndFlush(new Person(name))
        );
        cause.printStackTrace();

        // THEN
        then(cause.getConstraintViolations())
                .isNotEmpty()
                .extracting(ConstraintViolation::getMessage)
                .containsOnly("length must be between 1 and 20");
        then(cause)
                .hasMessageContaining("length must be between 1 and 20")
                .describedAs("missing violation detail message");
    }
}
