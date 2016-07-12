package chat.nainesh.com.chatapp.adapters;

public class HomePagerAdapter extends android.support.v4.app.FragmentStatePagerAdapter implements
        java.io.Serializable {

    public HomePagerAdapter(android.support.v4.app.FragmentManager fm) {
        super(fm);
    }
    public String[] tab_titles = new String[]{"Group Chat", "Statistics"};
    public static final int GROUP_CHAT = 0;
    public static final int CHAT_STATS = 1;


    @Override
    public int getCount() {
        return tab_titles.length;
    }

    @Override
    public void destroyItem(android.view.ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);

    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tab_titles[position];
    }

    @Override
    public android.support.v4.app.Fragment getItem(int position) {

        android.support.v4.app.Fragment fragment = null;
        switch (position) {
            case GROUP_CHAT:
                fragment = chat.nainesh.com.chatapp.fragments.GroupChatFragment.newInstance();
                break;
            case CHAT_STATS:
                fragment = chat.nainesh.com.chatapp.fragments.StatisticsFragment.newInstance();
                break;

            default:
                break;
        }

        return fragment;
    }

}
