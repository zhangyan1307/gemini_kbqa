package com.gemini.admin.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: XXY
 * @Date: 2021/3/21 9:09
 */
@Data
public class KbCustomerSaveRequest implements Serializable {
    private Long id;
    private String question;

    private String answer;

    private Integer recommendFlag;



}
