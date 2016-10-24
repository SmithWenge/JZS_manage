package com.jzs.function.admin.device.controller;

import com.google.common.base.Optional;
import com.jzs.arc.utils.ConstantFields;
import com.jzs.arc.utils.DeviceNumUtils;
import com.jzs.function.admin.device.Device;
import com.jzs.function.admin.device.service.DeviceService;
import com.jzs.function.admin.device.service.DeviceServiceI;
import com.jzs.function.admin.login.AdminUser;
import com.jzs.function.admin.seat.service.SeatServiceI;
import com.jzs.function.support.dictionary.impl.DefaultDictionaryManager;
import org.joda.time.DateTime;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("admin/device")
public class deviceController {

    private static final Logger LOG = LoggerFactory.getLogger(deviceController.class);

    @Autowired
    private DeviceServiceI deviceService;

    @RequestMapping(value = "routeList",method = RequestMethod.GET)
    public ModelAndView routeList(HttpSession session,Device device,@PageableDefault(value = ConstantFields.DEFAULT_PAGE_SIZE) Pageable pageable) {
        session.setAttribute(ConstantFields.SESSION_DEVICE_SEARCH_KEY,device);
        ModelAndView mav = new ModelAndView("admin/device/list");
        Page<Device> page = deviceService.list(device,pageable);
        mav.addObject(ConstantFields.PAGE_KEY,page);

        return mav;
    }

