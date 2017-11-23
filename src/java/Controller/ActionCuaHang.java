/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.CuaHang;
import Model.FileUploadModel;
import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.LOGIN;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.ValidationAware;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.SessionMap;

/**
 *
 * @author vancu
 */
public class ActionCuaHang extends ActionSupport implements ModelDriven<FileUploadModel>, ValidationAware {

    private SessionMap<String, Object> sessionMap;
    private static final long serialVersionUID = 1L;
    ArrayList<CuaHang> listCuaHang;
    DataUtil dataUtil;
    CuaHang ch, chEdit;
    FileUploadModel model = new FileUploadModel();
    private File photo; // Url file được chọn
    private String photoFileName; // Tên file vừa chọn
    private String photoContentType; // Loại file vừa chọn 
    private static final String UPLOAD_DIRECTORY = "img";
    private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3;  // 3MB
    private static final int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
    private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50; // 50MB
    private HttpServletRequest request;
    private HttpSession session;

    public ActionCuaHang() {
        dataUtil = new DataUtil();
        listCuaHang = new ArrayList<>();
        request = (HttpServletRequest) ActionContext.getContext()
                .get(ServletActionContext.HTTP_REQUEST);
        session = ServletActionContext.getRequest().getSession(true);
        try {
            dataUtil.getConnection();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ActionCuaHang.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String delCH() {
        int k = 0;
        String getUser_login = (String) session.getAttribute("user_login");
        String getUser_role = (String) session.getAttribute("user_role");
        int getUser_id = (int) session.getAttribute("user_id");
        try {
            String chid = request.getParameter("chid");
            CuaHang c = dataUtil.getCHById(chid);
            if (c != null) {
                String targetPath = "img/"; // Url đích đến
                //targetPath = "D:\\BTL\\dichvu\\Project_web\\goigas\\web\\";
                String applicationPath = request.getServletContext().getRealPath("");
                targetPath = applicationPath + File.separator + "";
                File a = new File(targetPath, c.getLinkimg());
                if (a != null) {
                    a.delete();
                }
                k = dataUtil.delCuaHang(chid);
            } else {
                addActionError("Cửa hàng không tồn tại!");
            }
            if (k == 1) {
                if (getUser_login != null && getUser_login != "") {
                    if (getUser_role.equals("admin")) {
                        listCuaHang = dataUtil.getAllCH();
                    } else {
                        listCuaHang = dataUtil.getListCHbyId(getUser_id + "");
                    }
                    addActionMessage("Xoa thanh cong!");
                    dataUtil.closeConnection();
                    return SUCCESS;
                } else {
                    return LOGIN;
                }
            } else {
                addActionError("Cửa hàng không tồn tại!");
                return ERROR;
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ActionUser.class.getName()).log(Level.SEVERE, null, ex);
            addActionError("Có lỗi xảy ra, vui lòng thử lại sau!");
            return ERROR;
        }
    }

    public String addCuaHang() {
        String getUser_login = (String) session.getAttribute("user_login");
        if (getUser_login != null && getUser_login != "") {
            return SUCCESS;
        } else {
            return LOGIN;
        }
    }

    public String editCuaHang() {
        String getUser_login = (String) session.getAttribute("user_login");
        try {
            if (getUser_login != null && getUser_login != "") {
                String chid = (String) request.getParameter("chid");
                chEdit = dataUtil.getCHById(chid);
                dataUtil.closeConnection();
                return SUCCESS;
            } else {
                return LOGIN;
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ActionUser.class.getName()).log(Level.SEVERE, null, ex);
            return LOGIN;
        }
    }

    public String home() {
        String getUser_login = (String) session.getAttribute("user_login");
        String getUser_role = (String) session.getAttribute("user_role");
        int getUser_id = (int) session.getAttribute("user_id");
        try {
            if (getUser_login != null && getUser_login != "") {
                if (getUser_role.equals("admin")) {
                    listCuaHang = dataUtil.getAllCH();
                } else {
                    listCuaHang = dataUtil.getListCHbyId(getUser_id + "");
                }
                dataUtil.closeConnection();
                return SUCCESS;
            } else {
                return LOGIN;
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ActionUser.class.getName()).log(Level.SEVERE, null, ex);
            return LOGIN;
        }
    }

    public String checkAddCuaHang() {
        String getUser_login = (String) session.getAttribute("user_login");
        CuaHang ch_new = null;
        if(ch!=null){
        try {
            ch_new = dataUtil.getCHByName(ch.getTencuahang());
            if (ch_new != null) {
                addActionError("Cửa hàng đã tồn tại!");
                return ERROR;
            }
            if (getUser_login != null && getUser_login != "") {
                int getUser_id = (int) session.getAttribute("user_id");
                String text_check = upload2();
                String getUser_role = (String) session.getAttribute("user_role");
                if (text_check == SUCCESS) {
                    ch.setUser_id(getUser_id);
                    int k = 0;
                    k = dataUtil.addCuaHang(ch);
                    if (k == 1) {
                        if (getUser_role.equals("admin")) {
                            listCuaHang = dataUtil.getAllCH();
                        } else {
                            listCuaHang = dataUtil.getListCHbyId(getUser_id + "");
                        }
                        dataUtil.closeConnection();
                        return SUCCESS;
                    } else {
                        addActionError("Có lỗi khi thêm cửa hàng, vui lòng thử lại!");
                        return INPUT;
                    }
                } else if (text_check == INPUT) {
                    return INPUT;
                } else {
                    return ERROR;
                }
            } else {
                return LOGIN;
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ActionUser.class.getName()).log(Level.SEVERE, null, ex);
            addActionError("Có lỗi khi thêm cửa hàng, vui lòng thử lại!");
            return INPUT;
        }
        }else{
             return INPUT;
        }
    }

    public String checkEditCuaHang() {
        String getUser_login = (String) session.getAttribute("user_login");
        try {
            if (getUser_login != null && getUser_login != "") {
                int getUser_id = (int) session.getAttribute("user_id");
                String getUser_role = (String) session.getAttribute("user_role");
                chEdit.setUser_id(getUser_id);
                int k = 0;
                if (getUser_role.equals("admin")) {
                    k = dataUtil.editCuaHangAdmin(chEdit);
                } else {
                    k = dataUtil.editCuaHang(chEdit);
                }
                if (k == 1) {
                    if (getUser_role.equals("admin")) {
                        listCuaHang = dataUtil.getAllCH();
                    } else {
                        listCuaHang = dataUtil.getListCHbyId(getUser_id + "");
                    }
                    dataUtil.closeConnection();
                    return SUCCESS;
                } else {
                    addActionError("Có lỗi khi sửa cửa hàng, vui lòng thử lại!");
                    return INPUT;
                }
            } else {
                return LOGIN;
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ActionUser.class.getName()).log(Level.SEVERE, null, ex);
            addActionError("Có lỗi khi sửa cửa hàng, vui lòng thử lại!");
            return INPUT;
        }
    }

    public String paging() {
        String getUser_login = (String) session.getAttribute("user_login");
        try {
            if (getUser_login != null && getUser_login != "") {
                String begin = (String) request.getParameter("begin");
                String numend = (String) request.getParameter("numend");
                int getUser_id = (int) session.getAttribute("user_id");
                String getUser_role = (String) session.getAttribute("user_role");
                if (getUser_role.equals("admin")) {
                    listCuaHang = dataUtil.getAllCHBeginEnd(begin, numend);
                } else {
                    listCuaHang = dataUtil.getListCHbyIdBeginEnd(begin, numend, getUser_id+"");
                }
            } else {
                return LOGIN;
            }
            dataUtil.closeConnection();
             return SUCCESS;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ActionUser.class.getName()).log(Level.SEVERE, null, ex);
             return LOGIN;
        }
    }

    String upload2() {
        String targetPath = "img//"; // Url đích đến
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext()
                .get(ServletActionContext.HTTP_REQUEST);
        String applicationPath = request.getServletContext().getRealPath("");
        targetPath = applicationPath + File.separator + "img";
        DateFormat dateFormat = new SimpleDateFormat("ddMMHHmmss");
        Date date = new Date();

//           File fileToCreate = new File(targetPath, photoFileName);
//             photoFileName = dateFormat.format(date) + photoFileName;
//             File a=new File(targetPath);
//              File b=new File(targetPath);
//            a.renameTo(new File(photoFileName));
        if (photoFileName != null) {
            photoFileName = dateFormat.format(date) + photoFileName;
            File fileToCreate = new File(targetPath, photoFileName);

            try {
                FileUtils.copyFile(this.photo, fileToCreate);
                ch.setLinkimg("img/" + photoFileName);
                return SUCCESS;
            } catch (IOException e) {

                addActionError("Bạn phải chọn hình ảnh!");
                return INPUT;
            }
        } else {
            addActionError("Bạn phải chọn hình ảnh!");
            return INPUT;
        }

    }

    private String getFileName(Part part) {
        final String partHeader = part.getHeader("content-disposition");
        System.out.println("*****partHeader :" + partHeader);
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }

        return null;
    }

