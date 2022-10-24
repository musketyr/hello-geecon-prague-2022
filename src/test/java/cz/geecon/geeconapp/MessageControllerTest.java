package cz.geecon.geeconapp;

import com.agorapulse.gru.Gru;
import com.agorapulse.gru.HttpStatusShortcuts;
import io.micronaut.context.annotation.Property;
import io.micronaut.security.authentication.BasicAuthUtils;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import java.util.Base64;

import static java.util.Base64.*;
import static org.junit.jupiter.api.Assertions.*;

@MicronautTest
class MessageControllerTest {

    @Inject
    Gru gru;

    @Test
    void testCreateMessage() throws Throwable {
        gru.verify(test -> test
                        .post("/message", req -> req
                                .json("createRequest.json")
                                .header("Authorization", getAuthHeader("sherlock", "password"))
                        )
                        .expect(resp -> resp.json("createResponse.json"))
                );
    }

    @Test
    void testCreateMessageWrongUser() throws Throwable {
        gru.verify(test -> test
                .post("/message", req -> req
                        .json("createRequest.json")
                        .header("Authorization", getAuthHeader("watson", "password"))
                )
                .expect(resp -> resp.status(HttpStatusShortcuts.FORBIDDEN))
        );
    }

    @Test
    void testCreateMessageAsAnonymous() throws Throwable {
        gru.verify(test -> test
                .post("/message", req -> req.json("createRequest.json"))
                .expect(resp -> resp.status(HttpStatusShortcuts.UNAUTHORIZED))
        );
    }

    private String getAuthHeader(String username, String password) {
        byte[] bytes = (username + ":" + password).getBytes();
        byte[] encode = getEncoder().encode(bytes);
        return "Basic " + new String(encode);
    }

}