    @RequestMapping(value = "routeImport",method = RequestMethod.GET)
    public ModelAndView routeImport() {
        ModelAndView mav = new ModelAndView("admin/device/import");

        return mav;
    }

    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public String downloadTemplate(HttpServletResponse response, HttpSession session) {
        String templatePath = session.getServletContext().getRealPath("/") + "WEB-INF/data/template/Template.xls";
        try {
            File file = new File(templatePath);

            response.setContentType("application/x-excel");
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Content-Disposition", "attachment; filename=" + file.getName());
            response.setHeader("Content-Length", String.valueOf(file.length()));

            int length = 0;
            byte[] buffer = new byte[1024];

            FileInputStream fis = new FileInputStream(file);
            OutputStream os = response.getOutputStream();

            while (-1 != (length = fis.read(buffer, 0, buffer.length))) {
                os.write(buffer, 0, length);
            }

            os.flush();
            os.close();
            fis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return null;

    }

    private File save(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        String targetPath = request.getSession().getServletContext().getRealPath("/WEB-INF/data/real/");
        String sourceFileName = file.getOriginalFilename();

        String dateString = new DateTime().toString("MM-dd-yyyy-HH-mm-ss-SSS");
        String prefixName = sourceFileName.substring(0, sourceFileName.indexOf("."));
        String subName = sourceFileName.substring(sourceFileName.lastIndexOf("."));
        String newName = prefixName + "-" + dateString + subName;

        File targetFile = new File(targetPath, newName);
        request.getSession().setAttribute(ConstantFields.UPLOAD_EXCEL_FILE_NAME, targetFile.getName());

        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }

        try {
            file.transferTo(targetFile);

            return targetFile;
        } catch (IllegalStateException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping(value = "/import", method = RequestMethod.POST)
    public ModelAndView processDataImport(@RequestParam("file") MultipartFile file, HttpServletRequest request,HttpSession session) {
        ModelAndView mav = new ModelAndView("admin/device/result");
        File importFile = save(file, request);
        Map<String, List<Device>> result = deviceService.importData(importFile);

        List<Device> errorData = result.get("errorData");
        List<Device> repeatData = result.get("repeatData");
        List<Device> rightData =  result.get("rightData");
        int repeatDataNum = repeatData.size();

        mav.addObject("errorData", errorData);
        mav.addObject("repeatData", repeatData);
        mav.addObject("rightData", rightData.size());
        mav.addObject("repeatDataNum",repeatDataNum);

        return mav;
    }

    @RequestMapping(value = "pageSearch",method = RequestMethod.POST)
    public ModelAndView pageSearch(Device device,HttpSession session,@PageableDefault(value = ConstantFields.DEFAULT_PAGE_SIZE) Pageable pageable) {
        session.setAttribute(ConstantFields.SESSION_DEVICE_SEARCH_KEY,device);
        ModelAndView mav = new ModelAndView("admin/device/list");
        Page<Device> page = deviceService.list(device,pageable);
        mav.addObject(ConstantFields.PAGE_KEY,page);

        return mav;

    }

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public ModelAndView list(@PageableDefault(value = ConstantFields.DEFAULT_PAGE_SIZE) Pageable pageable,HttpSession session) {
        Device device = (Device)session.getAttribute(ConstantFields.SESSION_DEVICE_SEARCH_KEY);
        Page<Device> page = deviceService.list(device, pageable);
        ModelAndView mav = new ModelAndView("admin/device/list");
        mav.addObject(ConstantFields.PAGE_KEY, page);

        return mav;
    }

    @RequestMapping(value = "/routeEdit/{deviceId}", method = RequestMethod.GET)
    public ModelAndView routeEdit(@PathVariable("deviceId") int deviceId) {
        Device device = deviceService.select(deviceId);
        ModelAndView mav = new ModelAndView("admin/device/edit");
        mav.addObject("device", device);

        return mav;
    }

    @RequestMapping(value = "/check/{deviceId}", method = RequestMethod.GET)
    public ModelAndView routeCheck(@PathVariable("deviceId") int deviceId) {
        Device device = deviceService.select(deviceId);
        ModelAndView mav = new ModelAndView("admin/device/check");
        mav.addObject("device", device);

        return mav;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String edit(Device device, HttpSession session, RedirectAttributes redirectAttributes) {
        AdminUser user = (AdminUser) session.getAttribute(ConstantFields.SESSION_ADMIN_KEY);
        String logUser = user.getUserName();

        if (deviceService.update(device, logUser)) {
            if (LOG.isInfoEnabled())
                LOG.info("[MANAGE] [OK] {} edit device {}.", logUser, device.getDeviceNumber());

            redirectAttributes.addFlashAttribute(ConstantFields.EDIT_SUCCESS_KEY, ConstantFields.EDIT_SUCCESS_MESSAGE);

            return "redirect:/admin/device/routeList.action";
        }

        redirectAttributes.addFlashAttribute(ConstantFields.EDIT_FAILURE_KEY, ConstantFields.EDIT_FAILURE_MESSAGE);
        return "redirect:/admin/device/routeEdit/" + device.getDeviceId() + ".action";
    }

    @RequestMapping(value = "/routeAdd", method = RequestMethod.GET)
    public ModelAndView routeAdd() {
        ModelAndView mav = new ModelAndView("admin/device/add");

        return mav;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(Device device, HttpSession session, RedirectAttributes redirectAttributes) {
        AdminUser user = (AdminUser) session.getAttribute(ConstantFields.SESSION_ADMIN_KEY);
        String logUser = user.getUserName();
        DefaultDictionaryManager dictionaryManager = DefaultDictionaryManager.getInstance();
        String track = dictionaryManager.dictionary(device.getTrackNum(), "track").getItemValue();
        device.setDeviceNumber(DeviceNumUtils.creatDeviceNumber(device.getPlaceNum(), track, device.getRegionNum(), device.getSeat()));
        if (!deviceService.selectRepeat(device)) {
            if (deviceService.add(device, logUser)) {
                if (LOG.isInfoEnabled())
                    LOG.info("[MANAGE] [OK] {} add device {}.", logUser, device.getDeviceNumber());

                redirectAttributes.addFlashAttribute(ConstantFields.ADD_SUCCESS_KEY, ConstantFields.ADD_SUCCESS_MESSAGE);

                return "redirect:/admin/device/routeList.action";
            }

            redirectAttributes.addFlashAttribute(ConstantFields.ADD_FAILURE_KEY, ConstantFields.ADD_FAILURE_MESSAGE);
            return "redirect:/admin/device/routeAdd.action";
        }

        redirectAttributes.addFlashAttribute(ConstantFields.ADD_REPEAT_KEY, ConstantFields.ADD_REPEAT_MESSAGE);
        return "redirect:/admin/device/routeAdd.action";
    }

    @RequestMapping(value = "/cancle/{deviceId}", method = RequestMethod.GET)
    public String cancle(@PathVariable("deviceId") int deviceId, HttpSession session, RedirectAttributes redirectAttributes) {
        AdminUser user = (AdminUser) session.getAttribute(ConstantFields.SESSION_ADMIN_KEY);
        String logUser = user.getUserName();
        Device device  = new Device();
        device.setDeviceId(deviceId);
        if(deviceService.selectState(device)) {
            if (deviceService.cancle(device, logUser)) {
                if (LOG.isInfoEnabled())
                    LOG.info("[MANAGE] [OK] {} cancle device {}.", logUser, device.getDeviceId());

                redirectAttributes.addFlashAttribute(ConstantFields.CANCLE_SUCCESS_KEY, ConstantFields.CANCLE_SUCCESS_MESSAGE);

                return "redirect:/admin/device/routeList.action";
            }

            redirectAttributes.addFlashAttribute(ConstantFields.CANCLE_FAILURE_KEY, ConstantFields.CANCLE_FAILURE_MESSAGE);
            return "redirect:/admin/device/routeList.action";
        }
        redirectAttributes.addFlashAttribute(ConstantFields.CANCLE_REPI_KEY, ConstantFields.CANCLE_REPI_MESSAGE);
        return "redirect:/admin/device/routeList.action";
    }
}
