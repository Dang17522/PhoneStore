package com.edu.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.edu.Utils.SessionUtil;
import com.edu.model.Favorite;
import com.edu.model.Product;
import com.edu.model.Review;
import com.edu.model.User;
import com.edu.model.Voucher;
import com.edu.service.BrandsService;
import com.edu.service.FavoriteService;
import com.edu.service.MultimageService;
import com.edu.service.ProductService;
import com.edu.service.ReviewService;
import com.edu.service.UserService;
import com.edu.service.VoucherService;

@Controller
public class ProductController {
    @Autowired
    BrandsService brandsService;

    @Autowired
    HttpServletRequest request;

    @Autowired
    UserService userService;

    @Autowired
    ProductService productService;

    @Autowired
    MultimageService multimageService;

    @Autowired
    FavoriteService favoriteService;

    @Autowired
    SessionUtil session;

    @Autowired
    VoucherService voucherService;

    @Autowired
    ReviewService reviewService;
    private final int PAGE_SIZE = 12;

    @RequestMapping("/PhoneStore")
    public String shop(Model model) {
        model.addAttribute("list", brandsService.findAll());
        Pageable pageable = PageRequest.of(0, 8);
        Page<Product> page = productService.findAll(pageable);
        Page<Product> page2 = productService.findByCreateDate(pageable);
        Page<Product> page3 = productService.finAllDiscount(pageable);
        model.addAttribute("pageProducts", page.getContent());
        model.addAttribute("op", page2.getContent());
        model.addAttribute("pa", page3.getContent());
        model.addAttribute("fa", favoriteService.findAllwhere(request.getRemoteUser()));
        return "product/home";
    }

    @RequestMapping("product/list/{id}")
    public String detail(Model model, @PathVariable("id") Long id) {
        model.addAttribute("list", productService.finID(id));
        model.addAttribute("list2", multimageService.findimage(id));

        Product product = productService.finID(id);
        model.addAttribute("color", productService.findbyName(product.getName()));

        model.addAttribute("review", reviewService.View(id));
        session.setAttribute("fa", favoriteService.findAllwhere(request.getRemoteUser()));
        Optional<Favorite> fa = favoriteService.find(request.getRemoteUser(), id);
        if (fa.isPresent()) {
            model.addAttribute("ah", 1);
            model.addAttribute("favorite", favoriteService.findproduct(request.getRemoteUser(), id));

        } else {
            model.addAttribute("ah", 0);
            model.addAttribute("favorite", new Favorite());

        }
        return "product/detail";

    }

