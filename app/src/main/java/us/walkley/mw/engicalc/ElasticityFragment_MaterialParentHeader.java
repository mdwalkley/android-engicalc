package us.walkley.mw.engicalc;

import java.util.ArrayList;

/**
 * Created by michael_walkley on 4/24/2018.
 */

public class ElasticityFragment_MaterialParentHeader {
    private String name;
    private ArrayList<ElasticityFragment_MaterialChildInfo> StructuralList = new ArrayList<ElasticityFragment_MaterialChildInfo>();

    ElasticityFragment_MaterialParentHeader(String str, ArrayList<ElasticityFragment_MaterialChildInfo> arrayList){
        name = str;
        StructuralList = arrayList;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public ArrayList<ElasticityFragment_MaterialChildInfo> getStructuralList() {
        return StructuralList;
    }
    public void setStructuralList(ArrayList<ElasticityFragment_MaterialChildInfo> list) {
        this.StructuralList = list;
    }
}
