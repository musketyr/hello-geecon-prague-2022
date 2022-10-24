package cz.geecon.geeconapp;
import com.agorapulse.gru.Gru;
import com.agorapulse.gru.micronaut.Micronaut;
import io.micronaut.context.annotation.Property;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;

import jakarta.inject.Inject;

import java.time.Clock;

@MicronautTest
@Property(name = "micronaut.security.enabled", value = "false")
class HelloWorldGruControllerTest {

    @Inject
    Gru gru;

    @Test
    void testGet() throws Throwable {
        MyOwnClockHolder.clock = Clock.fixed()
        gru.verify(test -> test
            .get("/gru")
            .expect(response -> response.json("gruIndex.json"))
        );
    }

}