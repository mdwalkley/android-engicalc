package us.walkley.mw.engicalc;

public class AnswerItem {
    private int mImgSrc;
    private String mAnswer = "";

    AnswerItem(int i, String s){
        mImgSrc = i;
        mAnswer = s;
    }

    public String getAnswer() {
        return mAnswer;
    }
    public void setAnswer(String s) {
        mAnswer = s;
    }
    public int getImgSrc() {
        return mImgSrc;
    }
    public void setImgSrc(int i) {
        mImgSrc = i;
    }
}