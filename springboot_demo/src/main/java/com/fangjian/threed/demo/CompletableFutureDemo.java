//package com.fangjian.threed.demo;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import java.util.concurrent.CompletableFuture;
//import java.util.stream.Collectors;
//
///**  
//* 类说明   
//*  
//* @author Jimmy.Fang
//* @date 2020年3月24日  新建  
//*/
//public class CompletableFutureDemo {
//	
//	List<CompletableFuture<List<TimesAndAmount>>>  allStationsTimesAmount =
//	        inputParamArrayList.stream()
//	                .map(inputParam -> CompletableFuture.supplyAsync(() ->nonOilSalesAndPerCustomerTransactionDao.getTimesHoursInterval(inputParam), executorService))
//	                .collect(Collectors.toList());
//
//	List<List<TimesAndAmount>>  timesAmount = allStationsTimesAmount.stream()
//	        .map(CompletableFuture::join)
//	        .collect(Collectors.toList());
//	
//	private List<String> getBarcodeList(String[] deptIds, String[] ids) {
//
//	    List<String> list = new ArrayList<>();
//	    List<String> list1 = new ArrayList<>();
//	    if (deptIds != null){
//	        list = Arrays.asList(deptIds);
//
//	        List<CompletableFuture<List<String>>>  allBarcodes =
//	                list.stream()
//	                        .map(inputParam -> CompletableFuture.supplyAsync(() ->nonOilSalesAndPerCustomerTransactionDao.getBarcodesBydeptid(inputParam), executorService))
//	                        .collect(Collectors.toList());
//
//	        List<List<String>>  listList = allBarcodes.stream()
//	                .map(CompletableFuture::join)
//	                .collect(Collectors.toList());
//
//	//List<List<String>> 转换为List<String> ，使用flatMap
//
//	        list1 =
//	                listList.stream()
//	                        .flatMap(inner -> inner.stream()).collect(Collectors.toList());
//
//
//	    }
//
//	    if (ids != null){
//	        list1.addAll(Arrays.asList(ids));
//	    }
//
//	    return list1;
//
//	}
//}
