package com.robertsmith.gifttracker.Data_Sources;

import android.net.Uri;

import java.io.Serializable;

/**
 * Created by robertsmith on 12/16/14.
 */
public class Gift implements Serializable
{
    String giftName;
    String giftPrice;
    String giftStore;
    String giftPic;
    Boolean purchased;

    public Boolean getPurchased()
    {
        return purchased;
    }

    public void setPurchased(Boolean purchased)
    {
        this.purchased = purchased;
    }

    public String getGiftName() {
        return giftName;
    }

    public void setGiftName(String giftName) {
        this.giftName = giftName;
    }

    public String getGiftPrice() {
        return giftPrice;
    }

    public void setGiftPrice(String giftPrice) {
        this.giftPrice = giftPrice;
    }

    public String getGiftStore() {
        return giftStore;
    }

    public void setGiftStore(String giftStore)
    {
        this.giftStore = giftStore;
    }

    public String getGiftPic() {
        return giftPic;
    }

    public void setGiftPic(String giftPic) {
        this.giftPic = giftPic;
    }
}
