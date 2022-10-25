package com.example.fishing.models;
import java.util.ArrayList;
import java.util.List;

public class FZ44 {
    // FZ44Controller fz44 = new FZ44Controller();
    public String tenderid; // +
    public String tendertype; // +
    public String clientname; // +
    public String article; // +
    public ArrayList<DocUrl> docUrl; // +
    public String maxPrice; // +
    public String inn; //+-
    public String kpp; //+-
    public String amount; // +
    public String publishDate;//+-
    public String url; // +
    public Boolean active; // +-

    //добавить в парсер
    public String ikz; //+-
    public String phone;
    public String email;
    public String person_name;
    public String person_first_name;

    public FZ44(String tenderid, String tendertype, String clientname, String article, String maxPrice, ArrayList<DocUrl> docUrl, Boolean active) {
        this.tenderid = tenderid;
        this.tendertype = tendertype;
        this.clientname = clientname;
        this.article = article;
        this.maxPrice = maxPrice;
        this.docUrl = docUrl;
    }

    public FZ44() {

    }

    public String getPerson_first_name() {
        return person_first_name;
    }

    public void setPerson_first_name(String person_first_name) {
        this.person_first_name = person_first_name;
    }

    public Boolean getActive() {
        return active;
    }

    public boolean setActive(Boolean active) {
        this.active = active;
        return false;
    }

    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }

    public String getTenderid() {
        return this.tenderid;
    }

    public String getTendertype() {
        return this.tendertype;
    }

    public String getClientname() {
        return this.clientname;
    }

    public String getArticle() {
        return this.article;
    }

    public List<DocUrl> getDocUrl() {
        return this.docUrl;
    }

    public String getMaxPrice() {
        return this.maxPrice;
    }

    public String getInn() {
        return this.inn;
    }

    public String getKpp() {
        return this.kpp;
    }

    public String getAmount() {
        return this.amount;
    }

    public String getPublishDate() {
        return this.publishDate;
    }

    public String getIkz() {
        return this.ikz;
    }

    public String getPhone() {
        return this.phone;
    }

    public String getEmail() {
        return this.email;
    }


    public String getPerson_name() {
        return this.person_name;
    }

    public void setTenderid(String num) {
        this.tenderid = num;
    }

    public void setTendertype(String tendertype) {
        this.tendertype = tendertype;
    }

    public void setClientname(String clientname) {
        this.clientname = clientname;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public void setDocUrl(ArrayList<DocUrl> docUrl) {
        this.docUrl = docUrl;
    }

    public void setMaxPrice(String maxPrice) {
        this.maxPrice = maxPrice;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public void setKpp(String kpp) {
        this.kpp = kpp;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public void setIkz(String ikz) {
        this.ikz = ikz;
    }


    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPerson_name(String person_name) {
        this.person_name = person_name;
    }

    @Override
    public String toString() {
        return "FZ44{" +
                "tenderid='" + tenderid + '\'' +
                ", tendertype='" + tendertype + '\'' +
                ", clientname='" + clientname + '\'' +
                ", article='" + article + '\'' +
                ", docUrl=" + docUrl +
                ", maxPrice='" + maxPrice + '\'' +
                ", inn='" + inn + '\'' +
                ", kpp='" + kpp + '\'' +
                ", amount='" + amount + '\'' +
                ", publishDate='" + publishDate + '\'' +
                ", url='" + url + '\'' +
                ", active=" + active +
                ", ikz='" + ikz + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", person_name='" + person_name + '\'' +
                ", person_first_name='" + person_first_name + '\'' +
                '}';
    }
}