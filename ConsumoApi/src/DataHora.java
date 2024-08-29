import com.google.gson.annotations.SerializedName;

public class DataHora {
    @SerializedName("datetime")
    private String datetime;

    @SerializedName("timezone")
    private String timezone;

    public String getDatetime() {
        return datetime;
    }

    public String getTimezone() {
        return timezone;
    }
}