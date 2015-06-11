package com.dimit.defaultMtd;
public class OverridableImpl implements Defaulable {
    @Override
    public String notRequired() {
        return "重写接口默认方法";
    }
}