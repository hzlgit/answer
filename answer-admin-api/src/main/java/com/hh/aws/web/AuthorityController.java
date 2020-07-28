package com.hh.aws.web;

import com.hh.aws.bean.RequestCheckResult;
import com.hh.aws.model.Authority;
import com.hh.aws.model.ResponseData;
import com.hh.aws.repository.AuthorityRepository;
import com.hh.aws.service.RequestService;
import com.hh.aws.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@RequestMapping("/admin/authority")
public class AuthorityController extends BaseController {
    @Resource
    RequestService requestService;
    @Autowired
    private AuthorityRepository authorityRepository;

    /**
     * 列表
     * @return
     */
    @RequestMapping("/list")
    public @ResponseBody
    ResponseEntity<String> list() {
        List<Authority> authoritys = authorityRepository.findAll();
        ResponseData responseData = new ResponseData();
        responseData.setData(authoritys);
        return ResponseUtil.responseEntity(responseData);
    }

    /**
     * 添加/保存
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("/save")
    public @ResponseBody
    ResponseEntity<String> save(HttpServletRequest request, HttpServletResponse response) throws Exception{
        ResponseData responseData = new ResponseData();
        RequestCheckResult checkRes = requestService.checkRequest(request, "name");
        Authority authority = new Authority();
        if(checkRes.isSuccess()) {
            Map<String, String> params = checkRes.getRequestParams();
            String name = params.getOrDefault("name", "");
            authority = authorityRepository.findAuthorityByName(name);

            authorityRepository.save(authority);
            responseData.setMsg("保存成功");
        } else {
            responseData.setCode(checkRes.getCode());
            responseData.setMsg(checkRes.getMsg());
        }
        return ResponseUtil.responseEntity(responseData);
    }

    /**
     * 删除
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("/del")
    public @ResponseBody
    ResponseEntity<String> del(HttpServletRequest request, HttpServletResponse response) throws Exception{
        ResponseData responseData = new ResponseData();
        RequestCheckResult checkRes = requestService.checkRequest(request, "name");
        Authority authority = new Authority();
        if(checkRes.isSuccess()) {
            Map<String, String> params = checkRes.getRequestParams();
            String name = params.getOrDefault("name", "");
            authority = authorityRepository.findAuthorityByName(name);

            authorityRepository.delete(authority);
            responseData.setMsg("删除成功");
        } else {
            responseData.setCode(checkRes.getCode());
            responseData.setMsg(checkRes.getMsg());
        }
        return ResponseUtil.responseEntity(responseData);
    }

}
