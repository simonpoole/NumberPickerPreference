NumberPickerPreference
======================

This is a fork of com.vanniktech.vntnumberpickerpreference.VNTNumberPickerPreference that extends android.support.v7.preference.DialogPreference instead of the platform DialogPreference. It further adds a couple of bells and whistles.


This is an easy to use custom preference, which opens a dialog with a number picker. The value gets automatically saved and you can set the default-, min- and maxValue conveniently in the XML.

```xml
<ch.poole.android.numberpickerpreference.NumberPickerPreference
    android:defaultValue="@integer/font_size_default_value"
    android:key="preference_font_size"
    android:title="@string/font_size"
    app:spt_maxValue="@integer/font_size_max_value"
    app:spt_minValue="@integer/font_size_min_value"
    app:spt_increment="2"
    app:spt_currentValueText="@string/current_vales"
    app:spt_setWrapSelectorWheel="true"/>
```

If spt_increment is larger that one available values are incremented by it, for example spt_minValue="0" and spt_maxValue="100" and spt_increment="10", will display 10, 20, 30 .... spt_currentValueText is the resource id of a text that will display the current value, it is expected to contain one number format parameter example: %1$d MB If spt_currentValueText is not set the selected number will be displayed.


# Setup

**build.gradle**

```groovy

compile 'ch.poole.android:NumberPickerPreference:1.0.0'
```

The library is available from jcenter.

Go to your preference XML file and insert the above mentioned XML tags. Afterwards you are good to go and can run your project!

```


Copyright (C) 2014-2016 Vanniktech - Niklas Baudy, 2017 Simon Poole

Licensed under the Apache License, Version 2.0
