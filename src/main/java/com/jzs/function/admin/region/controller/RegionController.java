package com.jzs.function.admin.region.controller;

import com.jzs.arc.utils.ConstantFields;
import com.jzs.function.admin.login.AdminUser;
import com.jzs.function.admin.region.Region;
import com.jzs.function.admin.region.service.RegionServiceI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("admin/region")
public class RegionController {

    private static final Logger LOG = LoggerFactory.getLogger(RegionController.class);

    @Autowired
    private RegionServiceI regionService;

    @RequestMapping(value = "routeList",method = RequestMethod.GET)
    public ModelAndView routeList() {

        ModelAndView mav = new ModelAndView("admin/region/list");
        List<Region> list = regionService.list();
        mav.addObject(ConstantFields.PAGE_KEY, list);

        return mav;
    }

    @RequestMapping(value = "routeAdd",method = RequestMethod.GET)
    public String routeAdd() {

        return "admin/region/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String Add(Region region, HttpSession session, RedirectAttributes redirectAttributes) {
        AdminUser user = (AdminUser) session.getAttribute(ConstantFields.SESSION_ADMIN_KEY);
        String logUser = user.getUserName();

        if (regionService.add(region, logUser)) {
            if (LOG.isInfoEnabled())
                LOG.info("[MANAGE] [OK] {} add new region {}.", logUser, region.getRegionName());

            redirectAttributes.addFlashAttribute(ConstantFields.ADD_SUCCESS_KEY, ConstantFields.ADD_SUCCESS_MESSAGE);
            return "redirect:/admin/region/routeList.action";
        }

        redirectAttributes.addFlashAttribute(ConstantFields.ADD_FAILURE_KEY, ConstantFields.ADD_FAILURE_MESSAGE);
        return "redirect:/admin/region/routeAdd" + region.getRegionId() + ".action";
    }

    @RequestMapping(value = "/routeEdit/{regionId}",method = RequestMethod.GET)
    public ModelAndView routeEdit(@PathVariable("regionId") int regionId) {

        ModelAndView mav = new ModelAndView("admin/region/edit");
        mav.addObject("region",regionService.select(regionId));

        return mav;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String edit(Region region, HttpSession session, RedirectAttributes redirectAttributes) {
        AdminUser user = (AdminUser) session.getAttribute(ConstantFields.SESSION_ADMIN_KEY);
        String logUser = user.getUserName();

        if (regionService.edit(region, logUser)) {
            if (LOG.isInfoEnabled())
                LOG.info("[MANAGE] [OK] {} edit region {}.", logUser, region.getRegionName());

            redirectAttributes.addFlashAttribute(ConstantFields.EDIT_SUCCESS_KEY, ConstantFields.EDIT_SUCCESS_MESSAGE);
            return "redirect:/admin/region/routeList.action";
        }

        redirectAttributes.addFlashAttribute(ConstantFields.EDIT_FAILURE_KEY, ConstantFields.EDIT_FAILURE_MESSAGE);
        return "redirect:/admin/region/routeEdit" + region.getRegionId() + ".action";
    }

    @RequestMapping(value = "/cancle/{regionId}", method = RequestMethod.GET)
    public String cancle(@PathVariable("regionId") int regionId, HttpSession session, RedirectAttributes redirectAttributes) {
        AdminUser user = (AdminUser) session.getAttribute(ConstantFields.SESSION_ADMIN_KEY);
        String logUser = user.getUserName();

        if (regionService.cancle(regionId, logUser)) {
            if (LOG.isInfoEnabled())
                LOG.info("[MANAGE] [OK] {} delete regionId {}.", logUser, regionId);

            redirectAttributes.addFlashAttribute(ConstantFields.DELETE_SUCCESS_KEY, ConstantFields.DELETE_SUCCESS_MESSAGE);
            return "redirect:/admin/region/routeList.action";
        }

        redirectAttributes.addFlashAttribute(ConstantFields.DELETE_FAILURE_KEY, ConstantFields.DELETE_FAILURE_MESSAGE);
        return "redirect:/admin/region/routeList.action";
    }
}
