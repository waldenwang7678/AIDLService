package com.wangjt.aidlservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.support.annotation.Nullable;

/**
 * Created by wangjt on 2017/8/9.
 * 使用 binder 完成简单的计算功能(参考 CalcSercice 使用AIDL 完成此功能)
 */

public class CalcServiceNoAidl extends Service {
    private String falg = "CalcServiceNoAidl";
    private MyBinder mBinder = new MyBinder();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    class MyBinder extends Binder {
        @Override
        protected boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            switch (code) {
                case 0x110: {
                    data.enforceInterface(falg);
                    int _arg0;
                    _arg0 = data.readInt();
                    int _arg1;
                    _arg1 = data.readInt();
                    int _result = _arg0 + _arg1;
                    reply.writeNoException();
                    reply.writeInt(_result);
                    return true;
                }
                case 0x111: {
                    data.enforceInterface(falg);
                    int _arg0;
                    _arg0 = data.readInt();
                    int _arg1;
                    _arg1 = data.readInt();
                    int _result = _arg0 - _arg1;
                    reply.writeNoException();
                    reply.writeInt(_result);
                    return true;
                }
            }
            return super.onTransact(code, data, reply, flags);

        }
    }
}
