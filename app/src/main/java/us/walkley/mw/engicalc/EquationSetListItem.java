package us.walkley.mw.engicalc;

import android.content.Intent;

/**
 * Created by michael_walkley on 5/4/2018.
 */

public class EquationSetListItem {
    private String mCategory, mTitle;
    private Intent mIntent;

    public EquationSetListItem(String category, String title, Intent intent){
        this.mCategory = category;
        this.mTitle = title;
        this.mIntent = intent;
    }

    public String getCategory() {
        return mCategory;
    }

    public void setCategory(String mCategory) {
        this.mCategory = mCategory;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public Intent getIntent() {
        return mIntent;
    }

    public void setIntent(Intent mIntent) {
        this.mIntent = mIntent;
    }

    @Override
    public String toString() {
        return "EquationSetListItem{" +
                "mCategory='" + mCategory + '\'' +
                ", mTitle='" + mTitle + '\'' +
                ", mIntent=" + mIntent +
                '}';
    }
}
