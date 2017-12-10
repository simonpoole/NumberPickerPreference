package ch.poole.android.numberpickerpreference.sample;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

public class NumberPickerPreferenceApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        LeakCanary.install(this);
    }
}
