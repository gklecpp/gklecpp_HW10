import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

public class ChatServerTest {

    @Test
    void testRegAndSendMsg(){
        ChatServer server = new ChatServer();
        User grace = new User("Grace", server);
        User bray = new User("Bray", server);

        grace.sendMessage(Arrays.asList(bray), "Hi Bray :)");

        assertEquals(1, grace.getChatHistory().getMessages().size());
        assertEquals(1, bray.getChatHistory().getMessages().size());

        assertEquals("Hi Bray :)", bray.getChatHistory().getMessages().get(0).getContent());
    }

    @Test
    void testSendMsgtoMultiUsers(){
        ChatServer server = new ChatServer();

        User grace = new User("Grace", server);
        User bray = new User("Bray", server);
        User jesse = new User("Jesse", server);

        grace.sendMessage(Arrays.asList(bray, jesse), "Hello guys!");

        assertEquals(1, bray.getChatHistory().getMessages().size());
        assertEquals(1, jesse.getChatHistory().getMessages().size());
    }

    @Test
    void testBlockUser(){
        ChatServer server = new ChatServer();

        User grace = new User("Grace", server);
        User bray = new User("Bray", server);

        bray.blockUser(grace);

        grace.sendMessage(Arrays.asList(bray), "Can you see this message?");

        assertTrue(bray.getChatHistory().getMessages().isEmpty());
        assertTrue(grace.getChatHistory().getMessages().isEmpty());
    }

    @Test
    void testUnregisterUser(){
        ChatServer server = new ChatServer();

        User grace = new User("Grace", server);
        User bray = new User("Bray", server);

        server.unregisterUser(bray);

        grace.sendMessage(Arrays.asList(bray), "Hi!");

        assertTrue(bray.getChatHistory().getMessages().isEmpty());
        assertTrue(grace.getChatHistory().getMessages().isEmpty());
    }
}
