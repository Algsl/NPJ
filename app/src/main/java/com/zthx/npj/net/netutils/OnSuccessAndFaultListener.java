package com.zthx.npj.net.netutils;

import java.io.IOException;

/**
 * Created by 眼神 on 2018/3/27.
 */
public interface OnSuccessAndFaultListener {
    void onSuccess(String result) throws IOException;
    void onFault(String errorMsg);
}
