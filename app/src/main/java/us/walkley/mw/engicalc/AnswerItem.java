package us.walkley.mw.engicalc;

public class AnswerItem {
    private int mImgSrc;
    private double mAnswer = 0;

    AnswerItem(int i, double d){
        mImgSrc = i;
        mAnswer = d;
    }

    public double getAnswer() {
        return mAnswer;
    }
    public void setAnswer(double d) {
        mAnswer = d;
    }
    public int getImgSrc() {
        return mImgSrc;
    }
    public void setImgSrc(int i) {
        mImgSrc = i;
    }
}