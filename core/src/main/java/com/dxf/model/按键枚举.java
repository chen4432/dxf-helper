package com.dxf.model;

public enum 按键枚举 {
    X(0, "x"),
    C(0, "c"),
    Z(0, "z"),
    V(0, "v"),
    Q(0, "q"),
    W(0, "w"),
    E(0, "e"),
    R(0, "r"),
    T(0, "t"),
    Y(0, "y"),
    A(0, "a"),
    S(0, "s"),
    D(0, "d"),
    F(0, "f"),
    G(0, "g"),
    H(0, "h"),
    方向上(0, "up"),
    方向下(0, "down"),
    方向左(0, "left"),
    方向右(0, "right"),
    退出(0, "esc"),
    空格(0, "space");
    int vkCode;
    String strCode;

    public String getStrCode() {
        return strCode;
    }

    按键枚举(int vkCode, String strCode) {
        this.vkCode = vkCode;
        this.strCode = strCode;
    }

}
