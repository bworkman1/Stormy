package yourwebdeveloper.com.stormy.weather;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import yourwebdeveloper.com.stormy.R;

/**
 * Created by phuck on 11/14/2015.
 */
public class Current {

    private String mLocation;
    private String mIcon;
    private long mTime;
    private double mTemperature;
    private double mHumidity;
    private double mPercipChance;
    private String mSummary;
    private String mTimeZone;

    public String getTimeZone() {
        return mTimeZone;
    }

    public void setTimeZone(String mTimeZone) {
        this.mTimeZone = mTimeZone;
    }

    public String getLocation() {
        return mLocation;
    }

    public void setLocation(String mLocation) {
        this.mLocation = mLocation;
    }

    public String getIcon() {
        return mIcon;
    }

    public void setIcon(String mIcon) {
        this.mIcon = mIcon;
    }

    public int getIconId() {

        return Forecast.getIconId(mIcon);
    }

    public long getTime() {
        return mTime;
    }

    public void setTime(long mTime) {
        this.mTime = mTime;
    }

    public String getFormattedTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("h:mm a");
        formatter.setTimeZone(TimeZone.getTimeZone(getTimeZone()));
        Date dateTime = new Date(getTime()*1000);
        String timeString = formatter.format(dateTime);

        return timeString;
    }

    public int getTemerature() {
        return (int) Math.round(mTemperature);
    }

    public void setTemerature(double mTemerature) {
        this.mTemperature = mTemerature;
    }

    public double getHumidity() {
        return mHumidity;
    }

    public void setHumidity(double mHumidity) {
        this.mHumidity = mHumidity;
    }

    public int getPercipChance() {
        double precipPercentage = mPercipChance * 100;
        return (int)Math.round(precipPercentage);
    }

    public void setPercipChance(double mpercipChance) {
        this.mPercipChance = mpercipChance;
    }

    public String getSummary() {
        return mSummary;
    }

    public void setSummary(String mSummary) {
        this.mSummary = mSummary;
    }


}
