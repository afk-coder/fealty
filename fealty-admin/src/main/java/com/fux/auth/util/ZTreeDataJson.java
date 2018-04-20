package com.fux.auth.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fuxiaoj on 2018/04/11 09:52
 */
public class ZTreeDataJson {
    private Long id;
    private Long pId;
    private String name;
    private boolean open;
    private String url;
    private String code;
    private String remark;
    private Long roleId;
    private boolean checked;
    private int weight;
    private List<ZTreeDataJson> children = new ArrayList<ZTreeDataJson>();

    public ZTreeDataJson() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getpId() {
        return pId;
    }

    public void setpId(Long pId) {
        this.pId = pId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public List<ZTreeDataJson> getChildren() {
        return children;
    }

    public void setChildren(List<ZTreeDataJson> children) {
        this.children = children;
    }
}
