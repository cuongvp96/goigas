/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.CuaHang;
import Model.User;
import static com.opensymphony.xwork2.Action.LOGIN;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author vancu
 */
public class ActionUser extends ActionSupport implements SessionAware {

    private static final long serialVersionUID = 1L;
    private DataUtil dataUtil;
    private User user;
    private SessionMap<String, Object> sessionMap;
    ArrayList<CuaHang> listCuaHang;
    String googleid, fbid;
    private HttpServletRequest request;
    private HttpSession session;
    String begin="0",numend="5";
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

    public ActionUser() {
        dataUtil = new DataUtil();
        listCuaHang = new ArrayList<>();
        request = (HttpServletRequest) ActionContext.getContext()
                .get(ServletActionContext.HTTP_REQUEST);
        session = ServletActionContext.getRequest().getSession(true);
        try {
            dataUtil.getConnection();
            User u = null;
            if (user != null) {
                u = dataUtil.checkLogin(user);
                if (u != null) {
                    if (u.getRole().equals("admin")) {
                        session.setAttribute("numberItem", dataUtil.getCountCH());
                    } else {
                        session.setAttribute("numberItem", dataUtil.getCountCHById(u.getUser_id() + ""));
                    }
                }
            } else {
                String getUser_login = (String) session.getAttribute("user_login");
                String getUser_role = (String) session.getAttribute("user_role");
                if (getUser_login != null && getUser_login != "") {
                    int getUser_id = (int) session.getAttribute("user_id");
                    if (getUser_role.equals("admin")) {
                        session.setAttribute("numberItem", dataUtil.getCountCH());
                    } else {
                        session.setAttribute("numberItem", dataUtil.getCountCHById(getUser_id + ""));
                    }
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ActionUser.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    public String login() {
        return LOGIN;
    }

    public String signOut() {
        sessionMap.remove("google_id");
        sessionMap.remove("fb_id");
        sessionMap.remove("user_login");
        sessionMap.remove("user_fullname");
        sessionMap.remove("user_id");
        sessionMap.remove("user_role");
        sessionMap.invalidate();
        
        return LOGIN;
    }

    public String checkLogin() {
        User u = null;
        try {
            if (user != null) {
                u = dataUtil.checkLogin(user);
                if (u != null) {
                    if (u.getRole().equals("admin")) {
                        listCuaHang = dataUtil.getAllCHBeginEnd(begin, numend);
                    } else {
                        listCuaHang = dataUtil.getListCHbyIdBeginEnd(begin,numend,u.getUser_id() + "");
                    }
                    sessionMap.put("user_login", u.getUser_name());
                    sessionMap.put("user_fullname", u.getFullname());
                    sessionMap.put("user_id", u.getUser_id());
                    sessionMap.put("user_role", u.getRole());
                    return SUCCESS;
                }
                addActionError("Loi dang nhap,sai username hoặc password !");
                return LOGIN;
            } else {
                String getUser_login = (String) session.getAttribute("user_login");
                String getUser_role = (String) session.getAttribute("user_role");
                if (getUser_login != null && getUser_login != "") {
                    int getUser_id = (int) session.getAttribute("user_id");
                    if (getUser_role.equals("admin")) {
                         listCuaHang = dataUtil.getAllCHBeginEnd(begin, numend);
                    } else {
                        listCuaHang = dataUtil.getListCHbyIdBeginEnd(begin,numend,getUser_id + "");
                    }
                    dataUtil.closeConnection();
                    return SUCCESS;
                } else {
                    return LOGIN;
                }
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ActionUser.class.getName()).log(Level.SEVERE, null, ex);
            return LOGIN;
        }
    }

    public String checkLoginGG() {
        User u = null;
        try {
            u = dataUtil.checkLoginGG(getGoogleid());
            if (u != null) {
                if (u.getRole().equals("admin")) {
                    listCuaHang = dataUtil.getAllCHBeginEnd(begin, numend);
                    } else {
                        listCuaHang = dataUtil.getListCHbyIdBeginEnd(begin,numend,u.getUser_id() + "");
                    }
                sessionMap.put("google_id", getGoogleid());
                sessionMap.put("user_login", u.getUser_name());
                sessionMap.put("user_fullname", u.getFullname());
                sessionMap.put("user_id", u.getUser_id());
                sessionMap.put("user_role", u.getRole());
                dataUtil.closeConnection();
                return SUCCESS;
            }
            request.setAttribute("fail_google", "1");
            addActionError("Tài khoản không tồn tại, vui lòng mở app và đăng ký !");
            return LOGIN;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ActionUser.class.getName()).log(Level.SEVERE, null, ex);
            return LOGIN;
        }
    }

    public String checkLoginFB() {
        User u = null;
        try {
            u = dataUtil.checkLoginFB(getFbid());
            if (u != null) {
                if (u.getRole().equals("admin")) {
                     listCuaHang = dataUtil.getAllCHBeginEnd(begin, numend);
                    } else {
                        listCuaHang = dataUtil.getListCHbyIdBeginEnd(begin,numend,u.getUser_id() + "");
                    }
                sessionMap.put("fb_id", getGoogleid());
                sessionMap.put("user_login", u.getUser_name());
                sessionMap.put("user_fullname", u.getFullname());
                sessionMap.put("user_id", u.getUser_id());
                sessionMap.put("user_role", u.getRole());
                dataUtil.closeConnection();
                return SUCCESS;
            }
            request.setAttribute("fail_fb", "1");
            addActionError("Tài khoản không tồn tại, vui lòng mở app và đăng ký !");
            return LOGIN;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ActionUser.class.getName()).log(Level.SEVERE, null, ex);
            return LOGIN;
        }
    }

    public String home() {
        User u = null;
        try {
            if (user != null) {
                u = dataUtil.checkLogin(user);
                if (u != null) {
                    if (u.getRole().equals("admin")) {
                         listCuaHang = dataUtil.getAllCHBeginEnd(begin, numend);
                    } else {
                        listCuaHang = dataUtil.getListCHbyIdBeginEnd(begin,numend,u.getUser_id() + "");
                    }
                    sessionMap.put("user_login", u.getUser_name());
                    sessionMap.put("user_fullname", u.getFullname());
                    sessionMap.put("user_id", u.getUser_id());
                    sessionMap.put("user_role", u.getRole());
                    dataUtil.closeConnection();
                    return SUCCESS;
                }
                addActionError("Loi dang nhap,sai uername hoặc password !");
                return LOGIN;
            } else {
                String getUser_login = (String) session.getAttribute("user_login");
                String getUser_role = (String) session.getAttribute("user_role");
                if (getUser_login != null && getUser_login != "") {
                    int getUser_id = (int) session.getAttribute("user_id");
                    if (getUser_role.equals("admin")) {
                         listCuaHang = dataUtil.getAllCHBeginEnd(begin, numend);
                    } else {
                        listCuaHang = dataUtil.getListCHbyIdBeginEnd(begin,numend,getUser_id + "");
                    }
                    dataUtil.closeConnection();
                    return SUCCESS;
                } else {
                    return LOGIN;
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ActionUser.class.getName()).log(Level.SEVERE, null, ex);
            return LOGIN;
        }
    }

    public void userNotNull(User u) {

    }

    @Override
    public void setSession(Map<String, Object> map) {
        sessionMap = (SessionMap<String, Object>) map;
    }

    public DataUtil getDataUtil() {
        return dataUtil;
    }

    public void setDataUtil(DataUtil dataUtil) {
        this.dataUtil = dataUtil;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ArrayList<CuaHang> getListCuaHang() {
        return listCuaHang;
    }

    public void setListCuaHang(ArrayList<CuaHang> listCuaHang) {
        this.listCuaHang = listCuaHang;
    }

}
