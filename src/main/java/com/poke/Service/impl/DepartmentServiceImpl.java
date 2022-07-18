package com.poke.Service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.poke.Mapper.DepartmentMapper;
import com.poke.Service.DepartmentService;
import com.poke.pojo.Department;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements DepartmentService {

}