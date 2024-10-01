package Model;


public class ActivityLogger {
    
    private String userName;
    private String affectedUserId;
    private String details;
    private String dateAndTime;

    public ActivityLogger(String userName, String affectedUserId, String details, String dateAndTime) {
        this.userName = userName;
        this.details = details;
        this.dateAndTime = dateAndTime;
        this.affectedUserId = affectedUserId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getDateAndTime() {
        return dateAndTime;
    }
    
}
