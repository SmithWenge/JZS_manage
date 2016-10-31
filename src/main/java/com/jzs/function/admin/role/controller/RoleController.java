package com.jzs.function.admin.role.controller;

import com.google.common.base.Optional;
import com.jzs.arc.exception.BatchRollbackException;
import com.jzs.arc.utils.ConstantFields;
import com.jzs.arc.utils.ListCutUtils;
import com.jzs.function.admin.login.AdminUser;
import com.jzs.function.admin.role.Role;
import com.jzs.function.admin.role.service.RoleServiceI;
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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("admin/role")
public class RoleController {

    private static final Logger LOG = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    private RoleServiceI roleService;

    @RequestMapping(value = "routeList",method = RequestMethod.GET)
    public ModelAndView routeList(HttpSession session,Pageable pageable) {
        ModelAndView mav = new ModelAndView("admin/role/list");
        Page<Role> page = roleService.list(pageable);
        mav.addObject(ConstantFields.PAGE_KEY, page);

        return mav;
    }

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public ModelAndView list(@PageableDefault(value = ConstantFields.DEFAULT_PAGE_SIZE) Pageable pageable,HttpSession session) {
        Page<Role> page = roleService.list(pageable);
        ModelAndView mav = new ModelAndView("admin/role/list");
        mav.addObject(ConstantFields.PAGE_KEY, page);

        return mav;
    }

