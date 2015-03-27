package detroitlabs.mtgexchange;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Drew on 2/24/15.
 */
public class Card implements Parcelable {

    private String cardName;
    private String setName;
    private String rarityName;
    private List<String> colors;
    private String imageURL;
    private Double currentPrice;
    private Double changeInPrice;

    private Long id;


    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getSetName() {
        return setName;
    }

    public void setSetName(String setName) {
        this.setName = setName;
    }

    public String getRarityName() {
        return rarityName;
    }

    public void setRarityName(String rarityName) {
        this.rarityName = setName;
    }

    public List<String> getColors() {
        return colors;
    }

    public void setColors(List<String> colors) {
        this.colors = colors;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public Double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(Double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public Double getChangeInPrice() {
        return changeInPrice;
    }

    public void setChangeInPrice(Double changeInPrice) {
        this.changeInPrice = changeInPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.cardName);
        dest.writeString(this.setName);
        dest.writeList(this.colors);
        dest.writeString(this.imageURL);
        dest.writeValue(this.currentPrice);
        dest.writeValue(this.changeInPrice);
        dest.writeValue(this.id);
    }

    public Card() {
    }

    private Card(Parcel in) {
        this.cardName = in.readString();
        this.setName = in.readString();
        this.colors = new ArrayList<String>();
        in.readList(this.colors, List.class.getClassLoader());
        this.imageURL = in.readString();
        this.currentPrice = (Double) in.readValue(Double.class.getClassLoader());
        this.changeInPrice = (Double) in.readValue(Double.class.getClassLoader());
        this.id = (Long) in.readValue(Long.class.getClassLoader());
    }

    public static final Parcelable.Creator<Card> CREATOR = new Parcelable.Creator<Card>() {
        public Card createFromParcel(Parcel source) {
            return new Card(source);
        }

        public Card[] newArray(int size) {
            return new Card[size];
        }
    };
}








