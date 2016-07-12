package chat.nainesh.com.chatapp.fragments;

/**
 * Created by naineshzaveri on 11/07/16.
 */
public class StatisticsFragment extends chat.nainesh.com.chatapp.base.BaseFragment implements chat.nainesh.com.chatapp.database.GroupChatDbManager.DashBoardChangeListener {


    private android.support.v7.widget.RecyclerView.LayoutManager mLayoutManager;
    private android.support.v7.widget.RecyclerView.Adapter mAdapter;
    private android.support.v7.widget.RecyclerView mRecyclerView;
    private android.content.Context mContext;
    private java.util.ArrayList<chat.nainesh.com.chatapp.models.StatsModel> arrsStatsModels;

    public static android.support.v4.app.Fragment newInstance() {
        return new StatisticsFragment();
    }

    @Override
    public void onAttach(android.content.Context context) {
        super.onAttach(context);
        this.mContext = context;
        chat.nainesh.com.chatapp.database.GroupChatDbManager.getInstance().registerDashBoardListener(this);
    }


    @Override
    public android.view.View onCreateView(android.view.LayoutInflater inflater, android.view.ViewGroup container, android.os.Bundle savedInstanceState) {
        android.view.View view = inflater.inflate(chat.nainesh.com.chatapp.R.layout.fragment_statistics, null);
        init(view);
        getStatsFromDb();
        setAdapter();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void init(android.view.View view) {
        mRecyclerView = (android.support.v7.widget.RecyclerView) view.findViewById(chat.nainesh.com.chatapp.R.id.rclChat);
        mLayoutManager = new android.support.v7.widget.LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(mLayoutManager);
    }
    private void setAdapter() {
        if (arrsStatsModels== null && arrsStatsModels.size() == 0) {

        } else {
            // specify an adapter (see also next example)
                mAdapter = new chat.nainesh.com.chatapp.adapters.StatisticsAdapter(mContext, arrsStatsModels);
                mRecyclerView.setAdapter(mAdapter);
        }
    }

    private void refreshDashBoard(){
        getStatsFromDb();
        setAdapter();
    }
    private void getStatsFromDb(){

        arrsStatsModels = chat.nainesh.com.chatapp.database.GroupChatDbManager.getInstance().getDistinctUsersFromDB();
        for(chat.nainesh.com.chatapp.models.StatsModel statsModel : arrsStatsModels){
            statsModel.setCount(chat.nainesh.com.chatapp.database.GroupChatDbManager.getInstance().getUserCountFromDb(statsModel.getUserName()));
        }
        getTotalFavChatCount();

    }
    private void getTotalFavChatCount(){
        chat.nainesh.com.chatapp.models.StatsModel statsModel = new chat.nainesh.com.chatapp.models.StatsModel();
        statsModel.setUserName(null);
        statsModel.setName("Total Favorite Chats");
        statsModel.setCount(chat.nainesh.com.chatapp.database.GroupChatDbManager.getInstance().getTotalFavChatCount());
        arrsStatsModels.add(statsModel);
    }

    @Override
    public void onDashBoardStatusChange() {
        refreshDashBoard();
    }
}