    public String uploadMulti() {
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext()
                .get(ServletActionContext.HTTP_REQUEST);

        String fileName = "";
        try {
            Part filePart = request.getPart("photo");
            fileName = (String) getFileName(filePart);
            String applicationPath = request.getServletContext().getRealPath("");
            String basePath = applicationPath + File.separator + UPLOAD_DIRECTORY + File.separator;
            InputStream inputStream = null;
            OutputStream outputStream = null;
            try {
                File outputFilePath = new File(basePath + fileName);
                inputStream = filePart.getInputStream();
                outputStream = new FileOutputStream(outputFilePath);
                int read = 0;
                final byte[] bytes = new byte[1024];
                while ((read = inputStream.read(bytes)) != -1) {
                    outputStream.write(bytes, 0, read);
                }
                ch.setLinkimg(applicationPath + "img/" + fileName);
            } catch (Exception e) {
                e.printStackTrace();
                fileName = "";
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            }

        } catch (Exception e) {
            addFieldError("photo", "Bạn phải chọn hình ảnh!");
            return INPUT;
        }
        return SUCCESS;
    }

    public ArrayList<CuaHang> getListCuaHang() {
        return listCuaHang;
    }

    public void setListCuaHang(ArrayList<CuaHang> listCuaHang) {
        this.listCuaHang = listCuaHang;
    }

