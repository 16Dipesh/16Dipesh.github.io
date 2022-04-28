package mra.com.tastyfoodcafe;

public class Bakeryitemss {

    String id,pname,pimg,prise;

    public Bakeryitemss()
    {}

    public Bakeryitemss(String id, String pname, String pimg,String prise) {
        this.id = id;
        this.pname = pname;
        this.pimg = pimg;
        this.prise=prise;
    }

    public String getId() {
        return id;
    }

    public String getPname() {
        return pname;
    }

    public String getPimg() {
        return pimg;
    }

    public String getPrise() {
        return prise;
    }

}
