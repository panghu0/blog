package cn.panghu.blog.controller;

import cn.panghu.blog.base.pojo.Link;
import cn.panghu.blog.service.LinkService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author pang hu
 * @date 2020/5/17 11:07
 */
@Api(value = "导航接口", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@RestController
@RequestMapping("/link")
public class LinkController {

    @Autowired
    private LinkService linkService;

    @RequestMapping(value = "/getBlogNavLinks", method = RequestMethod.GET)
    public List<Link> getBlogNavLinks() {

        return linkService.getBlogNavLinks();
    }

    @RequestMapping(value = "/getAdminNavLinks", method = RequestMethod.GET)
    public List<Link> getAdminNavLinks() {

        return linkService.getAdminNavLinks();
    }
}
