package ch.poole.android.numberpickerpreference;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.preference.DialogPreference;
import android.util.AttributeSet;

public class NumberPickerPreference extends DialogPreference {
    private static final int     DEFAULT_MIN_VALUE           = 0;
    private static final int     DEFAULT_MAX_VALUE           = 100;
    private static final int     DEFAULT_INCREMENT           = 1;
    private static final boolean DEFAULT_WRAP_SELECTOR_WHEEL = false;

    private int                  selectedValue;
    private final int            minValue;
    private final int            maxValue;
    private final int            increment;
    private final boolean        wrapSelectorWheel;
    private final int            currentValueTextResId;

    public NumberPickerPreference(final Context context, final AttributeSet attrs) {
        super(context, attrs);

        final TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.spt_NumberPickerPreference);

        minValue = a.getInt(R.styleable.spt_NumberPickerPreference_spt_minValue, DEFAULT_MIN_VALUE);
        maxValue = a.getInt(R.styleable.spt_NumberPickerPreference_spt_maxValue, DEFAULT_MAX_VALUE);
        increment = a.getInt(R.styleable.spt_NumberPickerPreference_spt_increment, DEFAULT_INCREMENT);
        wrapSelectorWheel = a.getBoolean(R.styleable.spt_NumberPickerPreference_spt_setWrapSelectorWheel, DEFAULT_WRAP_SELECTOR_WHEEL);
        currentValueTextResId = a.getResourceId(R.styleable.spt_NumberPickerPreference_spt_currentValueText, 0);
        a.recycle();
    }

    @Override
    protected void onSetInitialValue(final boolean restoreValue, final Object defaultValue) {
        final int intDefaultValue = defaultValue instanceof Integer ? (int) defaultValue : getMinValue();
        setSelectedValue(restoreValue ? this.getPersistedInt(intDefaultValue) : intDefaultValue);
        this.updateSummary();
    }

    @Override
    protected Object onGetDefaultValue(final TypedArray a, final int index) {
        return a.getInteger(index, 0);
    }

    void updateSummary() {
        this.setSummary(currentValueTextResId != 0 ? getContext().getString(currentValueTextResId, getSelectedValue()) : Integer.toString(getSelectedValue()));
    }

    public void persist() {
        persistInt(this.getSelectedValue());
    }

    int getSelectedValue() {
        return selectedValue;
    }

    void setSelectedValue(final int selectedValue) {
        this.selectedValue = selectedValue;
    }

    int getMinValue() {
        return minValue;
    }

    int getMaxValue() {
        return maxValue;
    }

    boolean isWrapSelectorWheel() {
        return wrapSelectorWheel;
    }

    public int getIncrement() {
        return increment;
    }
}
