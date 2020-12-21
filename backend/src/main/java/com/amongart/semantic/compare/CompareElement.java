
package com.amongart.semantic.compare;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CompareElement {

    @SerializedName("item")
    @Expose
    private Item item;
    @SerializedName("itemLabel")
    @Expose
    private ItemLabel itemLabel;
    @SerializedName("author")
    @Expose
    private Author author;
    @SerializedName("authorLabel")
    @Expose
    private AuthorLabel authorLabel;
    @SerializedName("date")
    @Expose
    private Date date;
    @SerializedName("image")
    @Expose
    private Image image;

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public ItemLabel getItemLabel() {
        return itemLabel;
    }

    public void setItemLabel(ItemLabel itemLabel) {
        this.itemLabel = itemLabel;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

}