    @RequestMapping("PhoneStore/home")
    public String listall(Model model,
            @RequestParam(value = "page", required = false, defaultValue = "1") Optional<Integer> pageIndex) {
        try {
            Pageable pageable = PageRequest.of(pageIndex.orElse(0), 12);
            Page<Product> page = productService.findAll(pageable);
            model.addAttribute("pageNumber", page.getNumber());
            model.addAttribute("pageTotalPages", page.getTotalPages());
            model.addAttribute("list", page.getContent());
            model.addAttribute("count", productService.count());
            model.addAttribute("load", productService.load());
            model.addAttribute("loadsize", productService.loadsize());
            model.addAttribute("fa", favoriteService.findAllwhere(request.getRemoteUser()));
            model.addAttribute("title", "Mặc định");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "product/list";

    }

    @GetMapping("/product/findbyprice")
    public String find(Model model, @RequestParam(value = "min", required = false) Optional<Double> min,
            @RequestParam(value = "max", required = false) Optional<Double> max,
            @RequestParam(value = "page", required = false, defaultValue = "1") int pageNumber) {
        List<Product> products = new ArrayList<>();
        try {
            Double m1 = min.orElse(session.getAttribute("min"));
            session.setAttribute("min", m1);

            Double m2 = max.orElse(session.getAttribute("max"));
            session.setAttribute("max", m2);

            Page<Product> pageProducts = productService.findByPrice(PAGE_SIZE, pageNumber, m1, m2);
            products = pageProducts.getContent();
            model.addAttribute("pageTotalPages", pageProducts.getTotalPages());
            model.addAttribute("pageNumber", pageNumber);
            model.addAttribute("min", m1);
            model.addAttribute("max", m2);
            model.addAttribute("count", productService.count());
            model.addAttribute("load", productService.load());
            model.addAttribute("loadsize", productService.loadsize());
            model.addAttribute("title", "Mặc định");
        } catch (Exception e) {
            e.printStackTrace();
            products = productService.findAll();
        }
        model.addAttribute("list", products);
        return "product/list";
    }

    @GetMapping("/product/search")
    public String searchProduct(Model model, @RequestParam(value = "keyword", required = false) Optional<String> kw,
            @RequestParam(value = "page", required = false, defaultValue = "1") int pageNumber) {
        List<Product> products = new ArrayList<>();
        try {
            String keyword = kw.orElse(session.getAttribute("keyword"));
            session.setAttribute("keyword", keyword);
            Page<Product> pageProducts = productService.searchByKeyword(PAGE_SIZE, pageNumber, keyword);
            products = pageProducts.getContent();
            model.addAttribute("pageTotalPages", pageProducts.getTotalPages());
            model.addAttribute("pageNumber", pageNumber);
            model.addAttribute("keyword", keyword);
            model.addAttribute("load", productService.load());
            model.addAttribute("loadsize", productService.loadsize());
            model.addAttribute("title", "Mặc định");
        } catch (Exception e) {
            e.printStackTrace();
            products = productService.findAll();
        }
        model.addAttribute("list", products);
        return "product/list";
    }

    @GetMapping("/product/finbybrand/{name}")
    public String searchProductbybrand(Model model, @PathVariable(value = "name", required = false) Optional<String> n,
            @RequestParam(value = "page", required = false, defaultValue = "1") int pageNumber) {
        List<Product> products = new ArrayList<>();
        try {
            String name = n.orElse(session.getAttribute("name"));
            session.setAttribute("name", name);
            Page<Product> pageProducts = productService.findByBrand(PAGE_SIZE, pageNumber, name);
            products = pageProducts.getContent();
            model.addAttribute("pageTotalPages", pageProducts.getTotalPages());
            model.addAttribute("pageNumber", pageNumber);
            model.addAttribute("name", name);
            model.addAttribute("count", productService.count());
            model.addAttribute("load", productService.load());
            model.addAttribute("loadsize", productService.loadsize());
            model.addAttribute("title", "Mặc định");
        } catch (Exception e) {
            e.printStackTrace();
            products = productService.findAll();
        }
        model.addAttribute("list", products);
        return "product/list";
    }

    @GetMapping("/product/finbysize/{size}")
    public String searchProductbySize(Model model, @PathVariable(value = "size", required = false) Optional<Integer> s,
            @RequestParam(value = "page", required = false, defaultValue = "1") int pageNumber) {
        List<Product> products = new ArrayList<>();
        try {
            Integer size = s.orElse(session.getAttribute("size"));
            session.setAttribute("size", size);
            Page<Product> pageProducts = productService.findBySize(PAGE_SIZE, pageNumber, size);
            products = pageProducts.getContent();
            model.addAttribute("pageTotalPages", pageProducts.getTotalPages());
            model.addAttribute("pageNumber", pageNumber);
            model.addAttribute("size", size);
            model.addAttribute("count", productService.count());
            model.addAttribute("load", productService.load());
            model.addAttribute("loadsize", productService.loadsize());
            model.addAttribute("title", "Mặc định");
        } catch (Exception e) {
            e.printStackTrace();
            products = productService.findAll();
        }
        model.addAttribute("list", products);
        return "product/list";
    }

    // sort
    @RequestMapping("product/listall/atoz")
    public String listAtoZ(Model model,
            @RequestParam(value = "page", required = false, defaultValue = "1") Optional<Integer> pageIndex) {
        try {

            Pageable pageable = PageRequest.of(pageIndex.orElse(0), 12);
            Page<Product> page = productService.sortAtoZ(pageable);

            model.addAttribute("pageNumber", page.getNumber());
            model.addAttribute("pageTotalPages", page.getTotalPages());
            model.addAttribute("list", page.getContent());
            model.addAttribute("count", productService.count());
            model.addAttribute("load", productService.load());
            model.addAttribute("loadsize", productService.loadsize());
            model.addAttribute("title", "A - Z");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "product/list";

    }

    @RequestMapping("product/listall/ztoa")
    public String listZtoA(Model model,
            @RequestParam(value = "page", required = false, defaultValue = "1") Optional<Integer> pageIndex) {
        try {

            Pageable pageable = PageRequest.of(pageIndex.orElse(0), 12);
            Page<Product> page = productService.sortZtoA(pageable);

            model.addAttribute("pageNumber", page.getNumber());
            model.addAttribute("pageTotalPages", page.getTotalPages());
            model.addAttribute("list", page.getContent());
            model.addAttribute("count", productService.count());
            model.addAttribute("load", productService.load());
            model.addAttribute("loadsize", productService.loadsize());
            model.addAttribute("title", "Z - A");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "product/list";

    }

    @RequestMapping("product/listall/pricelowtohigh")
    public String listPricelow(Model model,
            @RequestParam(value = "page", required = false, defaultValue = "1") Optional<Integer> pageIndex) {
        try {

            Pageable pageable = PageRequest.of(pageIndex.orElse(0), 12);
            Page<Product> page = productService.sortPriceLow(pageable);

            model.addAttribute("pageNumber", page.getNumber());
            model.addAttribute("pageTotalPages", page.getTotalPages());
            model.addAttribute("list", page.getContent());
            model.addAttribute("count", productService.count());
            model.addAttribute("load", productService.load());
            model.addAttribute("loadsize", productService.loadsize());
            model.addAttribute("title", "Giá, Thấp - Cao");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "product/list";

    }

    @RequestMapping("product/listall/pricehightolow")
    public String listPricehigh(Model model,
            @RequestParam(value = "page", required = false, defaultValue = "1") Optional<Integer> pageIndex) {
        try {

            Pageable pageable = PageRequest.of(pageIndex.orElse(0), 12);
            Page<Product> page = productService.sortPriceHeight(pageable);

            model.addAttribute("pageNumber", page.getNumber());
            model.addAttribute("pageTotalPages", page.getTotalPages());
            model.addAttribute("list", page.getContent());
            model.addAttribute("count", productService.count());
            model.addAttribute("load", productService.load());
            model.addAttribute("loadsize", productService.loadsize());
            model.addAttribute("title", "Giá, Cao - Thấp");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "product/list";

    }

    @RequestMapping("product/listall/sortnew")
    public String listsortnew(Model model,
            @RequestParam(value = "page", required = false, defaultValue = "1") Optional<Integer> pageIndex) {
        try {

            Pageable pageable = PageRequest.of(pageIndex.orElse(0), 12);
            Page<Product> page = productService.sortNew(pageable);

            model.addAttribute("pageNumber", page.getNumber());
            model.addAttribute("pageTotalPages", page.getTotalPages());
            model.addAttribute("list", page.getContent());
            model.addAttribute("count", productService.count());
            model.addAttribute("load", productService.load());
            model.addAttribute("loadsize", productService.loadsize());
            model.addAttribute("title", "Sản phẩm mới");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "product/list";

    }

    @RequestMapping("product/listall/sortold")
    public String listold(Model model,
            @RequestParam(value = "page", required = false, defaultValue = "1") Optional<Integer> pageIndex) {
        try {

            Pageable pageable = PageRequest.of(pageIndex.orElse(0), 12);
            Page<Product> page = productService.findByCreateDate(pageable);

            model.addAttribute("pageNumber", page.getNumber());
            model.addAttribute("pageTotalPages", page.getTotalPages());
            model.addAttribute("list", page.getContent());
            model.addAttribute("count", productService.count());
            model.addAttribute("load", productService.load());
            model.addAttribute("loadsize", productService.loadsize());
            model.addAttribute("title", "Sản phẩm củ");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "product/list";

    }

    @RequestMapping("/wish/list")
    public String wish(Model model) {
        model.addAttribute("favorite", favoriteService.findAllwhere(request.getRemoteUser()));
        return "product/wish";
    }

    @RequestMapping("/review/add")
    public String add(Model model, @RequestParam("pid") Long id, @RequestParam("rating") Double rating,
            @RequestParam("comment") String comment) {
        try {
            User user = userService.findID(request.getRemoteUser());
            Product product = productService.finID(id);
            Date date = new Date();

            Review review = new Review();
            review.setComment(comment);
            review.setCreateDate(date);
            review.setProduct(product);
            review.setUser(user);
            review.setRating(rating);
            reviewService.save(review);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/product/list/" + id;
    }

    @RequestMapping("/product/voucher")
    public String voucher(Model model, @RequestParam("voucher_code") String voucher_code,
            @RequestParam("product_id") Long id) {
        try {
            Date date = new Date();
            if (StringUtils.hasText(voucher_code)) {
                if (voucherService.find(id, date) == null) {
                    session.setAttribute("giamgia", 0);
                    System.out.println("khong thay");
                } else {
                    Voucher voucher = voucherService.find(id, date);
                    if (voucher.getVoucherCode().equalsIgnoreCase(voucher_code)) {
                        voucher.setMaxUser(voucher.getMaxUser() - 1);
                        voucherService.save(voucher);

                        session.setAttribute("giamgia", voucher.getDiscountAmount());
                        System.out.println("giamgia: "+ voucher.getDiscountAmount());
                    } else {
                        session.setAttribute("giamgia", 0);
                        model.addAttribute("message", "Voucher không hợp lệ !!!");
                        System.out.println("Voucher không hợp lệ !!!");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "cart/view";
    }
}
