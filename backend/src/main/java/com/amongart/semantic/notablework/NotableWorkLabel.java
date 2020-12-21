
package com.amongart.semantic.notablework;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NotableWorkLabel {

    @SerializedName("xml:lang")
    @Expose
    private String xmlLang;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("value")
    @Expose
    private String value;

    public String getXmlLang() {
        return xmlLang;
    }

    public void setXmlLang(String xmlLang) {
        this.xmlLang = xmlLang;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
