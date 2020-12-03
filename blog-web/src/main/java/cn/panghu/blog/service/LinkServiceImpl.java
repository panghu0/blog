package cn.panghu.blog.service;

import cn.panghu.blog.base.dao.LinkDao;
import cn.panghu.blog.base.pojo.Link;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author pang hu
 * @date 2020/5/17 11:09
 */
@Service
public class LinkServiceImpl implements LinkService {

    @Autowired
    private LinkDao linkDao;

    public List<Link> getBlogNavLinks() {
        return linkDao.getBlogNavLinks();
    }

    public List<Link> getAdminNavLinks() {
        return linkDao.getAdminNavLinks();
    }
}
