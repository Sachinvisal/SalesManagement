package com.project.sales.Services.admin.faq;

import com.project.sales.Dto.FAQDto;

public interface FAQService {

    FAQDto postFAQ(Long productId, FAQDto faqDto);
}
