/*
 * Copyright (C) 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package chat.nainesh.com.chatapp.customviews;

class SlidingTabStrip extends android.widget.LinearLayout {

    private static final int DEFAULT_BOTTOM_BORDER_THICKNESS_DIPS = 2;
    private static final byte DEFAULT_BOTTOM_BORDER_COLOR_ALPHA = 0x26;
    private static final int SELECTED_INDICATOR_THICKNESS_DIPS = 3;
    private static final int DEFAULT_SELECTED_INDICATOR_COLOR = 0x12334632;

    private static final int DEFAULT_DIVIDER_THICKNESS_DIPS = 0;
    private static final byte DEFAULT_DIVIDER_COLOR_ALPHA = 0x20;
    private static final float DEFAULT_DIVIDER_HEIGHT = 0.5f;

    private final int mBottomBorderThickness;
    private final android.graphics.Paint mBottomBorderPaint;

    private final int mSelectedIndicatorThickness;
    private final android.graphics.Paint mSelectedIndicatorPaint;

    private final android.graphics.Paint mDividerPaint;
    private final float mDividerHeight;

    private int mSelectedPosition;
    private float mSelectionOffset;

    private SlidingTabLayout.TabColorizer mCustomTabColorizer;
    private final SimpleTabColorizer mDefaultTabColorizer;

    SlidingTabStrip(android.content.Context context) {
        this(context, null);
    }

    SlidingTabStrip(android.content.Context context, android.util.AttributeSet attrs) {
        super(context, attrs);
        setWillNotDraw(false);

        final float density = getResources().getDisplayMetrics().density;

        mDefaultTabColorizer = new SimpleTabColorizer();
        mDefaultTabColorizer.setIndicatorColors(getResources().getColor(chat.nainesh.com.chatapp.R.color.color_primary));
        //mDefaultTabColorizer.setDividerColors(setColorAlpha(themeForegroundColor,
        //        DEFAULT_DIVIDER_COLOR_ALPHA));

        //mBottomBorderThickness = (int) (DEFAULT_BOTTOM_BORDER_THICKNESS_DIPS * density);
        mBottomBorderThickness = 0;
        mBottomBorderPaint = new android.graphics.Paint();
        //mBottomBorderPaint.setColor(mDefaultBottomBorderColor);

        mSelectedIndicatorThickness = (int) (SELECTED_INDICATOR_THICKNESS_DIPS * density);
        mSelectedIndicatorPaint = new android.graphics.Paint();

        mDividerHeight = DEFAULT_DIVIDER_HEIGHT;
        mDividerPaint = new android.graphics.Paint();
        //mDividerPaint.setStrokeWidth((int) (DEFAULT_DIVIDER_THICKNESS_DIPS * density));
    }

    void setCustomTabColorizer(SlidingTabLayout.TabColorizer customTabColorizer) {
        mCustomTabColorizer = customTabColorizer;
        invalidate();
    }

    void setSelectedIndicatorColors(int... colors) {
        // Make sure that the custom colorizer is removed
        mCustomTabColorizer = null;
        mDefaultTabColorizer.setIndicatorColors(colors);
        invalidate();
    }

    void setDividerColors(int... colors) {
        // Make sure that the custom colorizer is removed
        mCustomTabColorizer = null;
        mDefaultTabColorizer.setDividerColors(colors);
        invalidate();
    }

    void onViewPagerPageChanged(int position, float positionOffset) {
        mSelectedPosition = position;
        mSelectionOffset = positionOffset;
        invalidate();
    }

    @Override
    protected void onDraw(android.graphics.Canvas canvas) {
        final int height = getHeight();
        final int childCount = getChildCount();
        final int dividerHeightPx = (int) (Math.min(Math.max(0f, mDividerHeight), 1f) * height);
        final SlidingTabLayout.TabColorizer tabColorizer = mCustomTabColorizer != null
                ? mCustomTabColorizer
                : mDefaultTabColorizer;

        // Thick colored underline below the current selection
        if (childCount > 0) {
            android.view.View selectedTitle = getChildAt(mSelectedPosition);
            int left = selectedTitle.getLeft();
            int right = selectedTitle.getRight();
            int color = tabColorizer.getIndicatorColor(mSelectedPosition);

            if (mSelectionOffset > 0f && mSelectedPosition < (getChildCount() - 1)) {
                int nextColor = tabColorizer.getIndicatorColor(mSelectedPosition + 1);
                if (color != nextColor) {
                    color = blendColors(nextColor, color, mSelectionOffset);
                }

                // Draw the selection partway between the tabs
                android.view.View nextTitle = getChildAt(mSelectedPosition + 1);
                left = (int) (mSelectionOffset * nextTitle.getLeft() +
                        (1.0f - mSelectionOffset) * left);
                right = (int) (mSelectionOffset * nextTitle.getRight() +
                        (1.0f - mSelectionOffset) * right);
            }

            mSelectedIndicatorPaint.setColor(color);

            canvas.drawRect(left, height - mSelectedIndicatorThickness, right,
                    height, mSelectedIndicatorPaint);
        }

        // Thin underline along the entire bottom edge
        canvas.drawRect(0, height - mBottomBorderThickness, getWidth(), height, mBottomBorderPaint);

        // Vertical separators between the titles
        /*int separatorTop = (height - dividerHeightPx) / 2;
        for (int i = 0; i < childCount - 1; i++) {
            View child = getChildAt(i);
            mDividerPaint.setColor(tabColorizer.getDividerColor(i));
            canvas.drawLine(child.getRight(), separatorTop, child.getRight(),
                    separatorTop + dividerHeightPx, mDividerPaint);
        }*/
    }

    /**
     * Set the alpha value of the {@code color} to be the given {@code alpha} value.
     */
    private static int setColorAlpha(int color, byte alpha) {
        return android.graphics.Color.argb(alpha, android.graphics.Color.red(color), android.graphics.Color.green(color), android.graphics.Color.blue(color));
    }

    /**
     * Blend {@code color1} and {@code color2} using the given ratio.
     *
     * @param ratio of which to blend. 1.0 will return {@code color1}, 0.5 will give an even blend,
     *              0.0 will return {@code color2}.
     */
    private static int blendColors(int color1, int color2, float ratio) {
        final float inverseRation = 1f - ratio;
        float r = (android.graphics.Color.red(color1) * ratio) + (android.graphics.Color.red(color2) * inverseRation);
        float g = (android.graphics.Color.green(color1) * ratio) + (android.graphics.Color.green(color2) * inverseRation);
        float b = (android.graphics.Color.blue(color1) * ratio) + (android.graphics.Color.blue(color2) * inverseRation);
        return android.graphics.Color.rgb((int) r, (int) g, (int) b);
    }

    private static class SimpleTabColorizer implements SlidingTabLayout.TabColorizer {
        private int[] mIndicatorColors;
        private int[] mDividerColors;

        @Override
        public final int getIndicatorColor(int position) {
            return mIndicatorColors[position % mIndicatorColors.length];
        }

        @Override
        public final int getDividerColor(int position) {
            return mDividerColors[position % mDividerColors.length];
        }

        void setIndicatorColors(int... colors) {
            mIndicatorColors = colors;
        }

        void setDividerColors(int... colors) {
            mDividerColors = colors;
        }
    }
}