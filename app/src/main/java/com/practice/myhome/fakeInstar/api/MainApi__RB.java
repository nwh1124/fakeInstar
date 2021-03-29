package com.practice.myhome.fakeInstar.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MainApi__RB<T> {
    public String ResultCode;
    public String msg;
    public boolean fail;
    public boolean success;
    public T body;
}
