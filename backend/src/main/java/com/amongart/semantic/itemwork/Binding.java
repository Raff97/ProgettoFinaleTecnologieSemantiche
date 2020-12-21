
package com.amongart.semantic.itemwork;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Binding {

    @SerializedName("author")
    @Expose
    private Author author;
    @SerializedName("picAuthor")
    @Expose
    private PicAuthor picAuthor;
    @SerializedName("authorLabel")
    @Expose
    private AuthorLabel authorLabel;
    @SerializedName("pic")
    @Expose
    private Pic pic;
    @SerializedName("inception")
    @Expose
    private Inception inception;
    @SerializedName("label")
    @Expose
    private Label label;

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public PicAuthor getPicAuthor() {
        return picAuthor;
    }

    public void setPicAuthor(PicAuthor picAuthor) {
        this.picAuthor = picAuthor;
    }

    public AuthorLabel getAuthorLabel() {
        return authorLabel;
    }

    public void setAuthorLabel(AuthorLabel authorLabel) {
        this.authorLabel = authorLabel;
    }

    public Pic getPic() {
        return pic;
    }

    public void setPic(Pic pic) {
        this.pic = pic;
    }

    public Inception getInception() {
        return inception;
    }

    public void setInception(Inception inception) {
        this.inception = inception;
    }

    public Label getLabel() {
        return label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }

}
