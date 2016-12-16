package com.jzs.function.admin.maintain.controller;

import com.google.common.base.Optional;
import com.jzs.arc.exception.BatchRollbackException;
import com.jzs.arc.utils.ConstantFields;
import com.jzs.arc.utils.PasswordUtils;
import com.jzs.function.admin.faultType.FaultType;
import com.jzs.function.admin.faultType.service.FaultTypeServiceI;
import com.jzs.function.admin.login.AdminUser;
import com.jzs.function.admin.login.service.AdminLoginServiceI;
import com.jzs.function.admin.maintain.Maintain;
import com.jzs.function.admin.maintain.service.MaintainServiceI;
import com.jzs.function.admin.track.Track;
import com.jzs.function.admin.track.repository.TrackRepositoryI;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("admin/maintain")
public class MaintainController {

    private static final Logger LOG = LoggerFactory.getLogger(MaintainController.class);
    @Autowired
    private MaintainServiceI maintainService;
    @Autowired
    private TrackRepositoryI trackRepository;
    @Autowired
    private FaultTypeServiceI faultTypeService;
    @Autowired
    private AdminLoginServiceI adminLoginService;

    @RequestMapping(value = "maintainCheck/{faultRegisterId}",method = RequestMethod.GET)
    public ModelAndView routeMaintainCheck(@PathVariable("faultRegisterId") int faultRegisterId) {
        ModelAndView mav = new ModelAndView("admin/maintain/maintainCheck");
        mav.addObject("maintain",maintainService.selectMaintainById(faultRegisterId));

        return mav;
    }

    @RequestMapping(value = "routeMaintainIndex",method = RequestMethod.GET)
    public ModelAndView routeMaintainIndex() {
        ModelAndView mav = new ModelAndView("admin/maintain/maintain");
        List<Maintain> list = maintainService.listForMaintain();
        mav.addObject("protectApprovePeople",maintainService.selectProtectApprovePeople());
        mav.addObject("protectRequestPeople",maintainService.selectProtectRequestPeople());
        mav.addObject("workers",maintainService.selectWorker());
        mav.addObject("list", list);
        mav.addObject("faultTypes",faultTypeService.list());

        return mav;
    }

    @RequestMapping(value = "maiCancleProtect/{track}",method = RequestMethod.GET)
    public String maiCancleProtect(@PathVariable("track") int track,HttpSession session,RedirectAttributes redirectAttributes) {
        Maintain maintain = new Maintain();
        maintain.setTrack(track);
        AdminUser user = (AdminUser) session.getAttribute(ConstantFields.SESSION_ADMIN_KEY);
        String logUser = user.getUserName();
        if (maintainService.insCancleProtect(maintain,logUser)) {
            Track track2 = trackRepository.select(maintain.getTrack());
            if (LOG.isInfoEnabled())
                LOG.info("[MANAGE] [OK] {} cancle protectionTrack {}.", logUser, track2.getTrackName());

            redirectAttributes.addFlashAttribute(ConstantFields.OPREATE_SUCCESS_KEY, ConstantFields.OPREATE_SUCCESS_MESSAGE);
            return "redirect:/admin/maintain/routeMaintainIndex.action";
        }

        redirectAttributes.addFlashAttribute(ConstantFields.OPREATE_FAILURE_KEY, ConstantFields.OPREATE_FAILURE_MESSAGE);
        return "redirect:/admin/maintain/routeMaintainIndex.action";
    }

    @RequestMapping(value = "insCancleProtect/{track}",method = RequestMethod.GET)
    public String insCancleProtect(@PathVariable("track") int track,HttpSession session,RedirectAttributes redirectAttributes) {
        Maintain maintain = new Maintain();
        maintain.setTrack(track);
        AdminUser user = (AdminUser) session.getAttribute(ConstantFields.SESSION_ADMIN_KEY);
        String logUser = user.getUserName();
        if (maintainService.insCancleProtect(maintain,logUser)) {
            Track track2 = trackRepository.select(maintain.getTrack());
            if (LOG.isInfoEnabled())
                LOG.info("[MANAGE] [OK] {} cancle protectionTrack {}.", logUser, track2.getTrackName());

            redirectAttributes.addFlashAttribute(ConstantFields.OPREATE_SUCCESS_KEY, ConstantFields.OPREATE_SUCCESS_MESSAGE);
            return "redirect:/admin/maintain/routeIndex.action";
        }

        redirectAttributes.addFlashAttribute(ConstantFields.OPREATE_FAILURE_KEY, ConstantFields.OPREATE_FAILURE_MESSAGE);
        return "redirect:/admin/maintain/routeIndex.action";
    }

