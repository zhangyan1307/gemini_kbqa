package com.gemini.admin.controller;

import com.gemini.admin.busiEnum.ArticleTypeEnum;
import com.gemini.admin.request.CustomerKbContentAddRequest;
import com.gemini.admin.request.CustomerKbContentPageQueryRequest;
import com.gemini.admin.response.CommonPageResponse;
import com.gemini.admin.response.KbCustomerArticlePageQueryResponse;
import com.gemini.admin.response.Response;
import com.gemini.admin.service.CustomerKbService;
import com.mongodb.client.gridfs.model.GridFSFile;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * @Author: XXY
 * @Date: 2021/1/13 22:35
 */
@RestController
@Api(value = "客服知识库内容管理")
@AllArgsConstructor
@RequestMapping("/customerKb")
@Slf4j
public class CustomerKbContentController {

    //获得SpringBoot提供的mongodb的GridFS对象
    private final GridFsTemplate gridFsTemplate;

    private final CustomerKbService customerKbService;

    @PostMapping(value = "/file/upload")
    @ApiOperation("上传文档")
    public Response<Void> uploadFile(HttpServletRequest request){
        try {
            Part file = request.getPart("file");
            String fileId = null;
            if(file != null){
                // 获得提交的文件名
                String fileName = file.getSubmittedFileName();
                // 获得文件输入流
                InputStream ins = file.getInputStream();
                // 获得文件类型
                String contentType = file.getContentType();
                // 将文件存储到mongodb中,mongodb 将会返回这个文件的具体信息
                ObjectId gridFSFile = gridFsTemplate.store(ins, fileName, contentType);
                fileId = gridFSFile.toHexString();
            }

            CustomerKbContentAddRequest contentAddRequest = new CustomerKbContentAddRequest();
            Integer articleCategoryId = Integer.parseInt(request.getParameter("articleCategoryId"));
            String articleTitle = request.getParameter("articleTitle");
            String articleName = request.getParameter("articleName");
            String articleDesc = request.getParameter("articleDesc");
            contentAddRequest.setArticleCategoryId(articleCategoryId);
            contentAddRequest.setArticleContent(fileId);
            contentAddRequest.setArticleDesc(articleDesc);
            contentAddRequest.setArticleName(articleName);
            contentAddRequest.setArticleTitle(articleTitle);
            contentAddRequest.setArticleType(ArticleTypeEnum.FILE.getType());
            customerKbService.addCustomerKbContent(contentAddRequest);
        }catch (Exception e){
            log.error("添加客服内部知识失败");
            Response.fail();
        }
        return Response.ok();
    }

    @GetMapping("/queryCustomerContentByCategoryId")
    @ApiOperation("通过分类查询知识文档")
    public Response<CommonPageResponse<KbCustomerArticlePageQueryResponse>> queryCustomerContentListByCategoryId(CustomerKbContentPageQueryRequest request){
       return Response.ok(customerKbService.queryCustomerKbContentByCategoryId(request));
    }
}
