package com.abrsoftware.speeddytest.dummy;

import com.abrsoftware.speeddytest.model.Brand;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AbrWin on 29/10/17.
 */

public class DummyBrand {
    private String[] nameBrands = {"Samsung Galaxy S 3", "Samsung Galaxy Note 8", "Motorola g 6", "Google Pixel", "Iphone 7", "Iphone 8", "Iphone 9"};
    private String[] resumeBranfs = {
            "With a smarter S Pen, our largest Infinity Display and our best camera yet, Galaxy Note8 gives you more ways to create, share, and express your ideas than ever before",
            "Galaxy Note8 gives you more ways to create, share, and express your ideas than ever before",
            "With a smarter S Pen, our largest Infinity Display and our best camera yet, Galaxy Note8 gives you more ways to create, share, and express your ideas than ever before",
            "With a smarter S Pen, our largest Infinity Display and our best camera yet, Galaxy Note8 gives you more ways to create, share, and express your ideas than ever before",
            "With a smarter S Pen, our largest Infinity Display and our best camera yet, Galaxy Note8 gives you more ways to create, share, and express your ideas than ever before",
            "With a smarter S Pen, our largest Infinity Display and our best camera yet, Galaxy Note8 gives you more ways to create, share, and express your ideas than ever before",
            "With a smarter S Pen, our largest Infinity Display and our best camera yet, Galaxy Note8 gives you more ways to create, share, and express your ideas than ever before"
    };

    public List<Brand> getListBrand() {
        List<Brand> brandList = new ArrayList<>();
        for (int i = 0; i < nameBrands.length; i++) {
            Brand brand = new Brand();
            brand.setName(nameBrands[i]);
            brand.setResume(resumeBranfs[i]);
            brandList.add(brand);
        }
        return brandList;
    }


}
