package mra.com.tastyfoodcafe;

public class PaymentMode
{
    String id,name,mobile,add1,add2,qunty;


    public PaymentMode()
    {}

    public PaymentMode(String id,String name, String mobile, String add1, String add2, String qunty) {
        this.id=id;
        this.name = name;
        this.mobile = mobile;
        this.add1 = add1;
        this.add2 = add2;
        this.qunty = qunty;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAdd1() {
        return add1;
    }

    public void setAdd1(String add1) {
        this.add1 = add1;
    }

    public String getAdd2() {
        return add2;
    }

    public void setAdd2(String add2) {
        this.add2 = add2;
    }

    public String getQunty() {
        return qunty;
    }

    public void setQunty(String qunty) {
        this.qunty = qunty;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
