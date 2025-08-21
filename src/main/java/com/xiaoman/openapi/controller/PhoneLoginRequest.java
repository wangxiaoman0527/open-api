package com.xiaoman.openapi.controller;

import lombok.Data;


@Data
public class PhoneLoginRequest {
    private String loginCode;

    private String phoneCode;
}