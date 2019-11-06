package com.example.asus.customer.weight;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RadioGroup;

import com.example.asus.customer.R;

/**
 * 自定义可取消radioButton，可与radioGroup一起用
 *
 * @author gjc
 * @version 1.0.0
 * @since 2018-05-30
 */

public class ToggleRadioButton extends android.support.v7.widget.AppCompatRadioButton {

    public ToggleRadioButton(Context context) {
        this(context, null);
    }

    public ToggleRadioButton(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.radioButtonStyle);
    }

    public ToggleRadioButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void toggle() {
        setChecked(!isChecked());
        if (!isChecked()) {
            ((RadioGroup)getParent()).clearCheck();
        }
    }
}
