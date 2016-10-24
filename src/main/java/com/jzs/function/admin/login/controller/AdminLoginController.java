package com.jzs.function.admin.login.controller;

import com.google.common.base.Optional;
import com.jzs.arc.utils.ConstantFields;
import com.jzs.arc.utils.PasswordUtils;
import com.jzs.function.admin.authority.Authority;
import com.jzs.function.admin.authority.service.AuthorityServiceI;
import com.jzs.function.admin.login.AdminUser;
import com.jzs.function.admin.login.service.AdminLoginServiceI;
import com.jzs.function.admin.maintain.service.MaintainServiceI;
import com.jzs.function.admin.role.Role;
import com.jzs.function.admin.role.service.RoleServiceI;
import com.jzs.function.admin.seat.service.SeatServiceI;
import com.jzs.function.admin.workerInfo.service.WorkerInfoServiceI;
import com.jzs.function.support.utils.MailUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminLoginController {
    private static final Logger LOG = LoggerFactory.getLogger(AdminLoginController.class);

    @Autowired
    private AdminLoginServiceI adminLoginService;
    @Autowired
    private AuthorityServiceI authorityService;
    @Autowired
    private RoleServiceI roleService;
    @Autowired
    private WorkerInfoServiceI workerInfoService;
    @Autowired
    private SeatServiceI seatService;
    @Autowired
    private MaintainServiceI maintainService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(AdminUser adminUser, HttpSession session) {
        AdminUser loginUser = adminLoginService.login(adminUser);
        ModelAndView mav = new ModelAndView();

        Optional<AdminUser> optional = Optional.fromNullable(loginUser);
        if (optional.isPresent()) {
            Role role = new Role();
            role.setRoleId(loginUser.getRoleId());
            if (roleService.authorityTwoIsExit(role)) {
                if (workerInfoService.selectCount()) {
                    mav.setViewName("redirect:/admin/workerInfo/routeAdd.action");
                } else {
                    mav.setViewName("redirect:/admin/home/index.action");
                }
            }else {
                mav.setViewName("redirect:/admin/home/index.action");
            }
            session.setAttribute("places",seatService.selectPlaces());
            session.setAttribute("tracks",seatService.selectTracks());
            session.setAttribute("regions",seatService.selectRegions());
            session.setAttribute(ConstantFields.SESSION_ADMIN_KEY, loginUser);
            Authority authority = new Authority();
            authority.setRoleId(loginUser.getRoleId());
            session.setAttribute(ConstantFields.SESSION_AUTHORITY_KEY, authorityService.list(authority));
            session.setAttribute(ConstantFields.SESSION_FUNCTION_KEY,authorityService.listFunction(authority));
            if (LOG.isInfoEnabled())
                LOG.info("[LGB MANAGE] {} login system at {} .", loginUser.getUserLoginName(), DateTime.now());
        } else {
            mav.setViewName("redirect:/admin/routeLogin.action");
        }

        return mav;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginGet() {

        return "redirect:/admin/home/index.action";
    }

    @RequestMapping(value = "/routeLogin", method = RequestMethod.GET)
    public String routeLogin() {
        return "admin/login/adminLogin";
    }

    @RequestMapping(value = "/routePass", method = RequestMethod.GET)
    public String routePassword() {
        return "admin/login/adminPassword";
    }

    @RequestMapping(value = "/password", method = RequestMethod.POST)
    public ModelAndView password(AdminUser adminUser, HttpSession session) {
        ModelAndView mav = new ModelAndView("redirect:/admin/routeLogin.action");
        AdminUser user = (AdminUser) session.getAttribute(ConstantFields.SESSION_ADMIN_KEY);
        String logUser = user.getUserName();
        AdminUser newUser = adminLoginService.resetPassword(adminUser,logUser);
        Optional<AdminUser> optional = Optional.fromNullable(newUser);

        if (optional.isPresent()) {
            session.removeAttribute(ConstantFields.SESSION_ADMIN_KEY);
            if (LOG.isInfoEnabled())
                LOG.info("[LGB MANAGE] {} reset password.", newUser.getUserName());

            return mav;
        }

        return new ModelAndView("redirect:/admin/routePass.action");
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session,RedirectAttributes redirectAttributes) {
//        if (maintainService.isExitInspection()) {
//            redirectAttributes.addFlashAttribute(ConstantFields.EXIT_INSPECTION_KEY, ConstantFields.EXIT_INSPECTION_MESSAGE);
//
//            return "redirect:/admin/home/index.action";
//        } else {
            workerInfoService.temDelete();
            session.removeAttribute(ConstantFields.SESSION_ADMIN_KEY);

            return "redirect:/admin/routeLogin.action";
//        }
    }
}
