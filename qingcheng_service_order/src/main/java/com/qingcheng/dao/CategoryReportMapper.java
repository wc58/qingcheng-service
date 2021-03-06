package com.qingcheng.dao;

import com.qingcheng.pojo.order.CategoryReport;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.BaseMapper;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface CategoryReportMapper extends BaseMapper<CategoryReport> {

    @Select("SELECT\n" +
            "\ti.category_id1 categoryId1,\n" +
            "\ti.category_id2 categoryId2,\n" +
            "\ti.category_id3 categoryId3,\n" +
            "\tDATE_FORMAT(o.pay_time, '%Y-%m-%d') countDate,\n" +
            "\tSUM(i.num) num,\n" +
            "\tSUM(i.pay_money) money\n" +
            "FROM\n" +
            "\ttb_order o,\n" +
            "\ttb_order_item i\n" +
            "WHERE\n" +
            "\to.id = i.order_id\n" +
            "AND o.pay_status = '1'\n" +
            "AND o.is_delete = '0'\n" +
            "AND DATE_FORMAT(o.pay_time, '%Y-%m-%d') = #{date}\n" +
            "GROUP BY\n" +
            "\ti.category_id1,\n" +
            "\ti.category_id2,\n" +
            "\ti.category_id3,\n" +
            "\tDATE_FORMAT(o.pay_time, '%Y-%m-%d')")
    public List<CategoryReport> categoryReport(@Param("date") LocalDate date);

    @Select("SELECT category_id1 categoryId1, c.`name` name ,SUM(num) num,SUM(money) money\n" +
            "FROM tb_category_report r,tb_category1 c\n" +
            "WHERE r.category_id1 = c.id ANd count_date>=#{startDate} AND count_date<=#{endDate}\n" +
            "GROUP BY category_id1,c.`name`")
    public List<Map<String,String>> category1Count(@Param("startDate") String startDate, @Param("endDate") String endDate);
}
