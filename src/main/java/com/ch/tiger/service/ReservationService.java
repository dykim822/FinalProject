package com.ch.tiger.service;

import java.util.List;

import com.ch.tiger.model.Reservation;

public interface ReservationService {

	int insert(Reservation reservation);

	List<Reservation> reservationList(Reservation reservation);

	int getTotal(Reservation reservation);

	int updateAccept(Reservation reservation);

	int updateDenial(Reservation reservation);



}
