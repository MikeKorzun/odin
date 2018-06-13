package com.setplex.odin.controller.response;

import com.setplex.odin.entity.AbstractEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

import static org.springframework.util.StringUtils.hasText;

/**
 * Builds <code>org.springframework.http.ResponseEntity</code> with required header
 */
public class ResponseEntityHelper {

    private static final String ID = "/{id}";

    private ResponseEntityHelper() {
    }

    public static ResponseEntity<Void> createResponseEntity(int id) { //201
        return getCreatedBuilderWithLocationSet(id).build();
    }

    public static ResponseEntity<?> createResponseEntity(CrudResponse response) {
        int id = response.getId();
        String message = response.getMessage();
        if (hasText(message)) return createResponseEntityWithBody(id, response);
        else return createResponseEntity(id);
    }

    public static <T> ResponseEntity<T> createResponseEntityWithBody(int id, T body) {
        return getCreatedBuilderWithLocationSet(id).body(body);
    }

    public static <T extends AbstractEntity> ResponseEntity<T> createResponseEntityWithBody(T body) {
        return getCreatedBuilderWithLocationSet(body.getId()).body(body);
    }

    private static ResponseEntity.BodyBuilder getCreatedBuilderWithLocationSet(int id) {
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequestUri().path(ID).build().expand(id).toUri());
    }

    public static ResponseEntity<Void> updateResponseEntity() { // 200
        URI location = ServletUriComponentsBuilder.fromCurrentRequestUri().build().toUri();
        return ResponseEntity.ok().header(HttpHeaders.LOCATION, location.toASCIIString()).build();
    }

    public static ResponseEntity<?> updateResponseEntity(CrudResponse response) {
        String message = response.getMessage();
        if (hasText(message)) return updateResponseEntityWithBody(response);
        else return updateResponseEntity();
    }

    public static <T> ResponseEntity<T> updateResponseEntityWithBody(T body) {
        return getUpdatedBuilderWithLocationSet().body(body);
    }

    public static <T extends AbstractEntity> ResponseEntity<T> updateResponseEntityWithBody(T body) {
        return getUpdatedBuilderWithLocationSet().body(body);
    }

    private static ResponseEntity.BodyBuilder getUpdatedBuilderWithLocationSet() {
        return ResponseEntity.ok().header(HttpHeaders.LOCATION, ServletUriComponentsBuilder.fromCurrentRequestUri().build().toUri().toASCIIString());
    }

    public static ResponseEntity<Void> deleteResponseEntity() { // 200 ? 204
        return ResponseEntity.ok().build();
    }

}
