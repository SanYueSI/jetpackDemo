package com.sanyue.jetpakcdemonew.mvvm;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.sanyue.jetpakcdemonew.R;


public class MyDiglog {
    private static Dialog loadingDialog;

    public static Dialog createLoadingDialog(Context context, String msg) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.dialog_loading,null);
        loadingDialog=new Dialog(context,R.style.MyDialogStyle);
        TextView msgText= (TextView) view.findViewById(R.id.tipTextView);
        msgText.setText(msg);
        loadingDialog.setContentView(view);
        loadingDialog.setCancelable(true);
        loadingDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode== KeyEvent.KEYCODE_SEARCH)
                {
                    return true;
                }
                else
                {
                    return false; //默认返回 false
                }
            }
        });

        return loadingDialog;

    }



}