    @RequestMapping(value = "inspectionStop",method = RequestMethod.GET)
    public String inspectionStop (HttpSession session,RedirectAttributes redirectAttributes) throws BatchRollbackException{
        AdminUser user = (AdminUser) session.getAttribute(ConstantFields.SESSION_ADMIN_KEY);
        String logUser = user.getUserName();
        Maintain maintain = maintainService.getInspectionId();
        if (maintainService.inspectionStop(maintain, logUser)) {
            if (LOG.isInfoEnabled())
                LOG.info("[MANAGE] [OK] {} stop inspectionId {}.", logUser,maintain.getInspectionId() );

                redirectAttributes.addFlashAttribute(ConstantFields.OPREATE_SUCCESS_KEY, ConstantFields.OPREATE_SUCCESS_MESSAGE);
                return "redirect:/admin/maintain/routeIndex.action";
        }

        redirectAttributes.addFlashAttribute(ConstantFields.OPREATE_FAILURE_KEY, ConstantFields.OPREATE_FAILURE_MESSAGE);
        return "redirect:/admin/maintain/routeIndex.action";
    }

    @RequestMapping(value = "inspectionAdd",method = RequestMethod.POST)
    public String inspectionAdd(Maintain maintain,HttpSession session,RedirectAttributes redirectAttributes) throws BatchRollbackException{
        AdminUser user = (AdminUser) session.getAttribute(ConstantFields.SESSION_ADMIN_KEY);
        String logUser = user.getUserName();
        List<Maintain> list = maintainService.inspectionAdd(maintain, logUser);
        if (list != null) {
            if (LOG.isInfoEnabled())
                LOG.info("[MANAGE] [OK] {} add inspectionId {}.", logUser,maintain.getPlace() );

            redirectAttributes.addFlashAttribute(ConstantFields.OPREATE_SUCCESS_KEY, ConstantFields.OPREATE_SUCCESS_MESSAGE);
            return "redirect:/admin/maintain/routeIndex.action";
        }

        redirectAttributes.addFlashAttribute(ConstantFields.OPREATE_FAILURE_KEY, ConstantFields.OPREATE_FAILURE_MESSAGE);
        return "redirect:/admin/maintain/routeIndex.action";
    }

    @RequestMapping(value = "routeIndex",method = RequestMethod.GET)
    public ModelAndView routeIndex() {
        ModelAndView mav = new ModelAndView("admin/maintain/protect");
        mav.addObject("protectApprovePeople",maintainService.selectProtectApprovePeople());
        mav.addObject("protectRequestPeople",maintainService.selectProtectRequestPeople());
        mav.addObject("workers",maintainService.selectWorker());
        int state = maintainService.getInspectionState();
        mav.addObject("state", state);
        mav.addObject("faultTypes",faultTypeService.list());

        if (state != 0) {
            int placeId = maintainService.getInspectionId().getPlace();
            mav.addObject("inspections",maintainService.selectInspTracks(placeId));
        } else {
            mav.addObject("inspections",new ArrayList<Maintain>());
        }

        return mav;
    }

