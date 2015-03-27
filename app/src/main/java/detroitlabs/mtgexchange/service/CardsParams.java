package detroitlabs.mtgexchange.service;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class CardsParams implements Parcelable {

    private Double changeInPriceGreaterThan;
    private Double changeInPriceLessThan;
    private String set;
    private String rarity;
    private List<String> colors;
    private List<String> sortFields;
    private List<String> sortOrders;
    private Long start;
    private Long limit;

    public Double getChangeInPriceGreaterThan() {
        return changeInPriceGreaterThan;
    }

    public void setChangeInPriceGreaterThan(Double changeInPriceGreaterThan) {
        this.changeInPriceGreaterThan = changeInPriceGreaterThan;
    }

    public Double getChangeInPriceLessThan() {
        return changeInPriceLessThan;
    }

    public void setChangeInPriceLessThan(Double changeInPriceLessThan) {
        this.changeInPriceLessThan = changeInPriceLessThan;
    }

    public String getSet() {
        return set;
    }

    public void setSet(String set) {
        this.set = set;
    }

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    public List<String> getColors() {
        return colors;
    }

    public void setColors(List<String> colors) {
        this.colors = colors;
    }

    public List<String> getSortFields() {
        return sortFields;
    }

    public void setSortFields(List<String> sortFields) {
        this.sortFields = sortFields;
    }

    public List<String> getSortOrders() {
        return sortOrders;
    }

    public void setSortOrders(List<String> sortOrders) {
        this.sortOrders = sortOrders;
    }

    public Long getStart() {
        return start;
    }

    public void setStart(Long start) {
        this.start = start;
    }

    public Long getLimit() {
        return limit;
    }

    public void setLimit(Long limit) {
        this.limit = limit;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.changeInPriceGreaterThan);
        dest.writeValue(this.changeInPriceLessThan);
        dest.writeString(this.set);
        dest.writeString(this.rarity);
        dest.writeList(this.colors);
        dest.writeList(this.sortFields);
        dest.writeList(this.sortOrders);
        dest.writeValue(this.start);
        dest.writeValue(this.limit);
    }

    public CardsParams() {
    }

    private CardsParams(Parcel in) {
        this.changeInPriceGreaterThan = (Double) in.readValue(Double.class.getClassLoader());
        this.changeInPriceLessThan = (Double) in.readValue(Double.class.getClassLoader());
        this.set = in.readString();
        this.rarity = in.readString();
        this.colors = new ArrayList<String>();
        in.readList(this.colors, List.class.getClassLoader());
        this.sortFields = new ArrayList<String>();
        in.readList(this.sortFields, List.class.getClassLoader());
        this.sortOrders = new ArrayList<String>();
        in.readList(this.sortOrders, List.class.getClassLoader());
        this.start = (Long) in.readValue(Long.class.getClassLoader());
        this.limit = (Long) in.readValue(Long.class.getClassLoader());
    }

    public static final Parcelable.Creator<CardsParams> CREATOR = new Parcelable.Creator<CardsParams>() {
        public CardsParams createFromParcel(Parcel source) {
            return new CardsParams(source);
        }

        public CardsParams[] newArray(int size) {
            return new CardsParams[size];
        }
    };
}
