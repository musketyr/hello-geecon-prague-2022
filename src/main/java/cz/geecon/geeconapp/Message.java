package cz.geecon.geeconapp;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.Instant;

public class Message {

    private final String author;
    private final String body;

    private final Instant created;

    public Message(String author, String body) {
        this.author = author;
        this.body = body;
        this.created = Instant.now(MyOwnClockHolder.clock);
    }

    public String getAuthor() {
        return author;
    }

    public String getBody() {
        return body;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    public Instant getCreated() {
        return created;
    }

}