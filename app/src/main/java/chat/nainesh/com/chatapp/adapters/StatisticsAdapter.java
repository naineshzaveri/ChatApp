package chat.nainesh.com.chatapp.adapters;

/**
 * Created by naineshzaveri on 12/07/16.
 */
public class StatisticsAdapter  extends android.support.v7.widget.RecyclerView.Adapter<android.support.v7.widget.RecyclerView.ViewHolder>{

    private java.util.ArrayList<chat.nainesh.com.chatapp.models.StatsModel> arrStats;
    private android.content.Context mContext;

    public static class ViewHolder extends android.support.v7.widget.RecyclerView.ViewHolder {
        public ViewHolder(android.view.View v) {
            super(v);
        }
    }

    public static class StatsViewHolder extends android.support.v7.widget.RecyclerView.ViewHolder {
        public android.widget.TextView txtUserName, txtCount;

        public StatsViewHolder(android.view.View v) {
            super(v);
            txtUserName = (android.widget.TextView) v.findViewById(chat.nainesh.com.chatapp.R.id.txtName);
            txtCount = (android.widget.TextView) v.findViewById(chat.nainesh.com.chatapp.R.id.txtMsgCount);
        }
    }

    public StatisticsAdapter(android.content.Context context, java.util.ArrayList<chat.nainesh.com.chatapp.models.StatsModel> arrStats) {
        super();
        this.mContext = context;
        this.arrStats = arrStats;
    }


    @Override
    public void onBindViewHolder(final android.support.v7.widget.RecyclerView.ViewHolder viewHolder, int position) {

        final chat.nainesh.com.chatapp.models.StatsModel statsModel = arrStats.get(position);

        StatsViewHolder holder = (StatsViewHolder) viewHolder;

        holder.txtUserName.setText(statsModel.getName());
        holder.txtCount.setText("" + statsModel.getCount());
    }


    @Override
    public int getItemCount() {
        return arrStats.size();

    }

    @Override
    public android.support.v7.widget.RecyclerView.ViewHolder onCreateViewHolder(android.view.ViewGroup viewGroup, int viewType) {

        android.view.View  view = android.view.LayoutInflater.from(viewGroup.getContext())
                .inflate(chat.nainesh.com.chatapp.R.layout.row_statistics, viewGroup, false);
        return new chat.nainesh.com.chatapp.adapters.StatisticsAdapter.StatsViewHolder(view);

    }

}
