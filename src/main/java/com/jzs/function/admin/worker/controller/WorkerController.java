package com.jzs.function.admin.worker.controller;

import com.google.common.base.Optional;
import com.jzs.arc.exception.BatchRollbackException;
import com.jzs.arc.utils.ConstantFields;
import com.jzs.function.admin.adminUser.AdminManager;
import com.jzs.function.admin.adminUser.service.AdminUserServiceI;
import com.jzs.function.admin.login.AdminUser;
import com.jzs.function.admin.worker.Worker;
import com.jzs.function.admin.worker.service.WorkerServiceI;
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
@RequestMapping("admin/worker")
public class WorkerController {

    private static final Logger LOG = LoggerFactory.getLogger(WorkerController.class);

    @Autowired
    private WorkerServiceI workerService;

    @Autowired
    private AdminUserServiceI adminUserService;

    @RequestMapping(value = "routeList",method = RequestMethod.GET)
    public ModelAndView routeList(Pageable pageable) {
        ModelAndView mav = new ModelAndView("admin/worker/list");
        Page<Worker> page = workerService.list(pageable);
        mav.addObject(ConstantFields.PAGE_KEY, page);

        return mav;
    }

    @RequestMapping(value = "routeAdd",method = RequestMethod.GET)
    public ModelAndView routeAdd() {
        ModelAndView mav = new ModelAndView("admin/worker/add");

        return mav;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String Add(Worker worker, HttpSession session, RedirectAttributes redirectAttributes) {
        AdminUser user = (AdminUser) session.getAttribute(ConstantFields.SESSION_ADMIN_KEY);
        String logUser = user.getUserName();

        if (workerService.add(worker, logUser)) {
            if (LOG.isInfoEnabled())
                LOG.info("[MANAGE] [OK] {} add new worker {}.", logUser, worker.getUserName());

            redirectAttributes.addFlashAttribute(ConstantFields.ADD_SUCCESS_KEY, ConstantFields.ADD_SUCCESS_MESSAGE);
            return "redirect:/admin/worker/routeList.action";
        }

        redirectAttributes.addFlashAttribute(ConstantFields.ADD_FAILURE_KEY, ConstantFields.ADD_FAILURE_MESSAGE);
        return "redirect:/admin/worker/routeAdd.action";
    }

    @RequestMapping(value = "/routeEdit/{userId}", method = RequestMethod.GET)
    public ModelAndView routeEdit(@PathVariable("userId") int userId) {
        Worker worker = workerService.select(userId);
        ModelAndView mav = new ModelAndView("admin/worker/edit");
        mav.addObject("worker", worker);

        return mav;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String edit(Worker worker, HttpSession session, RedirectAttributes redirectAttributes) {
        AdminUser user = (AdminUser) session.getAttribute(ConstantFields.SESSION_ADMIN_KEY);
        String logUser = user.getUserName();

        if (workerService.update(worker, logUser)) {
            if (LOG.isInfoEnabled())
                LOG.info("[MANAGE] [OK] {} edit worker {}.", logUser, worker.getUserName());

            redirectAttributes.addFlashAttribute(ConstantFields.EDIT_SUCCESS_KEY, ConstantFields.EDIT_SUCCESS_MESSAGE);

            return "redirect:/admin/worker/routeList.action";
        }

        redirectAttributes.addFlashAttribute(ConstantFields.EDIT_FAILURE_KEY, ConstantFields.EDIT_FAILURE_MESSAGE);
        return "redirect:/admin/worker/routeEdit/" + worker.getUserId() + ".action";
    }

    @RequestMapping(value = "/delete/{userId}", method = RequestMethod.GET)
    public String delete(@PathVariable("userId") int userId, HttpSession session, RedirectAttributes redirectAttributes) {
        AdminUser user = (AdminUser) session.getAttribute(ConstantFields.SESSION_ADMIN_KEY);
        String logUser = user.getUserName();

        if (workerService.delete(userId, logUser)) {
            if (LOG.isInfoEnabled())
                LOG.info("[MANAGE] [OK] {} delete worker's ID {}.", logUser, userId);

            redirectAttributes.addFlashAttribute(ConstantFields.DELETE_SUCCESS_KEY, ConstantFields.DELETE_SUCCESS_MESSAGE);

            return "redirect:/admin/worker/routeList.action";
        }

        redirectAttributes.addFlashAttribute(ConstantFields.DELETE_FAILURE_KEY, ConstantFields.DELETE_FAILURE_MESSAGE);

        return "redirect:/admin/worker/routeList.action";
    }

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public ModelAndView list(@PageableDefault(value = ConstantFields.DEFAULT_PAGE_SIZE) Pageable pageable,HttpSession session) {
        Page<Worker> page = workerService.list(pageable);
        ModelAndView mav = new ModelAndView("admin/Worker/list");
        mav.addObject(ConstantFields.PAGE_KEY, page);

        return mav;
    }

    @RequestMapping(value = "/routePromote/{userId}", method = RequestMethod.GET)
    public ModelAndView routePromote(@PathVariable("userId") int userId) {
        Worker worker = workerService.select(userId);
        ModelAndView mav = new ModelAndView("admin/worker/promote");
        mav.addObject("adminManager", worker);

        List<AdminManager> roles = adminUserService.selectRoles();
        mav.addObject("roles",roles);

        return mav;

    }

    @RequestMapping(value = "/promote", method = RequestMethod.POST)
    public String promote(AdminManager adminManager, HttpSession session, RedirectAttributes redirectAttributes) throws BatchRollbackException {
        AdminUser user = (AdminUser) session.getAttribute(ConstantFields.SESSION_ADMIN_KEY);
        String logUser = user.getUserName();

        if (adminUserService.promote(adminManager, logUser)) {
            if (LOG.isInfoEnabled())
                LOG.info("[MANAGE] [OK] {} promote worker {}.", logUser, adminManager.getUserName());

            redirectAttributes.addFlashAttribute(ConstantFields.PROMOTE_SUCCESS_KEY, ConstantFields.PROMOTE_SUCCESS_MESSAGE);

            return "redirect:/admin/worker/routeList.action";
        }

        redirectAttributes.addFlashAttribute(ConstantFields.PROMOTE_FAILURE_KEY, ConstantFields.PROMOTE_FAILURE_MESSAGE);
        return "redirect:/admin/worker/routePromote/" + adminManager.getUserId() + ".action";
    }
}
