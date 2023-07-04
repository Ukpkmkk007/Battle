package com.ironmanjay.springboot.mapper;

import com.ironmanjay.springboot.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author IronmanJay
 * @since 2023-06-03
 */
public interface RoleMapper extends BaseMapper<Role> {

    Integer selectByFlag(String roleFlag);
}
