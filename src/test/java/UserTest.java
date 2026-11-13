import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

public class UserTest {

    @Test
    void testUndoLastMsg(){
        ChatServer server = new ChatServer();

        User grace = new User("Grace", server);
        User bray = new User("Bray", server);

        grace.sendMessage(Arrays.asList(bray), "Hi Bray :)");

        assertEquals(1, grace.getChatHistory().getMessages().size());
        assertEquals(1, bray.getChatHistory().getMessages().size());

        grace.undoLastMessage();

        assertTrue(grace.getChatHistory().getMessages().isEmpty());
        assertTrue(bray.getChatHistory().getMessages().isEmpty());
    }

    @Test
    void testUndoLastMultiMsg(){
        ChatServer server = new ChatServer();

        User grace = new User("Grace", server);
        User bray = new User("Bray", server);

        grace.sendMessage(Arrays.asList(bray), "First message");
        grace.sendMessage(Arrays.asList(bray), "Second message");

        grace.undoLastMessage();

        assertEquals(1, grace.getChatHistory().getMessages().size());
        assertEquals(1, bray.getChatHistory().getMessages().size());

        assertEquals("First message", bray.getChatHistory().getMessages().get(0).getContent());
    }

    @Test
    void testUndoNoMsg(){
        ChatServer server = new ChatServer();

        User grace = new User("Grace", server);

        assertDoesNotThrow(() -> grace.undoLastMessage());
        assertTrue(grace.getChatHistory().getMessages().isEmpty());
    }

    @Test
    void testUndoMsgSentMultiUsers(){
        ChatServer server = new ChatServer();

        User grace = new User("Grace", server);
        User bray = new User("Bray", server);
        User jesse = new User("Jesse", server);

        grace.sendMessage(Arrays.asList(bray, jesse), "Hi guys!");

        grace.undoLastMessage();

        assertTrue(grace.getChatHistory().getMessages().isEmpty());
        assertTrue(bray.getChatHistory().getMessages().isEmpty());
        assertTrue(jesse.getChatHistory().getMessages().isEmpty());

    }
}
