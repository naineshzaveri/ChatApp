package chat.nainesh.com.chatapp.models;

/**
 * Created by naineshzaveri on 11/07/16.
 */
public class GroupChatModel  {

    private int count;
    private java.util.ArrayList<Messages> messages;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public java.util.ArrayList<chat.nainesh.com.chatapp.models.GroupChatModel.Messages> getMessages() {
        return messages;
    }

    public void setMessages(java.util.ArrayList<chat.nainesh.com.chatapp.models.GroupChatModel.Messages> messages) {
        this.messages = messages;
    }

    public class Messages {

        private int id;
        private String body;
        private String username;
        private String Name;
        @com.google.gson.annotations.SerializedName("image-url")
        private String imageUrl;
        @com.google.gson.annotations.SerializedName("message-time")
        private String messageTime;

        private String isFavorite ;


        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getName() {
            return Name;
        }

        public void setName(String name) {
            Name = name;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getMessageTime() {
            return messageTime;
        }

        public void setMessageTime(String messageTime) {
            this.messageTime = messageTime;
        }

        public String getIsFavorite() {
            return isFavorite;
        }

        public void setIsFavorite(String isFavorite) {
            this.isFavorite = isFavorite;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return username;
        }
    }
}