    @RequestMapping(value = "/maintainProtectAdd", method = RequestMethod.POST)
    public String maintainProtectAdd(Maintain maintain, HttpSession session, RedirectAttributes redirectAttributes) throws BatchRollbackException {
        AdminUser user = (AdminUser) session.getAttribute(ConstantFields.SESSION_ADMIN_KEY);
        String logUser = user.getUserName();

        AdminUser adminUser = new AdminUser();
        adminUser.setUserLoginName(adminLoginService.selectNowDiaocheUserName().getUserLoginName());
        adminUser.setUserLoginPass(PasswordUtils.encrypt(maintain.getDiaochePass()));
        AdminUser diaoche = adminLoginService.selectUnique(adminUser);
        Optional<AdminUser> optional = Optional.fromNullable(diaoche);
        if (optional.isPresent()) {
            int tmp = maintainService.protectAdd(maintain, logUser);
            if (0 == tmp) {
                if (LOG.isInfoEnabled())
                    LOG.info("[MANAGE] [OK] {} add new protection {}.", logUser, "maintainProtect");

                redirectAttributes.addFlashAttribute(ConstantFields.PROTECT_SUCCESS_KEY, ConstantFields.PROTECT_SUCCESS_MESSAGE);
                return "redirect:/admin/maintain/routeMaintainIndex.action";
            }if (2 == tmp) {
                redirectAttributes.addFlashAttribute(ConstantFields.PROTECT_REPEAT_KEY, ConstantFields.PROTECT_REPEAT_MESSAGE);
                return "redirect:/admin/maintain/routeMaintainIndex.action";
            }else {
                redirectAttributes.addFlashAttribute(ConstantFields.PROTECT_FAILURE_KEY, ConstantFields.PROTECT_FAILURE_MESSAGE);
                return "redirect:/admin/maintain/routeMaintainIndex.action";
            }
        } else {
            redirectAttributes.addFlashAttribute(ConstantFields.DIAOCHE_WRONG_KEY, ConstantFields.DIAOCHE_WRONG_MESSAGE);
            return "redirect:/admin/maintain/routeMaintainIndex.action";
        }
    }

    @RequestMapping(value = "/protectAdd", method = RequestMethod.POST)
    public String protectAdd(Maintain maintain, HttpSession session, RedirectAttributes redirectAttributes) throws BatchRollbackException {
        AdminUser user = (AdminUser) session.getAttribute(ConstantFields.SESSION_ADMIN_KEY);
        String logUser = user.getUserName();

        AdminUser adminUser = new AdminUser();
        adminUser.setUserLoginName(adminLoginService.selectNowDiaocheUserName().getUserLoginName());
        adminUser.setUserLoginPass(PasswordUtils.encrypt(maintain.getDiaochePass()));
        AdminUser diaoche = adminLoginService.selectUnique(adminUser);
        Optional<AdminUser> optional = Optional.fromNullable(diaoche);
        if (optional.isPresent()) {
            int tmp = maintainService.protectAdd(maintain, logUser);
            if (0 == tmp) {
                if (LOG.isInfoEnabled())
                    LOG.info("[MANAGE] [OK] {} add new protection {}.", logUser,"maintainProtect");

                // 记录防护时间
//                session.setAttribute(String.valueOf(maintain.getTrack()), maintain.getProtectRemark());
                redirectAttributes.addFlashAttribute(ConstantFields.PROTECT_SUCCESS_KEY, ConstantFields.PROTECT_SUCCESS_MESSAGE);
                return "redirect:/admin/maintain/routeIndex.action";
            }if (2 == tmp) {
                redirectAttributes.addFlashAttribute(ConstantFields.PROTECT_REPEAT_KEY, ConstantFields.PROTECT_REPEAT_MESSAGE);
                return "redirect:/admin/maintain/routeIndex.action";
            }else {
                redirectAttributes.addFlashAttribute(ConstantFields.PROTECT_FAILURE_KEY, ConstantFields.PROTECT_FAILURE_MESSAGE);
                return "redirect:/admin/maintain/routeIndex.action";
            }
        } else {
            redirectAttributes.addFlashAttribute(ConstantFields.DIAOCHE_WRONG_KEY, ConstantFields.DIAOCHE_WRONG_MESSAGE);
            return "redirect:/admin/maintain/routeIndex.action";
        }
    }

