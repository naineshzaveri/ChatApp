package chat.nainesh.com.chatapp.adapters;

/**
 * Created by naineshzaveri on 11/07/16.
 */
public class GroupChatAdapter extends android.support.v7.widget.RecyclerView.Adapter<android.support.v7.widget.RecyclerView.ViewHolder>{

    private java.util.ArrayList<chat.nainesh.com.chatapp.models.GroupChatModel.Messages> arrGrpChats;
    private android.content.Context mContext;

    public static class ViewHolder extends android.support.v7.widget.RecyclerView.ViewHolder {
        public ViewHolder(android.view.View v) {
            super(v);
        }
    }

    public static class ChatViewHolder extends android.support.v7.widget.RecyclerView.ViewHolder {
        public android.widget.TextView txtName, txtTimeStamp, txtMessage;
        public android.widget.ImageView ivFavorite,ivProfileImg;

        public ChatViewHolder(android.view.View v) {
            super(v);
            txtName = (android.widget.TextView) v.findViewById(chat.nainesh.com.chatapp.R.id.txtName);
            txtTimeStamp = (android.widget.TextView) v.findViewById(chat.nainesh.com.chatapp.R.id.txtTimeStamp);
            txtMessage = (android.widget.TextView) v.findViewById(chat.nainesh.com.chatapp.R.id.txtMsg);
            ivFavorite = (android.widget.ImageView) v.findViewById(chat.nainesh.com.chatapp.R.id.ivFav);
            ivProfileImg = (android.widget.ImageView) v.findViewById(chat.nainesh.com.chatapp.R.id.ivProfileImg);
        }
    }


    public GroupChatAdapter(android.content.Context context, java.util.ArrayList<chat.nainesh.com.chatapp.models.GroupChatModel.Messages> arrGrpChats) {
        super();
        this.mContext = context;
        this.arrGrpChats = arrGrpChats;
    }


    @Override
    public void onBindViewHolder(final android.support.v7.widget.RecyclerView.ViewHolder viewHolder, int position) {

         final chat.nainesh.com.chatapp.models.GroupChatModel.Messages groupChatModel = arrGrpChats.get(position);

        ChatViewHolder holder = (ChatViewHolder) viewHolder;

           holder.txtTimeStamp.setText(chat.nainesh.com.chatapp.utils.Utility.getFormatedDate(groupChatModel.getMessageTime()));

        if(groupChatModel.getIsFavorite() != null && groupChatModel.getIsFavorite().equalsIgnoreCase("Y")){
            holder.ivFavorite.setImageResource(chat.nainesh.com.chatapp.R.drawable.ic_favorite);
        }else{
            holder.ivFavorite.setImageResource(chat.nainesh.com.chatapp.R.drawable.ic_unfavorite);
        }
        holder.txtMessage.setText(groupChatModel.getBody());
        holder.txtName.setText(groupChatModel.getName());

        holder.ivFavorite.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {

                if (groupChatModel.getIsFavorite() != null && groupChatModel.getIsFavorite().equalsIgnoreCase("Y")) {
                    groupChatModel.setIsFavorite("N");
                    chat.nainesh.com.chatapp.database.GroupChatDbManager.getInstance().saveFavoriteChatInDB(groupChatModel.getMessageTime(), false);
                } else {
                    groupChatModel.setIsFavorite("Y");
                    chat.nainesh.com.chatapp.database.GroupChatDbManager.getInstance().saveFavoriteChatInDB(groupChatModel.getMessageTime(), true);
                }
                chat.nainesh.com.chatapp.database.GroupChatDbManager.getInstance().notifyDashBoardListener();
                notifyDataSetChanged();
            }
        });
        setUrlImageUsingPicasso(mContext,groupChatModel.getImageUrl(),holder.ivProfileImg);

    }

    public void setUrlImageUsingPicasso(android.content.Context activity, String imagePath, android.widget.ImageView imgProfilePic) {

        if(imagePath != null && !imagePath.equalsIgnoreCase("")) {
            final int MAX_WIDTH = 80;
            final int MAX_HEIGHT = 80;

            int size = (int) Math.ceil(Math.sqrt(MAX_WIDTH * MAX_HEIGHT));

            try {
                com.squareup.picasso.Picasso.with(activity).load(android.net.Uri.parse(imagePath)).error(android.R.drawable.stat_notify_error).noFade().centerCrop().resize(size, size).into(imgProfilePic);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            imgProfilePic.setImageResource(chat.nainesh.com.chatapp.R.drawable.ic_launcher);
        }
        //    com.squareup.picasso.Picasso.with(activity).load(imagePath).noFade().into(imgProfilePic);

    }

    @Override
    public int getItemCount() {
        return arrGrpChats.size();

    }

    @Override
    public android.support.v7.widget.RecyclerView.ViewHolder onCreateViewHolder(android.view.ViewGroup viewGroup, int viewType) {

        android.view.View  view = android.view.LayoutInflater.from(viewGroup.getContext())
                    .inflate(chat.nainesh.com.chatapp.R.layout.row_chat, viewGroup, false);
            return new chat.nainesh.com.chatapp.adapters.GroupChatAdapter.ChatViewHolder(view);

    }

}
