package com.funtoys.service.domain.generation;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.funtoys.service.annotation.Domain;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.ToString;

@Domain
@Data
public class AccountInfo implements Serializable {

    private static final long serialVersionUID = 3675521780527550752L;

    /**
     * 主键
     */
    private Integer id;

    /**
     * 创建时间
     */
    private Date gmtCreated;

    /**
     * 修改时间
     */
    private Date gmtModified;

    /**
     * 创建人
     */
    private String createdBy;

    /**
     * 修改人
     */
    private String modifiedBy;

    /**
     * 是否删除(0:存在;1:删除)
     */
    private Integer isDel;

    /**
     * 账号
     */
    private String account;

    /**
     * 密码
     */
    private String password;

    /**
     * 账户类型(1:用户;2:商家)
     */
    private Integer accountType;

    /**
     * 随机码(和密码一起使用)
     */
    private String randomCode;

    /**
     * id属性对应的表达式属性，
     * 目前用来支持update set 字段=表达式(如:'字段+1')这种场景
     **/
    @JsonIgnore
    private String idExpression;

    /**
     * gmtCreated属性对应的表达式属性，
     * 目前用来支持update set 字段=表达式(如:'字段+1')这种场景
     **/
    @JsonIgnore
    private String gmtCreatedExpression;

    /**
     * gmtModified属性对应的表达式属性，
     * 目前用来支持update set 字段=表达式(如:'字段+1')这种场景
     **/
    @JsonIgnore
    private String gmtModifiedExpression;

    /**
     * createdBy属性对应的表达式属性，
     * 目前用来支持update set 字段=表达式(如:'字段+1')这种场景
     **/
    @JsonIgnore
    private String createdByExpression;

    /**
     * modifiedBy属性对应的表达式属性，
     * 目前用来支持update set 字段=表达式(如:'字段+1')这种场景
     **/
    @JsonIgnore
    private String modifiedByExpression;

    /**
     * isDel属性对应的表达式属性，
     * 目前用来支持update set 字段=表达式(如:'字段+1')这种场景
     **/
    @JsonIgnore
    private String isDelExpression;

    /**
     * account属性对应的表达式属性，
     * 目前用来支持update set 字段=表达式(如:'字段+1')这种场景
     **/
    @JsonIgnore
    private String accountExpression;

    /**
     * password属性对应的表达式属性，
     * 目前用来支持update set 字段=表达式(如:'字段+1')这种场景
     **/
    @JsonIgnore
    private String passwordExpression;

    /**
     * accountType属性对应的表达式属性，
     * 目前用来支持update set 字段=表达式(如:'字段+1')这种场景
     **/
    @JsonIgnore
    private String accountTypeExpression;

    /**
     * randomCode属性对应的表达式属性，
     * 目前用来支持update set 字段=表达式(如:'字段+1')这种场景
     **/
    @JsonIgnore
    private String randomCodeExpression;
}