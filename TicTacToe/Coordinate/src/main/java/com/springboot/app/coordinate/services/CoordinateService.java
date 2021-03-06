package com.springboot.app.coordinate.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.springboot.app.coordinate.dto.CoordinateDto;
import com.springboot.app.coordinate.entities.CoordinateEntity;
import com.springboot.app.coordinate.repository.CoordinateRepository;

@Service
public class CoordinateService implements ICoordinateService {
	
	@Autowired
	private CoordinateRepository coordinateRepository;

	@Override
	public CoordinateDto create(CoordinateDto coordinateTarget) {
		coordinateRepository.save(this.toEntity(coordinateTarget, new CoordinateEntity()));
		return coordinateTarget;
	}

	@Override
	public List<CoordinateDto> get(Integer playerId) {
		List<CoordinateEntity> coordinatesEntities = coordinateRepository.findByPlayerId(playerId);
		List<CoordinateDto> coordinatesDtos = new ArrayList<>();
		for (CoordinateEntity coordinate : coordinatesEntities) {
			coordinatesDtos.add(this.toDto(coordinate));
		}
		return coordinatesDtos;
	}
	
	private CoordinateDto toDto(CoordinateEntity coordinateEntity) {
		CoordinateDto coordinateDto = new CoordinateDto();
		coordinateDto.setBoard_id(coordinateEntity.getBoard_id());
		coordinateDto.setPlayer_id(coordinateEntity.getPlayer_id());
		coordinateDto.setColumn(coordinateEntity.getColumn());
		coordinateDto.setRow(coordinateEntity.getRow());
		return coordinateDto;
	}
	
	private CoordinateEntity toEntity(CoordinateDto coordinateDto, CoordinateEntity coordinateEntity) {
		coordinateEntity.setBoard_id(coordinateDto.getBoard_id());
		coordinateEntity.setPlayer_id(coordinateDto.getPlayer_id());
		coordinateEntity.setColumn(coordinateDto.getColumn());
		coordinateEntity.setRow(coordinateDto.getRow());
		return coordinateEntity;
	}
}
