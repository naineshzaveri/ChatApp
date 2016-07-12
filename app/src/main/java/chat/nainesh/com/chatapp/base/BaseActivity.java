package chat.nainesh.com.chatapp.base;

/**
 */
public class BaseActivity extends android.support.v7.app.AppCompatActivity {

    /**
     * The boolean to check if activity is in foreground.
     */
    private boolean isActivityVisible = true;

    /**
     * The Constant ADD_FRAGMENT.
     */
    public static final int ADD_FRAGMENT = 0;

    /**
     * The Constant REPLACE_FRAGMENT.
     */
    public static final int REPLACE_FRAGMENT = 1;

    /**
     * The Constant ADD_FRAGMENT WITH BACK STACK.
     */
    public static final int ADD_FRAGMENT_WITH_BACK_STACK = 2;


    private String TAG;

    @Override
    protected void onCreate(android.os.Bundle savedInstanceState) {
        getWindow().setSoftInputMode(android.view.WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        super.onCreate(savedInstanceState);

    }

    /**
     * Fragment transaction.
     *
     * @param transactionType  the transaction type
     * @param fragment         -the new fragment
     * @param container        the container
     * @param isAddToBackStack the is add to back stack
     * @author yashesh does Fragment transaction.
     */
    public void fragmentTransaction(int transactionType, android.support.v4.app.Fragment fragment,
                                    int container, boolean isAddToBackStack) {

        if (isActivityVisible) {
            android.support.v4.app.FragmentTransaction trans = getSupportFragmentManager()
                    .beginTransaction();
            /*****
             *
             * set transaction type add or replace
             *
             * **/
            switch (transactionType) {
                case (ADD_FRAGMENT):
                    trans.add(container, fragment);
                    break;
                case (REPLACE_FRAGMENT):
                    trans.replace(container, fragment);
                    if (isAddToBackStack)
                        trans.addToBackStack(null);

                    break;
                case (ADD_FRAGMENT_WITH_BACK_STACK):
                    trans.add(container, fragment);
                    if (isAddToBackStack)
                        trans.addToBackStack(null);
                    break;
                default:
                    break;

            }

            trans.commit();
        } else {

        }
    }

    public void fragmentTransactionWithAnimation(int transactionType, android.support.v4.app.Fragment fragment,
                                                 int container, boolean isAddToBackStack, int enter, int exit, int popEnter, int popExit) {

        if (isActivityVisible) {

            android.support.v4.app.FragmentTransaction trans = getSupportFragmentManager()
                    .beginTransaction();
            trans.setCustomAnimations(enter, exit, popEnter, 0);
            /*****
             *
             * set transaction type add or replace
             *
             * **/
            switch (transactionType) {
                case (ADD_FRAGMENT):
                    trans.add(container, fragment);
                    break;
                case (REPLACE_FRAGMENT):
                    trans.replace(container, fragment);
                    if (isAddToBackStack)
                        trans.addToBackStack(null);

                    break;
                case (ADD_FRAGMENT_WITH_BACK_STACK):
                    trans.add(container, fragment);
                    if (isAddToBackStack)
                        trans.addToBackStack(null);
                    break;
                default:
                    break;

            }

            trans.commit();
        } else {

        }

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
        throw new RuntimeException(message);
    }

    protected void longToast(String message) {

        android.widget.Toast.makeText(this, message, android.widget.Toast.LENGTH_LONG).show();
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
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        isActivityVisible = true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        isActivityVisible = false;
    }

    @Override
    public boolean onOptionsItemSelected(android.view.MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
