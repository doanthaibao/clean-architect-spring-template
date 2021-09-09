package bao.doan.productusecase.exception;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;

public class EntityAlreadyExistException extends RuntimeException {

  private static final long serialVersionUID = 34332532234234234L;

  private static final String DELIMITER = ", ";
  @Getter
  private final List<ErrorDetail> errorDetails;

  public EntityAlreadyExistException(final ErrorDetail errorDetail) {
    super();
    this.errorDetails = new ArrayList<>();
    this.errorDetails.add(errorDetail);
  }

  @Override
  public String getMessage() {
    return errorDetails.stream()
        .map(error -> String.format("'%s' %s", error.getField(), error.getMessage())).collect(
            Collectors.joining(DELIMITER));
  }

}
