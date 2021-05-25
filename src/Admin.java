public class Admin {
    private String accountID;
    private String accountUserName;
    private String accountPWD;

    public Admin() {
        // TODO Auto-generated constructor stub
    }

    public Admin(String accountID, String accountUserName, String accountPWD) {
        super();
        this.accountID = accountID;
        this.accountUserName = accountUserName;
        this.accountPWD = accountPWD;
    }

    public String getAccountID() {
        return accountID;
    }
    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }
    public String getAccountUserName() {
        return accountUserName;
    }
    public void setAccountUserName(String accountUserName) {
        this.accountUserName = accountUserName;
    }
    public String getAccountPWD() {
        return accountPWD;
    }
    public void setAccountPWD(String accountPWD) {
        this.accountPWD = accountPWD;
    }

    @Override
    public String toString() {
        return "The Admin ID = " + accountID + ", Username = " + accountUserName + ", Password = " + accountPWD + "]";
    }

}