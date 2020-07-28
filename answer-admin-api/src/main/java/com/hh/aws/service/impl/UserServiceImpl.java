package com.hh.aws.service.impl;

import com.hh.aws.model.PageData;
import com.hh.aws.model.User;
import com.hh.aws.repository.UserRepository;
import com.hh.aws.service.UserService;
import com.hh.aws.utils.SecurityUtils;
import com.hh.aws.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;


    @Transactional(readOnly = true)
    public Optional<User> getUserWithAuthorities() {
        return SecurityUtils.getCurrentUsername().flatMap(userRepository::findOneWithAuthoritiesByUserName);
    }

    /**
     * 分页查询
     * @param page
     * @param size
     * @param param
     * @param sortName
     * @param sortType
     * @return
     */
    @Override
    public PageData<User> getUserList(Integer page, Integer size, Map<String, String> param, String sortName, String sortType) {
        PageData<User> dataPage = new PageData<User>();
        String userName = param.get("userName");// 用户名
        String nickName = param.get("nickName"); // 昵称

        // 排序
        Sort sort = new Sort(Sort.Direction.ASC, "createTime");
        if(!"".equals(sortName)){
            Sort.Direction direction = "DESC".equalsIgnoreCase(sortType)?Sort.Direction.DESC:Sort.Direction.ASC;
            sort = new Sort(direction, sortName);;
        }

        Page<User> pageData = userRepository.findAll(new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<Predicate>();
                if(StringUtil.isNotEmpty(userName)){
                    predicates.add(cb.like(root.get("userName"),"%"+userName+"%"));
                }
                if(StringUtil.isNotEmpty(nickName)){
                    predicates.add(cb.like(root.get("userName"),"%"+nickName+"%"));
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
    public User findUserById(long id) {
        return null;
    }

    @Override
    public void save(User user) {
        if (user.getId() > 0) {
            User saveUser = userRepository.findById(user.getId()).get();
            if (!user.getUserName().isEmpty()) {
                saveUser.setUserName(user.getUserName());
            }
            if (!user.getAvatar().isEmpty()) {
                saveUser.setAvatar(user.getAvatar());
            }
            if (!user.getNickName().isEmpty()) {
                saveUser.setNickName(user.getNickName());
            }
            if (!user.getTrueName().isEmpty()) {
                saveUser.setTrueName(user.getTrueName());
            }
            if (!user.getSex().isEmpty()) {
                saveUser.setSex(user.getSex());
            }
            if (user.getClassId() > 0) {
                saveUser.setClassId(user.getClassId());
            }
            userRepository.save(saveUser);
        } else {
            userRepository.save(user);
        }

    }

    @Override
    public void edit(User user) {
        userRepository.save(user);
    }

    @Override
    public void delete(long id) {
        User user = new User();
        user.setId(id);
        userRepository.save(user);
    }

}
