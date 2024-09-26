package com.project.sales.Services.admin.faq;


import com.project.sales.Dto.FAQDto;
import com.project.sales.Entity.FAQ;
import com.project.sales.Entity.Product;
import com.project.sales.Repo.FAQRepo;
import com.project.sales.Repo.ProductRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FAQServiceImpl implements FAQService {

    private final FAQRepo faqRepo;

    private  final ProductRepo productRepo;

    public FAQDto postFAQ(Long productId, FAQDto faqDto){
        Optional<Product>optionalProduct = productRepo.findById(productId);
        if (optionalProduct.isPresent()){
            FAQ faq = new FAQ();
            faq.setQuestion(faqDto.getQuestion());
            faq.setAnswer(faqDto.getAnswer());
            faq.setProduct(optionalProduct.get());

            return faqRepo.save(faq).getFAQDto();
        }

        return  null;
    }



}
