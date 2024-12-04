package CC17.khryzalle.hyre;

public class ChatItem {
    private String userName;
    private String lastMessage;
    private String messageTime;

    public ChatItem(String userName, String lastMessage, String messageTime) {
        this.userName = userName;
        this.lastMessage = lastMessage;
        this.messageTime = messageTime;
    }

    public String getUserName() { return userName; }
    public String getLastMessage() { return lastMessage; }
    public String getMessageTime() { return messageTime; }
} 