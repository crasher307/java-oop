package lesson7.homework.model;

import lombok.*;

import java.time.*;

@Getter
public class ModelLog implements Log {
    protected final String dateTime;
    protected final String message;
    protected String error = "";

    public ModelLog(Object message) {
        this.dateTime = String.format("%s %s", LocalDate.now(), LocalTime.now().toString().substring(0, 12));
        if (!(message instanceof Throwable)) this.message = String.valueOf(message);
        else {
            this.message = ((Throwable) message).getMessage();
            StackTraceElement[] trace = ((Throwable) message).getStackTrace();
            if (trace.length > 0) this.error = String.valueOf(trace[trace.length - 1]);
        }
    }

    @Override
    public String toString() {
        return error.isEmpty()
                ? String.format("[%s] %s", this.dateTime, this.message)
                : String.format("[%s] %s, errorLine: %s", this.dateTime, this.message, this.error);
    }
}