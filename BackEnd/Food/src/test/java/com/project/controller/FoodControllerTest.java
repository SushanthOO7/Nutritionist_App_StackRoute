package com.project.controller;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.model.Favorite;
import com.project.model.Food;
import com.project.model.Nutrients;
import com.project.service.FavoriteService;
import com.project.service.FoodService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = FoodController.class)
public class FoodControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private FoodService foodService;
	
//	@MockBean
//	private UserServiceImpl useServiceImpl;
	
	@MockBean
	private FavoriteService favoriteService;
	
	String username;
	String token;

	Food food;
	List<Nutrients> nutrientsList;
	Nutrients nutrients;
	Optional<Food> optFood;
	List<Food> allFood;
	String fdcId;
	Favorite favorite;
	Optional<Favorite> optFavorite;
	List<Integer> favList;
	List<Integer> mealList;
	
	@Before
	public void setup() throws Exception {
		username = "admin";
		token = Jwts.builder().setId("admin").setIssuedAt(new Date())
				.signWith(SignatureAlgorithm.HS256, "usersecretkey").compact();
		nutrientsList = new ArrayList<Nutrients>();
		nutrients = new Nutrients(111,"Water","86.2","g","");
		nutrientsList.add(nutrients);
		nutrients = new Nutrients(112,"Energy","51","kcal","Calculated");
		nutrientsList.add(nutrients);
		food = new Food(1002,"Apple","Branded","2019-12-06","","Brand123","Malus domestica","Fruit",nutrientsList);
		optFood = Optional.of(food);
		allFood = new ArrayList<>();
		List<Integer> list = new ArrayList<Integer>();
		list.add(1002);
		list.add(1003);
		list.add(1004);
		favorite = new Favorite("100",list,list);
		optFavorite = Optional.of(favorite);
		favList =  new ArrayList<>();
		mealList = new ArrayList<>();
		fdcId = "1002";
	}
	
	@After
	public void teardown() {}
	
	@Test
	public void testSaveFoodSuccess() throws Exception{
		when(foodService.saveFood(Mockito.any())).thenReturn(food);
		mockMvc.perform(post("/api/v1/food/saveFood").header("Authorization", "Bearer "+token)
				.contentType(MediaType.APPLICATION_JSON).content(asJsonString(food)))
				.andExpect(status().isCreated()).andDo(print());
		verify(foodService).saveFood(Mockito.any());
	}
	
	@Test
	public void testSaveFoodFailure() throws Exception{
		when(foodService.saveFood(Mockito.any())).thenReturn(null);
		mockMvc.perform(post("/api/v1/food/saveFood").header("Authorization", "Bearer "+token)
				.contentType(MediaType.APPLICATION_JSON).content(asJsonString(food)))
				.andExpect(status().isInternalServerError()).andDo(print());
		verify(foodService).saveFood(Mockito.any());
	}
	
	@Test
	public void testGetFoodSuccess() throws Exception{
		when(foodService.getAllFood()).thenReturn(allFood);
		mockMvc.perform(get("/api/v1/food/getFood").header("Authorization", "Bearer "+token))
				.andExpect(status().isOk()).andDo(print());
		verify(foodService).getAllFood();
	}
	
	@Test
	public void testGetFoodFaliure() throws Exception {
		when(foodService.getAllFood()).thenReturn(null);
		mockMvc.perform(post("/api/v1/food/getFood").header("Authorization", "Bearer "+token))
				.andDo(print());
	}
	
	@Test
	public void testSaveFavoriteAlreadySavedFoodSuccess() throws Exception {
		when(foodService.getFoodById(food.getFdcId())).thenReturn(food);
		when(favoriteService.addToFavorite(username, food.getFdcId())).thenReturn(true);
		mockMvc.perform(post("/api/v1/food/saveFavorite").header("Authorization", "Bearer "+token)
				.contentType(MediaType.APPLICATION_JSON).content(asJsonString(food)))
				.andExpect(status().isOk()).andDo(print());
		verify(foodService).getFoodById(Mockito.any());
		verify(favoriteService).addToFavorite(Mockito.anyString(), Mockito.anyInt());
	}
	
	@Test
	public void testSaveFavoriteSaveFoodSuccess() throws Exception{
		when(foodService.getFoodById(food.getFdcId())).thenReturn(null);
		when(foodService.saveFood(Mockito.any(Food.class))).thenReturn(food);
		when(favoriteService.addToFavorite(username, food.getFdcId())).thenReturn(true);
		mockMvc.perform(post("/api/v1/food/saveFavorite").header("Authorization", "Bearer "+token)
				.contentType(MediaType.APPLICATION_JSON).content(asJsonString(food)))
				.andExpect(status().isOk()).andDo(print());
		verify(foodService).getFoodById(Mockito.any());
		verify(foodService).saveFood(Mockito.any());
		verify(favoriteService).addToFavorite(Mockito.anyString(), Mockito.anyInt());
	}
	
	@Test
	public void testSaveFavoriteFailure() throws Exception {
		when(foodService.getFoodById(food.getFdcId())).thenReturn(null);
		when(foodService.saveFood(Mockito.any(Food.class))).thenReturn(null);
		mockMvc.perform(post("/api/v1/food/saveFavorite").header("Authorization", "Bearer "+token)
				.contentType(MediaType.APPLICATION_JSON).content(asJsonString(food)))
				.andExpect(status().isInternalServerError()).andDo(print());
		verify(foodService).getFoodById(Mockito.any());
		verify(foodService).saveFood(Mockito.any());
	}
	
	@Test
	public void testSaveMealAlreadySavedFoodSuccess() throws Exception{
		when(foodService.getFoodById(food.getFdcId())).thenReturn(food);
		when(favoriteService.addToMeal(username, food.getFdcId())).thenReturn(true);
		mockMvc.perform(post("/api/v1/food/saveMeal").header("Authorization", "Bearer "+token)
				.contentType(MediaType.APPLICATION_JSON).content(asJsonString(food)))
				.andExpect(status().isOk()).andDo(print());
		verify(foodService).getFoodById(Mockito.any());
		verify(favoriteService).addToMeal(Mockito.anyString(), Mockito.anyInt());
	}
	
	@Test
	public void testSaveMealSaveFoodSuccess() throws Exception {
		when(foodService.getFoodById(food.getFdcId())).thenReturn(null);
		when(foodService.saveFood(Mockito.any(Food.class))).thenReturn(food);
		when(favoriteService.addToMeal(username, food.getFdcId())).thenReturn(true);
		mockMvc.perform(post("/api/v1/food/saveMeal").header("Authorization", "Bearer "+token)
				.contentType(MediaType.APPLICATION_JSON).content(asJsonString(food)))
				.andExpect(status().isOk()).andDo(print());
		verify(foodService).getFoodById(Mockito.any());
		verify(foodService).saveFood(Mockito.any());
		verify(favoriteService).addToMeal(Mockito.anyString(), Mockito.anyInt());
	}
	
	@Test
	public void testSaveMealFailure() throws Exception {
		when(foodService.getFoodById(food.getFdcId())).thenReturn(null);
		when(foodService.saveFood(Mockito.any(Food.class))).thenReturn(null);
		mockMvc.perform(post("/api/v1/food/saveMeal").header("Authorization", "Bearer "+token)
				.contentType(MediaType.APPLICATION_JSON).content(asJsonString(food)))
				.andExpect(status().isInternalServerError()).andDo(print());
		verify(foodService).getFoodById(Mockito.any());
		verify(foodService).saveFood(Mockito.any());
	}
	
	@Test
	public void testGetFavoriteSuccess() throws Exception {
		when(favoriteService.getFavorite(Mockito.anyString())).thenReturn(favList);
		mockMvc.perform(post("/api/v1/food/getFavorite").header("Authorization", "Bearer "+token))
				.andExpect(status().isOk()).andDo(print());
		verify(favoriteService).getFavorite(Mockito.any());
	}
	
	@Test
	public void testGetFavoriteFailure() throws Exception {
		when(favoriteService.getFavorite(Mockito.anyString())).thenReturn(null);
		mockMvc.perform(post("/api/v1/food/getFavorite").header("Authorization", "Bearer "+token))
				.andExpect(status().isConflict()).andDo(print());
		verify(favoriteService).getFavorite(Mockito.any());
	}
	
	
	@Test
	public void testGetMealSuccess() throws Exception {
		when(favoriteService.getFavorite(Mockito.anyString())).thenReturn(mealList);
		mockMvc.perform(post("/api/v1/food/getMeal").header("Authorization", "Bearer "+token))
				.andExpect(status().isOk()).andDo(print());
		verify(favoriteService).getMeal(Mockito.any());
	}
	
	@Test
	public void testGetMealFailure() throws Exception {
		when(favoriteService.getFavorite(Mockito.anyString())).thenReturn(null);
		mockMvc.perform(post("/api/v1/food/getMeal").header("Authorization", "Bearer "+token))
				.andExpect(status().isOk()).andDo(print());
		verify(favoriteService).getMeal(Mockito.any());
	}
	
		
	@Test
	public void testDeleteFavoriteSuccess() throws Exception {
		when(favoriteService.deleteFavorite(Mockito.anyString(), Mockito.anyInt())).thenReturn(true);
		when(favoriteService.getFavorite(Mockito.anyString())).thenReturn(favList);
		for(Integer e : favList) {
			when(foodService.getFoodById(e)).thenReturn(food);
			allFood.add(food);
		}
		mockMvc.perform(post("/api/v1/food/deleteFavorite").header("Authorization", "Bearer "+token)
				.param("fdcId", fdcId))
				.andExpect(status().isOk()).andDo(print());
		verify(favoriteService).deleteFavorite(Mockito.any(), Mockito.any());
		verify(favoriteService).getFavorite(Mockito.any());
	}
	
	@Test
	public void testDeleteFavoriteFailure() throws Exception {
		when(favoriteService.deleteFavorite(Mockito.anyString(), Mockito.anyInt())).thenReturn(false);

		mockMvc.perform(post("/api/v1/food/deleteFavorite").header("Authorization", "Bearer "+token)
				.param("fdcId", fdcId))
				.andExpect(status().isInternalServerError()).andDo(print());
		verify(favoriteService).deleteFavorite(Mockito.any(), Mockito.any());
	}
	
	@Test
	public void testDeleteMealSuccess() throws Exception {
		when(favoriteService.deleteMeal(Mockito.anyString(), Mockito.anyInt())).thenReturn(true);
		when(favoriteService.getMeal(Mockito.anyString())).thenReturn(mealList);
		for(Integer e : mealList) {
			when(foodService.getFoodById(e)).thenReturn(food);
			allFood.add(food);
		}
		mockMvc.perform(post("/api/v1/food/deleteMeal").header("Authorization", "Bearer "+token)
				.param("fdcId", fdcId))
				.andExpect(status().isOk()).andDo(print());
		verify(favoriteService).deleteMeal(Mockito.any(), Mockito.any());
		verify(favoriteService).getMeal(Mockito.any());
	}
	
	@Test
	public void testDeleteMealFailure() throws Exception {
		when(favoriteService.deleteFavorite(Mockito.anyString(), Mockito.anyInt())).thenReturn(false);

		mockMvc.perform(post("/api/v1/food/deleteMeal").header("Authorization", "Bearer "+token)
				.param("fdcId", fdcId))
				.andExpect(status().isInternalServerError()).andDo(print());
		verify(favoriteService).deleteMeal(Mockito.any(), Mockito.any());
	}
	
	@Test
	public void testIsAuthenticated() throws Exception {
		Map<String, String> map = new HashMap<>();
		map.put("isAuthenticated", "true");
		mockMvc.perform(get("/api/v1/food/isAuthenticated").header("Authorization", "Bearer "+token))
				.andExpect(status().isOk()).andDo(print());
		
	}
	
	public static String asJsonString(final Object obj) {
		try {

			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
