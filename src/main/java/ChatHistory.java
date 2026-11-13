import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Iterator;

public class ChatHistory implements IterableByUser {
    private List<Message> messages;

    public ChatHistory(){
        messages = new ArrayList<>();
    }

    public Message getLastMessageSent(User user){
        for(int i = messages.size() - 1; i >=0; i--){
            Message message = messages.get(i);

            if(message.getSender() == user){
                return message;
            }
        }
        return null;
    }

    public void removeMessage(Message message){
        messages.remove(message);
    }

    public List<Message> getMessages(){
        return new ArrayList<>(messages);
    }

    public void displayHistory(){
        if(messages.isEmpty()){
            System.out.println("No messages in chat history.");
            return;
        }

        for(Message message: messages){
            System.out.println((message));
        }
    }

    public void addMessage(Message message) {
        messages.add(message);
    }

    @Override
    public Iterator<Message> iterator(User userToSearchWith){
        return new searchMessagesByUser(messages, userToSearchWith);
    }
}
