package com.project.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.project.model.Favorite;
import com.project.repository.FavoriteRespository;

@RunWith(MockitoJUnitRunner.class)
public class FavoriteServiceImplTest {

	@Mock
	private FavoriteRespository favoriteRepo;
	
	@InjectMocks
	private FavoriteServiceImpl favoriteServiceImpl;
	
	Favorite favorite;
	Optional<Favorite> optFavorite;
	List<Integer> favList;
	List<Integer> mealList;
	Integer fdcId;
	Boolean flag;
	
	@Before
	public void setup() {
		List<Integer> list = new ArrayList<Integer>();
		list.add(1002);
		list.add(1003);
		list.add(1004);
		favorite = new Favorite("100",list,list);
		optFavorite = Optional.of(favorite);
		favList =  new ArrayList<>();
		mealList = new ArrayList<>();
		fdcId = new Integer(1002);
	}
	
	@After
	public void teardown() {
		
	}
	
	@Test
	public void testAddToFavoriteFirstFavoriteSuccess() {
		when(favoriteRepo.findById(favorite.get_id())).thenReturn(Optional.empty());
		favList.add(fdcId);
		Favorite fav = new Favorite(favorite.get_id(),favList, mealList);
		favoriteRepo.save(fav);
		Boolean result = favoriteServiceImpl.addToFavorite(favorite.get_id(), fdcId);
		assertEquals(true, result);
		verify(favoriteRepo).findById(Mockito.any());
	}
	
		
//	@Test
//	public void testAddToFavoriteSuccess() {
//		when(favoriteRepo.findById(favorite.get_id())).thenReturn(optFavorite);
//		when(optFavorite.get().getFavoriteList()).thenReturn(favList);
//		for(Integer e: favList) {
//			when(fdcId.equals(e)).thenReturn(false);
//		}
//		favorite.setFavoriteList(fdcId);
//		when(favoriteRepo.save(favorite)).thenReturn(favorite);
//		Boolean result = favoriteServiceImpl.addToFavorite(favorite.get_id(), fdcId);
//		assertEquals(true, result);
//		verify(favoriteRepo).findById(Mockito.any());
//	}
	
	@Test
	public void testGetFavoriteSuccess() {
		when(favoriteRepo.findById(favorite.get_id())).thenReturn(optFavorite);
		favList = optFavorite.get().getFavoriteList();
		List<Integer> fetchedList = favoriteServiceImpl.getFavorite(favorite.get_id());
		assertEquals(favList, fetchedList);
		verify(favoriteRepo).findById(Mockito.any());
	}
	
	@Test
	public void testGetFavoriteFailure() {
		when(favoriteRepo.findById(favorite.get_id())).thenReturn(Optional.empty());
		List<Integer> fetchedList = favoriteServiceImpl.getFavorite(favorite.get_id());
		assertEquals(null, fetchedList);
		verify(favoriteRepo).findById(Mockito.any());
	}
	
	@Test
	public void testDeleteFavoriteSuccess() {
		when(favoriteRepo.findById(favorite.get_id())).thenReturn(optFavorite);
		flag = optFavorite.get().getFavoriteList().remove(fdcId);
		Boolean result = favoriteServiceImpl.deleteFavorite(favorite.get_id(), fdcId);
		assertEquals(flag, result);
		verify(favoriteRepo).findById(Mockito.any());	
	}
	
	@Test
	public void testDeleteFavoriteFailure() {
		when(favoriteRepo.findById(favorite.get_id())).thenReturn(Optional.empty());
		Boolean result = favoriteServiceImpl.deleteFavorite(favorite.get_id(), fdcId);
		assertEquals(false, result);
		verify(favoriteRepo).findById(Mockito.any());
	}
	
	@Test
	public void testAddToMealFirstMealSuccess() {
		when(favoriteRepo.findById(favorite.get_id())).thenReturn(Optional.empty());
		mealList.add(fdcId);
		Favorite fav = new Favorite(favorite.get_id(),favList, mealList);
		favoriteRepo.save(fav);
		Boolean result = favoriteServiceImpl.addToMeal(favorite.get_id(), fdcId);
		assertEquals(true, result);
		verify(favoriteRepo).findById(Mockito.any());
	}
	
//	@Test
//	public void testAddToMealFailure() {
//		
//	}
	
	@Test
	public void testgetMealSuccess() {
		when(favoriteRepo.findById(favorite.get_id())).thenReturn(optFavorite);
		mealList = optFavorite.get().getMealList();
		List<Integer> fetchedList = favoriteServiceImpl.getMeal(favorite.get_id());
		assertEquals(mealList, fetchedList);
		verify(favoriteRepo).findById(Mockito.any());
	}
	
	@Test
	public void testGetMealFailure() {
		when(favoriteRepo.findById(favorite.get_id())).thenReturn(Optional.empty());
		List<Integer> fetchedList = favoriteServiceImpl.getMeal(favorite.get_id());
		assertEquals(null, fetchedList);
		verify(favoriteRepo).findById(Mockito.any());	
	}
	
	@Test
	public void testDeleteMealsuccess() {
		when(favoriteRepo.findById(favorite.get_id())).thenReturn(optFavorite);
		flag = optFavorite.get().getMealList().remove(fdcId);
		Boolean result = favoriteServiceImpl.deleteMeal(favorite.get_id(), fdcId);
		assertEquals(flag, result);
		verify(favoriteRepo).findById(Mockito.any());
		
	}
	
	@Test
	public void testDeleteMealFailure() {
		when(favoriteRepo.findById(favorite.get_id())).thenReturn(Optional.empty());
		Boolean result = favoriteServiceImpl.deleteMeal(favorite.get_id(), fdcId);
		assertEquals(false, result);
		verify(favoriteRepo).findById(Mockito.any());
	}
}
	
	
