
package com.oleksiisosevych.flickr.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Photos {

    @SerializedName("page") @Expose private Integer page;
    @SerializedName("pages") @Expose private Integer pages;
    @SerializedName("perpage") @Expose private Integer perpage;
    @SerializedName("total") @Expose private String total;
    @SerializedName("photo") @Expose private List<Photo> photo = new ArrayList<Photo>();

    private Photos(Builder builder) {
        setPage(builder.page);
        setPages(builder.pages);
        setPerpage(builder.perpage);
        setTotal(builder.total);
        setPhoto(builder.photo);
    }

    /**
     * @return The page
     */
    public Integer getPage() {
        return page;
    }

    /**
     * @param page The page
     */
    public void setPage(Integer page) {
        this.page = page;
    }

    /**
     * @return The pages
     */
    public Integer getPages() {
        return pages;
    }

    /**
     * @param pages The pages
     */
    public void setPages(Integer pages) {
        this.pages = pages;
    }

    /**
     * @return The perpage
     */
    public Integer getPerpage() {
        return perpage;
    }

    /**
     * @param perpage The perpage
     */
    public void setPerpage(Integer perpage) {
        this.perpage = perpage;
    }

    /**
     * @return The total
     */
    public String getTotal() {
        return total;
    }

    /**
     * @param total The total
     */
    public void setTotal(String total) {
        this.total = total;
    }

    /**
     * @return The photo
     */
    public List<Photo> getPhoto() {
        return photo;
    }

    /**
     * @param photo The photo
     */
    public void setPhoto(List<Photo> photo) {
        this.photo = photo;
    }


    public static final class Builder {
        private Integer page;
        private Integer pages;
        private Integer perpage;
        private String total;
        private List<Photo> photo;

        public Builder() {
        }

        public Builder page(Integer val) {
            page = val;
            return this;
        }

        public Builder pages(Integer val) {
            pages = val;
            return this;
        }

        public Builder perpage(Integer val) {
            perpage = val;
            return this;
        }

        public Builder total(String val) {
            total = val;
            return this;
        }

        public Builder photo(List<Photo> val) {
            photo = val;
            return this;
        }

        public Photos build() {
            return new Photos(this);
        }
    }
}
