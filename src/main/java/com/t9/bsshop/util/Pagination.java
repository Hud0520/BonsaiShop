package com.t9.bsshop.util;

import java.util.ArrayList;
import java.util.List;

public class Pagination {
	private int currentPage,totalPage;
	
	public Pagination(int currentPage, int totalPage) {
		this.currentPage = currentPage;
		this.totalPage = totalPage;
	}
	public List<String> getPages(){
		int sdp=Math.max(currentPage-2,1);
		int edp=Math.min(sdp+4,totalPage);
		List<String> pages=new ArrayList<>();
		if(sdp>1){
			pages.add("1");
		}
		if(sdp>2){
			pages.add("...");
		}
		for(int i=sdp;i<=edp;i++){
			pages.add(String.valueOf(i));
		}
		if(edp<totalPage-1){
			pages.add("...");
		}
		if(edp<totalPage){
			pages.add(String.valueOf(totalPage));
		}
		return pages;
	}
}
