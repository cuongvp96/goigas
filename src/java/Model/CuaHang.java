/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;

/**
 *
 * @author vancu
 */
public class CuaHang implements Serializable{
    private int idcuahang;
    private String tencuahang;
    private String loaigas;
    private float giagas;
    private String sdt;
     private String chucuahang;
    private String diadiem;
    private String latlng;
    private String linkimg;
    private int user_id;
    private float votescore;
    private int votenumber;
    private int commentnumber;
  private String user_name;

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }
    public CuaHang() {
    }

    public CuaHang(int idcuahang, String tencuahang, String loaigas, float giagas, String sdt, String chucuahang, String diadiem, String latlng, String linkimg, int user_id, float votescore, int votenumber, int commentnumber, String user_name) {
        this.idcuahang = idcuahang;
        this.tencuahang = tencuahang;
        this.loaigas = loaigas;
        this.giagas = giagas;
        this.sdt = sdt;
        this.chucuahang = chucuahang;
        this.diadiem = diadiem;
        this.latlng = latlng;
        this.linkimg = linkimg;
        this.user_id = user_id;
        this.votescore = votescore;
        this.votenumber = votenumber;
        this.commentnumber = commentnumber;
        this.user_name = user_name;
    }

    public CuaHang(int idcuahang, String tencuahang, String loaigas, float giagas, String sdt, String chucuahang, String diadiem, String latlng, String linkimg, int user_id, float votescore, int votenumber, int commentnumber) {
        this.idcuahang = idcuahang;
        this.tencuahang = tencuahang;
        this.loaigas = loaigas;
        this.giagas = giagas;
        this.sdt = sdt;
        this.chucuahang = chucuahang;
        this.diadiem = diadiem;
        this.latlng = latlng;
        this.linkimg = linkimg;
        this.user_id = user_id;
        this.votescore = votescore;
        this.votenumber = votenumber;
        this.commentnumber = commentnumber;
    }

    public CuaHang(int idcuahang, String tencuahang, String loaigas, float giagas, String sdt) {
        this.idcuahang = idcuahang;
        this.tencuahang = tencuahang;
        this.loaigas = loaigas;
        this.giagas = giagas;
        this.sdt = sdt;
    }

    public CuaHang(String tencuahang, String loaigas, float giagas, String sdt, String chucuahang, String diadiem, String latlng, String linkimg, int user_id) {
        this.tencuahang = tencuahang;
        this.loaigas = loaigas;
        this.giagas = giagas;
        this.sdt = sdt;
        this.chucuahang = chucuahang;
        this.diadiem = diadiem;
        this.latlng = latlng;
        this.linkimg = linkimg;
        this.user_id = user_id;
    }

    public String getChucuahang() {
        return chucuahang;
    }

    public void setChucuahang(String chucuahang) {
        this.chucuahang = chucuahang;
    }

    

    public int getIdcuahang() {
        return idcuahang;
    }

    public void setIdcuahang(int idcuahang) {
        this.idcuahang = idcuahang;
    }

    public String getTencuahang() {
        return tencuahang;
    }

    public void setTencuahang(String tencuahang) {
        this.tencuahang = tencuahang;
    }

    public String getLoaigas() {
        return loaigas;
    }

    public void setLoaigas(String loaigas) {
        this.loaigas = loaigas;
    }

    public float getGiagas() {
        return giagas;
    }

    public void setGiagas(float giagas) {
        this.giagas = giagas;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getDiadiem() {
        return diadiem;
    }

    public void setDiadiem(String diadiem) {
        this.diadiem = diadiem;
    }

    public String getLatlng() {
        return latlng;
    }

    public void setLatlng(String latlng) {
        this.latlng = latlng;
    }

    public String getLinkimg() {
        return linkimg;
    }

    public void setLinkimg(String linkimg) {
        this.linkimg = linkimg;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public float getVotescore() {
        return votescore;
    }

    public void setVotescore(float votescore) {
        this.votescore = votescore;
    }

    public int getVotenumber() {
        return votenumber;
    }

    public void setVotenumber(int votenumber) {
        this.votenumber = votenumber;
    }

    public int getCommentnumber() {
        return commentnumber;
    }

    public void setCommentnumber(int commentnumber) {
        this.commentnumber = commentnumber;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + this.idcuahang;
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
        final CuaHang other = (CuaHang) obj;
        if (this.idcuahang != other.idcuahang) {
            return false;
        }
        return true;
    }
    
    
}
