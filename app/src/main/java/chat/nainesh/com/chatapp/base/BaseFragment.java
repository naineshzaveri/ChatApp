package chat.nainesh.com.chatapp.base;

/**
 * The Class BaseFragment.
 */
public abstract class BaseFragment extends android.support.v4.app.Fragment {

    /**
     * The Constant TAG.
     */
    private static final String TAG = BaseFragment.class.getName();
    private android.os.Handler handler;

    @Override
    public void onCreate(android.os.Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handler = new android.os.Handler();
    }

    /**
     * Short toast.
     *
     * @param message the message
     */
    protected void shortToast(String message) {
        android.widget.Toast.makeText(getActivity(), message, android.widget.Toast.LENGTH_SHORT).show();
    }

    /**
     * Long toast.
     *
     * @param message the message
     */
    protected void longToast(String message) {
        android.widget.Toast.makeText(getActivity(), message, android.widget.Toast.LENGTH_LONG).show();
    }

    /**
     * Log d.
     *
     * @param message the message
     */
    protected void logD(String message) {
        android.util.Log.d(TAG, message);
    }

    /**
     * Log e.
     *
     * @param message the message
     */
    protected void logE(String message) {
        android.util.Log.e(TAG, message);
    }

    /**
     * Log i.
     *
     * @param message the message
     */
    protected void logI(String message) {
        android.util.Log.i(TAG, message);
    }

    /**
     * Log w.
     *
     * @param message the message
     */
    protected void logW(String message) {
        android.util.Log.w(TAG, message);
    }

    @Override
    public void onDestroyView() {
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        super.onDestroyView();
    }

    @Override
    public void onSaveInstanceState(android.os.Bundle outState) {
        //No call for super(). Bug on API Level > 11.
    }

}
