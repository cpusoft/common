package tk.cpusoft.common.util.test;

import java.util.Date;

public class Student {
    private String name;
    private String superName;
    private String id;
    private Date date;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public String getSuperName() {
        return superName;
    }
    public void setSuperName(String superName) {
        this.superName = superName;
    }
    
}