    @RequestMapping(value = "routeAdd",method = RequestMethod.GET)
    public ModelAndView routeAdd() {
        ModelAndView mav = new ModelAndView("admin/role/add");
        List<Role> workers =roleService.selectFunctions(1);
        mav.addObject("workers",workers);
        List<Role> adminUsers =roleService.selectFunctions(2);
        mav.addObject("adminUsers", adminUsers);
        List<Role> roles =roleService.selectFunctions(3);
        mav.addObject("roles", roles);
        List<Role> logs =roleService.selectFunctions(4);
        mav.addObject("logs",logs);
        List<Role> devices =roleService.selectFunctions(5);
        mav.addObject("devices",devices);
        List<Role> workerInfos =roleService.selectFunctions(6);
        mav.addObject("workerInfos",workerInfos);
        List<Role> selectInfos =roleService.selectFunctions(7);
        mav.addObject("selectInfos",selectInfos);
        List<Role> selectMaintains =roleService.selectFunctions(8);
        mav.addObject("selectMaintains",selectMaintains);
        List<Role> maintains =roleService.selectFunctions(30);
        mav.addObject("maintains",maintains);
        List<Role> places =roleService.selectFunctions(10);
        mav.addObject("places",places);
        List<Role> tracks =roleService.selectFunctions(11);
        mav.addObject("tracks",tracks);
        List<Role> regions =roleService.selectFunctions(12);
        mav.addObject("regions",regions);
        List<Role> selectInspections =roleService.selectFunctions(13);
        mav.addObject("selectInspections",selectInspections);
        List<Role> selectProtections =roleService.selectFunctions(14);
        mav.addObject("selectProtections",selectProtections);
        List<Role> maintainSearchs =roleService.selectFunctions(15);
        mav.addObject("maintainSearchs",maintainSearchs);
        List<Role> faultTypes =roleService.selectFunctions(16);
        mav.addObject("faultTypes",faultTypes);
        return mav;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String Add(Role role, HttpSession session, RedirectAttributes redirectAttributes,
                      @RequestParam(value = "workerCheckbox",required=false)String workerCheckbox,
                      @RequestParam(value = "adminUserCheckbox",required=false)String adminUserCheckbox,
                      @RequestParam(value = "roles",required=false)String roles,
                      @RequestParam(value = "logs",required=false)String logs,
                      @RequestParam(value = "devices",required=false)String devices,
                      @RequestParam(value = "workerInfos",required=false)String workerInfos,
                      @RequestParam(value = "selectInfos",required=false)String selectInfos,
                      @RequestParam(value = "selectMaintains",required=false)String selectMaintains,
                      @RequestParam(value = "maintains",required=false)String maintains,
                      @RequestParam(value = "places",required=false)String places,
                      @RequestParam(value = "tracks",required=false)String tracks,
                      @RequestParam(value = "regions",required=false)String regions,
                      @RequestParam(value = "selectInspections",required=false)String selectInspections,
                      @RequestParam(value = "selectProtections",required=false)String selectProtections,
                      @RequestParam(value = "maintainSearchs",required=false)String maintainSearchs,
                      @RequestParam(value = "faultTypes",required=false)String faultTypes
                      ) throws BatchRollbackException {
        AdminUser user = (AdminUser) session.getAttribute(ConstantFields.SESSION_ADMIN_KEY);
        String logUser = user.getUserName();
        List<String> list = new ArrayList<String>();
        if(workerCheckbox != null) {
            list.add(workerCheckbox);
        }
        if(faultTypes != null) {
            list.add(faultTypes);
        }
        if (adminUserCheckbox != null) {
            list.add(adminUserCheckbox);
        }
        if (roles != null) {
            list.add(roles);
        }
        if (logs != null) {
            list.add(logs);
        }
        if (devices != null) {
            list.add(devices);
        }
        if (workerInfos != null) {
            list.add(workerInfos);
        }
        if (selectInfos != null) {
            list.add(selectInfos);
        }
        if (selectMaintains != null) {
            list.add(selectMaintains);
        }
        if (maintains != null) {
            list.add(maintains);
        }
        if (places != null) {
            list.add(places);
        }
        if (tracks != null) {
            list.add(tracks);
        }
        if (regions != null) {
            list.add(regions);
        }
        if (selectInspections != null) {
            list.add(selectInspections);
        }
        if (selectProtections != null) {
            list.add(selectProtections);
        }
        if (maintainSearchs != null) {
            list.add(maintainSearchs);
        }
        if (roleService.add(role, logUser, list)) {
            if (LOG.isInfoEnabled())
                LOG.info("[MANAGE] [OK] {} add new role {}.", logUser, role.getRoleName());

            redirectAttributes.addFlashAttribute(ConstantFields.ADD_SUCCESS_KEY, ConstantFields.ADD_SUCCESS_MESSAGE);
            return "redirect:/admin/role/routeList.action";
        }

        redirectAttributes.addFlashAttribute(ConstantFields.ADD_FAILURE_KEY, ConstantFields.ADD_FAILURE_MESSAGE);
        return "redirect:/admin/role/routeAdd.action";
    }

    @RequestMapping(value = "/delete/{roleId}", method = RequestMethod.GET)
    public String delete(@PathVariable("roleId") String roleId, HttpSession session, RedirectAttributes redirectAttributes) {
        AdminUser user = (AdminUser) session.getAttribute(ConstantFields.SESSION_ADMIN_KEY);
        String logUser = user.getUserName();

        if (roleService.delete(roleId, logUser)) {
            if (LOG.isInfoEnabled())
                LOG.info("[MANAGE] [OK] {} delete adminManager's ID {}.", logUser, roleId);

            redirectAttributes.addFlashAttribute(ConstantFields.DELETE_SUCCESS_KEY, ConstantFields.DELETE_SUCCESS_MESSAGE);

            return "redirect:/admin/role/routeList.action";
        }

        redirectAttributes.addFlashAttribute(ConstantFields.DELETE_FAILURE_KEY, ConstantFields.DELETE_FAILURE_MESSAGE);

        return "redirect:/admin/role/routeList.action";
    }

    @RequestMapping(value = "/routeCheck/{roleId}", method = RequestMethod.GET)
    public ModelAndView routeCheck(@PathVariable("roleId") String roleId) {
        Role role = roleService.selectRole(roleId);
        ModelAndView mav = new ModelAndView("admin/role/check");
        mav.addObject("role", role);
        role.setAuthorityTwoId(1);
        mav.addObject("workers",roleService.selectRoleFunction(role));
        role.setAuthorityTwoId(2);
        mav.addObject("adminUsers",roleService.selectRoleFunction(role));
        role.setAuthorityTwoId(3);
        mav.addObject("roles",roleService.selectRoleFunction(role));
        role.setAuthorityTwoId(4);
        mav.addObject("logs",roleService.selectRoleFunction(role));
        role.setAuthorityTwoId(5);
        mav.addObject("devices",roleService.selectRoleFunction(role));
        role.setAuthorityTwoId(6);
        mav.addObject("workerInfos",roleService.selectRoleFunction(role));
        role.setAuthorityTwoId(7);
        mav.addObject("selectInfos",roleService.selectRoleFunction(role));
        role.setAuthorityTwoId(8);
        mav.addObject("selectMaintains",roleService.selectRoleFunction(role));
        role.setAuthorityTwoId(30);
        mav.addObject("maintains",roleService.selectRoleFunction(role));
        role.setAuthorityTwoId(10);
        mav.addObject("places",roleService.selectRoleFunction(role));
        role.setAuthorityTwoId(11);
        mav.addObject("tracks",roleService.selectRoleFunction(role));
        role.setAuthorityTwoId(12);
        mav.addObject("regions",roleService.selectRoleFunction(role));
        role.setAuthorityTwoId(13);
        mav.addObject("selectInspections",roleService.selectRoleFunction(role));
        role.setAuthorityTwoId(14);
        mav.addObject("selectProtections",roleService.selectRoleFunction(role));
        role.setAuthorityTwoId(15);
        mav.addObject("maintainSearchs",roleService.selectRoleFunction(role));
        role.setAuthorityTwoId(16);
        mav.addObject("faultTypes",roleService.selectRoleFunction(role));

        return mav;
    }

    @RequestMapping(value = "/routeEdit/{roleId}", method = RequestMethod.GET)
    public ModelAndView routeEdit(@PathVariable("roleId") String roleId) {
        Role role = roleService.selectRole(roleId);
        ModelAndView mav = new ModelAndView("admin/role/edit");
        mav.addObject("role", role);
        role.setAuthorityTwoId(1);
        List<Role> workers = roleService.selectRoleFunction(role);
        mav.addObject("workers",workers);
        role.setAuthorityTwoId(2);
        List<Role> adminUsers = roleService.selectRoleFunction(role);
        mav.addObject("adminUsers",adminUsers);
        role.setAuthorityTwoId(3);
        List<Role> roles = roleService.selectRoleFunction(role);
        mav.addObject("roles",roles);
        role.setAuthorityTwoId(4);
        List<Role> logs = roleService.selectRoleFunction(role);
        mav.addObject("logs",logs);
        role.setAuthorityTwoId(5);
        List<Role> devices = roleService.selectRoleFunction(role);
        mav.addObject("devices",devices);
        role.setAuthorityTwoId(6);
        List<Role> workerInfos = roleService.selectRoleFunction(role);
        mav.addObject("workerInfos",workerInfos);
        role.setAuthorityTwoId(7);
        List<Role> selectInfos = roleService.selectRoleFunction(role);
        mav.addObject("selectInfos",selectInfos);
        role.setAuthorityTwoId(8);
        List<Role> selectMaintains = roleService.selectRoleFunction(role);
        mav.addObject("selectMaintains",selectMaintains);
        role.setAuthorityTwoId(30);
        List<Role> maintains = roleService.selectRoleFunction(role);
        mav.addObject("maintains",maintains);
        role.setAuthorityTwoId(10);
        List<Role> places = roleService.selectRoleFunction(role);
        mav.addObject("places",places);
        role.setAuthorityTwoId(11);
        List<Role> tracks = roleService.selectRoleFunction(role);
        mav.addObject("tracks",tracks);
        role.setAuthorityTwoId(12);
        List<Role> regions = roleService.selectRoleFunction(role);
        mav.addObject("regions", regions);
        role.setAuthorityTwoId(13);
        List<Role> selectInspections = roleService.selectRoleFunction(role);
        mav.addObject("selectInspections", selectInspections);
        role.setAuthorityTwoId(14);
        List<Role> selectProtections = roleService.selectRoleFunction(role);
        mav.addObject("selectProtections", selectProtections);
        role.setAuthorityTwoId(15);
        List<Role> maintainSearchs = roleService.selectRoleFunction(role);
        mav.addObject("maintainSearchs", maintainSearchs);
        role.setAuthorityTwoId(16);
        List<Role> faultTypes = roleService.selectRoleFunction(role);
        mav.addObject("faultTypes", faultTypes);

        List<Role> workersAll =roleService.selectFunctions(1);
        mav.addObject("workersAll",workersAll);
        List<Role> adminUsersAll =roleService.selectFunctions(2);
        mav.addObject("adminUsersAll", adminUsersAll);
        List<Role> rolesAll =roleService.selectFunctions(3);
        mav.addObject("rolesAll", rolesAll);
        List<Role> logsAll =roleService.selectFunctions(4);
        mav.addObject("logsAll",logsAll);
        List<Role> devicesAll =roleService.selectFunctions(5);
        mav.addObject("devicesAll",devicesAll);
        List<Role> workerInfosAll =roleService.selectFunctions(6);
        mav.addObject("workerInfosAll",workerInfosAll);
        List<Role> selectInfosAll =roleService.selectFunctions(7);
        mav.addObject("selectInfosAll",selectInfosAll);
        List<Role> selectMaintainsAll =roleService.selectFunctions(8);
        mav.addObject("selectMaintainsAll",selectMaintainsAll);
        List<Role> maintainAll =roleService.selectFunctions(30);
        mav.addObject("maintainsAll",maintainAll);
        List<Role> placeAll =roleService.selectFunctions(10);
        mav.addObject("placesAll",placeAll);
        List<Role> trackAll =roleService.selectFunctions(11);
        mav.addObject("tracksAll",trackAll);
        List<Role> regionAll =roleService.selectFunctions(12);
        mav.addObject("regionsAll",regionAll);
        List<Role> selectInspectionsAll =roleService.selectFunctions(13);
        mav.addObject("selectInspectionsAll",selectInspectionsAll);
        List<Role> selectProtectionsAll =roleService.selectFunctions(14);
        mav.addObject("selectProtectionsAll", selectProtectionsAll);
        List<Role> maintainSearchsAll =roleService.selectFunctions(15);
        mav.addObject("maintainSearchsAll",maintainSearchsAll);
        List<Role> faultTypesAll =roleService.selectFunctions(16);
        mav.addObject("faultTypesAll",faultTypesAll);

        mav.addObject("workersCut", ListCutUtils.getList(workersAll,workers));
        mav.addObject("adminUsersCut", ListCutUtils.getList(adminUsersAll,adminUsers));
        mav.addObject("rolesCut", ListCutUtils.getList(rolesAll,roles));
        mav.addObject("logsCut", ListCutUtils.getList(logsAll,logs));
        mav.addObject("devicesCut", ListCutUtils.getList(devicesAll,devices));
        mav.addObject("workerInfosCut", ListCutUtils.getList(workerInfosAll,workerInfos));
        mav.addObject("selectInfosCut", ListCutUtils.getList(selectInfosAll,selectInfos));
        mav.addObject("selectMaintainsCut", ListCutUtils.getList(selectMaintainsAll,selectMaintains));
        mav.addObject("maintainsCut", ListCutUtils.getList(maintainAll,maintains));
        mav.addObject("placesCut", ListCutUtils.getList(placeAll,places));
        mav.addObject("tracksCut", ListCutUtils.getList(trackAll,tracks));
        mav.addObject("regionsCut", ListCutUtils.getList(regionAll,regions));
        mav.addObject("selectInspectionsCut", ListCutUtils.getList(selectInspectionsAll,selectInspections));
        mav.addObject("selectProtectionsCut", ListCutUtils.getList(selectProtectionsAll,selectProtections));
        mav.addObject("maintainSearchsCut", ListCutUtils.getList(maintainSearchsAll,maintainSearchs));
        mav.addObject("faultTypesCut", ListCutUtils.getList(faultTypesAll,faultTypes));

        return mav;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String edit(Role role, HttpSession session, RedirectAttributes redirectAttributes,
                      @RequestParam(value = "workerCheckbox",required=false)String workerCheckbox,
                      @RequestParam(value = "adminUserCheckbox",required=false)String adminUserCheckbox,
                      @RequestParam(value = "roles",required=false)String roles,
                      @RequestParam(value = "logs",required=false)String logs,
                      @RequestParam(value = "devices",required=false)String devices,
                      @RequestParam(value = "workerInfos",required=false)String workerInfos,
                      @RequestParam(value = "selectInfos",required=false)String selectInfos,
                      @RequestParam(value = "selectMaintains",required=false)String selectMaintains,
                      @RequestParam(value = "maintains",required=false)String maintains,
                       @RequestParam(value = "places",required=false)String places,
                       @RequestParam(value = "tracks",required=false)String tracks,
                       @RequestParam(value = "regions",required=false)String regions,
                       @RequestParam(value = "selectInspections",required=false)String selectInspections,
                       @RequestParam(value = "selectProtections",required=false)String selectProtections,
                       @RequestParam(value = "maintainSearchs",required=false)String maintainSearchs,
                       @RequestParam(value = "faultTypes",required=false)String faultTypes
    ) throws BatchRollbackException {
        AdminUser user = (AdminUser) session.getAttribute(ConstantFields.SESSION_ADMIN_KEY);
        String logUser = user.getUserName();
        List<String> list = new ArrayList<String>();
        if(workerCheckbox != null) {
            list.add(workerCheckbox);
        }
        if(faultTypes != null) {
            list.add(faultTypes);
        }
        if (adminUserCheckbox != null) {
            list.add(adminUserCheckbox);
        }
        if (roles != null) {
            list.add(roles);
        }
        if (logs != null) {
            list.add(logs);
        }
        if (devices != null) {
            list.add(devices);
        }
        if (workerInfos != null) {
            list.add(workerInfos);
        }
        if (selectInfos != null) {
            list.add(selectInfos);
        }
        if (selectMaintains != null) {
            list.add(selectMaintains);
        }
        if (maintains != null) {
            list.add(maintains);
        }
        if (places != null) {
            list.add(places);
        }
        if (tracks != null) {
            list.add(tracks);
        }
        if (regions != null) {
            list.add(regions);
        }
        if (selectInspections != null) {
            list.add(selectInspections);
        }
        if (selectProtections != null) {
            list.add(selectProtections);
        }
        if (maintainSearchs != null) {
            list.add(maintainSearchs);
        }
        if (roleService.edit(role, logUser, list)) {
            if (LOG.isInfoEnabled())
                LOG.info("[MANAGE] [OK] {} edit new role {}.", logUser, role.getRoleName());

            redirectAttributes.addFlashAttribute(ConstantFields.EDIT_SUCCESS_KEY, ConstantFields.EDIT_SUCCESS_MESSAGE);
            return "redirect:/admin/role/routeList.action";
        }

        redirectAttributes.addFlashAttribute(ConstantFields.EDIT_FAILURE_KEY, ConstantFields.EDIT_FAILURE_MESSAGE);
        return "redirect:/admin/role/routeEdit/" + role.getRoleId() + ".action";
    }

}
