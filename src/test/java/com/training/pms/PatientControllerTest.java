package com.training.pms;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.training.pms.model.Patient;

@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
public class PatientControllerTest extends AbstractTest {
	
//	private String uri = "/patient";
//	
//	// when saving a product, the database will always assign one, even if one is provided.
//	// this makes it difficult to test since the id always changes even if the previous id is deleted.
//	public int patientId = 0;
//		
//	Patient patient = new Patient(patientId, "Firstname", "Lastname", "name@email.com", "password",(long) 1000000000, "Male", null, "123 Test Road", "City", "State", 10000, null);
//	Patient updatedPatient = new Patient(patientId, "Firstname1", "Lastname1", "name@email.com", "password1",(long) 1000000001, "Male", null, "456 Test Road", "City1", "State1", 10001, null);
//
//	@BeforeEach
//	protected void setUp() {
//		super.setUp();
//	}
//
//	// test save functionality
//	@Test
//	@Order(value = 1)
//	@DisplayName("1. Testing save functionality")
//	void testSave() throws Exception {
//
//		String patientJSON = super.mapToJson(patient);
//
//		MvcResult mvcResult = mvc.perform(
//				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(patientJSON))
//				.andReturn();
//
//		int statusCode = mvcResult.getResponse().getStatus();
//		String message = mvcResult.getResponse().getContentAsString();
//
//		assertEquals("Patient (id:"+patientId+") saved successfully", message);
//		assertEquals(201, statusCode);
//
//	}
//
//	// test maxId functionality
//	@Test
//	@Order(value = 2)
//	@DisplayName("2. Testing maxId functionality")
//	void testMax() throws Exception {
//
//		MvcResult mvcResult = mvc
//				.perform(MockMvcRequestBuilders.get(uri + "/max").contentType(MediaType.APPLICATION_JSON_VALUE))
//				.andReturn();
//
//		int statusCode = mvcResult.getResponse().getStatus();
//		String message = mvcResult.getResponse().getContentAsString();
//		Patient actualpatient = super.mapFromJson(message, Patient.class);
//		patientId = actualpatient.getPatient_id();
//
//		assertTrue(actualpatient.getPatient_id() > 0);
//		assertEquals(200, statusCode);
//
//	}
//
//	// test get functionality
//	@Test
//	@Order(value = 3)
//	@DisplayName("3. Testing get functionality")
//	void testGet() throws Exception {
//
//		MvcResult mvcResult = mvc
//				.perform(MockMvcRequestBuilders.get(uri + "/" + patientId).contentType(MediaType.APPLICATION_JSON_VALUE))
//				.andReturn();
//
//		int statusCode = mvcResult.getResponse().getStatus();
//		String message = mvcResult.getResponse().getContentAsString();
//		Patient actualpatient = super.mapFromJson(message, Patient.class);
//
//		assertEquals(patient, actualpatient);
//		assertEquals(200, statusCode);
//
//	}
//
//	// test update functionality
//	@Test
//	@Order(value = 4)
//	@DisplayName("4. Testing update functionality")
//	void testUpdate() throws Exception {
//
//		String updatedPatientJSON = super.mapToJson(updatedPatient);
//
//		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri + "/" + patientId)
//				.contentType(MediaType.APPLICATION_JSON_VALUE).content(updatedPatientJSON)).andReturn();
//
//		int statusCode = mvcResult.getResponse().getStatus();
//		String message = mvcResult.getResponse().getContentAsString();
//
//		assertEquals("Patient (id:"+patientId+") updated successfully", message);
//		assertEquals(200, statusCode);
//
//	}
//
//	// test get all functionality
//	@Test
//	@Order(value = 5)
//	@DisplayName("5. Testing getAll functionality")
//	void testGetAll() throws Exception {
//
//		MvcResult mvcResult = mvc
//				.perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON_VALUE))
//				.andReturn();
//
//		int statusCode = mvcResult.getResponse().getStatus();
//		String message = mvcResult.getResponse().getContentAsString();
//		Patient[] actualpatients = super.mapFromJson(message, Patient[].class);
//
//		assertTrue(actualpatients.length > 0);
//		assertEquals(200, statusCode);
//
//	}
//
//	// test delete functionality
//	@Test
//	@Order(value = 9)
//	@DisplayName("9. Testing deleting functionality")
//	void testDelete() throws Exception {
//
//		MvcResult mvcResult = mvc.perform(
//				MockMvcRequestBuilders.delete(uri + "/" + patientId).contentType(MediaType.APPLICATION_JSON_VALUE))
//				.andReturn();
//
//		int statusCode = mvcResult.getResponse().getStatus();
//		String message = mvcResult.getResponse().getContentAsString();
//
//		assertEquals("Patient (id:"+patientId+") deleted successfully", message);
//		assertEquals(200, statusCode);
//
//	}
}
