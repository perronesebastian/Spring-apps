package com.springboot.app.coordinate.services;

import java.util.List;

import com.springboot.app.coordinate.dto.CoordinateDto;

public interface ICoordinateService {

	CoordinateDto create(CoordinateDto coordinateTarget);

	List<CoordinateDto> get (Integer playerId);

}
