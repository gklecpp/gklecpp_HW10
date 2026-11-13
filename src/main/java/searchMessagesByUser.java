import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class searchMessagesByUser implements Iterator<Message> {
    private List<Message> messages;
    private User userToSearchWith;
    private int currentIndex;

    public searchMessagesByUser(List<Message> messages, User userToSearchWith) {
        this.messages = messages;
        this.userToSearchWith = userToSearchWith;
        this.currentIndex = 0;
    }

    @Override
    public boolean hasNext(){
        while(currentIndex < messages.size()){
            Message message = messages.get(currentIndex);

            if(message.getSender() == userToSearchWith || message.getRecipients().contains(userToSearchWith)){
                return true;
            }

            currentIndex++;
        }
        return false;
    }

    @Override
    public Message next(){
        if (!hasNext()){
            throw new NoSuchElementException();
        }

        return messages.get(currentIndex++);
    }
}
