package us.walkley.mw.engicalc;

/**
 * Created by michael_walkley on 4/24/2018.
 */

public class ElasticityFragment_MaterialChildInfo {
    private String name = "";
    private double e_value = 0;

    ElasticityFragment_MaterialChildInfo(String str, double e){
        name = str;
        e_value = e;
    }

    public double getEValue() {
        return e_value;
    }
    public void setEValue(double e) {
        e_value = e;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}

