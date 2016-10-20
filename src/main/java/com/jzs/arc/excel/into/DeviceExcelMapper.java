package com.jzs.arc.excel.into;

import com.jzs.arc.utils.DateUtils;
import com.jzs.arc.utils.DeviceNumUtils;
import com.jzs.function.admin.device.Device;
import com.jzs.function.admin.seat.Seat;
import com.jzs.function.admin.seat.service.SeatService;
import com.jzs.function.admin.seat.service.SeatServiceI;
import com.jzs.function.support.dictionary.impl.DefaultDictionaryManager;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.springframework.beans.factory.annotation.Autowired;

public class DeviceExcelMapper implements ExcelMapper<Device> {
	private static String[] strings = null;
	
	//把excel表格中的一行数据读出来,放到一个<code>java.lang.String[]</code>数据中
	public String[] mapExcel(HSSFRow row) {
		strings = new String[row.getLastCellNum()];
		
		for (int cellIndex = 0; cellIndex < row.getLastCellNum(); cellIndex++) {
			HSSFCell cell = row.getCell(cellIndex);
			
			if (null != cell) {
				strings[cellIndex] = getTypeValue(cell);
			}else {
				if (cellIndex == 10) {
					strings[cellIndex] = "";
				}
			}
		}
		
		return strings;
	}

	private  String getTypeValue(HSSFCell cell) {
		switch (cell.getCellType()) {
			case HSSFCell.CELL_TYPE_STRING:
				return cell.getStringCellValue();
			case HSSFCell.CELL_TYPE_BLANK:
				break;
			case HSSFCell.CELL_TYPE_NUMERIC:
				//判断是够是日期类型
				if (HSSFDateUtil.isCellDateFormatted(cell)) {
					return  cell.getDateCellValue().toString();
				} else {
					cell.setCellType(HSSFCell.CELL_TYPE_STRING);
					return cell.getStringCellValue();
				}
			default:
				return "";
		}

		return "";
	}

	public Device mapStringArray(HSSFRow row) {
		this.mapExcel(row);
		Device device = new Device();

		device.setDeviceState(1);
		device.setDeviceNumber(strings[0]);
		device.setDeviceFactory(strings[1]);
		device.setDeviceModel(strings[2]);
		device.setDeviceTimeDate(DateUtils.dataFromStringToDate(strings[3]));
		device.setDeviceTime(strings[3]);
		device.setPlace(strings[4]);
		device.setTrack(strings[5]);
		device.setRegion(strings[6]);
		device.setSeat(strings[7]);
		device.setStationNum(strings[8]);
		device.setStationName(strings[9]);
		device.setCancelTimeDate(DateUtils.dataFromStringToDate(strings[10]));
		device.setCancelTime(strings[10]);
		device.setRollingTimes(strings[11]);
		device.setRemark(strings[12]);
		if (strings[10].equals("")) {
			device.setCancelStateNum(1);
		}else {
			device.setCancelStateNum(2);
		}

		strings = null;
		
		return device;
	}

}