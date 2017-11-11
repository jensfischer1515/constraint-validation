package jensfischerhh.constraintvalidation;

import java.util.stream.Collectors;

import javax.validation.ConstraintViolationException;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.support.PersistenceExceptionTranslator;

/**
 * Translates {@link ConstraintViolationException}s into {@link DataIntegrityViolationException}s, adding
 * property path and message for all contained constraint violations to the exception message.
 *
 * @author Gunnar Morling
 */
public class ConstraintViolationExceptionMessageEnricher implements PersistenceExceptionTranslator {

	@Override
	public DataAccessException translateExceptionIfPossible(RuntimeException ex) {
		if (ex instanceof ConstraintViolationException) {
			String message = ex.getMessage() + System.lineSeparator();
			message += ( (ConstraintViolationException) ex ).getConstraintViolations()
				.stream()
				.map( cv -> cv.getPropertyPath() + ": " + cv.getMessage() )
				.collect( Collectors.joining( ";" + System.lineSeparator() ) );

			return new DataIntegrityViolationException( message, ex );
		}

		return null;
	}
}
