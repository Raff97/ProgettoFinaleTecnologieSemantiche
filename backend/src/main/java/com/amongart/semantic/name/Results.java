
package com.amongart.semantic.name;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Results {

    @SerializedName("bindings")
    @Expose
    private List<Element> bindings = null;

    public List<Element> getBindings() {
        return bindings;
    }

    public void setBindings(List<Element> bindings) {
        this.bindings = bindings;
    }

}
