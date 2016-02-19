package com.mobaires.clase16rest2;

import java.util.List;

/**
 * Created by cduarte on 2/19/16.
 */
public class Forecast5 {

    public Forecast5() {}

    private String code;
    private String message;
    private List<ForecastMoment> list;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ForecastMoment> getList() {
        return list;
    }

    public void setList(List<ForecastMoment> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        if (list==null||list.isEmpty()) {
            return super.toString();
        } else {
            StringBuilder sb = new StringBuilder();
            for (ForecastMoment moment: list) {
                sb.append(moment.getDt_txt());
                sb.append(":\n");
                sb.append(moment.toString());
                sb.append("\n");
            }
            return sb.toString();
        }
    }
}

