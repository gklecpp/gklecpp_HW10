import java.util.Arrays;
import java.util.Iterator;

public class Main {
    public static void main(String[] args){
        ChatServer server = new ChatServer();

        User grace = new User("Grace", server);
        User bray = new User("Bray", server);
        User jesse = new User("Jesse", server);

        // send messages
        System.out.println("\n=== Sending Messages ===");
        grace.sendMessage(Arrays.asList(bray), "Hello Bray :)");
        bray.sendMessage(Arrays.asList(grace), "Hello Grace :)");
        jesse.sendMessage(Arrays.asList(grace, bray), "Hello guys :)");
        grace.sendMessage(Arrays.asList(bray, jesse), "How is everyone?");

        // view chat history
        System.out.println("\n=== Chat History ===");

        grace.viewChatHistory();
        bray.viewChatHistory();
        jesse.viewChatHistory();

        // iterator pattern
        System.out.println("\n=== Iterator Pattern ===");
        System.out.println("Grace's message involving Bray:");

        Iterator<Message> iterator = grace.iterator(bray);

        while (iterator.hasNext()){
            Message message = iterator.next();
            System.out.println(message);
        }

        // blocking messages
        System.out.println("\n=== Blocking Messages ===");
        bray.blockUser(jesse);

        jesse.sendMessage(Arrays.asList(bray), "Can you see this message?");

        // undo last message
        System.out.println("\n=== Undo Last Message ===");
        grace.undoLastMessage();

        // view history after undo
        System.out.println("\n=== Chat History After Undo ===");
        grace.viewChatHistory();
        bray.viewChatHistory();
        jesse.viewChatHistory();

        // unregister user
        System.out.println("\n=== Unregister User ===");
        server.unregisterUser(jesse);
    }
}
