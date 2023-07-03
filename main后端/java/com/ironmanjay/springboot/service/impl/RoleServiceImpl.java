package com.ironmanjay.springboot.service.impl;

import com.ironmanjay.springboot.entity.Role;
import com.ironmanjay.springboot.mapper.RoleMapper;
import com.ironmanjay.springboot.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author IronmanJay
 * @since 2023-06-03
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

}