    @RequestMapping(value = "/reMaintainAdd", method = RequestMethod.POST)
    public String reMaintainAdd(Maintain maintain, HttpSession session, RedirectAttributes redirectAttributes) throws BatchRollbackException {
        AdminUser user = (AdminUser) session.getAttribute(ConstantFields.SESSION_ADMIN_KEY);
        String logUser = user.getUserName();
        String faultFindPeopleText = maintain.getFaultFindPeopleText();
        if (faultFindPeopleText != null && !faultFindPeopleText.equals("")) {
            maintain.setFaultFindPeople(faultFindPeopleText);
        }

        maintain.setPlace(maintainService.selectPlaceId(maintain.getTrack()));
        if (maintainService.selectExitMaintain(maintain)) {
            if (maintainService.maintainAdd(maintain, logUser)) {
                if (LOG.isInfoEnabled())
                    LOG.info("[MANAGE] [OK] {} add new maintain {}.", logUser,"maintainProtect");

                redirectAttributes.addFlashAttribute(ConstantFields.MAINTAIN_SUCCESS_KEY, ConstantFields.MAINTAIN_SUCCESS_MESSAGE);
                return "redirect:/admin/maintain/routeMaintainIndex.action";
            }
            redirectAttributes.addFlashAttribute(ConstantFields.MAINTAIN_FAILURE_KEY, ConstantFields.MAINTAIN_FAILURE_MESSAGE);
            return "redirect:/admin/maintain/routeMaintainIndex.action";
        }
        redirectAttributes.addFlashAttribute(ConstantFields.MAINTAIN_REPEAT_KEY, ConstantFields.MAINTAIN_REPEAT_MESSAGE);
        return "redirect:/admin/maintain/routeMaintainIndex.action";
    }

    @RequestMapping(value = "/maintainAdd", method = RequestMethod.POST)
    public String maintainAdd(Maintain maintain, HttpSession session, RedirectAttributes redirectAttributes) throws BatchRollbackException {
        AdminUser user = (AdminUser) session.getAttribute(ConstantFields.SESSION_ADMIN_KEY);
        String logUser = user.getUserName();
        String faultFindPeopleText = maintain.getFaultFindPeopleText();
        if (faultFindPeopleText != null && !faultFindPeopleText.equals("")) {
            maintain.setFaultFindPeople(faultFindPeopleText);
        }
        if (maintainService.selectExitMaintain(maintain)) {
            if (maintainService.maintainAdd(maintain, logUser)) {
                if (LOG.isInfoEnabled())
                    LOG.info("[MANAGE] [OK] {} add new maintain {}.", logUser, "maintainProtect");

                redirectAttributes.addFlashAttribute(ConstantFields.MAINTAIN_SUCCESS_KEY, ConstantFields.MAINTAIN_SUCCESS_MESSAGE);
                return "redirect:/admin/maintain/routeIndex.action";
            }
            redirectAttributes.addFlashAttribute(ConstantFields.MAINTAIN_FAILURE_KEY, ConstantFields.MAINTAIN_FAILURE_MESSAGE);
            return "redirect:/admin/maintain/routeIndex.action";
        }
        redirectAttributes.addFlashAttribute(ConstantFields.MAINTAIN_REPEAT_KEY, ConstantFields.MAINTAIN_REPEAT_MESSAGE);
        return "redirect:/admin/maintain/routeIndex.action";
    }

    @RequestMapping(value = "/reFinishMaintain", method = RequestMethod.POST)
    public String reFinishMaintain(Maintain maintain,HttpSession session,RedirectAttributes redirectAttributes) throws BatchRollbackException{
        AdminUser user = (AdminUser) session.getAttribute(ConstantFields.SESSION_ADMIN_KEY);
        String logUser = user.getUserName();
        Maintain device = maintainService.selectSeat(maintain.getFaultRegisterId());
        maintain.setTrack(device.getTrack());
        maintain.setRegion(device.getRegion());
        maintain.setSeat(device.getSeat());
        maintain.setDealResult("待定");

//        if (maintainService.selectExitCanclePro(maintain)) {
            if (maintainService.updateMaintain(maintain, logUser)) {
                if (LOG.isInfoEnabled())
                    LOG.info("[MANAGE] [OK] {} finish maintain {}.", logUser, maintain.getFaultRegisterId());

                redirectAttributes.addFlashAttribute(ConstantFields.MAINTAIN_FINISH_SUCCESS_KEY, ConstantFields.MAINTAIN_FINISH_SUCCESS_MESSAGE);
                return "redirect:/admin/maintain/routeMaintainIndex.action";
            }

            redirectAttributes.addFlashAttribute(ConstantFields.MAINTAIN_FINISH_FAILURE_KEY, ConstantFields.MAINTAIN_FINISH_FAILURE_MESSAGE);
            return "redirect:/admin/maintain/routeMaintainIndex.action";
//        }else {
//
//            redirectAttributes.addFlashAttribute(ConstantFields.PROTECT_REMAIN_KEY, ConstantFields.PROTECT_REMAIN_MESSAGE);
//            return "redirect:/admin/maintain/routeMaintainIndex.action";
//        }
    }

