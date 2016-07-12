package chat.nainesh.com.chatapp.database.tables;

public interface GroupChatTable extends BaseTable {

    public interface Chat {
        String TABLE_NAME = "groupchat";
        String COLUMN_AUTOINCREMENTID = "auto_id";
        String COLUMN_USER_NAME = "username";
        String COLUMN_BODY = "body";
        String COLUMN_NAME = "name";
        String COLUMN_CHAT_FAVORITE = "fav_chat";
        String COLUMN_IMAGE_URL = "image_url";
        String COLUMN_MESSAGE_TIME = "message_time";

        String CREATE_STATEMENT = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "("
                + COLUMN_AUTOINCREMENTID + " integer primary key autoincrement, "
                + COLUMN_USER_NAME + " text, "
                + COLUMN_BODY + " text, "
                + COLUMN_NAME + " text, "
                + COLUMN_IMAGE_URL + " text, "
                + COLUMN_CHAT_FAVORITE + " text DEFAULT 'N', "
                + COLUMN_MESSAGE_TIME + " text);";

        String BASE_PATH = "Chat";
        android.net.Uri CONTENT_URI = android.net.Uri.parse(BASE_CONTENT_URI + BASE_PATH);
    }

    String URI_CHAT = BASE_CONTENT_URI + GroupChatTable.Chat.BASE_PATH;


}
