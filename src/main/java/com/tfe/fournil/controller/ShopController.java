package com.tfe.fournil.controller;

import com.tfe.fournil.entity.City;
import com.tfe.fournil.entity.ProductCategory;
import com.tfe.fournil.entity.Shop;
import com.tfe.fournil.repository.CityRepository;
import com.tfe.fournil.repository.ShopRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * The type Shop controller.
 */
@Slf4j
@Controller
@RequestMapping("/shop")
public class ShopController {

    /**
     * The Shop repository.
     */
    @Autowired
    ShopRepository shopRepository;
    /**
     * The City repository.
     */
    @Autowired
    CityRepository cityRepository;

    /**
     * Show shop list string.
     *
     * @param model the model
     * @return the string
     */
    @GetMapping("")
    public String showShopList(Model model) {
        List<City> cityList = cityRepository.findAll();
        model.addAttribute("cityList", cityList);
        return "shop";
    }

    /**
     * Ajax showdisplay shop response entity.
     *
     * @return the response entity
     */
    @GetMapping("/getAll")
    public ResponseEntity<List<Shop>> ajaxShowdisplayShop() {
        List<Shop> shop = shopRepository.findAll();
        return ResponseEntity.ok(shop);
    }
}
