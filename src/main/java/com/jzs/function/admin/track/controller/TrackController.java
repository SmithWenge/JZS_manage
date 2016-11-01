package com.jzs.function.admin.track.controller;

import com.jzs.arc.utils.ConstantFields;
import com.jzs.function.admin.login.AdminUser;
import com.jzs.function.admin.track.Track;
import com.jzs.function.admin.track.service.TrackServiceI;
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
@RequestMapping("admin/track")
public class TrackController {
    private static final Logger LOG = LoggerFactory.getLogger(TrackController.class);

    @Autowired
    private TrackServiceI trackService;

    @RequestMapping(value = "routeList",method = RequestMethod.GET)
    public ModelAndView routeList() {

        ModelAndView mav = new ModelAndView("admin/track/list");
        List<Track> list = trackService.list();
        mav.addObject(ConstantFields.PAGE_KEY, list);

        return mav;
    }

    @RequestMapping(value = "routeAdd",method = RequestMethod.GET)
    public String routeAdd() {
        return "admin/track/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String Add(Track track, HttpSession session, RedirectAttributes redirectAttributes) {
        AdminUser user = (AdminUser) session.getAttribute(ConstantFields.SESSION_ADMIN_KEY);
        String logUser = user.getUserName();
        track.setTrackState(1);
        if (trackService.add(track, logUser)) {
            if (LOG.isInfoEnabled())
                LOG.info("[MANAGE] [OK] {} add new track {}.", logUser, track.getTrackName());

            redirectAttributes.addFlashAttribute(ConstantFields.ADD_SUCCESS_KEY, ConstantFields.ADD_SUCCESS_MESSAGE);
            return "redirect:/admin/track/routeList.action";
        }

        redirectAttributes.addFlashAttribute(ConstantFields.ADD_FAILURE_KEY, ConstantFields.ADD_FAILURE_MESSAGE);
        return "redirect:/admin/track/routeAdd" + track.getTrackId() + ".action";
    }

    @RequestMapping(value = "/routeEdit/{trackId}",method = RequestMethod.GET)
    public ModelAndView routeEdit(@PathVariable("trackId") int trackId) {

        ModelAndView mav = new ModelAndView("admin/track/edit");
        mav.addObject("track", trackService.select(trackId));

        return mav;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String edit(Track track, HttpSession session, RedirectAttributes redirectAttributes) {
        AdminUser user = (AdminUser) session.getAttribute(ConstantFields.SESSION_ADMIN_KEY);
        String logUser = user.getUserName();

        if (trackService.edit(track, logUser)) {
            if (LOG.isInfoEnabled())
                LOG.info("[MANAGE] [OK] {} edit track {}.", logUser, track.getTrackName());

            redirectAttributes.addFlashAttribute(ConstantFields.EDIT_SUCCESS_KEY, ConstantFields.EDIT_SUCCESS_MESSAGE);
            return "redirect:/admin/track/routeList.action";
        }

        redirectAttributes.addFlashAttribute(ConstantFields.EDIT_FAILURE_KEY, ConstantFields.EDIT_FAILURE_MESSAGE);
        return "redirect:/admin/track/routeEdit" + track.getTrackId() + ".action";
    }

    @RequestMapping(value = "/cancle/{trackId}", method = RequestMethod.GET)
    public String cancle(@PathVariable("trackId") int trackId, HttpSession session, RedirectAttributes redirectAttributes) {
        AdminUser user = (AdminUser) session.getAttribute(ConstantFields.SESSION_ADMIN_KEY);
        String logUser = user.getUserName();

        if (trackService.cancle(trackId, logUser)) {
            if (LOG.isInfoEnabled())
                LOG.info("[MANAGE] [OK] {} delete trackId {}.", logUser, trackId);

            redirectAttributes.addFlashAttribute(ConstantFields.DELETE_SUCCESS_KEY, ConstantFields.DELETE_SUCCESS_MESSAGE);
            return "redirect:/admin/track/routeList.action";
        }

        redirectAttributes.addFlashAttribute(ConstantFields.DELETE_FAILURE_KEY, ConstantFields.DELETE_FAILURE_MESSAGE);
        return "redirect:/admin/track/routeList.action";
    }

    @ResponseBody
    @RequestMapping(value = "/selectJsonByPlaceId/{placeId}", method = RequestMethod.POST)
    public Map<String,List<Track>> selectJsonByFauId(@PathVariable("placeId") int placeId) {
        List<Track> jsonByPlaceId = trackService.selectJsonByFauId(placeId);
        Map<String,List<Track>> map = new HashMap<>();
        map.put("jsonByPlaceId", jsonByPlaceId);

        return map;
    }
}
