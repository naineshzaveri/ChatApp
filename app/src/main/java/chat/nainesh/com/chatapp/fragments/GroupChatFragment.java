package chat.nainesh.com.chatapp.fragments;

/**
 * Created by naineshzaveri on 11/07/16.
 */
public class GroupChatFragment extends chat.nainesh.com.chatapp.base.BaseFragment  implements chat.nainesh.com.chatapp.database.GroupChatDbManager.DashBoardChangeListener {

    private android.support.v7.widget.RecyclerView.LayoutManager mLayoutManager;
    private android.support.v7.widget.RecyclerView.Adapter mAdapter;
    private android.support.v7.widget.RecyclerView mRecyclerView;
    private android.content.Context mContext;

    private java.util.ArrayList<chat.nainesh.com.chatapp.models.GroupChatModel.Messages> messagesArrayList;

    public static android.support.v4.app.Fragment newInstance() {
        return new GroupChatFragment();
    }

    @Override
    public void onAttach(android.content.Context context) {
        super.onAttach(context);
        this.mContext = context;
        chat.nainesh.com.chatapp.database.GroupChatDbManager.getInstance().registerDashBoardListener(this);
    }

    @Override
    public void onCreate(android.os.Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        messagesArrayList = chat.nainesh.com.chatapp.utils.Utility.getGroupChats(chat.nainesh.com.chatapp.utils.Utility.readJsonFromAssets(getActivity()));
    }

    @Override
    public android.view.View onCreateView(android.view.LayoutInflater inflater, android.view.ViewGroup container, android.os.Bundle savedInstanceState) {
        android.view.View view = inflater.inflate(chat.nainesh.com.chatapp.R.layout.fragment_group_chat, null);
        init(view);

        setAdapter();
        return view;
    }
    private void init(android.view.View view){
        mRecyclerView = (android.support.v7.widget.RecyclerView) view.findViewById(chat.nainesh.com.chatapp.R.id.rclChat);
        mLayoutManager = new android.support.v7.widget.LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(mLayoutManager);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void setAdapter() {
        if (messagesArrayList == null || messagesArrayList.size() == 0) {  // no cardview for patient

        } else {
            // specify an adapter (see also next example)
            if (mAdapter == null) {
                mAdapter = new chat.nainesh.com.chatapp.adapters.GroupChatAdapter(mContext, messagesArrayList);
                mRecyclerView.setAdapter(mAdapter);
            } else {
                mAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void onDashBoardStatusChange() {

    }
}
