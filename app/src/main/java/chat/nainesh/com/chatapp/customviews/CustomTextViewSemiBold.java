package chat.nainesh.com.chatapp.customviews;

/**
 * Created by naineshzaveri on 01/04/16.
 */
public class CustomTextViewSemiBold  extends android.widget.TextView {

    public CustomTextViewSemiBold(android.content.Context context, android.util.AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    public CustomTextViewSemiBold(android.content.Context context, android.util.AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CustomTextViewSemiBold(android.content.Context context) {
        super(context);
        init(context);
    }

    public void init(android.content.Context context) {
        android.graphics.Typeface typeFace= android.graphics.Typeface.createFromAsset(context.getAssets(), "fonts/Mark Simonson - Proxima Nova Semibold.otf");
        setTypeface(typeFace);
        //setTypeface(null ,Typeface.BOLD);

    }

}