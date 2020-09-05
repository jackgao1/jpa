package com.baidu.service;

import com.baidu.entity.repository.RkJbxxRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RkJbxxService {
    @Autowired
    private RkJbxxRepository rkJbxxRepository;
    
}
