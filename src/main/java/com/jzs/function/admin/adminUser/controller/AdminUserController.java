package com.jzs.function.admin.adminUser.controller;

import com.google.common.base.Optional;
import com.jzs.arc.exception.BatchRollbackException;
import com.jzs.arc.utils.ConstantFields;
import com.jzs.function.admin.adminUser.AdminManager;
import com.jzs.function.admin.adminUser.service.AdminUserServiceI;
import com.jzs.function.admin.login.AdminUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("admin/adminManager")
public class AdminUserController {

    private static final Logger LOG = LoggerFactory.getLogger(AdminUserController.class);

    @Autowired
    private AdminUserServiceI adminUserService;

    @RequestMapping(value = "/resetPass", method = RequestMethod.POST)
    public String resetPass(AdminManager adminManager, HttpSession session, RedirectAttributes redirectAttributes){
        AdminUser user = (AdminUser) session.getAttribute(ConstantFields.SESSION_ADMIN_KEY);
        String logUser = user.getUserName();

        if (adminUserService.resetPass(adminManager, logUser)) {
            if (LOG.isInfoEnabled())
                LOG.info("[MANAGE] [OK] {} resetPass adminManager {}.", logUser, adminManager.getUserName());

            redirectAttributes.addFlashAttribute(ConstantFields.OPREATE_SUCCESS_KEY, ConstantFields.OPREATE_SUCCESS_MESSAGE);

            return "redirect:/admin/adminManager/routeList.action";
        }

        redirectAttributes.addFlashAttribute(ConstantFields.OPREATE_FAILURE_KEY, ConstantFields.OPREATE_FAILURE_MESSAGE);
        return "redirect:/admin/adminManager/routeEdit/" + adminManager.getUserId() + ".action";
    }

    @RequestMapping(value = "routeList",method = RequestMethod.GET)
    public ModelAndView routeList(Pageable pageable) {

        ModelAndView mav = new ModelAndView("admin/adminManager/list");
        Page<AdminManager> page = adminUserService.list(pageable);
        mav.addObject(ConstantFields.PAGE_KEY, page);

        return mav;
    }

    @RequestMapping(value = "routeAdd",method = RequestMethod.GET)
    public ModelAndView routeAdd() {
        ModelAndView mav = new ModelAndView("admin/adminManager/add");
        List<AdminManager> roles = adminUserService.selectRoles();
        mav.addObject("roles",roles);

        return mav;

    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String Add(AdminManager adminManager, HttpSession session, RedirectAttributes redirectAttributes) throws BatchRollbackException {
        AdminUser user = (AdminUser) session.getAttribute(ConstantFields.SESSION_ADMIN_KEY);
        String logUser = user.getUserName();

        if(adminUserService.nameIsUnique(adminManager.getUserLoginName())) {
            if (adminUserService.add(adminManager, logUser)) {
                if (LOG.isInfoEnabled())
                    LOG.info("[MANAGE] [OK] {} add new adminManager {}.", logUser, adminManager.getUserName());

                redirectAttributes.addFlashAttribute(ConstantFields.ADD_SUCCESS_KEY, ConstantFields.ADD_SUCCESS_MESSAGE);
                return "redirect:/admin/adminManager/routeList.action";
            }

            redirectAttributes.addFlashAttribute(ConstantFields.ADD_FAILURE_KEY, ConstantFields.ADD_FAILURE_MESSAGE);
            return "redirect:/admin/adminManager/routeAdd.action";
        }else {
            redirectAttributes.addFlashAttribute(ConstantFields.ADD_NOTUNIQUE_KEY, ConstantFields.ADD_NOTUNIQUE_MESSAGE);
            return "redirect:/admin/adminManager/routeAdd.action";
        }
    }

    @RequestMapping(value = "/routeEdit/{userId}", method = RequestMethod.GET)
    public ModelAndView routeEdit(@PathVariable("userId") int userId) {
        AdminManager adminManager = adminUserService.select(userId);
        ModelAndView mav = new ModelAndView("admin/adminManager/edit");
        mav.addObject("adminManager", adminManager);
        List<AdminManager> roles = adminUserService.selectRoles();
        mav.addObject("roles",roles);

        return mav;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String edit(AdminManager adminManager, HttpSession session, RedirectAttributes redirectAttributes) throws BatchRollbackException {
        AdminUser user = (AdminUser) session.getAttribute(ConstantFields.SESSION_ADMIN_KEY);
        String logUser = user.getUserName();

        if (adminUserService.update(adminManager, logUser)) {
            if (LOG.isInfoEnabled())
                LOG.info("[MANAGE] [OK] {} edit adminManager {}.", logUser, adminManager.getUserName());

            redirectAttributes.addFlashAttribute(ConstantFields.EDIT_SUCCESS_KEY, ConstantFields.EDIT_SUCCESS_MESSAGE);

            return "redirect:/admin/adminManager/routeList.action";
        }

        redirectAttributes.addFlashAttribute(ConstantFields.EDIT_FAILURE_KEY, ConstantFields.EDIT_FAILURE_MESSAGE);
        return "redirect:/admin/adminManager/routeEdit/" + adminManager.getUserId() + ".action";
    }

    @RequestMapping(value = "/delete/{userId}", method = RequestMethod.GET)
    public String delete(@PathVariable("userId") int userId, HttpSession session, RedirectAttributes redirectAttributes) throws BatchRollbackException {
        AdminUser user = (AdminUser) session.getAttribute(ConstantFields.SESSION_ADMIN_KEY);
        String logUser = user.getUserName();

        if (adminUserService.delete(userId, logUser)) {
            if (LOG.isInfoEnabled())
                LOG.info("[MANAGE] [OK] {} delete adminManager's ID {}.", logUser, userId);

            redirectAttributes.addFlashAttribute(ConstantFields.DELETE_SUCCESS_KEY, ConstantFields.DELETE_SUCCESS_MESSAGE);

            return "redirect:/admin/adminManager/routeList.action";
        }

        redirectAttributes.addFlashAttribute(ConstantFields.DELETE_FAILURE_KEY, ConstantFields.DELETE_FAILURE_MESSAGE);

        return "redirect:/admin/adminManager/routeList.action";
    }

    @RequestMapping(value = "/reuse/{userId}", method = RequestMethod.GET)
    public String reuse(@PathVariable("userId") int userId, HttpSession session, RedirectAttributes redirectAttributes) throws BatchRollbackException {
        AdminUser user = (AdminUser) session.getAttribute(ConstantFields.SESSION_ADMIN_KEY);
        String logUser = user.getUserName();

        if (adminUserService.reuse(userId, logUser)) {
            if (LOG.isInfoEnabled())
                LOG.info("[MANAGE] [OK] {} reuse adminManager's ID {}.", logUser, userId);

            redirectAttributes.addFlashAttribute(ConstantFields.OPREATE_SUCCESS_KEY, ConstantFields.OPREATE_SUCCESS_MESSAGE);

            return "redirect:/admin/adminManager/routeList.action";
        }

        redirectAttributes.addFlashAttribute(ConstantFields.OPREATE_FAILURE_KEY, ConstantFields.OPREATE_FAILURE_MESSAGE);

        return "redirect:/admin/adminManager/routeList.action";

    }

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public ModelAndView list(@PageableDefault(value = ConstantFields.DEFAULT_PAGE_SIZE) Pageable pageable) {
        Page<AdminManager> page = adminUserService.list(pageable);
        ModelAndView mav = new ModelAndView("admin/adminManager/list");
        mav.addObject(ConstantFields.PAGE_KEY, page);

        return mav;
    }
}
