package ch.poole.android.numberpickerpreference;

import android.os.Bundle;
import android.support.v7.app.AlertDialog.Builder;
import android.support.v7.preference.PreferenceDialogFragmentCompat;
import android.view.Gravity;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.NumberPicker;

public class NumberPickerPreferenceFragment extends PreferenceDialogFragmentCompat {

    private NumberPicker         numberPicker;    
    private int                  increment = 1;
    public static NumberPickerPreferenceFragment newInstance(final String key) {
        final NumberPickerPreferenceFragment fragment =
                new NumberPickerPreferenceFragment();
        final Bundle b = new Bundle(1);
        b.putString(ARG_KEY, key);
        fragment.setArguments(b);
        return fragment;
    }

    
    @Override
    protected void onPrepareDialogBuilder(final Builder builder) {
        super.onPrepareDialogBuilder(builder);
        final NumberPickerPreference preference = (NumberPickerPreference) getPreference();
        increment = Math.max(1, preference.getIncrement());
        final int min = preference.getMinValue()/increment;
        final int max = preference.getMaxValue()/increment;
        numberPicker = new NumberPicker(this.getContext());
        if (increment > 1) {
            final String[]displayedValues = new String[max-min+1];
            for (int i=min;i<=max;i++) {
                displayedValues[i-min] = Integer.toString(i*increment);
            }
            numberPicker.setDisplayedValues(displayedValues);
        }
        
        numberPicker.setMinValue(min);
        numberPicker.setMaxValue(max);
        numberPicker.setValue(preference.getSelectedValue()/increment);
        numberPicker.setWrapSelectorWheel(preference.isWrapSelectorWheel());
        numberPicker.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));

        final LinearLayout linearLayout = new LinearLayout(this.getContext());
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        linearLayout.setGravity(Gravity.CENTER);
        linearLayout.addView(numberPicker);

        builder.setView(linearLayout);
    }

    @Override
    public void onDialogClosed(final boolean positiveResult) {
        final NumberPickerPreference preference = (NumberPickerPreference) getPreference();
        if (positiveResult && numberPicker != null) {
            final int newValue = numberPicker.getValue()*increment;

            if (preference.callChangeListener(newValue)) {
                preference.setSelectedValue(newValue);

                preference.updateSummary();
                preference.persist();
            }
        }
    }
}
