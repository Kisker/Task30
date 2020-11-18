package com.ftninformatika.android30.dialog;

import android.content.Context;
import android.content.DialogInterface;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

public class About extends AlertDialog.Builder{
    public About(@NonNull Context context) {
        super(context);

        setTitle("About");

        setMessage("By : Sveto");
        setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
    }

    public AlertDialog prepareDialog() {
        AlertDialog dialog = create();
        dialog.setCanceledOnTouchOutside(true);

        return dialog;
    }
}
