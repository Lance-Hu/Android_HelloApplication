package com.example.helloapplication.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.telephony.SmsMessage;

/**
 * Created by Administrator on 2018/7/24.
 */
public class SmsReceiver extends BroadcastReceiver {
    private Handler handler;


    public SmsReceiver(Handler handler) {
        this.handler = handler;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String format = intent.getStringExtra("format");
        Bundle bundle = intent.getExtras();
        Object[] pdus = (Object[]) bundle.get("pdus");
        SmsMessage[] messages = new SmsMessage[pdus.length];
        if (Build.VERSION.SDK_INT >= 23) {
            for (int i = 0; i < messages.length; i++) {
                messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i], format);
            }
        } else {
            for (int i = 0; i < messages.length; i++) {
                messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
            }
        }

        String number = messages[0].getOriginatingAddress();
        String content = "";
        for (SmsMessage smsMessage : messages) {
            //最好使用java中的stringbuilder或者stringbuffer类
            content += smsMessage.getMessageBody();
        }

        Message message = handler.obtainMessage();
        message.what = 1;
        Bundle handlerBundle = new Bundle();
        handlerBundle.putString("number", number);
        handlerBundle.putString("content", content);
        message.setData(handlerBundle);
        handler.sendMessage(message);
    }
}
