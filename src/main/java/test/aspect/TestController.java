package test.aspect;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	@RequestMapping("getlist")
	@OperationLogger(modelName = "返回数据", option = "getList", user = "me")
	public String getList() {
		return "OK";
	}
	
}
