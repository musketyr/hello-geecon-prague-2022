package cz.geecon.geeconapp;

import com.agorapulse.permissions.TemporaryPermissions;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;

@Controller("/message")
public class MessageController {

    private final MessageService messageService;
    private final TemporaryPermissions temporaryPermissions;

    public MessageController(MessageService messageService, TemporaryPermissions temporaryPermissions) {
        this.messageService = messageService;
        this.temporaryPermissions = temporaryPermissions;
    }

    @Post
    public Message create(String author, String body) {
        Message message = new Message(author, body);
        return temporaryPermissions.grantPermissions("create", message, ()
                -> messageService.save(message)
        );
    }

}
