package com.qingcheng.controller.order;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qingcheng.pojo.order.CategoryReport;
import com.qingcheng.service.order.CategoryReportService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/categoryReport")
public class CategoryReportController {

    @Reference
    private CategoryReportService categoryReportService;


    /**
     * 昨天数据统计
     * @return
     */
    @RequestMapping("/yesterday")
    public List<CategoryReport> yesterday() {
        //得到昨天日期
        LocalDate localDate = LocalDate.now().minusDays(1);
        return categoryReportService.categoryReport(localDate);
    }


}