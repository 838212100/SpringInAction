package test.aspect;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	@RequestMapping("getlist")
	@OperationLogger(modelName = "获取列表", user = "baba")//option = "getList", user = "me"
	public String getList(String name, Integer num ,User user) {
		return "OK";
	}
	
	@RequestMapping("getName")
	public String getName(String name, Integer num ,User user) {
		return "OK";
	}
	
	@RequestMapping("getValues")
	@OperationLogger(modelName = "获取所有值", user = "mama")//option = "getList", user = "me"
	public String getValues(String name, Integer num ,User user) {
		return "OK";
	}
	
}