    @RequestMapping(value = "/finishMaintain", method = RequestMethod.POST)
    public String finishMaintain(Maintain maintain,HttpSession session,RedirectAttributes redirectAttributes) throws BatchRollbackException{
        AdminUser user = (AdminUser) session.getAttribute(ConstantFields.SESSION_ADMIN_KEY);
        String logUser = user.getUserName();
        Maintain device = maintainService.selectSeat(maintain.getFaultRegisterId());
        maintain.setTrack(device.getTrack());
        maintain.setRegion(device.getRegion());
        maintain.setSeat(device.getSeat());
        maintain.setDealResult("待定");

        if (maintainService.updateMaintain(maintain, logUser)) {
            if (LOG.isInfoEnabled())
                LOG.info("[MANAGE] [OK] {} finish maintain {}.", logUser, maintain.getFaultRegisterId());

            redirectAttributes.addFlashAttribute(ConstantFields.MAINTAIN_FINISH_SUCCESS_KEY, ConstantFields.MAINTAIN_FINISH_SUCCESS_MESSAGE);

            return "redirect:/admin/maintain/routeIndex.action";
        }

        redirectAttributes.addFlashAttribute(ConstantFields.MAINTAIN_FINISH_FAILURE_KEY, ConstantFields.MAINTAIN_FINISH_FAILURE_MESSAGE);
        return "redirect:/admin/maintain/routeIndex.action";
    }

    @RequestMapping(value = "/inspectionPage", method = RequestMethod.GET)
    public ModelAndView inspectionPage(@PageableDefault(value = ConstantFields.DEFAULT_PAGE_SIZE) Pageable pageable,HttpSession session) {
        Maintain maintain = (Maintain)session.getAttribute(ConstantFields.SESSION_INSPECTION_SEARCH_KEY);
        Page<Maintain> page = maintainService.pageInspection(maintain, pageable);
        ModelAndView mav = new ModelAndView("admin/maintain/inspectionSearch");
        mav.addObject(ConstantFields.PAGE_KEY, page);

        return mav;
    }

    @RequestMapping(value = "/maintainPage", method = RequestMethod.GET)
    public ModelAndView listOne(@PageableDefault(value = ConstantFields.DEFAULT_PAGE_SIZE) Pageable pageable,HttpSession session) {
        Maintain maintain = (Maintain)session.getAttribute(ConstantFields.SESSION_MAINTAIN_SEARCH_KEY);
        Page<Maintain> page = maintainService.listMaintain(maintain, pageable);
        ModelAndView mav = new ModelAndView("admin/maintain/maintainSearch");
        mav.addObject(ConstantFields.PAGE_KEY, page);
        mav.addObject("faultTypes",faultTypeService.list());

        return mav;
    }

    @RequestMapping(value = "/protectPage", method = RequestMethod.GET)
    public ModelAndView listTwo(@PageableDefault(value = ConstantFields.DEFAULT_PAGE_SIZE) Pageable pageable,HttpSession session) {
        Maintain maintain = (Maintain)session.getAttribute(ConstantFields.SESSION_PROTECT_SEARCH_KEY);
        Page<Maintain> page = maintainService.listProtect(maintain, pageable);
        ModelAndView mav = new ModelAndView("admin/maintain/protectSearch");
        mav.addObject(ConstantFields.PAGE_KEY, page);

        return mav;
    }

    @RequestMapping(value = "inspectionSearch",method = RequestMethod.POST)
    public ModelAndView inspectionSearch(HttpSession session,Pageable pageable,Maintain maintain) {
        session.setAttribute(ConstantFields.SESSION_INSPECTION_SEARCH_KEY, maintain);
        ModelAndView mav = new ModelAndView("admin/maintain/inspectionSearch");
        Page<Maintain> page = maintainService.pageInspection(maintain,pageable);
        mav.addObject(ConstantFields.PAGE_KEY,page);

        return mav;
    }

