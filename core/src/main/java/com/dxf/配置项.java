package com.dxf;

import java.util.List;

public class 配置项 {

    public List<技能信息类> 技能栏;

    public List<技能信息类> 取技能栏() {
        return 技能栏;
    }

    public void 添加技能(技能信息类 技能信息) {
        技能栏.add(技能信息);
    }

}
