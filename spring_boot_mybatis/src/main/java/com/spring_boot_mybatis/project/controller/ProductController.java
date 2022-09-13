package com.spring_boot_mybatis.project.controller;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring_boot_mybatis.project.model.ProductVO;
import com.spring_boot_mybatis.project.service.ProductService;


@Controller
public class ProductController {
	//DI 설정
	@Autowired
	ProductService prdService;
	
	// 시작 시 index 페이지 열기
	@RequestMapping("/")
	public String viewIndex() {
		return "index";
	}
	
	// 전체 상품 조회 요청 처리
	@RequestMapping("/product/productAllList")
	public String viewProductAllList(Model model) {
		// 서비스  클래스의 메소드 호출해서 결과 받아옴
		ArrayList<ProductVO> prdList = prdService.listAllProduct();
		// 모델 설정
		model.addAttribute("prdList",prdList);
		return "product/productAllListView"; // product 폴더의 productAllListView.jsp
	}
	
	//상품 등록 폼 열기 요청 처리
	@RequestMapping("/product/productNewForm")
	public String viewProductNewForm() {
		return "product/productNewForm";
	}
	
	// 상품 등록 : 상품 정보 DB 저장
	@RequestMapping("/product/insertProduct")
	public String insertProduct(ProductVO prd) {
		prdService.insertProduct(prd);
		
		//DB에 데이터 저장한후 전체 상품 조회 화면으로 포워딩
		return "redirect:./productAllList";
		//return "product/productAllListView"; //뷰페이지 이름 반환 (데이터 없는 빈 페이지)
	}
	
	// 상품 상세 정보 조회 : 1개 상품 조회
	@RequestMapping("/product/productDetailView/{prdNo}")
	public String datailViewProduct(@PathVariable String prdNo, Model model) {
		ProductVO prd = prdService.detailViewProduct(prdNo);
		model.addAttribute("prd", prd);
		return "product/productDetailView";
	}
	
	// 상품 정보 수정 폼 요청 처리
	@RequestMapping("/product/productUpdateForm/{prdNo}")
	public String updateProductForm(@PathVariable String prdNo,Model model) {
		// 수정할 상품번호 받아서, detailViewProduct() 메소드 호출하면서 전달하고
		// 해당 상품 정보 (1개) 받아서 모델 설정
		ProductVO prd = prdService.detailViewProduct(prdNo);
		model.addAttribute("prd", prd);
		return "product/productUpdateForm";
	}
	
	//수정 내용 DB 저장
	@RequestMapping("/product/updateProduct")
	public String updateProduct(ProductVO prd) {
		prdService.updateProduct(prd);
		
		//DB에 수정되 데이터 저장한 후 전체 상품 조회 화면으로 포워딩
		return "redirect:./productAllList";
		//return "redirect:./productAllList";
	}
	
	@RequestMapping("/product/deleteProduct/{prdNo}")
	public String deleteProduct(@PathVariable String prdNo) {
		prdService.deleteProduct(prdNo);
		
		
		//삭제한 후 전체 상품 조회 화면으로 포워딩
		return "redirect:/product/productAllList";
	}
	
	@ResponseBody
	@RequestMapping("/product/prdNoCheck")
	public String prdNoCheck(@RequestParam("prdNo") String prdNo) {
		String prdNo_result=prdService.prdNoCheck(prdNo);
		
		String result="use";
		if(prdNo_result!=null)
			result="no_use";
		
		return result;
	}
	
	//상품 검색 폼 열기 요청 처리
	@RequestMapping("/product/productSearchForm1")
	public String viewProductSearchForm1() {
		return "product/productSearchForm1";
	}
	
	// 상품 검색 : 방법1
	@ResponseBody
	@RequestMapping("/product/productSearch1")
	public ArrayList<ProductVO> productSearch1(@RequestParam HashMap<String,Object> param, Model model){
		ArrayList<ProductVO> prdList= prdService.productSearch(param);
		model.addAttribute("prdList",prdList);
		
		return prdList;
	}
	
	@RequestMapping("/product/productSearchForm2")
	public String viewProductSearchForm2() {
		return "product/productSearchForm2";
	}
	
	// 상품 검색 : 방법2
	// @ResponseBody 사용하지 않고 뷰 페이지 전달
	// 검색 내용은 동일하고 반환값만 다름
	@RequestMapping("/product/productSearch2")
	public String productSearch2(@RequestParam HashMap<String,Object> param, Model model){
		ArrayList<ProductVO> prdList= prdService.productSearch(param);
		model.addAttribute("prdList",prdList);
		
		return "product/productSearchResultView"; //뷰 페이지 반환
	}
	
	//상품 검색 폼 열기 요청 처리
		@RequestMapping("/product/productSearchForm3")
		public String viewProductSearchForm3() {
			return "product/productSearchForm3";
		}
}
