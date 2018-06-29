package com.dongfang.monitor.enums;

import java.util.ArrayList;
import java.util.List;

public enum ResourceTypeEnum {
    TOPMENU("顶级菜单"),DIR("目录"),MENU("左侧菜单"),BUTTON("按钮");
    String title;

    ResourceTypeEnum(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public static List<ResourceTypeEnum> getTopList(){
        List <ResourceTypeEnum> topList = new ArrayList<>();
        topList.add(TOPMENU);
        topList.add(DIR);
        return topList;
    }
}
