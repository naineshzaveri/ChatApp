package chat.nainesh.com.chatapp.customviews;

/**
 * Created by sumeet on 10/6/15.
 */
public class CustomTextViewBold extends android.widget.TextView {

    public CustomTextViewBold(android.content.Context context, android.util.AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    public CustomTextViewBold(android.content.Context context, android.util.AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CustomTextViewBold(android.content.Context context) {
        super(context);
        init(context);
    }

    public void init(android.content.Context context) {
        android.graphics.Typeface typeFace= android.graphics.Typeface.createFromAsset(context.getAssets(), "fonts/Mark Simonson - Proxima Nova Bold.otf");
        setTypeface(typeFace);
        //setTypeface(null ,Typeface.BOLD);

    }

}