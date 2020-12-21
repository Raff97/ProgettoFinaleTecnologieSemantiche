
package com.amongart.semantic.depict;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Results {

    @SerializedName("bindings")
    @Expose
    private List<DepictElement> bindings = null;

    public List<DepictElement> getBindings() {
        return bindings;
    }

    public void setBindings(List<DepictElement> bindings) {
        this.bindings = bindings;
    }

}
