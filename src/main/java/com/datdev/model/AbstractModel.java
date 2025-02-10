package com.datdev.model;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractModel<T> {
    private long id;
    private Timestamp createDate;
    private Timestamp modifireDate;
    private String createBy;
    private String modifireBy;
    private long[] ids;
    private Integer page;
    private Integer maxPageItem;
    private Integer totalPage;
    private Integer totalItem;
    private String sortName;
    private String sortBy;


    private List<T> listResult = new ArrayList<T>();

    public AbstractModel(long id, Timestamp createDate, Timestamp modifireDate, String createBy, String modifireBy) {
        super();
        this.id = id;
        this.createDate = createDate;
        this.modifireDate = modifireDate;
        this.createBy = createBy;
        this.modifireBy = modifireBy;
    }

    public AbstractModel() {
        super();
        // TODO Auto-generated constructor stub
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public Timestamp getModifireDate() {
        return modifireDate;
    }

    public void setModifireDate(Timestamp modifireDate) {
        this.modifireDate = modifireDate;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getModifireBy() {
        return modifireBy;
    }

    public void setModifireBy(String modifireBy) {
        this.modifireBy = modifireBy;
    }

    public long[] getIds() {
        return ids;
    }

    public void setIds(long[] ids) {
        this.ids = ids;
    }

    public List<T> getListResult() {
        return listResult;
    }

    public void setListResult(List<T> listResult) {
        this.listResult = listResult;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getMaxPageItem() {
        return maxPageItem;
    }

    public void setMaxPageItem(Integer maxPageItem) {
        this.maxPageItem = maxPageItem;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Integer getTotalItem() {
        return totalItem;
    }

    public void setTotalItem(Integer totalItem) {
        this.totalItem = totalItem;
    }

    public String getSortName() {
        return sortName;
    }

    public void setSortName(String sortName) {
        this.sortName = sortName;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }
}
