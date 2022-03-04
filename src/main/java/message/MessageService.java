package message;

import java.util.Optional;

public class MessageService {

    private UserRepository userRepository;
    private MessageRepository messageRepository;

    public MessageService(UserRepository userRepository, MessageRepository messageRepository) {
        this.userRepository = userRepository;
        this.messageRepository = messageRepository;
    }


    public void registerUser(String userName) {
        if(userRepository.findUserByName(userName).isEmpty()){
            userRepository.insertUser(userName);
        }else {
            throw new IllegalArgumentException("Username is already taken: " + userName);
        }
    }

    public void sendMessage(User sender, User receiver, String message){
        long senderId = ensureUserIsRegistered(sender);
        String receiverId = Long.toString(ensureUserIsRegistered(receiver));
        messageRepository.insertMessage(new Message(senderId,receiverId,message));
    }

    private long ensureUserIsRegistered(User user){
        Optional<User> found = userRepository.findUserByName(user.getUsername());
        if(found.isPresent()){
            return found.get().getId();
        }
        throw new IllegalArgumentException("No registered user with user name: " + user.getUsername());
    }


}
