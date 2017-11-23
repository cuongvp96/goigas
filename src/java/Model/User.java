/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author vancu
 */
public class User implements Serializable {

    private int user_id;
    private String user_name;
    private String std;
    private String address;
    private String password;
    private String fbid;
    private String googleid;
    private String fullname;
    private String role;

    public User() {
    }

    public User(String user_name, String password) {
        this.user_name = user_name;
        this.password = password;
    }



    public User(int user_id, String user_name, String std, String address, String password, String fbid, String googleid, String fullname, String role) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.std = std;
        this.address = address;
        this.password = password;
        this.fbid = fbid;
        this.googleid = googleid;
        this.fullname = fullname;
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getStd() {
        return std;
    }

    public void setStd(String std) {
        this.std = std;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFbid() {
        return fbid;
    }

    public void setFbid(String fbid) {
        this.fbid = fbid;
    }

    public String getGoogleid() {
        return googleid;
    }

    public void setGoogleid(String googleid) {
        this.googleid = googleid;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + this.user_id;
        hash = 41 * hash + Objects.hashCode(this.user_name);
        hash = 41 * hash + Objects.hashCode(this.fbid);
        hash = 41 * hash + Objects.hashCode(this.googleid);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (this.user_id != other.user_id) {
            return false;
        }
        if (!Objects.equals(this.user_name, other.user_name)) {
            return false;
        }
        if (!Objects.equals(this.fbid, other.fbid)) {
            return false;
        }
        if (!Objects.equals(this.googleid, other.googleid)) {
            return false;
        }
        return true;
    }

}
