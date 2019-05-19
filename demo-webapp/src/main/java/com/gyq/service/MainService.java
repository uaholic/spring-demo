package com.gyq.service;

import org.springframework.stereotype.Service;

@Service
public class MainService {

    public String getView() {
        return "index";
    }
}
