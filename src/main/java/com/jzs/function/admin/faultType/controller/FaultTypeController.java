package com.jzs.function.admin.faultType.controller;

import com.jzs.arc.utils.ConstantFields;
import com.jzs.function.admin.faultType.FaultType;
import com.jzs.function.admin.faultType.service.FaultTypeServiceI;
import com.jzs.function.admin.login.AdminUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("admin/faultType")
public class FaultTypeController {
    private static final Logger LOG = LoggerFactory.getLogger(FaultTypeController.class);

    @Autowired
    private FaultTypeServiceI faultTypeService;

    @RequestMapping(value = "routeList",method = RequestMethod.GET)
    public ModelAndView routeList() {

        ModelAndView mav = new ModelAndView("admin/faultType/list");
        List<FaultType> list = faultTypeService.list();
        mav.addObject(ConstantFields.PAGE_KEY, list);

        return mav;
    }

    @RequestMapping(value = "routeAdd",method = RequestMethod.GET)
    public String routeAdd() {

        return "admin/faultType/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String Add(FaultType faultType, HttpSession session, RedirectAttributes redirectAttributes) {
        AdminUser user = (AdminUser) session.getAttribute(ConstantFields.SESSION_ADMIN_KEY);
        String logUser = user.getUserName();

        if (faultTypeService.add(faultType, logUser)) {
            if (LOG.isInfoEnabled())
                LOG.info("[MANAGE] [OK] {} add new faultType {}.", logUser, faultType.getFaultTypeName());

            redirectAttributes.addFlashAttribute(ConstantFields.ADD_SUCCESS_KEY, ConstantFields.ADD_SUCCESS_MESSAGE);
            return "redirect:/admin/faultType/routeList.action";
        }

        redirectAttributes.addFlashAttribute(ConstantFields.ADD_FAILURE_KEY, ConstantFields.ADD_FAILURE_MESSAGE);
        return "redirect:/admin/faultType/routeAdd" + faultType.getFaultTypeId() + ".action";
    }

    @RequestMapping(value = "/routeEdit/{faultTypeId}",method = RequestMethod.GET)
    public ModelAndView routeEdit(@PathVariable("faultTypeId") int faultTypeId) {

        ModelAndView mav = new ModelAndView("admin/faultType/edit");
        mav.addObject("faultType", faultTypeService.select(faultTypeId));

        return mav;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String edit(FaultType faultType, HttpSession session, RedirectAttributes redirectAttributes) {
        AdminUser user = (AdminUser) session.getAttribute(ConstantFields.SESSION_ADMIN_KEY);
        String logUser = user.getUserName();

        if (faultTypeService.edit(faultType, logUser)) {
            if (LOG.isInfoEnabled())
                LOG.info("[MANAGE] [OK] {} edit faultType {}.", logUser, faultType.getFaultTypeName());

            redirectAttributes.addFlashAttribute(ConstantFields.EDIT_SUCCESS_KEY, ConstantFields.EDIT_SUCCESS_MESSAGE);
            return "redirect:/admin/faultType/routeList.action";
        }

        redirectAttributes.addFlashAttribute(ConstantFields.EDIT_FAILURE_KEY, ConstantFields.EDIT_FAILURE_MESSAGE);
        return "redirect:/admin/faultType/routeEdit" + faultType.getFaultTypeId() + ".action";
    }

    @RequestMapping(value = "/delete/{faultTypeId}", method = RequestMethod.GET)
    public String cancle(@PathVariable("faultTypeId") int faultTypeId, HttpSession session, RedirectAttributes redirectAttributes) {
        AdminUser user = (AdminUser) session.getAttribute(ConstantFields.SESSION_ADMIN_KEY);
        String logUser = user.getUserName();

        if (faultTypeService.delete(faultTypeId, logUser)) {
            if (LOG.isInfoEnabled())
                LOG.info("[MANAGE] [OK] {} delete faultTypeId {}.", logUser, faultTypeId);

            redirectAttributes.addFlashAttribute(ConstantFields.DELETE_SUCCESS_KEY, ConstantFields.DELETE_SUCCESS_MESSAGE);
            return "redirect:/admin/faultType/routeList.action";
        }

        redirectAttributes.addFlashAttribute(ConstantFields.DELETE_FAILURE_KEY, ConstantFields.DELETE_FAILURE_MESSAGE);
        return "redirect:/admin/faultType/routeList.action";
    }

    @ResponseBody
    @RequestMapping(value = "/selectByFaultTypeId/{faultTypeId}", method = RequestMethod.POST)
    public Map<String,FaultType> selectByFaultTypeId(@PathVariable("faultTypeId") int faultTypeId) {
        FaultType jsonByFauTyId = faultTypeService.select(faultTypeId);
        Map<String,FaultType> map = new HashMap<>();
        map.put("jsonByFauTyId", jsonByFauTyId);

        return map;
    }
}
