package bao.doan.productusecase.exception;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ErrorDetail implements Serializable {

  private static final long serialVersionUID = 3432532234234234L;
  private final String field;
  private final String message;
}
