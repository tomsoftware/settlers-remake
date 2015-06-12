package jsettlers.ai.construction;

import java.util.ArrayList;
import java.util.List;

import jsettlers.algorithms.construction.AbstractConstructionMarkableMap;
import jsettlers.common.buildings.EBuildingType;
import jsettlers.common.mapobject.EMapObjectType;
import jsettlers.common.position.ShortPoint2D;
import jsettlers.logic.map.grid.objects.ObjectsGrid;
import jsettlers.logic.map.grid.partition.PartitionsGrid;

public class BestLumberJackConstructionPositionFinder implements IBestConstructionPositionFinder {

	EBuildingType buildingType;

	public BestLumberJackConstructionPositionFinder(EBuildingType buildingType) {
		this.buildingType = buildingType;
	}
	
	@Override
	public ShortPoint2D findBestConstructionPosition(AbstractConstructionMarkableMap constructionMap, PartitionsGrid partitionsGrid, ObjectsGrid objectsGrid, byte playerId) {
		short minX = partitionsGrid.getWidth();
		short maxX = 0;
		short minY = partitionsGrid.getHeight();
		short maxY = 0;
		
		for(short x = 0; x < partitionsGrid.getWidth(); x++) {
			for(short y = 0; y < partitionsGrid.getHeight(); y++) {
				if (partitionsGrid.getPlayerAt(x, y) != null && partitionsGrid.getPlayerAt(x, y).playerId == playerId) {
					if (minX > x) {
						minX = x;
					}
					if (minY > y) {
						minY = y;
					}
					if (maxX < x) {
						maxX = x;
					}
					if (maxY < y) {
						maxY = y;
					}
				}
			}	
		}
		
		List<ShortPoint2D> trees = new ArrayList<ShortPoint2D>();
		for(short xx = minX; xx < maxX; xx++) {
			for(short yy = minY; yy < maxY; yy++) {
				if (objectsGrid.hasCuttableObject(xx, yy, EMapObjectType.TREE_ADULT)) {
					trees.add(new ShortPoint2D(xx, yy));
				}
			}
		}
		
		List<ScoredConstructionPosition> scoredConstructionPositions = new ArrayList<ScoredConstructionPosition>();
		for(short xx = minX; xx < maxX; xx++) {
			for(short yy = minY; yy < maxY; yy++) {
				if (constructionMap.canConstructAt(xx, yy, buildingType, playerId)) {
					double score = 0;
					for (ShortPoint2D tree : trees) {
						score = score + 1/(Math.abs(tree.x - xx) * Math.abs((tree.y - yy)));
					}
					scoredConstructionPositions.add(new ScoredConstructionPosition(new ShortPoint2D(xx, yy), (int) Math.round(score)));
				}
			}
		}
		
		ScoredConstructionPosition winnerPosition = null;
		for (ScoredConstructionPosition scoredConstructionPosition : scoredConstructionPositions) {
			if (winnerPosition == null) {
				winnerPosition = scoredConstructionPosition;
			} else if (winnerPosition.score > scoredConstructionPosition.score) {
				winnerPosition = scoredConstructionPosition;
			}
		}
			
		if (winnerPosition == null) {
			return null;
		}
		
		return winnerPosition.point;
	}

}


