package edu.ahpu.boke.service;

import java.util.List;

import edu.ahpu.boke.domain.Face;

public interface FaceService {
	
	public List<Face> findAllFaces();
	
	public Face findDefaultFace();

}
