
package com.amongart.semantic.notablework;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ElementWork {

    @SerializedName("notableWorkItem")
    @Expose
    private NotableWorkItem notableWorkItem;
    @SerializedName("notableWorkInception")
    @Expose
    private NotableWorkInception notableWorkInception;
    @SerializedName("notableWorkPic")
    @Expose
    private NotableWorkPic notableWorkPic;
    @SerializedName("notableWorkLabel")
    @Expose
    private NotableWorkLabel notableWorkLabel;

    public NotableWorkItem getNotableWorkItem() {
        return notableWorkItem;
    }

    public void setNotableWorkItem(NotableWorkItem notableWorkItem) {
        this.notableWorkItem = notableWorkItem;
    }

    public NotableWorkInception getNotableWorkInception() {
        return notableWorkInception;
    }

    public void setNotableWorkInception(NotableWorkInception notableWorkInception) {
        this.notableWorkInception = notableWorkInception;
    }

    public NotableWorkPic getNotableWorkPic() {
        return notableWorkPic;
    }

    public void setNotableWorkPic(NotableWorkPic notableWorkPic) {
        this.notableWorkPic = notableWorkPic;
    }

    public NotableWorkLabel getNotableWorkLabel() {
        return notableWorkLabel;
    }

    public void setNotableWorkLabel(NotableWorkLabel notableWorkLabel) {
        this.notableWorkLabel = notableWorkLabel;
    }

}
