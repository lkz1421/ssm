# ssm
  
```java
public interface UserMapper extends Mapper<User>{
	
	int insertBatch(List<User> list);
	
    @Select("select id,username from user")
	List<Map<String, Object>> findAll();

    @Select("select * from user where username=#{name}")
	List<User> find(@Param("name")String name);
}
```
