package detroitlabs.mtgexchange;

import android.graphics.Color;
import android.os.Parcel;
import android.os.Parcelable;

import java.lang.reflect.Array;

/**
 * Created by Drew on 2/24/15.
 */
public class Card implements Parcelable {

    private String[] cardColor;
    private String cardName;
    private Double cardValue;
    private Double valueChange;
    private String manaCost;
    private Long cardID;
    private String pictureURL;

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public Long getCardID() {
        return cardID;
    }

    public void setCardID(Long cardID) {
        this.cardID = cardID;
    }

    public String getManaCost() {
        return manaCost;
    }

    public void setManaCost(String manaCost) {
        this.manaCost = manaCost;
    }

    public Double getValueChange() {
        return valueChange;
    }

    public void setValueChange(Double valueChange) {
        this.valueChange = valueChange;
    }

    public Double getCardValue() {
        return cardValue;
    }

    public void setCardValue(Double cardValue) {
        this.cardValue = cardValue;
    }

    public String[] getCardColor() { return cardColor; }

    public void setCardColor(String[] cardColor) {
        this.cardColor = cardColor;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.cardName);
        dest.writeValue(this.cardValue);
        dest.writeValue(this.valueChange);
        dest.writeString(this.manaCost);
        dest.writeValue(this.cardID);
        dest.writeString(this.pictureURL);
    }

    public Card() {
    }

    private Card(Parcel in) {
        this.cardName = in.readString();
        this.cardValue = (Double) in.readValue(Double.class.getClassLoader());
        this.valueChange = (Double) in.readValue(Double.class.getClassLoader());
        this.manaCost = in.readString();
        this.cardID = (Long) in.readValue(Long.class.getClassLoader());
        this.pictureURL = in.readString();
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
