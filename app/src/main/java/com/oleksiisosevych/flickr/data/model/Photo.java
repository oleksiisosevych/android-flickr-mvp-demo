
package com.oleksiisosevych.flickr.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
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

    /**
     * @return The id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id The id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return The owner
     */
    public String getOwner() {
        return owner;
    }

    /**
     * @param owner The owner
     */
    public void setOwner(String owner) {
        this.owner = owner;
    }

    /**
     * @return The secret
     */
    public String getSecret() {
        return secret;
    }

    /**
     * @param secret The secret
     */
    public void setSecret(String secret) {
        this.secret = secret;
    }

    /**
     * @return The server
     */
    public String getServer() {
        return server;
    }

    /**
     * @param server The server
     */
    public void setServer(String server) {
        this.server = server;
    }

    /**
     * @return The farm
     */
    public Integer getFarm() {
        return farm;
    }

    /**
     * @param farm The farm
     */
    public void setFarm(Integer farm) {
        this.farm = farm;
    }

    /**
     * @return The title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return The ispublic
     */
    public Integer getIspublic() {
        return ispublic;
    }

    /**
     * @param ispublic The ispublic
     */
    public void setIspublic(Integer ispublic) {
        this.ispublic = ispublic;
    }

    /**
     * @return The isfriend
     */
    public Integer getIsfriend() {
        return isfriend;
    }

    /**
     * @param isfriend The isfriend
     */
    public void setIsfriend(Integer isfriend) {
        this.isfriend = isfriend;
    }

    /**
     * @return The isfamily
     */
    public Integer getIsfamily() {
        return isfamily;
    }

    /**
     * @param isfamily The isfamily
     */
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
