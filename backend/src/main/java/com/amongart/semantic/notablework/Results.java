
package com.amongart.semantic.notablework;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Results {

    @SerializedName("bindings")
    @Expose
    private List<ElementWork> bindings = null;

    public List<ElementWork> getBindings() {
        return bindings;
    }

    public void setBindings(List<ElementWork> bindings) {
        this.bindings = bindings;
    }

}
