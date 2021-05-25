import java.time.LocalDateTime;

public class User {
    private String uID;
    private String uname;
    private String upwd;
    private String uaddress;
    private String utel;
    private LocalDateTime utime;

    public User() {
        // TODO Auto-generated constructor stub
    }

    public User(String uID, String uname, String upwd, String uaddress, String utel, LocalDateTime utime) {
        super();
        this.uID = uID;
        this.uname = uname;
        this.upwd = upwd;
        this.uaddress = uaddress;
        this.utel = utel;
        this.utime = utime;
    }

    public String getuID() {
        return uID;
    }

    public void setuID(String uID) {
        this.uID = uID;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUpwd() {
        return upwd;
    }

    public void setUpwd(String upwd) {
        this.upwd = upwd;
    }

    public String getUaddress() {
        return uaddress;
    }

    public void setUaddress(String uaddress) {
        this.uaddress = uaddress;
    }

    public String getUtel() {
        return utel;
    }

    public void setUtel(String utel) {
        this.utel = utel;
    }

    public LocalDateTime getUtime() {
        return utime;
    }

    public void setUtime(LocalDateTime utime) {
        this.utime = utime;
    }

    @Override
    public String toString() {
        return "User ID = " + uID + ", Name = " + uname + ", Password =" + upwd + ", Address =" + uaddress
                + ", utel=" + utel + ", utime=" + utime + "]";
    }

}