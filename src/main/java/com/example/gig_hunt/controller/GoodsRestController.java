package com.example.gig_hunt.controller;

import com.example.gig_hunt.model.entity.Goods;
import com.example.gig_hunt.model.repository.UserRepository;
import com.example.gig_hunt.service.impl.GoodsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/goods")
public class GoodsRestController {

    private final GoodsServiceImpl goodsService;
    private final UserRepository userRepository;

    @Autowired
    public GoodsRestController(GoodsServiceImpl goodsService, UserRepository userRepository) {
        this.goodsService = goodsService;
        this.userRepository = userRepository;
    }

    @GetMapping(value = "")
    public List<Goods> getAllGoods() {
        return goodsService.getAll();
    }

    @GetMapping(value = "/{goodsId}")
    public Goods getGoodsById(@PathVariable Long goodsId) {
        return goodsService.readById(goodsId);
    }

    //FINDS ALL ITEMS FROM A CERTAIN MASTER
    @GetMapping(value = "/from_master")
    public List<Goods> findItemsFromMaster(@RequestParam(value = "master_id") Long userId) {
        return goodsService.findItemsFromMaster(userId);
    }

    @PostMapping(value = "/")
    public void createItem(@RequestBody Goods goods) {
        goodsService.createOrUpdate(goods);
    }

    @PostMapping(value = "/{userId}/add_to_basket")
    public ResponseEntity<Goods> editBasket(@PathVariable(value = "userId") Long userId, @RequestBody Goods goodsReq) {
        Optional<Object> goods = userRepository.findById(userId).map(customer -> {
            long goodsId = goodsReq.getGoodsId();
            if(goodsId != 0L) {
                Goods newGoods = goodsService.readById(goodsId);
                customer.addGoodsToBasket(newGoods);
                userRepository.save(customer);
                return newGoods;
            }
            customer.addGoodsToBasket(goodsReq);
            return goodsService.createOrUpdate(goodsReq);
        });
        return new ResponseEntity(goods, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{goodsId}")
    public ResponseEntity<Goods> updateGoods(@PathVariable Long goodsId, @RequestBody Goods goods) {
        var updatedGoods = goodsService.readById(goodsId);

        var master = goods.getMaster();
        updatedGoods.setMaster(master);

        updatedGoods.setPrice(goods.getPrice());
        updatedGoods.setDescription(goods.getDescription());
        goodsService.createOrUpdate(updatedGoods);
        return ResponseEntity.ok(updatedGoods);
    }

    @DeleteMapping(value = "/{goodsId}")
    public void deleteGoods(@PathVariable Long goodsId) {
        goodsService.delete(goodsId);
    }

}
