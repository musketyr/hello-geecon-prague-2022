package cz.geecon.geeconapp;
import com.agorapulse.permissions.RequiresPermission;
import jakarta.inject.Singleton;

@Singleton
public class MessageService {

    @RequiresPermission("create")
    public Message save(Message message) {
        return message;
    }

}