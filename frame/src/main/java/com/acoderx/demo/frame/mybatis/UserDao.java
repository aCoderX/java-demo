package com.acoderx.demo.frame.mybatis;

import com.acoderx.demo.frame.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * Created by xudi on 2018/4/28.
 */
@Mapper
@Repository
public interface UserDao {

    @Select("select * from user where id = #{id}")
    User getUser(int id);

}
