package com.revature.backend.testing.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.backend.controller.AssociateController;
import com.revature.backend.model.Associate;
import com.revature.backend.model.AssociateStatus;
import com.revature.backend.model.Batch;
import com.revature.backend.model.Manager;
import com.revature.backend.model.dto.AssociateDTO;
import com.revature.backend.service.AssociateService;
import com.revature.backend.service.BackendService;
import com.revature.backend.service.BatchService;
import com.revature.backend.util.ClientMessage;


/**
 * This is a set of tests for the AssociateController class. It contains 7 tests related to this class.<p>
 * test 1:Tests whether the application loads properly.<p>
 * test 2:Tests the HTTP status of a successful response.<p>
 * test 3:Tests the response body of a successful response.<p>
 * test 4:Tests the HTTP status for when the Service layer has a null return value.<p>
 * test 5:Tests the HTTP status for an empty list being returned from the service layer.<p>
 * test 6:Tests the HTTP status for a non integer value.<p>
 * test 7:Tests the HTTP status when a parameter name is not a Manager.<p>
 * @author ?: wrote tests and initial documentation.
 * @author Matthew Sheldon: Updated tests to work with Junit 5 and updated documentation.
 */
@SpringBootTest
public class AssociateControllerTests {
	private final Logger logger = LoggerFactory.getLogger(getClass());

	ObjectMapper objectMapper = new ObjectMapper();

//	@Autowired
	@InjectMocks
	AssociateController associateController;

//	@Autowired
	private MockMvc mockMvc;

//	@MockBean
	@Mock
	private BackendService service;

	@Mock
	private AssociateService assoServImpl;

	@Mock
	private BatchService batchServImpl;

	Manager manager = new Manager(1, "manager@manager.com", "Demo", "Manager");
	Batch batch = new Batch(1, "salesID", "name", "skill", "location");
	List<Associate> associates = new ArrayList<>();
	List<AssociateDTO> associateDTOs = new ArrayList<>();

	@BeforeEach
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(associateController).build();
		Associate associate1 = new  Associate(1, "salesID", "email@email.com", "John", "Doe", manager, batch, AssociateStatus.STAGING);
		Associate associate2 = new  Associate(2, "salesID", "email1@email.com", "John", "Doe", manager, batch, AssociateStatus.STAGING);
		AssociateDTO associateDTO1 = new AssociateDTO(1, "salesID", "email1@email.com", "John", "Doe", 1, 1,AssociateStatus.STAGING.toString());
		AssociateDTO associateDTO2 = new AssociateDTO(2, "salesID", "email@email.com", "John", "Doe", 1, 1,AssociateStatus.STAGING.toString());
		associates.add(associate1);
		associates.add(associate2);
		associateDTOs.add(associateDTO1);
		associateDTOs.add(associateDTO2);
		Batch batchTwo = new Batch(2, "salesID", "name", "skill", "location");

		when(assoServImpl.getAssociateById(1)).thenReturn(associate1);
		when(batchServImpl.getBatchById(1)).thenReturn(batch);
		when(batchServImpl.getBatchById(2)).thenReturn(batchTwo);
		when(service.findAssociatesByManagerId(1)).thenReturn(associates);
		when(service.findAssociatesByManagerId(2)).thenReturn(null);
  }

	/**
	 * Sanity Check - if this fails, application context is not loaded and all other
	 * tests should fail
	 */
	@Test
	public void contextLoads() throws Exception {
		assertThat(associateController).isNotNull();
	}

	/**
	 * Verifies the HTTP Status for a successful response
	 */
	@Test
	public void controllerSuccess() throws Exception {
		this.mockMvc.perform(get("/associates").param("manager", "1")).andDo(print()).andExpect(status().isOk());
	}

	/**
	 * Verifies the ResponseBody content for a successful response
	 */
	@Test
	public void controllerSuccessContentVerification() throws Exception {

		MvcResult mvcResult = this.mockMvc.perform(get("/associates").param("manager", "1")).andReturn();
		String actualResponse = mvcResult.getResponse().getContentAsString();
		assertThat(actualResponse).isEqualToIgnoringWhitespace(objectMapper.writeValueAsString(associateDTOs));

	}

	/**
	 * Verifies HTTP Status for a null return value from the service layer
	 */
	@Test
	public void controllerFailReceivesNull() throws Exception {
		this.mockMvc.perform(get("/associates").param("manager", "2")).andDo(print()).andExpect(status().isNoContent());
	}

	/**
	 * Verifies HTTP Status for an empty list returned from the service layer
	 */
	@Test
	public void controllerFailReceivesEmpty() throws Exception {
		this.mockMvc.perform(get("/associates").param("manager", "")).andDo(print()).andExpect(status().isBadRequest());
	}

	/**
	 * Verifies HTTP Status for a non integer parameter value
	 */
	@Test
	public void controllerFailWrongParamType() throws Exception {
		this.mockMvc.perform(get("/associates").param("manager", "string")).andDo(print()).andExpect(status().isBadRequest());
	}

	/**
	 * Verifies HTTP Status where parameter name is not 'manager'
	 */
	@Test
	public void controllerFailWrongParamName() throws Exception {
		this.mockMvc.perform(get("/associates").param("associate", "1")).andDo(print()).andExpect(status().isBadRequest());
	}

//	you can't test a getmapping of a single associate because there is not repository and service that will grab a single associate
//	@Test
//	public void associateGetMappingTest() throws Exception {
//		this.mockMvc.perform(get("/associates").param("manager", "1")).andExpect(status().isOk()).andExpect(jsonPath("$.firstName", is("John")));
//	}

	/**
	 * Testing the PutMapping by updating associate's batch_id and or status,
	 * using status_id to accessing AssociateStatus Enum. Updated this test to
	 * handle the status_id changes as well
	 *
	 * @throws Exception
	 */
	@Test
	public void associatePutMappingTest() throws Exception {
		this.mockMvc.perform(put("/associates").contentType(MediaType.APPLICATION_JSON).content(
			"{ "
			+ "\"associate_id\": 1,"
			+ "\"batch_id\" : 2, "
			+ "\"status_id\": 2"
			+ " }"
		)).andExpect(status().isOk()).andExpect(jsonPath("$").value("Associate updated successfully"));
	}

	@Test
	public void checkAssociateLogin() throws Exception{
		MvcResult mvcResult = this.mockMvc.perform(post("/associate").contentType("application/json")
							  .content(objectMapper.writeValueAsString(new ClientMessage("email1@email.com"))))
							  .andReturn();
		String actualResponse = mvcResult.getResponse().getContentAsString();
		logger.debug("actual response is: " + actualResponse);
		logger.debug("expected response is: " + objectMapper.writeValueAsString(associates.get(0)));
		assertThat(actualResponse).isEqualToIgnoringWhitespace(objectMapper.writeValueAsString(associates.get(0)));
	}
}
