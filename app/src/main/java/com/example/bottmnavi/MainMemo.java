package com.example.bottmnavi;

public class MainMemo {

    private int iv_profile;
    private String tv_title;
    private String tv_content;

    public MainMemo(int iv_profile,String tv_title,String tv_content) {
        this.iv_profile = iv_profile;
        this.tv_title = tv_title;
        this.tv_content = tv_content;
    }

    public int getIv_profile() {
        return iv_profile;
    }

    public void setIv_profile(int iv_profile) {
        this.iv_profile = iv_profile;
    }

    public String getTv_content() {
        return tv_content;
    }

    public void setTv_content(String tv_content) {
        this.tv_content = tv_content;
    }

    public String getTv_title() {
        return tv_title;
    }

    public void setTv_title(String tv_title) {
        this.tv_title = tv_title;
    }
}
