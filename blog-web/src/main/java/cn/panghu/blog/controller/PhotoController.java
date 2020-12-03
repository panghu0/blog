package cn.panghu.blog.controller;

import cn.panghu.blog.base.pojo.Photo;
import cn.panghu.blog.service.PhotoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author pang hu
 * @date 2020/10/24 12:55
 */
@Api(value = "图片管理", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@RestController
@RequestMapping("/photo")
public class PhotoController {

    @Autowired
    private PhotoService photoService;

    @RequestMapping(value = "/list/{type}", method = RequestMethod.POST)
    @ApiOperation(notes="查询图片",value="查询图片",httpMethod="POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "图片类型", required = true, paramType = "path", dataType = "Integer"),
            @ApiImplicitParam(name = "photo", value = "图片对象", required = true, paramType = "body", dataType = "Photo")
    })
    public List<Photo> list(@PathVariable("type") Integer type, @RequestBody Photo photo) {

        return photoService.list(type,photo);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ApiOperation(notes="添加图片",value="添加图片",httpMethod="POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "file", value = "图片对象", required = true, paramType = "query", dataType = "MultipartFile"),
            @ApiImplicitParam(name = "type", value = "图片类型", required = true, paramType = "query", dataType = "Integer")
    })
    public Photo add(@RequestParam("file") MultipartFile file, @RequestParam("type") Integer type) {

        return photoService.save(file, type);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ApiOperation(notes="删除图片",value="删除图片",httpMethod="DELETE")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "图片id", required = true, paramType = "path", dataType = "Integer")
    })
    public void del(@PathVariable("id") Integer id) {

        photoService.deleteById(id);
    }

    @RequestMapping(value = "/batchDel", method = RequestMethod.DELETE)
    @ApiOperation(notes="批量删除图片",value="批量删除图片",httpMethod="DELETE")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "idList", value = "图片id列表", required = true, paramType = "body", dataType = "List<Integer>")
    })
    public void batchDel(@RequestBody List<Integer> idList) {

        photoService.batchDel(idList);
    }

}
