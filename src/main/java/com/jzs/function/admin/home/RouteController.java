package com.jzs.function.admin.home;

import com.jzs.arc.utils.ConstantFields;
import com.jzs.function.admin.device.service.DeviceServiceI;
import com.jzs.function.admin.login.AdminUser;
import com.jzs.function.admin.maintain.service.MaintainServiceI;
import com.jzs.function.admin.workerInfo.WorkerInfo;
import com.jzs.function.admin.workerInfo.service.WorkerInfoServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/admin/home")
public class RouteController {

    @Autowired
    private WorkerInfoServiceI workerInfoService;
    @Autowired
    private MaintainServiceI maintainService;
    @Autowired
    private DeviceServiceI deviceService;

    @RequestMapping("/index")
    public ModelAndView index() {
        ModelAndView mav = new ModelAndView("admin/home/index");
        List<WorkerInfo> page = workerInfoService.listForHome();
        mav.addObject(ConstantFields.PAGE_KEY, page);
        mav.addObject("num",maintainService.selectFaultNum());
        mav.addObject("waringNum",deviceService.selectNumOfWarning());
        int state = maintainService.getInspectionState();
        mav.addObject("state", state);

        return mav;
    }
}
