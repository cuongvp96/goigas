/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author vancu
 */
public class UserInfo {

    private String id;
    private String name;
    private String email;
    private String link_fb;

    public UserInfo() {
    }

    public UserInfo(String id, String name, String email, String link_fb) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.link_fb = link_fb;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLink_fb() {
        return link_fb;
    }

    public void setLink_fb(String link_fb) {
        this.link_fb = link_fb;
    }

}
