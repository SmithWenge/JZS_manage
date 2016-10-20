package com.jzs.function.admin.workerInfo.controller;

import com.google.common.base.Optional;
import com.jzs.arc.exception.BatchRollbackException;
import com.jzs.arc.utils.ChecksToList;
import com.jzs.arc.utils.ConstantFields;
import com.jzs.function.admin.device.service.DeviceServiceI;
import com.jzs.function.admin.login.AdminUser;
import com.jzs.function.admin.workerInfo.WorkerInfo;
import com.jzs.function.admin.workerInfo.service.WorkerInfoServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/admin/workerInfo")
public class WorkerInfoController {

    @Autowired
    private WorkerInfoServiceI workerInfoService;
    @Autowired
    private DeviceServiceI deviceService;

    @RequestMapping(value = "routeAdd",method = RequestMethod.GET)
    public ModelAndView routeAdd() {
        ModelAndView mav = new ModelAndView("admin/workerInfo/workerInfoAdd");
        mav.addObject("diaoches",workerInfoService.selectDiaoChe());
        mav.addObject("fanghus",workerInfoService.selectWorker());
        mav.addObject("workers",workerInfoService.selectWorker());
        mav.addObject("zhibans",workerInfoService.selectZhiBan());

        return mav;
    }

    @RequestMapping(value = "workerInfoAdd",method = RequestMethod.POST)
    public ModelAndView workerInfoAdd(HttpSession session,RedirectAttributes redirectAttributes,
                                      @RequestParam(value = "fanghu")String fanghu,
                                      @RequestParam(value = "zhiban")String zhiban,
                                      @RequestParam(value = "diaoche")String diaoche,
                                      @RequestParam(value = "worker")String worker) throws BatchRollbackException{
        AdminUser adminUser =(AdminUser) session.getAttribute(ConstantFields.SESSION_ADMIN_KEY);
        Optional<AdminUser> optionalUser = Optional.fromNullable(adminUser);
        if (optionalUser.isPresent()) {
            String logUser = adminUser.getUserName();
            List<String> list  = new ArrayList<String>();
            if (fanghu != null) {
                list.add(fanghu);
            }
            if (zhiban != null) {
                list.add(zhiban);
            }
            if (diaoche != null) {
                list.add(diaoche);
            }
            if (worker != null) {
                list.add(worker);
            }
//            if (workerInfoService.selectCount()) {
                if (workerInfoService.add(list, logUser)) {
                    deviceService.updateWarning();

                    redirectAttributes.addFlashAttribute(ConstantFields.ADD_SUCCESS_KEY, ConstantFields.ADD_SUCCESS_MESSAGE);
                    ModelAndView mav = new ModelAndView("redirect:/admin/workerInfo/routeList.action");

                    return mav;
                }
                redirectAttributes.addFlashAttribute(ConstantFields.ADD_FAILURE_KEY, ConstantFields.ADD_FAILURE_MESSAGE);
                ModelAndView mav = new ModelAndView("redirect:/admin/workerInfo/routeAdd.action");

                return mav;
//            } else {
//                redirectAttributes.addFlashAttribute(ConstantFields.WOEKERINFO_ADD_FAILURE_KEY, ConstantFields.WOEKERINFO_ADD_FAILURE_MESSAGE);
//                return new ModelAndView("redirect:/admin/workerInfo/routeAdd.action");
//            }
        }else {

            return new ModelAndView("redirect:/admin/routeLogin.action");
        }
    }

    @RequestMapping(value = "routeList",method = RequestMethod.GET)
    public ModelAndView routeList() {
        ModelAndView mav = new ModelAndView("admin/workerInfo/list");
        WorkerInfo workerInfo = new WorkerInfo();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        workerInfo.setAttTime(sdf.format(new Date()));
        List<WorkerInfo> page = workerInfoService.list(workerInfo);
        mav.addObject(ConstantFields.PAGE_KEY, page);

        return mav;
    }

    @RequestMapping(value = "routeList",method = RequestMethod.POST)
    public ModelAndView routeList(WorkerInfo workerInfo) {
        ModelAndView mav = new ModelAndView("admin/workerInfo/list");
        List<WorkerInfo> page = workerInfoService.listForPost(workerInfo);
        mav.addObject(ConstantFields.PAGE_KEY, page);

        return mav;
    }

}
