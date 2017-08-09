package com.wangjt.aidlservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import com.walden.aidl.MyAidl;

/**
 * Created by wangjt on 2017/8/9.
 */

public class CalcService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBiner;
    }

    MyAidl.Stub mBiner = new MyAidl.Stub() {
        @Override
        public int add(int x, int y) throws RemoteException {
            return x + y;
        }

        @Override
        public int min(int x, int y) throws RemoteException {
            return x - y;
        }
    };


}
