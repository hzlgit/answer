package com.hh.aws.service.impl;

import com.hh.aws.model.PageData;
import com.hh.aws.model.Question;
import com.hh.aws.repository.QuestionRepository;
import com.hh.aws.service.QuestionService;
import com.hh.aws.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    /**
     * 分页查找
     * @param page
     * @param size
     * @param param
     * @param sortName
     * @param sortType
     * @return
     */
    @Override
    public PageData<Question> getQuestionList(Integer page, Integer size, Map<String, String> param, String sortName, String sortType) {
        PageData<Question> dataPage = new PageData<Question>();
        String title = param.get("title");// 题目
        String level = param.get("level"); // 难度

        // 排序
        Sort sort = new Sort(Sort.Direction.ASC, "createTime");
        if(!"".equals(sortName)){
            Sort.Direction direction = "DESC".equalsIgnoreCase(sortType)?Sort.Direction.DESC:Sort.Direction.ASC;
            sort = new Sort(direction, sortName);;
        }

        Page<Question> pageData = questionRepository.findAll(new Specification<Question>() {
            @Override
            public Predicate toPredicate(Root<Question> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<Predicate>();
                if(StringUtil.isNotEmpty(level)){
                    predicates.add(cb.equal(root.get("level"),level));
                }
                if(StringUtil.isNotEmpty(title)){
                    predicates.add(cb.like(root.get("title"),"%"+title+"%"));
                }
                // 设置查询条件
                if (predicates.size() > 0) {
                    query.where(predicates.toArray(new Predicate[predicates.size()]));
                }
                return null;
            }
        }, PageRequest.of(page - 1, size, sort));

        dataPage.setList(pageData.getContent());
        dataPage.setTotalPage(pageData.getTotalPages());
        dataPage.setCurrentPage(page);
        return dataPage;
    }

    @Override
    public Question findQuestionById(long id) {
        return null;
    }

    @Override
    public void save(Question user) {

    }
}
