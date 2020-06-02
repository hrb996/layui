package com.hrb.util;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 对应之前的navs2.json
 */
@Data
@NoArgsConstructor

public class TreeNode {
    private  Object status;
    private Integer id;
    @JsonProperty("parentId")//用于返回给前端数据时名字叫这个
    private Integer pid;
    private String title;
    private String checkArr="0";//0表示不选中，1表示选中


    public TreeNode(Object status){
        this.status=status;
    }

    /**
     * dTree复选树的构造器
     * @param id
     * @param pid
     * @param title
     * @param checkArr
     */
    public TreeNode(Integer id, Integer pid, String title, String checkArr) {
        super();
        this.id = id;
        this.pid = pid;
        this.title = title;
        this.checkArr = checkArr;
    }
}

