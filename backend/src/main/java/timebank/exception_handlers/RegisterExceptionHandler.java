package timebank.exception_handlers;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import timebank.exceptions.RegisterException;
import timebank.model.ErrorMessage;

@ControllerAdvice
public class RegisterExceptionHandler {
  private final Log log = LogFactory.getLog(getClass());

  @ExceptionHandler(RegisterException.class)
  @ResponseBody
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorMessage handleRegisterException(RegisterException e) {
    log.warn(e.getMessage());
    return new ErrorMessage(e.getMessage());
  }

}
