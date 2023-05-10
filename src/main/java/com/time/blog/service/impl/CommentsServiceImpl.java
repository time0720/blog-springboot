package com.time.blog.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.maxmind.geoip2.DatabaseReader;
import com.time.blog.domain.entity.Comments;
import com.time.blog.mapper.CommentsMapper;
import com.time.blog.service.CommentsService;
import com.time.blog.utils.IpUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author mjw
 * @date 2023/5/9
 */
@Slf4j
@Service
public class CommentsServiceImpl implements CommentsService {

    private final CommentsMapper commentsMapper;

    @Autowired
    public CommentsServiceImpl(CommentsMapper commentsMapper) {
        this.commentsMapper = commentsMapper;
    }

    @Override
    public PageInfo<Comments> selectCommentsByCondition(int pageNum, int pageSize, Long articleId) {
        PageHelper.startPage(pageNum, pageSize);
        List<Comments> commentsList = commentsMapper.selectCommentsByCondition(articleId);
        PageInfo<Comments> pageInfo = new PageInfo<>(commentsList);
        return pageInfo;
    }

    @Override
    public void addComments(Comments comments, HttpServletRequest request) throws Exception {
        //获取本机IP
        String path = "/home/local/blog/blog-springboot/GeoLite2-City.mmdb";
        // 创建 GeoLite2 数据库
        File database = new File(path);
        // 读取数据库内容
        DatabaseReader reader = new DatabaseReader.Builder(database).build();
        // 访问IP
        String ip = IpUtils.getIP(request);
        comments.setIp(ip);
        String address = "";
        String site = IpUtils.getCountry(reader, ip) + "-" + IpUtils.getProvince(reader, ip) + "-" + IpUtils.getCity(reader, ip);
        if ("null".equals(site)) {
            address = "地球";
        } else {
            int index = site.lastIndexOf("-");
            address = site.substring(index + 1) + "市";
        }
        comments.setAddress(address);
        comments.setCreationDate(new Date());
        comments.setUpvote("0");
        //TODO：根据用户ID设置头像
        if (Objects.equals(comments.getCommentsName(), "时光")) {
            comments.setVia("https://time7.top:9000/blog/web_head.jpg");
        } else {
            comments.setVia("https://time7.top:9000/blog/tourists.jpg");
        }
        log.info("当前的评论信息为：{}", comments);
        commentsMapper.addComments(comments);
    }

}
