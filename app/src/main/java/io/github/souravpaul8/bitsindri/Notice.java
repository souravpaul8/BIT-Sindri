package io.github.souravpaul8.bitsindri;

public class Notice {

    private String title;
    private String desc;
    private String image;
    private String fullDesc;
    private String attachNotice;

    public Notice(String title, String desc, String image,String fullDesc,String attachNotice) {
        this.title = title;
        this.desc = desc;
        this.image = image;
        this.fullDesc = fullDesc;
        this.attachNotice = attachNotice;
    }

    public Notice() {
    }

    public String getFullDesc() {
        return fullDesc;
    }

    public void setFullDesc(String fullDesc) {
        this.fullDesc = fullDesc;
    }

    public String getAttachNotice() {
        return attachNotice;
    }

    public void setAttachNotice(String attachNotice) {
        this.attachNotice = attachNotice;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
