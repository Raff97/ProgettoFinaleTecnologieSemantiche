
package com.amongart.semantic.important;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Results {

    @SerializedName("bindings")
    @Expose
    private List<FamousWork> bindings = null;

    public List<FamousWork> getBindings() {
        return bindings;
    }

    public void setBindings(List<FamousWork> bindings) {
        this.bindings = bindings;
    }

}