    public DataUtil getDataUtil() {
        return dataUtil;
    }

    public void setDataUtil(DataUtil dataUtil) {
        this.dataUtil = dataUtil;
    }

    public CuaHang getCh() {
        return ch;
    }

    public void setCh(CuaHang ch) {
        this.ch = ch;
    }

    @Override
    public FileUploadModel getModel() {
        return model;
    }

    public void setModel(FileUploadModel model) {
        this.model = model;
    }

    public File getPhoto() {
        return photo;
    }

    public void setPhoto(File photo) {
        this.photo = photo;
    }

    public String getPhotoFileName() {
        return photoFileName;
    }

    public void setPhotoFileName(String photoFileName) {
        this.photoFileName = photoFileName;
    }

    public String getPhotoContentType() {
        return photoContentType;
    }

    public void setPhotoContentType(String photoContentType) {
        this.photoContentType = photoContentType;
    }

    @Override
    public void validate() {
        if (ch != null) {
            if (photo == null) {
                addFieldError("photo", "Bạn phải chọn hình ảnh!");
            }
            if (ch.getTencuahang() == null || ch.getTencuahang().trim().equals("")) {
//                addActionError("Bạn phải nhập tên cửa hàng!");
                addFieldError("ch.tencuahang", "Bạn phải nhập tên cửa hàng!");
            }
            if (ch.getLoaigas() == null || ch.getLoaigas().trim().equals("")) {
//                addActionError("Bạn phải nhập loại gas!");
                addFieldError("ch.loaigas", "Bạn phải nhập loại gas!");
            }
            if (ch.getGiagas() == 0 || !(ch.getGiagas() + "").matches("[-+]?[0-9]*\\.?[0-9]+") || ch.getGiagas() < 50000) {
//                addActionError("Bạn phải nhập giá gas!");
                addFieldError("ch.giagas", "Bạn phải nhập giá gas đúng!");
            }
            if (ch.getSdt() == null || !ch.getSdt().matches("[0-9]+") || ch.getSdt().trim().length() < 10
                    || ch.getSdt().trim().length() > 11) {
//                addActionError("Bạn phải nhập số điện thoại đúng!");
                addFieldError("ch.sdt", "Bạn phải nhập số điện thoại đúng!");
            }
            if (ch.getChucuahang() == null || ch.getChucuahang().trim().equals("")) {
//                addActionError("Bạn phải nhập tên chủ cửa hàng!");
                addFieldError("ch.chucuahang", "Bạn phải nhập tên chủ cửa hàng!");
            }
            if (ch.getDiadiem() == null || ch.getDiadiem().trim().equals("")) {
//                addActionError("Bạn phải nhập địa điểm!");
                addFieldError("ch.diadiem", "Bạn phải nhập địa điểm!");
            }
            if (ch.getLatlng() == null || ch.getLatlng().trim().equals("")) {
                addActionError("Bạn phải nhập vị trí!");
            }
        }
        if (chEdit != null) {
            if (chEdit.getTencuahang() == null || chEdit.getTencuahang().trim().equals("")) {
                addFieldError("chEdit.tencuahang", "Bạn phải nhập tên cửa hàng!");
            }
            if (chEdit.getLoaigas() == null || chEdit.getLoaigas().trim().equals("")) {
                addFieldError("chEdit.loaigas", "Bạn phải nhập loại gas!");
            }
            if (chEdit.getGiagas() == 0 || !(chEdit.getGiagas() + "").matches("[-+]?[0-9]*\\.?[0-9]+")) {
                addFieldError("chEdit.giagas", "Bạn phải nhập giá gas!");
            }
            if (chEdit.getSdt() == null || chEdit.getSdt().trim().equals("") || !chEdit.getSdt().matches("[0-9]+") || chEdit.getSdt().trim().length() < 10
                    || chEdit.getSdt().trim().length() > 11) {
                addFieldError("chEdit.sdt", "Bạn phải nhập số điện thoại đúng!");
            }
            if (chEdit.getChucuahang() == null || chEdit.getChucuahang().trim().equals("")) {
                addFieldError("chEdit.chucuahang", "Bạn phải nhập tên chủ cửa hàng!");
            }
        }

    }

    public CuaHang getChEdit() {
        return chEdit;
    }

    public void setChEdit(CuaHang chEdit) {
        this.chEdit = chEdit;
    }

}
