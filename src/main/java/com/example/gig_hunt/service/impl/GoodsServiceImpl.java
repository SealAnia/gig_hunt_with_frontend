package com.example.gig_hunt.service.impl;

import com.example.gig_hunt.model.entity.Goods;
import com.example.gig_hunt.model.repository.GoodsRepository;
import com.example.gig_hunt.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;
import java.util.Set;

@Service
public class GoodsServiceImpl implements GoodsService {

    private final GoodsRepository goodsRepository;

    @Autowired
    public GoodsServiceImpl(GoodsRepository goodsRepository) {
        this.goodsRepository = goodsRepository;
    }

    @Override
    public List<Goods> getAll() {
        //return goodsRepository.findAll();

        List<Goods> goods = goodsRepository.findAll();
        for(Goods g : goods) {
            //System.out.println(g.getImage());
            if(g.getImage() != null) {
                byte[] imageBytes = g.getImage();
                String base64String = Base64.getEncoder().encodeToString(imageBytes);
                byte[] decodedBytes = Base64.getDecoder().decode(base64String);
            }
        }
        return goods;
    }

    @Override
    public Goods readById(Long goodsId) {
        //return goodsRepository.findById(id).get();
        Goods goods = goodsRepository.findById(goodsId).get();
        if(goods.getImage() != null) {
            byte[] imageBytes = goods.getImage();
            String base64String = Base64.getEncoder().encodeToString(imageBytes);
            byte[] decodedBytes = Base64.getDecoder().decode(base64String);
        }
        return goods;
    }

    @Override
    public Goods createOrUpdate(Goods goods) {

        MultipartFile imageFile = new MultipartFile() {
            @Override
            public String getName() {
                return null;
            }

            @Override
            public String getOriginalFilename() {
                return null;
            }

            @Override
            public String getContentType() {
                return null;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public long getSize() {
                return 0;
            }

            @Override
            public byte[] getBytes() throws IOException {
                return new byte[0];
            }

            @Override
            public InputStream getInputStream() throws IOException {
                return null;
            }

            @Override
            public void transferTo(File dest) throws IOException, IllegalStateException {

            }
        };

        if (imageFile != null && !imageFile.isEmpty()) {
            try {
                goods.setImage(imageFile.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return goodsRepository.saveAndFlush(goods);
    }

    @Override
    public void delete(Long id) {
        goodsRepository.deleteById(id);
    }

    @Override
    public Set<Goods> findItemsFromMaster(Long userId) {
        return goodsRepository.findItemsFromMaster(userId);
    }

}
