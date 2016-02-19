package com.mobaires.clase16rest2;

public class ForecastMoment {

    public ForecastMoment() {}

    private long dt;
    private String dt_txt;
    private ForecastMain main;
    public long getDt() {
        return dt;
    }

    public void setDt(long dt) {
        this.dt = dt;
    }

    public String getDt_txt() {
        return dt_txt;
    }

    public void setDt_txt(String dt_txt) {
        this.dt_txt = dt_txt;
    }

    public ForecastMain getMain() {
        return main;
    }

    public void setMain(ForecastMain main) {
        this.main = main;
    }

    @Override
    public String toString() {
        return main==null?"\tNo info": ("\tT: " + main.getTemp() + "º" + "    H: " + main.getHumidity() + "%");
    }
}

