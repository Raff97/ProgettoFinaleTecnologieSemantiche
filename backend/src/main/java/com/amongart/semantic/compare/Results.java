
package com.amongart.semantic.compare;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Results {

    @SerializedName("bindings")
    @Expose
    private List<CompareElement> bindings = null;

    public List<CompareElement> getBindings() {
        return bindings;
    }

    public void setBindings(List<CompareElement> bindings) {
        this.bindings = bindings;
    }

}
