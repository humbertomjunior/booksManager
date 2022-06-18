package br.com.fatec.booksManager.exception;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.AllArgsConstructor;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

@AllArgsConstructor
public class BadRequestExceptionHandler extends ResponseEntityExceptionHandler {

    private String message;
    private Integer status;
    private List<String> errors;

    public String getMessage() {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
        return gson.toJson(this.message);

    }
}
