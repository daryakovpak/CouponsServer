package com.darya.coupons.expiredCoupons;

import java.util.Calendar;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.darya.coupons.dao.ICouponDao;
import com.darya.coupons.enums.ErrorType;
import com.darya.coupons.exceptions.ProjectException;

@Component
public class TimerTask {

	@Autowired
	ICouponDao couponDao;

	@Scheduled(cron = "0 01 0 * * ?")
	@Transactional
	public void expiredCouponsCheck() throws ProjectException {

		Date now = new Date(Calendar.getInstance().getTimeInMillis());

		try {
			couponDao.deleteAllByEndDateBefore(now);
		} catch (Exception e) {
			throw new ProjectException(ErrorType.GENERAL_ERROR, "Failed to delete expired coupons on date "+now, e);
		}
		System.out.println("TimerTask is finished!");
	}
}
