import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Iterator;

public class User implements IterableByUser {
    private String name;
    private ChatServer server;
    private ChatHistory chatHistory;
    private List<MessageMemento> sentMementos;
    private List<Message> sentMessages;

    public User(String name, ChatServer server) {
        this.name = name;
        this.server = server;
        this.chatHistory = new ChatHistory();
        this.sentMementos = new ArrayList<>();
        this.sentMessages = new ArrayList<>();

        server.registerUser(this);
    }

    public String getName() {
        return name;
    }

    public void sendMessage(List<User> recipients, String content) {
        Message message = new Message(this, recipients, content);
        server.sendMessage(message);
    }

    public void receiveMessage(Message message) {
        chatHistory.addMessage(message);
        System.out.println(name + " received: "
                + message.getContent());
    }

    public void recordSentMessage(Message message) {
        chatHistory.addMessage(message);
        sentMessages.add(message);

        MessageMemento memento = new MessageMemento(
                message.getContent(),
                message.getTimestamp()
        );

        sentMementos.add(memento);

        System.out.println(name + " sent: "
                + message.getContent());
    }

    public void undoLastMessage() {
        if (sentMementos.isEmpty()) {
            System.out.println(name + " has no messages to undo.");
            return;
        }

        int lastIndex = sentMementos.size() - 1;

        MessageMemento memento = sentMementos.remove(lastIndex);
        Message message = sentMessages.remove(lastIndex);

        chatHistory.removeMessage(message);
        server.undoMessage(message);

        System.out.println("Removed message: "
                + memento.getContent());
    }

    public void removeMessage(Message message) {
        chatHistory.removeMessage(message);
    }

    public void blockUser(User userToBlock) {
        server.blockUser(this, userToBlock);
    }

    public void viewChatHistory() {
        System.out.println("\nChat history for " + name + ":");
        chatHistory.displayHistory();
    }

    public ChatHistory getChatHistory() {
        return chatHistory;
    }

    @Override
    public Iterator<Message> iterator(User userToSearchWith){
        return chatHistory.iterator(userToSearchWith);
    }
}
