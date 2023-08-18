package com.training.loanapplication;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.training.loanapplication.dao.ItemRepository;
import com.training.loanapplication.model.Item;
import com.training.loanapplication.model.ItemCategory;
import com.training.loanapplication.service.ItemService;

@RunWith(SpringRunner.class)
@WebMvcTest
public class ItemControllerTest {
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private ItemService itemService ;
	
	@MockBean
	private ItemRepository itemRepository;
	
	ObjectMapper mapper = new ObjectMapper()
			.findAndRegisterModules()
			.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
	
	@Test
	public void testGetAllItems() throws Exception{
		Item item = new Item();
		item.setCategory(ItemCategory.FURNITURE);
		item.setDescription("new");
		item.setMake("wood");
		item.setStatus("A");
		item.setValue(1500);
		item.setItem_id(1);
		List<Item> allItems = new ArrayList<>();
		allItems.add(item);
		
		Mockito.when(itemService.getallItems()).thenReturn(allItems);
		
		mvc.perform(get("/getAllItems").
				contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect((ResultMatcher) ((ResultActions) jsonPath("$", Matchers.hasSize(1))));
//				.andExpect(jsonPath("$[0].value", Matchers.equalTo(item.getValue()))));
//				);
//		String Result = requestResult.getResponse().getContentAsString();
//		assertEquals(true,);
	}
}
