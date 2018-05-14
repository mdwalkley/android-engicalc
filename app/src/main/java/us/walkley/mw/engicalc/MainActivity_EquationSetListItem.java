package us.walkley.mw.engicalc;

import android.content.Intent;

/**
 * Created by michael_walkley on 5/4/2018.
 */

public class MainActivity_EquationSetListItem {
    private String mCategory, mTitle;
    private Intent mIntent;

    public MainActivity_EquationSetListItem(String category, String title, Intent intent){
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
        return "MainActivity_EquationSetListItem{" +
                "mCategory='" + mCategory + '\'' +
                ", mTitle='" + mTitle + '\'' +
                ", mIntent=" + mIntent +
                '}';
    }
}
