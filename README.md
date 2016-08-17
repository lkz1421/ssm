# ssm
  
```java
@Controller
@RequestMapping("/test")
public class TestController {
	
	@Resource
	private UserMapper userMapper;
	
	@RequestMapping(value="/get",method=RequestMethod.GET)
	public void get(User user,HttpServletRequest request,HttpServletResponse response) throws IOException{
		System.out.println(JSONObject.toJSONString(user));
		List<Map<String, Object>> list = userMapper.findAll();
		response.getWriter().write(JSONObject.toJSONString(list));
	}
	
	@RequestMapping(value="/find/{name}",method=RequestMethod.GET)
	public void find(@PathVariable String name,HttpServletRequest request,HttpServletResponse response) throws IOException{
		PageHelper.startPage(2, 5).setOrderBy("id desc");
		List<User> users = userMapper.find(name);
		PageInfo<User> page = new PageInfo<User>(users);
		response.getWriter().write(JSONObject.toJSONString(page));
	}
	
	public static void main(String[] args) {
		User user = new User();
		user.setId("id0001");
		List<Object> list = new ArrayList<Object>();
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("date", new Date());
		map.put("user", user);
		JSONObject obj = JSON.parseObject(JSON.toJSONString(map));
		list.add(obj);
		System.out.println(obj instanceof Map);
		SerializeConfig config = new SerializeConfig();
		config.put(Date.class, new SimpleDateFormatSerializer("yyyy-MM-dd HH:mm:ss"));
		System.err.println(JSON.toJSONString(list,config));
	}
}
```
