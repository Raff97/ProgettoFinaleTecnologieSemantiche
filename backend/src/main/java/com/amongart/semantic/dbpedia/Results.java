
package com.amongart.semantic.dbpedia;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Results {

    @SerializedName("distinct")
    @Expose
    private Boolean distinct;
    @SerializedName("ordered")
    @Expose
    private Boolean ordered;
    @SerializedName("bindings")
    @Expose
    private List<Binding> bindings = null;

    public Boolean getDistinct() {
        return distinct;
    }

    public void setDistinct(Boolean distinct) {
        this.distinct = distinct;
    }

    public Boolean getOrdered() {
        return ordered;
    }

    public void setOrdered(Boolean ordered) {
        this.ordered = ordered;
    }

    public List<Binding> getBindings() {
        return bindings;
    }

    public void setBindings(List<Binding> bindings) {
        this.bindings = bindings;
    }

}
