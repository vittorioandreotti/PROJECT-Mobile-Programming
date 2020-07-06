package it.univpm.mobile_programming_project.utils.picker;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;

import java.util.Calendar;

import it.univpm.mobile_programming_project.R;

public class TimePickerFragment extends DialogFragment {

    private final TimePickerDialog.OnTimeSetListener timeSetListener;

    public TimePickerFragment (TimePickerDialog.OnTimeSetListener timeSetListener) {
        this.timeSetListener = timeSetListener;
    }

    @NonNull
    public Dialog onCreateDialog (Bundle savedIstance) {
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        return new TimePickerDialog(getActivity(), R.style.Theme_Widget_Dialog, this.timeSetListener, hour, minute, DateFormat.is24HourFormat(getActivity()));
    }
}
