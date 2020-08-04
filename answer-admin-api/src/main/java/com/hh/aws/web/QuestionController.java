package com.hh.aws.web;

import com.hh.aws.bean.RequestCheckResult;
import com.hh.aws.bean.ResponseData;
import com.hh.aws.model.PageData;
import com.hh.aws.model.Question;
import com.hh.aws.repository.QuestionRepository;
import com.hh.aws.service.QuestionService;
import com.hh.aws.service.RequestService;
import com.hh.aws.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 题目
 */
@RequestMapping("/admin/qus")
public class QuestionController extends  BaseController{

    @Resource
    RequestService requestService;
    @Resource
    private QuestionService questionService;
    @Autowired
    private QuestionRepository questionRepository;

    @RequestMapping("/list")
    public  @ResponseBody ResponseEntity<String> list(HttpServletRequest request, HttpServletResponse response) {
        String []keys={"password"};
        ResponseData responseData = new ResponseData();
        RequestCheckResult checkRes = requestService.checkRequest(request);

        if(checkRes.isSuccess()) {
            Map<String, String> params = checkRes.getRequestParams();
            String title = params.getOrDefault("title", "");
            String level = params.getOrDefault("level", "");
            int page = Integer.parseInt(params.getOrDefault("page", "1"));
            int pageSize = Integer.parseInt(params.getOrDefault("pageSize", "10"));
            String sortName = params.getOrDefault("sortName", "createTime");
            String sortType = params.getOrDefault("sortType", "desc");
            // 查询参数
            Map<String, String> param = new HashMap<String, String>();
            param.put("title", title);
            param.put("level", level);

            PageData<Question> dataPage = questionService.getQuestionList(page, pageSize, param, sortName, sortType);
            responseData.setData(dataPage);
            responseData.setMsg("保存成功");
        } else {
            responseData.setCode(checkRes.getCode());
            responseData.setMsg(checkRes.getMsg());
        }

        return ResponseUtil.responseEntity(responseData,keys);
    }
}
