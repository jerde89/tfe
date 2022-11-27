package com.tfe.fournil.controller;

import com.google.gson.Gson;
import com.tfe.fournil.entity.Feedback;
import com.tfe.fournil.entity.Product;
import com.tfe.fournil.repository.ProductRepository;
import com.tfe.fournil.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ProductController {

    @Autowired
    FileStorageService fileStorageService;


}
