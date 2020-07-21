package com.hh.aws.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

@Controller
public class BaseController {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());
}