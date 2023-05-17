package controlWork.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class Log {
    private final boolean isError;
    private final String message;
}
