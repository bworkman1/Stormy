package yourwebdeveloper.com.stormy.weather;

import yourwebdeveloper.com.stormy.R;

/**
 * Created by phuck on 11/15/2015.
 */
public class Forecast {
    private Current mCurrent;
    private Hour[] mHourlyForecast;
    private Day[] mDailyForecast;

    public Current getCurrent() {
        return mCurrent;
    }

    public void setCurrent(Current current) {
        mCurrent = current;
    }

    public Hour[] getHourlyForecast() {
        return mHourlyForecast;
    }

    public void setHourlyForecast(Hour[] hourlyForecast) {
        mHourlyForecast = hourlyForecast;
    }

    public Day[] getDailyForecast() {
        return mDailyForecast;
    }

    public void setDailyForecast(Day[] dailyForecast) {
        mDailyForecast = dailyForecast;
    }

    public static int getIconId(String iconSting) {

        //clear-day, clear-night, rain, snow, sleet, wind, fog, cloudy, partly-cloudy-day, or partly-cloudy-night
        int iconId = R.drawable.clear_day;

        if (iconSting.equals("clear-day")) {
            iconId = R.drawable.clear_day;
        }
        else if (iconSting.equals("clear-night")) {
            iconId = R.drawable.clear_night;
        }
        else if (iconSting.equals("rain")) {
            iconId = R.drawable.rain;
        }
        else if (iconSting.equals("snow")) {
            iconId = R.drawable.snow;
        }
        else if (iconSting.equals("sleet")) {
            iconId = R.drawable.sleet;
        }
        else if (iconSting.equals("wind")) {
            iconId = R.drawable.wind;
        }
        else if (iconSting.equals("fog")) {
            iconId = R.drawable.fog;
        }
        else if (iconSting.equals("cloudy")) {
            iconId = R.drawable.cloudy;
        }
        else if (iconSting.equals("partly-cloudy-day")) {
            iconId = R.drawable.partly_cloudy;
        }
        else if (iconSting.equals("partly-cloudy-night")) {
            iconId = R.drawable.cloudy_night;
        }

        return iconId;

    }
}
