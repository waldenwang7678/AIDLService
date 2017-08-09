package com.wangjt.aidlservice;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.os.SystemClock;
import android.support.annotation.Nullable;

/**
 * Created by wangjt on 2017/8/9.
 * messenger 进程间通信
 */

public class MessengerService extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return messenger.getBinder();
    }

    private Messenger messenger = new Messenger(new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            SystemClock.sleep(3000);
            Message message = Message.obtain();
            Bundle bundle = new Bundle();
            bundle.putString("address", "https://www.baidu.com");
            message.setData(bundle);

            Messenger messenger = msg.replyTo;  //
            try {
                messenger.send(message);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    });
}
