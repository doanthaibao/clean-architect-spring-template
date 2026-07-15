package bao.doan.productapi.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import lombok.Builder;
import lombok.Generated;
import lombok.Getter;

@Getter
@Builder
@Generated
public class ExceptionResponse implements Serializable {

  @Serial
  private static final long serialVersionUID = -8324344343443433434L;

  private final String error;
  private final transient List<ErrorDto> errorDetails;
  private final String path;
  private final Integer code;
  private final String traceId;

  @JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSX")
  public Date getTimestamp() {
    return new Date();
  }
}
