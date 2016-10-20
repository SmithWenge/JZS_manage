package com.jzs.function.admin.place.controller;

import com.jzs.arc.utils.ConstantFields;
import com.jzs.function.admin.login.AdminUser;
import com.jzs.function.admin.place.Place;
import com.jzs.function.admin.place.service.PlaceService;
import com.jzs.function.admin.place.service.PlaceServiceI;
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
@RequestMapping("admin/place")
public class PlaceController {
    private static final Logger LOG = LoggerFactory.getLogger(PlaceController.class);

    @Autowired
    private PlaceServiceI placeService;

    @RequestMapping(value = "routeList",method = RequestMethod.GET)
    public ModelAndView routeList() {

        ModelAndView mav = new ModelAndView("admin/place/list");
        List<Place> list = placeService.list();
        mav.addObject(ConstantFields.PAGE_KEY, list);

        return mav;
    }

    @RequestMapping(value = "routeAdd",method = RequestMethod.GET)
    public String routeAdd() {

        return "admin/place/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String Add(Place place, HttpSession session, RedirectAttributes redirectAttributes) {
        AdminUser user = (AdminUser) session.getAttribute(ConstantFields.SESSION_ADMIN_KEY);
        String logUser = user.getUserName();

        if (placeService.add(place, logUser)) {
            if (LOG.isInfoEnabled())
                LOG.info("[MANAGE] [OK] {} add new place {}.", logUser, place.getPlaceName());

            redirectAttributes.addFlashAttribute(ConstantFields.ADD_SUCCESS_KEY, ConstantFields.ADD_SUCCESS_MESSAGE);
            return "redirect:/admin/place/routeList.action";
        }

        redirectAttributes.addFlashAttribute(ConstantFields.ADD_FAILURE_KEY, ConstantFields.ADD_FAILURE_MESSAGE);
        return "redirect:/admin/place/routeAdd" + place.getPlaceId() + ".action";
    }

    @RequestMapping(value = "/routeEdit/{placeId}",method = RequestMethod.GET)
    public ModelAndView routeEdit(@PathVariable("placeId") int placeId) {

        ModelAndView mav = new ModelAndView("admin/place/edit");
        mav.addObject("place",placeService.select(placeId));

        return mav;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String edit(Place place, HttpSession session, RedirectAttributes redirectAttributes) {
        AdminUser user = (AdminUser) session.getAttribute(ConstantFields.SESSION_ADMIN_KEY);
        String logUser = user.getUserName();

        if (placeService.edit(place, logUser)) {
            if (LOG.isInfoEnabled())
                LOG.info("[MANAGE] [OK] {} edit place {}.", logUser, place.getPlaceName());

            redirectAttributes.addFlashAttribute(ConstantFields.EDIT_SUCCESS_KEY, ConstantFields.EDIT_SUCCESS_MESSAGE);
            return "redirect:/admin/place/routeList.action";
        }

        redirectAttributes.addFlashAttribute(ConstantFields.EDIT_FAILURE_KEY, ConstantFields.EDIT_FAILURE_MESSAGE);
        return "redirect:/admin/place/routeEdit" + place.getPlaceId() + ".action";
    }

    @RequestMapping(value = "/cancle/{placeId}", method = RequestMethod.GET)
    public String cancle(@PathVariable("placeId") int placeId, HttpSession session, RedirectAttributes redirectAttributes) {
        AdminUser user = (AdminUser) session.getAttribute(ConstantFields.SESSION_ADMIN_KEY);
        String logUser = user.getUserName();

        if (placeService.cancle(placeId, logUser)) {
            if (LOG.isInfoEnabled())
                LOG.info("[MANAGE] [OK] {} delete placeId {}.", logUser, placeId);

            redirectAttributes.addFlashAttribute(ConstantFields.DELETE_SUCCESS_KEY, ConstantFields.DELETE_SUCCESS_MESSAGE);
            return "redirect:/admin/place/routeList.action";
        }

        redirectAttributes.addFlashAttribute(ConstantFields.DELETE_FAILURE_KEY, ConstantFields.DELETE_FAILURE_MESSAGE);
        return "redirect:/admin/place/routeList.action";
    }
}
