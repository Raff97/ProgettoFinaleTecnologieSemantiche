
package com.amongart.semantic.depict;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DepictElement {

    @SerializedName("depict")
    @Expose
    private Depict depict;
    @SerializedName("depictLabel")
    @Expose
    private DepictLabel depictLabel;

    public Depict getDepict() {
        return depict;
    }

    public void setDepict(Depict depict) {
        this.depict = depict;
    }

    public DepictLabel getDepictLabel() {
        return depictLabel;
    }

    public void setDepictLabel(DepictLabel depictLabel) {
        this.depictLabel = depictLabel;
    }

}
