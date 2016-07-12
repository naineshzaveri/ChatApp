package chat.nainesh.com.chatapp.database;

/**
 * Created by naineshzaveri on 09/03/16.
 */
public class GroupChatDbManager {

    public interface DashBoardChangeListener {
        void onDashBoardStatusChange();
    }
    java.util.List<chat.nainesh.com.chatapp.database.GroupChatDbManager.DashBoardChangeListener> arrayBoardListener = new java.util.ArrayList<>();
    private android.content.ContentResolver contentResolver;
    private android.content.Context mContext;
    private static final String TAG = GroupChatDbManager.class.getName();
    private android.content.Context context;
    private static GroupChatDbManager theSingleton;

    public static GroupChatDbManager getInstance() {
        if (theSingleton == null) {
            theSingleton = new GroupChatDbManager();
        }
        return theSingleton;
    }

    public void registerDashBoardListener(DashBoardChangeListener dashBoardChangeListener) {
        if (dashBoardChangeListener != null) {
            arrayBoardListener.add(dashBoardChangeListener);
        }
    }

    public void deRegisterDashBoardListener(DashBoardChangeListener dashBoardChangeListener) {
        if (dashBoardChangeListener != null) {
            arrayBoardListener.remove(dashBoardChangeListener);
        }
    }

    public void notifyDashBoardListener() {
        if (arrayBoardListener != null && arrayBoardListener.size() > 0) {
            for (DashBoardChangeListener dashBoardChangeListener : arrayBoardListener) {
                dashBoardChangeListener.onDashBoardStatusChange();
            }
        }
    }

    public void setContext(android.content.Context context) {
        this.context = context;

        contentResolver = context.getContentResolver();
    }

    public android.content.Context getContext() {
        return context;
    }

    public int getUserCountFromDb(String username) {
        android.database.Cursor mCursor = null;
        try {
            String str = username;

            mCursor = contentResolver.query(android.net.Uri.parse(chat.nainesh.com.chatapp.database.tables.GroupChatTable.URI_CHAT)
                    , null, "username " + " =?", new String[]{str}, null);

            mCursor.moveToFirst();

            if (mCursor == null){
                return 0;
            }else{
                return mCursor.getCount();
            }

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }finally {
            mCursor.close();
        }

    }

