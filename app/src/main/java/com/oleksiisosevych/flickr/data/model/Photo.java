
package com.oleksiisosevych.flickr.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Photo {

    @SerializedName("id") @Expose private String id;
    @SerializedName("owner") @Expose private String owner;
    @SerializedName("secret") @Expose private String secret;
    @SerializedName("server") @Expose private String server;
    @SerializedName("farm") @Expose private Integer farm;
    @SerializedName("title") @Expose private String title;
    @SerializedName("ispublic") @Expose private Integer ispublic;
    @SerializedName("isfriend") @Expose private Integer isfriend;
    @SerializedName("isfamily") @Expose private Integer isfamily;

    private Photo(Builder builder) {
        setId(builder.id);
        setOwner(builder.owner);
        setSecret(builder.secret);
        setServer(builder.server);
        setFarm(builder.farm);
        setTitle(builder.title);
        setIspublic(builder.ispublic);
        setIsfriend(builder.isfriend);
        setIsfamily(builder.isfamily);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public Integer getFarm() {
        return farm;
    }

    public void setFarm(Integer farm) {
        this.farm = farm;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getIspublic() {
        return ispublic;
    }

    public void setIspublic(Integer ispublic) {
        this.ispublic = ispublic;
    }

    public Integer getIsfriend() {
        return isfriend;
    }

    public void setIsfriend(Integer isfriend) {
        this.isfriend = isfriend;
    }

    public Integer getIsfamily() {
        return isfamily;
    }

    public void setIsfamily(Integer isfamily) {
        this.isfamily = isfamily;
    }

    public static final class Builder {
        private String id;
        private String owner;
        private String secret;
        private String server;
        private Integer farm;
        private String title;
        private Integer ispublic;
        private Integer isfriend;
        private Integer isfamily;

        public Builder() {
        }

        public Builder id(String val) {
            id = val;
            return this;
        }

        public Builder owner(String val) {
            owner = val;
            return this;
        }

        public Builder secret(String val) {
            secret = val;
            return this;
        }

        public Builder server(String val) {
            server = val;
            return this;
        }

        public Builder farm(Integer val) {
            farm = val;
            return this;
        }

        public Builder title(String val) {
            title = val;
            return this;
        }

        public Builder ispublic(Integer val) {
            ispublic = val;
            return this;
        }

        public Builder isfriend(Integer val) {
            isfriend = val;
            return this;
        }

        public Builder isfamily(Integer val) {
            isfamily = val;
            return this;
        }

        public Photo build() {
            return new Photo(this);
        }
    }
}
