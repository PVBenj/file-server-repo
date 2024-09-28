package Model;

/**
 *
 * @author benjamin
 */
public class UserFile {
    
    private UserModel user;
    private FileModel file;

    // Constructor
    public UserFile(UserModel user, FileModel file) {
        this.user = user;
        this.file = file;
    }

    // Getters and Setters
    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public FileModel getFile() {
        return file;
    }

    public void setFile(FileModel file) {
        this.file = file;
    }
}