    public java.util.ArrayList<chat.nainesh.com.chatapp.models.GroupChatModel.Messages> getAllGroupChats(){
        java.util.ArrayList<chat.nainesh.com.chatapp.models.GroupChatModel.Messages> arrChats = new java.util.ArrayList<chat.nainesh.com.chatapp.models.GroupChatModel.Messages>();
        arrChats.clear();
        android.database.Cursor mCursor = contentResolver.query(android.net.Uri.parse(chat.nainesh.com.chatapp.database.tables.GroupChatTable.URI_CHAT)
                , null, null, null, null);

        if (mCursor.moveToFirst()) {
            if (mCursor.getCount() > 0) {
                do {
                    arrChats.add(parseChatCursorToModel(mCursor));

                } while (mCursor.moveToNext());
            } else {
                return null;
            }
        }
        mCursor.close();

        return arrChats.size() > 0 ? arrChats : null;
    }
    public void deleteChatsFromDB() {

        try {
            contentResolver.delete(android.net.Uri.parse(chat.nainesh.com.chatapp.database.tables.GroupChatTable.URI_CHAT), null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private chat.nainesh.com.chatapp.models.GroupChatModel.Messages parseChatCursorToModel(android.database.Cursor cursor) {

        chat.nainesh.com.chatapp.models.GroupChatModel groupChatModel = new chat.nainesh.com.chatapp.models.GroupChatModel();
        chat.nainesh.com.chatapp.models.GroupChatModel.Messages messages =  groupChatModel.new Messages();
        messages.setBody(cursor.getString(cursor.getColumnIndex(chat.nainesh.com.chatapp.database.tables.GroupChatTable.Chat.COLUMN_BODY)));
        messages.setName(cursor.getString(cursor.getColumnIndex(chat.nainesh.com.chatapp.database.tables.GroupChatTable.Chat.COLUMN_NAME)));
        messages.setUsername(cursor.getString(cursor.getColumnIndex(chat.nainesh.com.chatapp.database.tables.GroupChatTable.Chat.COLUMN_USER_NAME)));
        messages.setIsFavorite(cursor.getString(cursor.getColumnIndex(chat.nainesh.com.chatapp.database.tables.GroupChatTable.Chat.COLUMN_CHAT_FAVORITE)));
        messages.setImageUrl(cursor.getString(cursor.getColumnIndex(chat.nainesh.com.chatapp.database.tables.GroupChatTable.Chat.COLUMN_IMAGE_URL)));
        messages.setMessageTime(cursor.getString(cursor.getColumnIndex(chat.nainesh.com.chatapp.database.tables.GroupChatTable.Chat.COLUMN_MESSAGE_TIME)));

        return messages;
    }

    private chat.nainesh.com.chatapp.models.StatsModel parseChatCursorToStatsModel(android.database.Cursor cursor) {

        chat.nainesh.com.chatapp.models.StatsModel statsModel = new chat.nainesh.com.chatapp.models.StatsModel();

        statsModel.setName(cursor.getString(cursor.getColumnIndex(chat.nainesh.com.chatapp.database.tables.GroupChatTable.Chat.COLUMN_NAME)));
        statsModel.setUserName(cursor.getString(cursor.getColumnIndex(chat.nainesh.com.chatapp.database.tables.GroupChatTable.Chat.COLUMN_USER_NAME)));
        return statsModel;
    }
    private android.content.ContentValues parsChatModelToContentVaules(chat.nainesh.com.chatapp.models.GroupChatModel.Messages messages) {

        android.content.ContentValues contentValues = new android.content.ContentValues();

        contentValues.put(chat.nainesh.com.chatapp.database.tables.GroupChatTable.Chat.COLUMN_BODY, messages.getBody());
        contentValues.put(chat.nainesh.com.chatapp.database.tables.GroupChatTable.Chat.COLUMN_CHAT_FAVORITE, messages.getIsFavorite());
        contentValues.put(chat.nainesh.com.chatapp.database.tables.GroupChatTable.Chat.COLUMN_IMAGE_URL, messages.getImageUrl());
        contentValues.put(chat.nainesh.com.chatapp.database.tables.GroupChatTable.Chat.COLUMN_MESSAGE_TIME, messages.getMessageTime());
        contentValues.put(chat.nainesh.com.chatapp.database.tables.GroupChatTable.Chat.COLUMN_NAME, messages.getName());
        contentValues.put(chat.nainesh.com.chatapp.database.tables.GroupChatTable.Chat.COLUMN_USER_NAME, messages.getUsername());
        return contentValues;
    }

   /* public int getChatCountOfUser(String username){
        String[] args = {username};
        //android.database.Cursor cur = contentResolver.rawquery("SELECT * FROM " + chat.nainesh.com.chatapp.database.tables.GroupChatTable.Chat.TABLE_NAME + " where username = ?" , null);
        if(cur.moveToFirst())
        {
            if(cur != null) {
                return cur.getCount();
            }else{
                return 0;
            }
        }
        return 0;
    }*/

    public void saveFavoriteChatInDB(String userName, boolean isFav){
        android.content.ContentValues values = new android.content.ContentValues();
        if(isFav) {
            values.put(chat.nainesh.com.chatapp.database.tables.GroupChatTable.Chat.COLUMN_CHAT_FAVORITE, "Y"); //whatever column you want to update
        }else{
            values.put(chat.nainesh.com.chatapp.database.tables.GroupChatTable.Chat.COLUMN_CHAT_FAVORITE, "N"); //whatever column you want to update
        }

       contentResolver.update(chat.nainesh.com.chatapp.database.tables.GroupChatTable.Chat.CONTENT_URI, values, chat.nainesh.com.chatapp.database.tables.GroupChatTable.Chat.COLUMN_MESSAGE_TIME + "=?", new String[]{userName}); //id is the id of the row you wan to update

    }

    public void addAllChatsToDB(java.util.ArrayList<chat.nainesh.com.chatapp.models.GroupChatModel.Messages> arrayList) {
        try {
            for(chat.nainesh.com.chatapp.models.GroupChatModel.Messages messages : arrayList){

                addChatToDB(parsChatModelToContentVaules(messages));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public java.util.ArrayList<chat.nainesh.com.chatapp.models.StatsModel> getDistinctUsersFromDB(){
        java.util.ArrayList<chat.nainesh.com.chatapp.models.StatsModel> arrUsers = new java.util.ArrayList<chat.nainesh.com.chatapp.models.StatsModel>();
        arrUsers.clear();
        android.database.Cursor mCursor = contentResolver.query(android.net.Uri.parse(chat.nainesh.com.chatapp.database.tables.GroupChatTable.URI_CHAT)
                , null, null, null, null);

        if (mCursor.moveToFirst()) {
            if (mCursor.getCount() > 0) {
                do {
                    arrUsers.add(parseChatCursorToStatsModel(mCursor));

                } while (mCursor.moveToNext());
            } else {
                return null;
            }
        }
        mCursor.close();

        return arrUsers;
    }

    public int getTotalFavChatCount() {

        try {
            String str = "Y";

            android.database.Cursor mCursor = contentResolver.query(android.net.Uri.parse(chat.nainesh.com.chatapp.database.tables.GroupChatTable.URI_CHAT)
                    , null, "fav_chat " + " =?", new String[]{str}, null);

            mCursor.moveToFirst();
            if(mCursor != null && mCursor.getCount() >0 ) {
                return mCursor.getCount();
            }else{
                return 0;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    public void addChatToDB(android.content.ContentValues values) {
        try {
            contentResolver.insert(android.net.Uri.parse(chat.nainesh.com.chatapp.database.tables.GroupChatTable.URI_CHAT), values);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
