package it.univpm.mobile_programming_project.utils.picker;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;

import java.util.Calendar;

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

        return new TimePickerDialog(getActivity(), this.timeSetListener, hour, minute, DateFormat.is24HourFormat(getActivity()));
    }
}
