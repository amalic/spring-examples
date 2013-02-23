package org.amalic.controller;

import org.amalic.model.TestModel;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/jsons")
public class JSONController {
	
	private static Log log = LogFactory.getLog(JSONController.class);

	@RequestMapping(value="/getSomethingAsJSON/{property}/{property2}", method = RequestMethod.GET)
	public @ResponseBody TestModel getSomethingAsJSON(@PathVariable String property, @PathVariable String property2) {
		log.info("RequestMapping works!");
		
		TestModel testModel = new TestModel();
		testModel.setProperty(property);
		testModel.setSomeOtherProperty(property2);
		testModel.setStringArray(new String[]{"string1", "string2"});
		
		return testModel;
	}
	
}
