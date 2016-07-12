package chat.nainesh.com.chatapp;

/**
 * Created by naineshzaveri on 12/07/16.
 */
public class GroupChat extends android.app.Application {
    private static GroupChat mThis;

    @Override
    public void onCreate() {
        super.onCreate();
        // app instance
        mThis = this;
        chat.nainesh.com.chatapp.database.GroupChatDbManager.getInstance().setContext(getApplicationContext());
    }
    public static GroupChat getInstance() {
        return mThis;
    }



}
