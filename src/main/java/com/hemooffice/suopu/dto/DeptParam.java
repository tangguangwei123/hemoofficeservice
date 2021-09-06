package com.hemooffice.suopu.dto;

import java.util.Arrays;

public class DeptParam extends Dept{

    private Integer[] deptUserKeys;

    public void setDeptUserKeys(Integer[] deptUserKeys) {
        this.deptUserKeys = deptUserKeys;
    }

    public Integer[] getDeptUserKeys() {
        return deptUserKeys;
    }

    @Override
    public String toString() {
        return  super.toString() + "DeptParam{" +
                "deptUserKeys=" + Arrays.toString(deptUserKeys) +
                '}';
    }
}
