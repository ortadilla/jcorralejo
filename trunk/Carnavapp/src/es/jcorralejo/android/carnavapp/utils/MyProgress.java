package es.jcorralejo.android.carnavapp.utils;

import es.jcorralejo.android.carnavapp.R;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;

public class MyProgress extends ProgressDialog{

	public MyProgress(Context context) {
		super(context);
	}
	
    public static MyProgress show(Context context, CharSequence title, CharSequence message, boolean indeterminate, boolean cancelable, OnCancelListener cancelListener) {
    	MyProgress dialog = new MyProgress(context);
        dialog.setTitle(title);
        dialog.setMessage(message);
        dialog.setIndeterminate(indeterminate);
        dialog.setCancelable(cancelable);
        dialog.setOnCancelListener(cancelListener);
        dialog.show();
        return dialog;
    }
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	 TypedArray a = getContext().obtainStyledAttributes(null, R.styleable.AlertDialog, R.attr.alertDialogStyle, R.style.Holo_AlertDialog);
    	 View view = getLayoutInflater().inflate(a.getResourceId(R.styleable.AlertDialog_progressLayout, R.layout.progress), null);
    	 setView(view);
    }

}
