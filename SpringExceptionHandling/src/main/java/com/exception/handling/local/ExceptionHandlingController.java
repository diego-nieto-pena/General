package com.exception.handling.local;

import java.sql.SQLException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.exception.handling.exception.DataAccessException;
import com.exception.handling.exception.DataIntegrityViolationException;
import com.exception.handling.exception.DatabaseException;
import com.exception.handling.exception.InvalidCreditCardException;
import com.exception.handling.exception.SupportInfoException;
import com.exception.handling.exception.UnhandledException;
import com.exception.handling.exceptions.OrderNotFoundException;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/local")
@Slf4j
public class ExceptionHandlingController {

	@RequestMapping("")
	public String home1() {
		log.info("Local home page 1");
		return "local";
	}
	
	@RequestMapping("/")
	public String home2() {
		log.info("Localhome page 2");
		return "local";
	}
	
	/**
	 * No handler is needed for this exception, as the
	 * Exception class it self is annotated with @ResponseStatus
	 * @return
	 */
	@RequestMapping("/orderNotFound")
	String throwOrderNotFoundException() {
		log.info("Throw OrderNotFoundException for unknown order 666");
		throw new OrderNotFoundException("666");
	}
	
	/**
	 * Throws an unnannotated  DataIntegrityViolationException, 
	 * must be caught by an exception handler. 
	 * @return Nothing - it always throws the exception
	 * @throws DataIntegrityViolationException
	 *             Always thrown.
	 */
	@RequestMapping("/dataIntegrityViolation")
	String throwDataIntegrityViolationException() throws SQLException {
		log.info("Throw DataIntegrityViolationException");
		throw new DataIntegrityViolationException("Duplicate id");
	}
	
	/**
	 * Simulates a database exception by always throwing  SQLException
	 * Must be caught by an ExceptionHandler
	 * @return Nothing -it always thrown the exception
	 * @throws SQLException
	 */
	@RequestMapping("/databaseError1")
	String throwDatabaseException1() throws SQLException {
		log.info("Throw SQLException");
		throw new SQLException();
	}
	
	@RequestMapping("/databaseError2")
	String throwDatabaseException2() throws DataAccessException {
		log.info("Throw SQLException");
		throw new DataAccessException("Error accessing Database");
	}
	
	@RequestMapping("/invalidCreditCard")
	String throwInvalidCreditCardException() throws Exception {
		log.info("Throw InvalidCreditCardException");
		throw new InvalidCreditCardException("E12231431323123");
	}
	
	@RequestMapping("/databaseException")
	String throwDatabaseException() throws DatabaseException {
		log.info("Throw InvalidCreditCardException");
		throw new DatabaseException("Database not found: info.db");
	}
	
	@RequestMapping("/supportInfoException")
	String throwCustomException() throws Exception {
		log.info("Throw SupportInfoException");
		throw new SupportInfoException("Custom exception occurred");
	}
	
	@RequestMapping("/unhandledException")
	String throwUnhandledException() throws Exception {
		log.info("Throw UnhandledException");
		throw new UnhandledException("Some exception occurred");
	}
	
	/* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - */
	/* . . . . . . . . . . . . . EXCEPTION HANDLERS . . . . . . . . . . . . .. */
	/* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - */
	
	/**
	 * Converts a predefined exception to an HTTP Status code
	 */
	@ResponseStatus(value=HttpStatus.CONFLICT, reason="Data integrity violation")
	@ExceptionHandler(DataIntegrityViolationException.class)
	public void conflict() {
		log.error("Request raised a DataIntegrityViolationException");
	}
	
	/**
	 * Convert a predefined exception to an HTTP Status code and specify the
	 * name of a specific view that will be used to display the error.
	 * @param ex
	 * @return Exception view
	 */
	@ExceptionHandler({SQLException.class, DataAccessException.class})
	public String databaseError(Exception ex) {
		log.error("Request raised " + ex.getClass().getSimpleName());
		return "databaseError";
	}
	
	/**
	 * Demonstrates how to take total control - setup a model, add useful
	 * information and return the "support" view name.
	 * @param request
	 * 			Current HTTP request
	 * @param exception
	 * 			The exception thrown - always {@link SupportInfoException}
	 * @return The model and view used by the DispatcherServlet to generate
	 * 			output.
	 * 			
	 * @throws Exception
	 */
	@ExceptionHandler(SupportInfoException.class)
	public ModelAndView handleError(HttpServletRequest request, Exception exception) throws Exception {
		
		if(AnnotationUtils.findAnnotation(exception.getClass(), ResponseStatus.class) != null)
			throw exception;
		
		log.error("Request: " + request.getRequestURI() + " raised " + exception);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("exception", exception);
		mav.addObject("url", request.getRequestURL());
		mav.addObject("timestamp", new Date().toString());
		mav.addObject("status", 500);
		
		mav.setViewName("support");
		
		return mav;
	}
	
}
