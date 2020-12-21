
package com.amongart.semantic.name;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Element {

    @SerializedName("item")
    @Expose
    private Item item;
    @SerializedName("author")
    @Expose
    private Author author;
    @SerializedName("label")
    @Expose
    private Label label;
    @SerializedName("labelAuthor")
    @Expose
    private LabelAuthor labelAuthor;

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Label getLabel() {
        return label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }

    public LabelAuthor getLabelAuthor() {
        return labelAuthor;
    }

    public void setLabelAuthor(LabelAuthor labelAuthor) {
        this.labelAuthor = labelAuthor;
    }

}
