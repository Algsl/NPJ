package com.zthx.npj.utils.marquee;

import com.squareup.otto.Bus;

public class AppBus extends Bus {

    private static AppBus bus;

    private AppBus() {
    }

    public static AppBus getInstance() {
        if (bus == null) {
            bus = new AppBus();
        }
        return bus;
    }
}