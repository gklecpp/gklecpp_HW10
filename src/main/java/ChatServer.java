import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChatServer {
    private List<User> users;
    private Map<User, List<User>> blockedUsers;

    public ChatServer(){
        users = new ArrayList<>();
        blockedUsers = new HashMap<>();
    }

    public void registerUser(User user){
        if(!users.contains(user)){
            users.add(user);
            blockedUsers.put(user, new ArrayList<>());
            System.out.println(user.getName() + " registered.");
        }
    }

    public void unregisterUser(User user){
        users.remove(user);
        blockedUsers.remove(user);

        for (List<User> blocked : blockedUsers.values()){
            blocked.remove(user);
        }
        System.out.println(user.getName() + " unregistered.");
    }

    public void blockUser(User user, User userToBlock){
        if(users.contains(user) && users.contains(userToBlock)){
            List<User> blocked = blockedUsers.get(user);

            if(!blocked.contains(userToBlock)){
                blocked.add(userToBlock);
                System.out.println(user.getName() + " blocked " + userToBlock.getName());
            }
        }
    }

    public void sendMessage(Message message){
        User sender = message.getSender();
        if(!users.contains(sender)){
            System.out.println("Sender is not registered.");
            return;
        }

        boolean delivered = false;

        for(User recipient: message.getRecipients()){
            if (!users.contains(recipient)){
                System.out.println("Recipient is not registered.");
                continue;
            }

            if (blockedUsers.get(recipient).contains(sender)){
                System.out.println(recipient.getName() + " has blocked " + sender.getName());
                continue;
            }

            recipient.receiveMessage(message);
            delivered = true;
        }

        if(delivered){
            sender.recordSentMessage(message);
        }
    }

    public void undoMessage(Message message){
        for (User recipient : message.getRecipients()){
            recipient.removeMessage(message);
        }

        System.out.println(message.getSender().getName() + " undid their last message.");
    }

}
