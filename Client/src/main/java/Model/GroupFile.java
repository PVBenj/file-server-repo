package Model;

/**
 *
 * @author benjamin
 */
public class GroupFile {
    
    private GroupModel group;
    private FileModel file;

    // Constructor
    public GroupFile(GroupModel group, FileModel file) {
        this.group = group;
        this.file = file;
    }

    // Getters and Setters
    public GroupModel getGroup() {
        return group;
    }

    public void setGroup(GroupModel group) {
        this.group = group;
    }

    public FileModel getFile() {
        return file;
    }

    public void setFile(FileModel file) {
        this.file = file;
    }
}

