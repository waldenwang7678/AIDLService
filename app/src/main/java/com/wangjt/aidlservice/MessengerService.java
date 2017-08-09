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
        public void handleMessage(Message msgFromClient) {
            Message msgToClient = Message.obtain(msgFromClient);//将客户端发来的消息复制一份
            switch (msgFromClient.what) {
                case 200:
                    SystemClock.sleep(3000);
                    Bundle bundle = new Bundle();
                    bundle.putString("address", "https://www.baidu.com");

                    msgToClient.setData(bundle);
                    msgToClient.what = 200;
                    msgToClient.arg2 = msgFromClient.arg1 + msgFromClient.arg2;
                    Messenger messenger = msgFromClient.replyTo;  // 客户端信使 , 发消息给客户端
                    try {
                        messenger.send(msgToClient);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    break;
            }
            super.handleMessage(msgToClient);
        }
    });
}
