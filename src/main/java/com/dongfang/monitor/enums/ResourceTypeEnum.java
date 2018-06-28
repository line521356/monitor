package com.dongfang.monitor.enums;

public enum ResourceTypeEnum {
    DIR("目录"),MENU("左侧菜单"),BUTTON("按钮");
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
}
