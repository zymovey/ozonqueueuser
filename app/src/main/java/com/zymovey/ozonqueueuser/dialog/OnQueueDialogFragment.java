package com.zymovey.ozonqueueuser.dialog;

        import android.app.AlertDialog;
        import android.app.Dialog;
        import android.os.Bundle;
        import androidx.fragment.app.DialogFragment;
        import androidx.annotation.NonNull;

        import com.zymovey.ozonqueueuser.R;

public class OnQueueDialogFragment extends DialogFragment {

    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        return builder
                .setTitle("В очередь?")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setView(R.layout.dialog)
                .setPositiveButton("ОК", null)
                .setNegativeButton("Отмена", null)
                .create();

    }
}