
package com.amongart.semantic.important;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FamousWork {

    @SerializedName("item")
    @Expose
    private Item item;
    @SerializedName("label")
    @Expose
    private Label label;
    @SerializedName("author")
    @Expose
    private Author author;
    @SerializedName("authorLabel")
    @Expose
    private AuthorLabel authorLabel;
    @SerializedName("image")
    @Expose
    private Image image;
    @SerializedName("date")
    @Expose
    private DateImportant date;

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Label getLabel() {
        return label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public AuthorLabel getAuthorLabel() {
        return authorLabel;
    }

    public void setAuthorLabel(AuthorLabel authorLabel) {
        this.authorLabel = authorLabel;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public DateImportant getDate() {
        return date;
    }

    public void setDate(DateImportant date) {
        this.date = date;
    }

}
