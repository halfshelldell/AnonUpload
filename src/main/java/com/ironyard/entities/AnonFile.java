package com.ironyard.entities;

import javax.persistence.*;

/**
 * Created by illladell on 6/27/16.
 */

@Entity
@Table (name = "files")
public class AnonFile {

    @Id
    @GeneratedValue
    int id;

    @Column(nullable = false)
    String originalFileName;

    @Column(nullable = false)
    String realFileName;

    @Column(nullable = false)
    boolean isPermanent;

    @Column(nullable = false)
    String comment;

    @Column(nullable = false)
    String password;

    public AnonFile() {
    }

    public AnonFile(String originalFileName, String realFileName, boolean isPermanent, String comment, String password) {
        this.originalFileName = originalFileName;
        this.realFileName = realFileName;
        this.isPermanent = isPermanent;
        this.comment = comment;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOriginalFileName() {
        return originalFileName;
    }

    public void setOriginalFileName(String originalFileName) {
        this.originalFileName = originalFileName;
    }

    public String getRealFileName() {
        return realFileName;
    }

    public void setRealFileName(String realFileName) {
        this.realFileName = realFileName;
    }

    public boolean isPermanent() {
        return isPermanent;
    }

    public void setPermanent(boolean permanent) {
        isPermanent = permanent;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
