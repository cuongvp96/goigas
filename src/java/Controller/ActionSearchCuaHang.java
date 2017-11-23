/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.CuaHang;
import Model.User;
import static com.opensymphony.xwork2.Action.LOGIN;
import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.sql.SQLException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author vancu
 */
public class ActionSearchCuaHang extends ActionSupport implements SessionAware {

    private DataUtil dataUtil;
    private User user;
    private SessionMap<String, Object> sessionMap;
    ArrayList<CuaHang> listCuaHang;
    String textSearch;
    ArrayList<String> listSearch;
    String categorySearchChoise;

    private HttpServletRequest request;
    private HttpSession session;

    public ActionSearchCuaHang() {
        dataUtil = new DataUtil();
        listSearch = new ArrayList<>();
        listSearch.add("Theo địa điểm");
        listSearch.add("Theo tên cửa hàng");

        listCuaHang = new ArrayList<>();
        request = (HttpServletRequest) ActionContext.getContext()
                .get(ServletActionContext.HTTP_REQUEST);
        session = ServletActionContext.getRequest().getSession(true);
        try {
            dataUtil.getConnection();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ActionUser.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    public String search() {
        String getUser_login = (String) session.getAttribute("user_login");
        String getUser_role = (String) session.getAttribute("user_role");
           int getUser_id = (int) session.getAttribute("user_id");
        int slt = -1;
        if (categorySearchChoise != null) {
            slt = listSearch.indexOf(categorySearchChoise);
        }

        if (getTextSearch() == null) {
            listCuaHang = null;
            addActionMessage("Không có cửa hàng nào được tìm thấy!");

        } else if (getTextSearch().trim().equals("")) {
            listCuaHang = null;
            addActionMessage("Vui lòng nhập gì đó để tìm kiếm!");
        } else {
            try {
                ArrayList<CuaHang> arrayList = null;
                 if (getUser_role.equals("admin")) {
                      arrayList = dataUtil.getAllCH();
                 }
                 else  arrayList = dataUtil.getListCHbyId(getUser_id+"");
                int count = arrayList.size();
                if (slt == 0) {
                    if (count > 0) {
                        for (int i = 0; i < count; i++) {
                            CuaHang e = arrayList.get(i);
                            if (removeAccent(e.getDiadiem().toLowerCase()).contains(removeAccent(getTextSearch().trim().toLowerCase()))) {
                                listCuaHang.add(e);
                            }
                        }
                    }
                }
                if (slt == 1) {
                    for (int i = 0; i < count; i++) {
                        CuaHang e = arrayList.get(i);
                       if (removeAccent(e.getTencuahang().toLowerCase()).contains(removeAccent(getTextSearch().trim().toLowerCase()))) {
                            listCuaHang.add(e);
                        }
                    }
                }
                if (slt == -1) {
                    listCuaHang = null;
                    addActionMessage("Vui lòng nhập gì đó để tìm kiếm!");
                }
                if (listCuaHang.size() == 0) {
                    listCuaHang = null;
                    addActionMessage("Không có cửa hàng nào được tìm thấy với từ khóa \""
                            + getTextSearch() + "\" !");
                }
            } catch (SQLException ex) {
                Logger.getLogger(ActionSearchCuaHang.class.getName()).log(Level.SEVERE, null, ex);
                return ERROR;
            }
        }
        if (listCuaHang != null) {
            addActionMessage("Tìm thấy " + listCuaHang.size() + " kết quả cho từ khóa \""
                    + getTextSearch() + "\" !");
        }
        return SUCCESS;
    }

    public static String removeAccent(String s) {

        String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(temp).replaceAll("");
    }

    @Override
    public void setSession(Map<String, Object> map) {
        sessionMap = (SessionMap<String, Object>) map;
    }

    public String getCategorySearchChoise() {
        return categorySearchChoise;
    }

    public void setCategorySearchChoise(String categorySearchChoise) {
        this.categorySearchChoise = categorySearchChoise;
    }

    public ArrayList<CuaHang> getListCuaHang() {
        return listCuaHang;
    }

    public ArrayList<String> getListSearch() {
        return listSearch;
    }

    public void setListSearch(ArrayList<String> listSearch) {
        this.listSearch = listSearch;
    }

    public void setListCuaHang(ArrayList<CuaHang> listCuaHang) {
        this.listCuaHang = listCuaHang;
    }

    public SessionMap<String, Object> getSessionMap() {
        return sessionMap;
    }

    public void setSessionMap(SessionMap<String, Object> sessionMap) {
        this.sessionMap = sessionMap;
    }

    public String getTextSearch() {
        return textSearch;
    }

    public void setTextSearch(String textSearch) {
        this.textSearch = textSearch;
    }
}
