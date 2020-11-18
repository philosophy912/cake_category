package com.sophia.cake.dao;

import com.sophia.cake.entity.po.Middle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IMiddleDao extends JpaRepository<Middle, Integer> {

    List<Middle> findByNameLike(String name);
}