    @RequestMapping(value = "protectSearch",method = RequestMethod.POST)
    public ModelAndView protectSearch(HttpSession session,Pageable pageable,Maintain maintain) {
        session.setAttribute(ConstantFields.SESSION_PROTECT_SEARCH_KEY, maintain);
        ModelAndView mav = new ModelAndView("admin/maintain/protectSearch");
        Page<Maintain> page = maintainService.listProtect(maintain, pageable);
        mav.addObject(ConstantFields.PAGE_KEY,page);

        return mav;
    }

    @RequestMapping(value = "maintainSearch",method = RequestMethod.POST)
    public ModelAndView maintainSearch(HttpSession session,Pageable pageable,Maintain maintain) {
        session.setAttribute(ConstantFields.SESSION_MAINTAIN_SEARCH_KEY, maintain);
        ModelAndView mav = new ModelAndView("admin/maintain/maintainSearch");
        Page<Maintain> page = maintainService.listMaintain(maintain, pageable);
        mav.addObject(ConstantFields.PAGE_KEY,page);
        mav.addObject("faultTypes",faultTypeService.list());

        return mav;
    }

    @RequestMapping(value = "routeAll",method = RequestMethod.GET)
    public ModelAndView routeAll(HttpSession session,Pageable pageable) {
        Maintain maintain = new Maintain();
        session.setAttribute(ConstantFields.SESSION_ALL_SEARCH_KEY, maintain);
        ModelAndView mav = new ModelAndView("admin/maintain/all");
        Page<Maintain> page = maintainService.listAll(maintain, pageable);
        mav.addObject(ConstantFields.PAGE_KEY,page);

        return mav;
    }

    @RequestMapping(value = "/allPage", method = RequestMethod.GET)
    public ModelAndView listAll(@PageableDefault(value = ConstantFields.DEFAULT_PAGE_SIZE) Pageable pageable,HttpSession session) {
        Maintain maintain = (Maintain)session.getAttribute(ConstantFields.SESSION_ALL_SEARCH_KEY);
        Page<Maintain> page = maintainService.listAll(maintain, pageable);
        ModelAndView mav = new ModelAndView("admin/maintain/all");
        mav.addObject(ConstantFields.PAGE_KEY, page);

        return mav;
    }

    @RequestMapping(value = "AllSearch",method = RequestMethod.POST)
    public ModelAndView allSearch(HttpSession session,Pageable pageable,Maintain maintain) {
        session.setAttribute(ConstantFields.SESSION_ALL_SEARCH_KEY, maintain);
        ModelAndView mav = new ModelAndView("admin/maintain/all");
        Page<Maintain> page = maintainService.listAll(maintain, pageable);
        mav.addObject(ConstantFields.PAGE_KEY,page);

        return mav;
    }

    @RequestMapping(value = "/check/{faultRegisterId}", method = RequestMethod.GET)
    public ModelAndView routeCheck(@PathVariable("faultRegisterId") int faultRegisterId,HttpSession session) {
        Maintain maintain = maintainService.selectAll(faultRegisterId);
        ModelAndView mav = new ModelAndView("admin/maintain/check");
        mav.addObject("maintain", maintain);

        return mav;
    }

    @ResponseBody
    @RequestMapping(value = "/fauRegTrack/{track}", method = RequestMethod.POST)
    public Map<String,Maintain> fauRegTrack(@PathVariable("track") int track) {
        Maintain maintain = maintainService.selectPlaceAndTrack(track);
        Map<String,Maintain> map = new HashMap<>();
        map.put("maintain", maintain);

        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/fauCanTrack/{track}", method = RequestMethod.POST)
    public Map<String,List<Maintain>> fauCanTrack(@PathVariable("track") int track) {
        List<Maintain> list = maintainService.selectTrackMaintains(track);
        Map<String,List<Maintain>> map = new HashMap<>();
        map.put("list",list);

        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/selectJsonByFauId/{faultRegisterId}", method = RequestMethod.POST)
    public Map<String,Maintain> selectJsonByFauId(@PathVariable("faultRegisterId") int faultRegisterId) {
        Maintain jsonByFauId = maintainService.selectJsonByFauId(faultRegisterId);
        Map<String,Maintain> map = new HashMap<>();
        map.put("jsonByFauId",jsonByFauId);

        return map;
    }
}
