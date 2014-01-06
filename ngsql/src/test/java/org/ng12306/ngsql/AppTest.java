package org.ng12306.ngsql;

import static org.junit.Assert.fail;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppTest {
	
	Logger log = LoggerFactory.getLogger(AppTest.class);

	@Test
	public void test() {
		log.debug("hello world");
	}

}
