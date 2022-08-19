package com.example.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.demo.model.Person;

@Mapper
public interface PersonRepository {
	
	@Select("select * from persons")
	public List<Person> findAll();
	
	@Select("select * from persons where id = #{id}")
	public Person findById(String id);
	
	@Delete("delete from persons where id = #{id}")
	public boolean deleteById(String id);
	
	@Select("select * from persons where name = #{name}")
	public Person findByName(String name);
	
	@Insert("insert into persons(id, name, email) values (#{id}, #{name}, #{email})")
	public boolean insert(Person person);
	
	@Update("update employees set name=#{name}, email=#{name} where id=#{id}")
	public boolean update(Person person);
}
