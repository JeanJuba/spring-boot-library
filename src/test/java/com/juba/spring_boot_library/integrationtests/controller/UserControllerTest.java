package com.juba.spring_boot_library.integrationtests.controller;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.juba.spring_boot_library.config.TestConfigs;
import com.juba.spring_boot_library.integrationtests.testcontainers.AbstractIntegrationTest;
import com.juba.spring_boot_library.model.Role;
import com.juba.spring_boot_library.model.User;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;

@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class UserControllerTest extends AbstractIntegrationTest {

	private static RequestSpecification specification;
	private static ObjectMapper objectMapper;

	@BeforeAll
	static void setup() {
		objectMapper = new ObjectMapper();
		objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

		specification = new RequestSpecBuilder().setBasePath("/auth").setPort(TestConfigs.SERVER_PORT)
				.addFilter(new RequestLoggingFilter(LogDetail.ALL)).addFilter(new ResponseLoggingFilter(LogDetail.ALL))
				.build();
	}
	
	@Test
	@Order(1)
	@DisplayName("Test When Find By Id Sould Return SosGrupo Object")
	public void integrationTest_When_CreateUser_ShouldReturnUserObject()
			throws JsonMappingException, JsonProcessingException {
		
		Role role = new Role();
		role.setName("USER_ROLE");
		List<Role> roles = new ArrayList<>();
		roles.add(role);
		
		User user = new User("Jean Juba", "jean.juba@sulprint.com.br", "12343", roles);

		String content = given().spec(specification).contentType(TestConfigs.CONTENT_TYPE_JSON).body(user).when()
				.post("/signup").then().statusCode(200).extract().body().asString();

		// Converts JSON to object
		


		assertEquals("User Added Successfully", content);
	}

}
