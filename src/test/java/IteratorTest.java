import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Iterator;


public class IteratorTest {

    @Test
    void testMsgByUser(){
        ChatServer server = new ChatServer();

        User grace = new User("Grace", server);
        User bray = new User("Bray", server);
        User jesse = new User("Jesse", server);

        grace.sendMessage(Arrays.asList(bray), "Hi Bray :)");
        jesse.sendMessage(Arrays.asList(grace), "Hi Grace :)");
        bray.sendMessage(Arrays.asList(grace), "Hello Grace :)");

        Iterator<Message> iterator = grace.iterator(bray);

        assertTrue(iterator.hasNext());
        assertEquals("Hi Bray :)", iterator.next().getContent());

        assertTrue(iterator.hasNext());
        assertEquals("Hello Grace :)", iterator.next().getContent());

        assertFalse(iterator.hasNext());
    }

    @Test
    void testNoMatchingMsg(){
        ChatServer server = new ChatServer();

        User grace = new User("Grace", server);
        User bray = new User("Bray", server);
        User jesse = new User("Jesse", server);

        grace.sendMessage(Arrays.asList(jesse), "Hi Jesse :)");

        Iterator<Message> iterator = grace.iterator(bray);

        assertFalse(iterator.hasNext());
    }

    @Test
    void testIteratorEmptyHistory(){
        ChatServer server = new ChatServer();

        User grace = new User("Grace", server);
        User bray = new User("Bray", server);

        Iterator<Message> iterator = grace.iterator(bray);

        assertFalse(iterator.hasNext());
    }

    @Test
    void testMsgMultiRecipients(){
        ChatServer server = new ChatServer();

        User grace = new User("Grace", server);
        User bray = new User("Bray", server);
        User jesse = new User("Jesse", server);

        grace.sendMessage(Arrays.asList(bray,jesse), "Hi guys!");

        Iterator<Message> iterator = grace.iterator(bray);

        assertTrue(iterator.hasNext());
        assertEquals("Hi guys!", iterator.next().getContent());

        assertFalse(iterator.hasNext());
    }
}
