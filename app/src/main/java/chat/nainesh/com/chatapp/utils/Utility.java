package chat.nainesh.com.chatapp.utils;

/**
 * Created by naineshzaveri on 11/07/16.
 */
public class Utility {

 static  final String SERVER_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
    static  final String APP_DATE_FORMAT = "dd MMM yyyy hh:mm";

    public static String readJsonFromAssets(android.app.Activity activity){

            String json = null;
            try {
                java.io.InputStream is = activity.getAssets().open("groupchat.txt");
                int size = is.available();
                byte[] buffer = new byte[size];
                is.read(buffer);
                is.close();
                json = new String(buffer, "UTF-8");
            } catch (java.io.IOException ex) {
                ex.printStackTrace();
                return null;
            }
            return json;
        }

    public static java.util.ArrayList<chat.nainesh.com.chatapp.models.GroupChatModel.Messages> getGroupChats(String jsonChats){
        com.google.gson.Gson gson = new com.google.gson.Gson();
        chat.nainesh.com.chatapp.models.GroupChatModel groupChatModel  = gson.fromJson(jsonChats, chat.nainesh.com.chatapp.models.GroupChatModel.class);

            chat.nainesh.com.chatapp.database.GroupChatDbManager.getInstance().deleteChatsFromDB();
            chat.nainesh.com.chatapp.database.GroupChatDbManager.getInstance().addAllChatsToDB(groupChatModel.getMessages());

        return groupChatModel.getMessages();
    }
    public static String getFormatedDate(String date){
        try {
            if (date == null)
                return "";
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(SERVER_FORMAT);
            java.text.SimpleDateFormat output = new java.text.SimpleDateFormat(APP_DATE_FORMAT);
            java.util.Date d = sdf.parse(date);
            String formattedTime = output.format(d);
            return formattedTime;
        }catch (Exception e){
            return "";
        }
    }
}
