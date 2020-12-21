
package com.amongart.semantic.dbpedia;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Binding {

    @SerializedName("abstractAuthor")
    @Expose
    private AbstractAuthor abstractAuthor;
    @SerializedName("abstractPainting")
    @Expose
    private AbstractPainting abstractPainting;

    public AbstractAuthor getAbstractAuthor() {
        return abstractAuthor;
    }

    public void setAbstractAuthor(AbstractAuthor abstractAuthor) {
        this.abstractAuthor = abstractAuthor;
    }

    public AbstractPainting getAbstractPainting() {
        return abstractPainting;
    }

    public void setAbstractPainting(AbstractPainting abstractPainting) {
        this.abstractPainting = abstractPainting;
    }

}
