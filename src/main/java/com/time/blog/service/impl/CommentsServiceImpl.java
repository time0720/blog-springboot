package com.time.blog.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.maxmind.geoip2.DatabaseReader;
import com.time.blog.domain.entity.Comments;
import com.time.blog.mapper.CommentsMapper;
import com.time.blog.service.CommentsService;
import com.time.blog.utils.IpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author mjw
 * @date 2023/5/9
 */
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
        String site = IpUtils.getCountry(reader, ip) + "-" + IpUtils.getProvince(reader, ip) + "-" + IpUtils.getCity(reader, ip);
        int index = site.lastIndexOf("-");
        String address = site.substring(index) + "市";
        comments.setAddress(address);
        comments.setCreationDate(new Date());
        comments.setUpvote("0");
        commentsMapper.addComments(comments);
    }

}